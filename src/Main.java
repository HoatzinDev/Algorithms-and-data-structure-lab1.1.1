import java.util.Scanner;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        ArrayStack stack = new ArrayStack(10);
        for (int i = 0; i < 6; i++)
        {stack.push(random.nextInt(21) - 10); }// [-10  10]
        stack.display();

        while (true)
        {
            System.out.println("\n1 - add 2 - remove, 0 - next");
            String choice = input.nextLine();

            if (choice.equals("1"))
            {
                System.out.print("number to add: ");
                try
                {
                    int val = Integer.parseInt(input.nextLine());
                    if (stack.push(val)) stack.display();
                } catch (NumberFormatException e)
                {
                    System.out.println("error, must be integer");
                }
            } else if (choice.equals("2"))
            {
                try
                {
                    int removed = stack.pop();
                    System.out.println(removed+" gone ");
                    stack.display();
                } catch (RuntimeException e)
                {
                    System.out.println(e.getMessage());
                }
            } else if (choice.equals("0")) {break;}
        }
        DoublyLinkedList list = new DoublyLinkedList();
        System.out.println("\nMoving elements to List ");
        while (!stack.isEmpty())
        {
            int num = stack.pop();

            if (num > 0)
            {

                String hex = Integer.toHexString(num).toUpperCase();
                list.addFirst(hex);
                System.out.println("Positive " + num + " moved to FRONT as " + hex);
            }
            else if (num < 0)
            {

                int inverted = Math.abs(num);
                String hex = Integer.toHexString(inverted).toUpperCase();
                list.addLast(hex);
                System.out.println("Negative " + num + " inverted and moved to BACK as " + hex);
            }

        }

        stack.display();
        list.printList();
        while (true)
        {
            System.out.println("\n1 - add 2 - remove, 0 - finish");
            String choice = input.nextLine();

            if (choice.equals("1"))
            {
                System.out.print("number to add: ");
                try
                {
                    int num = Integer.parseInt(input.nextLine());

                    if (num > 0) {

                        String hex = Integer.toHexString(num).toUpperCase();
                        list.addFirst(hex);
                        System.out.println("Positive " + num + " moved to FRONT as " + hex);
                    }
                    else if (num < 0)
                    {
                        int inverted = Math.abs(num);
                        String hex = Integer.toHexString(inverted).toUpperCase();
                        list.addLast(hex);
                        System.out.println("Negative " + num + " inverted and moved to BACK as " + hex);
                    }
                } catch (NumberFormatException e) {System.out.println("error, must be integer");}
            } else if (choice.equals("2"))
            {
                System.out.print("Enter value to remove: ");
                String hexToRemove = input.nextLine().toUpperCase();
                if (list.remove(hexToRemove)) {System.out.println("Value " + hexToRemove + " removed.");}
                else {System.out.println("Value not found in list.");}
                list.printList();
            }
            else if (choice.equals("0")) {break;}
        }
    }
}

class ArrayStack
{
    private int[] data;
    private int topIndex;
    private int capacity;
    public ArrayStack(int size)
    {
        this.capacity = size;
        this.data = new int[size];
        this.topIndex = -1;
    }


    public boolean isFull() {return topIndex== capacity - 1;}

    public boolean isEmpty() {return topIndex == -1;}


    public boolean push(int value)
    {

        if (isFull())
        {
            System.out.println("stack overflow");
            return false;
        }
        data[++topIndex] = value;
        return true;
    }

    public int pop()
    {
        if (isEmpty()) {throw new RuntimeException("stack is empty");}
        return data[topIndex--];
    }

    public void display()
    {
        if (isEmpty())
        {
            System.out.println("stack is empty");
            return;
        }
        System.out.print("stack data: ");
        for (int i = 0; i <= topIndex; i++)
        {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
class Node
{
    public String data;
    public Node next;
    public Node prev;

    public Node(String data) {this.data = data;}
}
class DoublyLinkedList
{
    public Node head;
    public Node tail;

    public boolean isEmpty()
    {
        return head == null;
    }

    public void addFirst(String value)
    {
        Node newNode = new Node(value);
        if (isEmpty())
        {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void addLast(String value)
    {
        Node newNode = new Node(value);
        if (isEmpty()) {head = tail = newNode;}
        else
        {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void printList()
    {
        if (isEmpty())
        {
            System.out.println("List is empty.");
            return;
        }
        Node current = head;
        System.out.print("List: ");
        while (current != null)
        {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    public boolean remove(String value)
    {
        if (isEmpty()) return false;
        Node current = head;
        while (current != null)
        {
            if (current.data.equals(value))
            {
                if (current.prev != null) {current.prev.next = current.next;}
                else {head = current.next;}
                if (current.next != null) {current.next.prev = current.prev;}
                else {tail = current.prev; }
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
