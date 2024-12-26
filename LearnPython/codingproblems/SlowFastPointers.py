from typing import Optional, List


# 141. Linked List Cycle
# Given head, the head of a linked list, determine if the linked list has a cycle in it.
#
# There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
#
# Return true if there is a cycle in the linked list. Otherwise, return false.

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def hasCycle(head: Optional[ListNode]) -> bool:
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
        if slow == fast:
            return True
    return False


__visitedNumbers = set()
def isHappy(n: int) -> bool:
    if n == 1: return True
    elif n in __visitedNumbers: return False
    else:
        __visitedNumbers.add(n)
        s = str(n)
        sum = 0
        for i in range(0, len(s)):
            sum += int(s[i])**2
        return isHappy(sum)

# print('isHappy',isHappy(19))
# print('isHappy', isHappy(2))
# print('isHappy', isHappy(10))


# 287. Find the Duplicate Number
# Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
# There is only one repeated number in nums, return this repeated number.
# You must solve the problem without modifying the array nums and using only constant extra space.

def findDuplicate(nums: List[int]) -> int:
    slow = fast = nums[0]
    while True:
        slow = nums[slow]
        fast = nums[nums[fast]]
        if slow == fast: break

    slow2 = nums[0]
    while slow != slow2:
        slow = nums[slow]
        slow2 = nums[slow2]

    return slow

print('findDuplicate', findDuplicate([1,3,4,2,2]))
print('findDuplicate', findDuplicate([3,1,3,4,2]))
print('findDuplicate', findDuplicate([3,3,3,3,3]))