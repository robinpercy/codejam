#!/usr/bin/python

for n in range(1,10000000):
    s = str(n)
    l = list(s)
    l.reverse()
    if (''.join(l) == s): print s

