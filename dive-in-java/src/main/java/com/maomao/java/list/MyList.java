package com.maomao.java.list;

/**
 * 单链表的操作
 * Created by maomao on 2018/10/9.
 */
public class MyList {
    /**
     * 头结点的插入
     */
    public static void headInsert(ListNode head, ListNode newHead) {
        ListNode old = head;
        head = newHead;
        head.next = old;
    }

    /**
     * 尾节点的插入
     */
    public static void tailInsert(ListNode tail, ListNode newTail) {
        ListNode old = tail;
        tail = newTail;
        tail.next = null;
        old.next = tail;
    }

    /**
     * 遍历
     */
    public static void traverse(ListNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 查找
     */
    public static int find(ListNode head, int value) {
        int index = -1;
        int count = 0;
        while (head != null) {
            if (value == head.value) {
                index = count;
                return index;
            }
            count++;
            head = head.next;
        }
        return index;
    }

    /**
     * 普通插入
     */
    public static void insert(ListNode p, ListNode s) {
        ListNode next = p;
        p.next = s;
        s.next = next.next;
    }

    /**
     * 删除
     */
    public static void delete(ListNode head, ListNode q) {
        if (q != null && q.next != null) {
            ListNode p = q.next;
            q.value = p.value;
            // 删除 q.next
            q.next = p.next;
            p = null;
        }
        // 删除最后一个元素的情况
        if (q.next == null) {
            while (head != null) {
                if (head.next != null && head.next == q) {
                    head.next = null;
                    break;
                }
                head = head.next;
            }
        }
    }
}
