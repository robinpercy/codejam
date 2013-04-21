#!/usr/bin/python

import sys
import math



def fcmp(a, b, precision = .0000000000001):
    """
    Compares floating points with a specified precision
    returns a number RET such that:
        0 == RET iff a == b
        0 <  RET iff a < b
        0 >  RET iff a > b
    """
    if abs(a - b) < precision: return 0
    else: return cmp(a, b)

def realbinsearch(low, high, test):
    """
    Runs a binary search across the range of real numbers: [low, high] and
    returns the first value that passes test(x)

    test -- must take a float X and return a value, RET, such that
            0 == RET iff X is the desired value
            0 <  RET iff X is higher than the desired value
            0 >  RET iff X is lower than the desired value
    """
    left, right = float(low), float(high)
    prec = .0000000000001
    while fcmp(left, right, prec) < 0:
        mid = (left + right) * 0.5
        t = test(mid)
        if t == 0: break
        elif t > 0: right = mid - prec
        else: left = mid + prec
    return mid

def testCheck():
    assert 0 == check(.3333333333, [20,10], 0, 30 )
    assert 0 == check(.3333333333, [10,10,10], 0, 30 )
    assert 0 < check(.34, [20,10], 0, 30)
    assert 0 > check(.299999, [20,10], 0, 30)
    assert 0 == check(.5, [0,30,0], 0, 30)
    assert 0 == check(0, [30,0,0], 0, 30)

def check(val, scores, player, X):
    """
    Returns 0 if val is safe
    Returns < 0 if val could be lower
    Returns > 0 if val needs to be higher
    """
    playerFinal = scores[player] + (X * val)
    safePcts = [val]

    for p, J in enumerate(scores):
        if p == player: continue
        safePcts.append( max(0,(playerFinal - J)/X) )
    """
    if sum is less than one, then there is room for other players to increase their score, so we're not yet save
    if sum is greater than one, then we're taking more votes than necessary (or our score is so high, we can't lose)
    if sum == 1.0 then we've found the votes we need
    """
    c = fcmp(math.fsum(safePcts), 1.0)
    if c > 0 and fcmp(val,0.0) == 0:
        return 0        # can't go lower than 0
    else:
        return c


def testFindFor():
    assert 0 == fcmp(.333333333, find_for([20,10], 0, 30))
    assert 0 == fcmp((1.0/3.0), find_for([10,10,10], 0, 30))
    assert 0 == fcmp((0), find_for([30,0,0], 0, 30))
    assert 0 == fcmp((.5), find_for([30,0,0], 1, 30))
    assert 0 == fcmp((.5), find_for([30,0,0], 2, 30))

def find_for(scores, player, X):
    answer = realbinsearch(0.0, 1.0, lambda x: check(x, scores, player, X))
    return answer

def solve(scores):
    req = []
    X = sum(scores)
    for i,J in enumerate(scores):
       req.append(str(100 * find_for(scores, i, X)))
    return " ".join(req)


#testCheck()
#testFindFor()
T = int(sys.stdin.readline())
for i in range(1,T+1):
    scores = [int(s) for s in sys.stdin.readline().split()[1:]]
    print "Case #%d: %s" % (i, solve(scores))

