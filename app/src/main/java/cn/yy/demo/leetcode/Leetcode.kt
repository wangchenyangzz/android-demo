package cn.yy.demo.leetcode

import kotlin.math.abs

/**
 *    author : cy.wang
 *    date   : 2020/6/28
 *    desc   : leetcode
 */

class Solution {
    /* 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
    示例：
    输入：nums = [-1,2,1,-4], target = 1
    输出：2
    解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。 

    提示：
    3 <= nums.length <= 10^3
    -10^3 <= nums[i] <= 10^3
    -10^4 <= target <= 10^4 */
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()
        var ret = nums[0] + nums[1] + nums[2]
        for (i in nums.indices) {
            var m = i + 1
            var n = nums.size - 1
            while (m < n) {
                if (nums[i] + nums[m] + nums[n] == target) {
                    return target
                }
                if (abs(nums[i] + nums[m] + nums[n] - target) < abs(ret - target))  {
                    ret = nums[i] + nums[m] + nums[n]
                }
                if (nums[i] + nums[m] + nums[n] > target) {
                    n--
                } else  {
                    m++
                }
            }
        }
        return ret
    }
}