#!/usr/bin/python
import sys
import math

DEBUG = False

# Init pals with none for size zero
pals = [[]]  
def debug(s):
    if DEBUG: print s

def solve(A, B):
    if B == 1: 
        return 1
    startDigits = int(math.log10(A))
    endDigits = int((math.log10(B) + 2)/2)
    debug("digits: %d %d" % (startDigits, endDigits))
    for d in range(1, endDigits + 1):
        pals.append(genpalsofsize(d))

def genpalsofsize(d):
    debug("Generating pals of size %d" % d)
    if d == 1: 
        return [ [i] for i in range(0,10)]
    elif d == 2:
        return [ [i,i] for i in range(0,10)]
    else:
        palsofsize = []
        for seed in pals[d-2]:
            for i in range(0,10):
                palsofsize.append([i] + seed + [i])
        return palsofsize


solve(10,10000000000000)
for palOfSize in pals:
    for p in palOfSize:
        if p[0] != 0: print ''.join([str(n) for n in p])


"""
def solve(A, B):
    score = 0
    gen = Generator(A,int(math.sqrt(B)))

    while gen.hasNext():
        if test(gen.nextVal()): 
            score++
    return score 

class Generator:
    start = 0
    end = 0
    evenSeeds = []
    oddSeeds = []
    curVal = []
    nextVal = []

    def __init__(start, end):
        self.start = start
        self.end = end

    def hasNext(self):
        self.nextVal = self.generateNext()
        return self.nextVal <= end

    def generateNext(self):
        if self.curVal.length % 2 == 0:
            if isAllNines(self.curVal):
                return self.generateNextOdd()
            else:
                return self.generateNextEven()
        else:
            if isAllNines(self.curVal):
                return self.generateNextEven()
            else:
                return self.generateNextOdd()

    def generateNextOdd(self):
        pass
"""
"""
T = int(sys.stdin.readline())
for i in range(1, T+1):
    [A, B] = sys.stdin.readline().split()
    print "%s %s" % (A, B)
    solution = solve(int(A), int(B))
    print "Case #%d: %d" % (i, solution)
"""
