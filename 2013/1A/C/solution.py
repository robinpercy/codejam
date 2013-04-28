#!/usr/bin/python

import sys
import math

def solve(R, N, M, K):
    for r in range(R):
        products = [int(i) for i in sys.stdin.readline().split()]
        certainFactors = {2:0, 3:0, 4:0, 5:0}

        for i,p in enumerate(products):
            primes = {2:0, 3:0, 5:0}
            twos = 0
            while p % 2 == 0:
                twos += 1
                primes[2] += 1
                p /= 2
            while p % 3 == 0:
                primes[3] += 1
                p /= 3
            while p % 5 == 0:
                primes[5] += 1
                p /= 5

            for k, v in primes.iteritems():
                certainFactors[k] = max(certainFactors[k], primes[k])

            #print certainFactors

        bestGuess = []
        for k, v in certainFactors.iteritems():
            bestGuess += [k] * v

        while len(bestGuess) > N:
            bestGuess.remove(2)
            bestGuess.remove(2)
            bestGuess.append(4)

        while len(bestGuess) < N:
            bestGuess.append(2)

        print "".join([str(i) for i in bestGuess])

T = int(sys.stdin.readline()[:-1])
for i in range(1,T+1):
    R, N, M, K = [int(s) for s in sys.stdin.readline().split()]
    print "Case #1:"
    solve(R, N, M, K)

