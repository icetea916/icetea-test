package life.icetea.test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icetea
 * @date 2025/7/16
 */
public class Test2 {


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode n = head.next;
        n = n.next = new ListNode(3);
        n = n.next = new ListNode(4);
        n = n.next = new ListNode(5);
        removeNthFromEnd(head, 2);



    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public  static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        int size = 0;
        ListNode tn = head;
        while (tn != null) {
            size++;
            tn = tn.next;
        }

        if (n > size) {
            return null;
        }

        int index = n - size;
        int count = 1;
        ListNode node = head;
        while (node.next != null) {
            count++;
            node = node.next;
            if (count == index) {
                node.next = node.next.next;
                break;
            }
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
