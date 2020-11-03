package cn.yy.demo.leetcode

import android.util.Log
import java.lang.StringBuilder
import java.util.*

/**
 *    author : cy.wang
 *    date   : 2020/6/28
 *    desc   : leetcode
 */

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun reorderList(head: ListNode?): Unit {
        val stack = Stack<ListNode>()
        var node = head
        while (node != null) {
            stack.push(node)
            node = node.next
        }
        node = head
        val size = stack.size
        if (size <= 2) return
        for (i in 0..size / 2) {
            if (i == size / 2 && i % 2 == 0) {
                node?.next = null
                break
            }
            val nd = node?.next
            node?.next = stack.pop()
            if (i == size / 2 && i % 2 == 1) {
                node?.next?.next = null
            } else {
                node?.next?.next = nd
                node = nd
            }
        }
    }

    fun reorderList1(head: ListNode?): Unit {
        var slow = head
        var fast = head?.next
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        val last = reverseList(slow)
        var node1 = head
        var node2 = last
        while (node1 != null && node2 != null) {
            val node = node1.next
            node1.next = node2
            val nd = node2.next
            node2.next = node
            node1 = node
            node2 = nd
        }
    }

    fun reverseList(head: ListNode?): ListNode? {
        if(head?.next == null)
            return head
        val p: ListNode? = reverseList(head.next)
        head.next!!.next = head
        head.next = null
        return p
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}