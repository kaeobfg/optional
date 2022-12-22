class Node {
    String value;
    Node next;

    Node() {
        value = null;
        next = null;
    };

    Node(String value) {
        this.value = value;
        next = null;
    }
}

public class LinkedList extends Node {
    Node head;

    // creating and adding a new node
    public void addNew(String data) {
        Node newNode = new Node(data);

        // if list is empty, assign it to head
        // else iterate till the end and add as the next node
        if (head == null) {
            head = newNode;
        }
        else {
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
        }
    }

    // adding an existing node (making a loop)
    public void addExisting(int pos) {
        // if list is empty, throw an exception
        // else create a reference to the node on chosen position and add it at the end
        // if chosen position is unavailable, throw an exception
        if (head == null) {
            throw new RuntimeException("No node at current position: list is empty");
        }
        else {
            Node lastNode = head;
            for (int i = 1; i < pos; i++) {
                if (lastNode.next == null) {
                    throw new RuntimeException("No node at current position to add");
                }
                else {
                    lastNode = lastNode.next;
                }
            }
            Node thisNode = lastNode;
            lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = thisNode;
        }
    }

    public String detectLoop() {

        if (head.next.next == null) { return null; }

        Node iterSlow = head, iterFast = head;
        iterSlow = iterSlow.next; // 1x
        iterFast = iterFast.next.next; // 2x

        // if slow = fast break, else continue till the end of the list
        while (iterFast.next != null) {
            if (iterSlow == iterFast) { break; }
            iterSlow = iterSlow.next;
            iterFast = iterFast.next.next;
        }

        // if no cycle return null
        if (iterSlow != iterFast) { return null; }

        iterSlow = head; // if cycle is found, aimed node is equally far from the head and the node where slow and fast overlap
        while (iterSlow != iterFast) {
            iterSlow = iterSlow.next;
            iterFast = iterFast.next;
        }

        return iterSlow.value;
    }

    public void printList(String check) {

        // add flag to stop printing the loop after the same character as given occurred
        Node thisNode = head;

        System.out.print("LinkedList: ");
        int flag = 0;
        while (thisNode != null) {
            System.out.print(thisNode.value + " ");
            if (thisNode.value.equals(check)) { flag++; }
            if (flag > 1) { break; }
            thisNode = thisNode.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addNew("A"); //1
        list.addNew("B"); //2
        list.addNew("C"); //3
        list.addNew("D"); //4
        list.addNew("E"); //5
        list.addExisting(3);
        String loop = list.detectLoop();
        list.printList(loop);
        System.out.println("\nNode at the beginning of the loop: " + loop);
    }
}

// Explanation:
//
// The fast iterator is twice as fast as the slow one
// m - length of list outside the loop
// n - length of list loop
// k - distance from the beginning of the loop to the node where the iterators overlapped (if loop exists)
// x, y - number of cycles passed by the fast and slow iterator respectively
//
// m + n*x + k = 2(m + n*y + k)
// m + k = n(x - 2y)
//
// (x-2y) is a cycle period, so m = n-k
// It means if the slow iterator starts from the head and another continues through the loop with the same step,
// they will overlap in the beginning of the loop


