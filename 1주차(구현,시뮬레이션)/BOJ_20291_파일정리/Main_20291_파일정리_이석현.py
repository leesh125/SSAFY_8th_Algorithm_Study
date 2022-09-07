import sys,re
input = sys.stdin.readline

dic = {}
for _ in range(int(input())):
    str = input().rstrip().split('.')[1]
    dic[str] = 1 if str not in dic else dic[str] + 1
for d in sorted(dic):
    print(d,dic[d])