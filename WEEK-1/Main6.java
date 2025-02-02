
class LinkedList
{
    static class Node 
    {
        int data;
        Node next;
        Node(int data)
        {
            this.data = data;
            this.next = null;
        }
    }
    public boolean has_Cycle(Node head)
    {
        if (head == null) 
        	return false;
        Node slow = head,fast = head;
        while (fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) 
            	return true;
        }
        return false;
    }
}
class Main6
{
    public static void main(String[] args)
    {
        LinkedList.Node head = new LinkedList.Node(1);
        head.next = new LinkedList.Node(2);
        head.next.next = new LinkedList.Node(3);
        head.next.next.next = head.next;
        LinkedList list = new LinkedList();
        System.out.println("Has cycle: " + list.has_Cycle(head));
    }
}