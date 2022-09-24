import sys
from collections import deque
input = sys.stdin.readline

n, w, L = map(int, input().split())
bridge = deque()
trucks = list(map(int,input().split()))
totalWeight, min_time, idx = 0, 0, 0

while idx < n:
    if len(bridge) == w:
        totalWeight -= bridge.popleft()
    temp_sum = totalWeight + trucks[idx]
    if temp_sum <= L:
        bridge.append(trucks[idx])
        totalWeight = temp_sum
        idx += 1
    else:
        bridge.append(0)
    min_time += 1
    
print(min_time + w)