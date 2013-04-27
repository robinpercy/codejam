#!/usr/bin/pypy

import sys

def ring_area(r):
    return (r * 2) + 1

def solve(r, t):
    paint_used = ring_area(r)
    rings_painted = 0
    while paint_used <= t:
        r += 2
        rings_painted += 1
        paint_used += ring_area(r)
    return rings_painted

def solve_large(r, t):
    high = t
    low = 1

    while high >= low:
        mid = (low + high) / 2
        #print "%d %d %d" % (low, mid, high)
        paint = required_paint(r, mid)
        #print "paint = %d" % paint
        if paint < t:
             if required_paint(r, mid + 1) > t: break
             low = mid + 1
        elif paint > t:
             high = mid - 1
        elif paint == t:
        #    print "break"
            break

    return mid


def required_paint(r, n):
    """
    Calculates the ml of paint required for n rings starting at radius r
    note that we don't care about pi since it is 1:1 with ml
    The formula below is based on the fact that a ring with inner radius r has area 2r + 1
    and because the area of rings increases linearly, we can use a Gauss-like formula to sum them
    eg
        [area(r) + area(r + 2(n-1))] * n/2

    where r = 1 and n = 5, we'd be summing
        3 + 7 + 11 + 15 + 19 == 22 * 5/2 == 55

    The formula below is just an expanded version of the one above
    """
    return ( 2 * r + 2 * n -1 ) * n

T = int(sys.stdin.readline())
for i in range(1,T+1):
    r, t = [int(s) for s in sys.stdin.readline().split()]
    print "Case #%d: %s" % (i, solve_large(r, t))

