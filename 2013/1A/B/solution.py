#!/usr/bin/python

import sys



def solve(E, R, vals):
    nextLocalMax = [(vals[-1], len(vals)-1)]
    distToNextLocalMax = [None] * len(vals)
    distToNextLocalMax[-1] = 0L
    for i in reversed(xrange(len(vals)-1)):
        # pop any smaller elements off the stack to find the next largest and
        # record the distances to it
        while len(nextLocalMax) > 0 and nextLocalMax[-1][0] <= vals[i]:
            nextLocalMax.pop()

        if len(nextLocalMax) == 0:
            # this is the biggest reached so far
            distToNextLocalMax[i] = 0L
        else:
            # record the distance to the next largest element
            distToNextLocalMax[i] = nextLocalMax[-1][1] - i

        # push onto the stack, .5 chance it'll be popped off by the next iteration
        nextLocalMax.append((vals[i], i))

    currentEnergy = E
    gain = 0L
    for i,v in enumerate(vals):
        #print "current %d" % currentEnergy
        s = energyToSpend(E, R, long(distToNextLocalMax[i]), currentEnergy)
        #print "spend %d" % s
        gain += s * v
        currentEnergy = currentEnergy - s + R
    return gain

def testSolve():
    solve(5, 2, [1, 5])

def energyToSpend(E, R, distToNextLocalMax, currentEnergy):
    # Figure out how much we can afford to spend without cutting into the amount at the next max element
    futureEarnings = R * distToNextLocalMax
    requiredSavings = 0 if distToNextLocalMax == 0 else max(0, E - futureEarnings)
    availableToSpend = max(0, currentEnergy - requiredSavings)
    """
    e = currentEnergy + R * distToNextLocalMax # the most energy we'd have available by the time we get to the next max
    if distToNextLocalMax > 0:
        # if this isn't the current max, then subtract the amount we'll spend when we get there
        e = max(0L, e - E)
    # e is now

    return min(e,currentEnergy)
    """
    return availableToSpend

TEST = False

if TEST:
    testSolve()
else:
    T = int(sys.stdin.readline())
    for casen in xrange(T):
        E, R, N = [long(s) for s in sys.stdin.readline().split()]
        R = E if R > E else R
        vals = [long(s) for s in sys.stdin.readline().split()]
        print "Case #%d: %s" % (casen+1, solve(E, R, vals))
