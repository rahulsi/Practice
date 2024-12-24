#167. Two Sum II - Input Array Is Sorted
from typing import List


# Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
#
# Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.


def twoSumBrute(numbers: List[int], target: int) -> List[int]:
    n = len(numbers)
    for i in range(1, n+1):
        for j in range(n, -1, -1):
            if i < j:
                if numbers[i-1] + numbers[j-1] == target:
                    return [i,j]
            else:
                break

def twoSum(self, numbers: List[int], target: int) -> List[int]:
    n = len(numbers)
    i = 1
    j = n
    while i < j:
        sum = numbers[i - 1] + numbers[j - 1]
        if sum == target:
            return [i, j]
        elif target < sum:
            j -= 1
        else:
            i += 1

# print('twoSum', twoSum([2,7,11,15], 9))
# print('twoSum', twoSum([2,3,4], 6))
# print('twoSum', twoSum([-1,0], -1))


#15. 3Sum
# Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
# Notice that the solution set must not contain duplicate triplets.

def threeSumBrute(nums: List[int]) -> List[List[int]]:
    n = len(nums)
    results = {}
    for i in range(n):
        for j in range(n-1,-1,-1):
            for k in range(i+1,j):
                if nums[i] + nums[j] + nums[k] == 0:
                    output = [nums[i],nums[k],nums[j]]
                    output.sort()
                    outputStr = str(output[0]) + str(output[1]) + str(output[2])
                    if outputStr not in results.keys():
                        results[outputStr] = output
    return list(results.values())

# print('threeSumBrute',threeSumBrute([-1,0,1,2,-1,-4]))
# print('threeSumBrute',threeSumBrute([0,1,1]))
# print('threeSumBrute',threeSumBrute([0,0,0]))


def threeSum(nums: List[int]) -> List[List[int]]:
    n = len(nums)
    results = {}
    nums.sort()
    for i in range(0,n):
        j = i+1
        k = n-1
        while j<k:
            if nums[j] + nums[k] == 0 - nums[i]:
                output = [nums[i], nums[k], nums[j]]
                output.sort()
                outputStr = str(output[0]) + str(output[1]) + str(output[2])
                if outputStr not in results.keys():
                    results[outputStr] = output
                j += 1
            elif nums[j] + nums[k] > 0 - nums[i]:
                k -= 1
            else:
                j += 1
    return list(results.values())

# print('threeSum',threeSum([-1,0,1,2,-1,-4]))
# print('threeSum',threeSum([0,1,1]))
# print('threeSum',threeSum([0,0,0]))


# 11. Container With Most Water
# You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
# Find two lines that together with the x-axis form a container, such that the container contains the most water.
# Return the maximum amount of water a container can store.

def maxAreaBrute(height: List[int]) -> int:
    n = len(height)
    maxcapacity = 0
    for i in range(0,n):
        for j in range(i+1, n):
            capacity = (j-i)*min(height[i], height[j])
            maxcapacity = max(maxcapacity, capacity)
    return maxcapacity


print('maxAreaBrute',maxAreaBrute([1,8,6,2,5,4,8,3,7]))
print('maxAreaBrute',maxAreaBrute([1,1]))

def maxArea(height: List[int]) -> int:
   n = len(height)
   maxcapacity = 0;
   i = 0
   j= n -1
   while i<j:
       capacity = (j-i)*min(height[i], height[j])
       maxcapacity = max(maxcapacity, capacity)
       if height[i]< height[j]:
           i += 1
       else:
           j -= 1
   return maxcapacity

print('maxArea',maxArea([1,8,6,2,5,4,8,3,7]))
print('maxArea',maxArea([1,1]))