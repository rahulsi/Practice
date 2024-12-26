# 643. Maximum Average Subarray I
# You are given an integer array nums consisting of n elements, and an integer k.
#
# Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
from typing import List


def findMaxAverageBrute(nums: List[int], k: int) -> float:
    n = len(nums)
    maxavg = 0
    for i in range(0,n-k+1):
        # print('\ni=',i)
        sum = 0
        for j in range(0,k):
            # print('j=', j)
            sum += nums[i+j]
        avg = sum / k
        if i != 0:
            maxavg = max(maxavg, avg)
        else:
            maxavg = avg
    return maxavg


# print('findMaxAverageBrute', findMaxAverageBrute([1,12,-5,-6,50,3], 4))
# print('findMaxAverageBrute', findMaxAverageBrute([5], 1))

def findMaxAverage(nums: List[int], k: int) -> float:
    n = len(nums)
    sum = 0
    for i in range(0,k):
        # print('i=',i)
        sum += nums[i]
    maxavg = sum / k
    for i in range(1,n-k+1):
        # print('i=', i, 'i-1', i-1, 'i+k-1', i+k-1)
        sum = sum - nums[i-1] + nums[i+k-1]
        maxavg = max(maxavg, sum/k)
    return maxavg

# print('findMaxAverage', findMaxAverage([1,12,-5,-6,50,3], 4))
# print('findMaxAverage', findMaxAverage([5], 1))



#3. Longest Substring Without Repeating Characters
# Given a string s, find the length of the longest
# substring without repeating characters.

def lengthOfLongestSubstringBrute( s: str) -> int:
    n = len(s)
    maxlen = 0
    for i in range(0,n):
        for j in range(i+1,n+1):
            substring = s[i:j]
            distinctstr = set(substring)
            if len(distinctstr) == len(substring):
                maxlen = max(maxlen, len(substring))
            # print(substring)
    # print(n)
    return maxlen

# print('lengthOfLongestSubstringBrute',lengthOfLongestSubstringBrute("abcabcbb"))
# print('lengthOfLongestSubstringBrute',lengthOfLongestSubstringBrute("bbbbb"))
# print('lengthOfLongestSubstringBrute',lengthOfLongestSubstringBrute("pwwkew"))
# print('lengthOfLongestSubstringBrute',lengthOfLongestSubstringBrute("aabaab!bb"))

def lengthOfLongestSubstring( s: str) -> int:
    # print('input', s)
    n = len(s)
    maxlen = 0
    charMap = {}
    i = 0
    j = i
    while j < n:
        # print('processing' , s[j])
        if s[j] not in charMap:
            # print('adding to map', s[j])
            charMap[s[j]] = j
            maxlen = max(maxlen, len(charMap))
        else:
            k = charMap[s[j]]
            # print('cleaning up', i, k)
            for a in range(i, k+1):
                # print('removing', s[a])
                del charMap[s[a]]
                charMap[s[j]] = j
            i = k+1
        j += 1
    return maxlen

# print('lengthOfLongestSubstring',lengthOfLongestSubstring("abcabcbb"))
# print('lengthOfLongestSubstring',lengthOfLongestSubstring("bbbbb"))
# print('lengthOfLongestSubstring',lengthOfLongestSubstring("pwwkew"))
# print('lengthOfLongestSubstring',lengthOfLongestSubstring("aabaab!bb"))


# 76. Minimum Window Substring
# Given two strings s and t of lengths m and n respectively, return the minimum window
# substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
# The testcases will be generated such that the answer is unique.


def createMap(s):
    charMap = {}
    for i in range(len(s)):
        if s[i] in charMap:
            charMap[s[i]] += 1
        else:
            charMap[s[i]] = 1
    return charMap


def containsMap(smap, tmap):
    for k in tmap.keys():
        if k in smap and smap[k] >= tmap[k]:
            continue
        else:
            return False
    return True


def compareSubstr(s, t):
    smap = createMap(s)
    tmap = createMap(t)
    if containsMap(smap, tmap) == True:
        return True
    else:
        return False

#todo : complete these functions
def minWindowBrute(s: str, t: str) -> str:
    n = len(s)
    i=0
    outputstr = ""
    while i<n:
        j = i + len(t)
        while j<=n:
            # print('comparing s and t', s[i:j], t)
            if compareSubstr(s[i:j],t) == True:
                if outputstr == "":
                    outputstr = s[i:j]
                elif len(s[i:j])<len(outputstr):
                    outputstr = s[i:j]
            j += 1
        i += 1
    return outputstr

print('minWindowBrute',minWindowBrute(s = "ADOBECODEBANC", t = "ABC"))
print('minWindowBrute',minWindowBrute(s = "a", t = "a"))
print('minWindowBrute',minWindowBrute(s = "a", t = "aa"))


def updateValueMap(valueMap, tmap, value):
    pass


def contains(valueMap, tmap):
    pass


def updateStart(valueMap, tmap, ch, i):
    pass


def minWindow(s: str, t: str) -> str:
    tmap = createMap(t)
    valueMap = {}
    n = len(s)
    outputstr = ""
    i = 0
    j = i
    while j < n :
        if updateValueMap(valueMap, tmap, s[j]):
            if contains(valueMap, tmap) == True:
                if outputstr == "":
                    outputstr = s[i:j+1]
                elif len(s[i:j+1])<len(outputstr):
                    outputstr = s[i:j+1]
            i = updateStart(valueMap, tmap, s[j], i)
        j += 1
    return outputstr
