# 525. Contiguous Array
# Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
# Example 1:
# Input: nums = [0,1]
# Output: 2
# Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
# Example 2:
# Input: nums = [0,1,0]
# Output: 2
# Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
# Constraints:
# 1 <= nums.length <= 105
# nums[i] is either 0 or 1.
import random
from typing import List


#generate input data

def prefixSumBruteForce(arr)->int:
    n = len(arr)
    # print('len = ', n)
    maxlen = 0
    for i in range(0,n):
        # print('\nsarching for i=', i)
        for j in range(i+1, n):
            # print('sarching for j=', j)
            count0 = 0
            count1 = 0
            for k in range(i,j+1):
                # print('sarching for k=', k)
                if arr[k]==0:
                    count0 += 1
                else:
                    count1 += 1
            # print('\n')
            if count0 == count1:
                maxlen = max(maxlen, j-i+1)
                # print('maxlen', maxlen, i, j)
    return maxlen

def prefixSumBruteForceOptimise(arr)->int:
    n = len(arr)
    # print('len = ', n)
    maxlen = 0
    for i in range(0,n):
        # print('\nsarching for i=', i)
        count0 = 0
        count1 = 0
        for j in range(i, n):
            # print('sarching for j=', j)
            if arr[j] == 0:
                count0 += 1
            else:
                count1 += 1
            # print('\n')
            if count0 == count1:
                maxlen = max(maxlen, j-i+1)
                # print('maxlen', maxlen, i, j)
    return maxlen

def prefixSum(arr)->int:
    n = len(arr)
    count = 0
    valdict = {}
    valdict[0] = -1
    maxlen = 0
    for i in range(0,n):
        # print('i=',i)
        if arr[i] == 1:
            count += 1
        else:
            count -= 1
        # print('count=',count)
        if count in valdict.keys():
            maxlen = max(maxlen, i-valdict[count])
        else:
            valdict[count]=i
    return maxlen



def generateInput(n):
     return [random.randint(0,1) for _ in range(n)]

# input = generateInput(10)
# print(input)
# print('prefixSumBruteForce', prefixSumBruteForce(input))
# print('prefixSumBruteForceOptimise', prefixSumBruteForceOptimise(input))
# print('prefixSum', prefixSum(input))



#303. Range Sum Query - Immutable
# Given an integer array nums, handle multiple queries of the following type:
# 1. Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
# Implement the NumArray class:
# * NumArray(int[] nums) Initializes the object with the integer array nums.
# * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
#  
# Example 1:
# Input
# ["NumArray", "sumRange", "sumRange", "sumRange"]
# [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
# Output
# [null, 1, -1, -3]

class NumArray:
    __prefixSum:List[int] = []
    def __init__(self, nums: List[int]):
        n = len(nums)
        prefixSum = 0
        for i in range(0,n):
            prefixSum += nums[i]
            self.__prefixSum.append(prefixSum)

    def sumRange(self, left: int, right: int) -> int:
        if left == 0:
            return self.__prefixSum[right]
        else:
            return self.__prefixSum[right] - self.__prefixSum[left-1]


# numsArray = NumArray([-1])
# print('sumsRange',numsArray.sumRange(0,0))


# 560. Subarray Sum Equals K
# 
# Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
# A subarray is a contiguous non-empty sequence of elements within an array.
#  
# Example 1:
# Input: nums = [1,1,1], k = 2
# Output: 2
# Example 2:
# Input: nums = [1,2,3], k = 3
# Output: 2

def subarraySumBruteForce(nums: List[int], k: int) -> int:
    n = len(nums)
    count = 0
    for i in range(0,n):
        sum = 0
        for j in range(i,n):
            sum += nums[j]
            if sum==k:
                count += 1
    return count


#todo : fix this
def subarraySum(nums: List[int], k: int) -> int:
    n = len(nums)
    prefixSum = {}
    count = 0
    total = 0
    if n == 1:
        if nums[0] == k:
            return 1
        else:
            return 0
    for i in range(0, n):
        total += nums[i]
        if total in prefixSum:
            prefixSum[total] += 1
        else:
            prefixSum[total] = 1

    for sum in prefixSum.keys():
        if sum - k in prefixSum.keys():
            count += prefixSum[sum - k]
    return count



print('subarraySumBruteForce',subarraySumBruteForce([1,1,1], 2))
print('subarraySumBruteForce',subarraySumBruteForce([1,2,3], 3))

print('subarraySum',subarraySum([1,1,1], 2))
print('subarraySum',subarraySum([1,2,3], 3))
print('subarraySum',subarraySum([1], 0))
print('subarraySum',subarraySum([-1,-1,1], 0))