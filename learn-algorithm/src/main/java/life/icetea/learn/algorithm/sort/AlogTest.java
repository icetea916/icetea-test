package life.icetea.learn.algorithm.sort;

public class AlogTest {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);

        ListNode reverseList = reverseList(listNode);
        ListNode next = reverseList;
        while (next.next != null) {
            System.out.println(reverseList.val);
            next = next.next;
        }
        System.out.println(next.val);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextHead = head.next;
        head.next = null;
        ListNode reverseListHead = reverseList(nextHead);

        ListNode next = reverseListHead;
        while (next.next != null) {
            next = next.next;
        }

        next.next = head;
        return reverseListHead;
    }


}
