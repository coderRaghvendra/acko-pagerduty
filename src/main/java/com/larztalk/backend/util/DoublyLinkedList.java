package com.larztalk.backend.util;

/**
 * Created by raghvendra.mishra on 12/09/19.
 */
public class DoublyLinkedList<T> {
    public class Node {
        Node left;
        T data;
        Node right;

        private Node(Node left, T data, Node right) {
            this.left = left;
            this.data = data;
            this.right = right;
        }

        public T getData() {
            return data;
        }
    }

    Node head;
    Node tail;
    int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Node addTop(T obj) {
        size++;
        if(this.head == null) {
            this.head = new Node(null, obj, null);
            this.tail = this.head;
            return this.head;
        } else {
            Node node = new Node(null, obj, this.head);
            this.head.left = node;
            this.head = node;
            return node;
        }
    }

    private Node addTop(Node node) {
        node.left = null;
        node.right = null;
        size++;
        if(this.head == null) {
            this.head = node;
            this.tail = this.head;
            return this.head;
        } else {
            node.right = this.head;
            this.head.left = node;
            this.head = node;
            return node;
        }
    }

    public Node addBottom(T obj) {
        size++;
        if(this.head == null) {
            this.head = new Node(null, obj, null);
            this.tail = this.head;
            return this.tail;
        } else {
            Node node = new Node(this.tail, obj, null);
            this.tail.right = node;
            this.tail = node;
            return node;
        }
    }

    public void remove(Node node) {
        if(node == null) {
            throw new RuntimeException("Empty node found");
        }
        if(this.head == null) {
            throw new RuntimeException("List is empty");
        } else if(this.head == this.tail) {
            if(this.head == node) {
                size--;
                this.head = null;
                this.tail = null;
            } else {
                throw new RuntimeException("Provided node is not from list");
            }
        } else if(node == this.head) {
            size--;
            this.head = this.head.right;
            this.head.left = null;
        } else if(node == this.tail) {
            size--;
            this.tail = this.tail.left;
            this.tail.right = null;
        } else if(node.left != null && node.right != null) {
            size--;
            node.left.right = node.right;
            node.right.left = node.left;
        } else {
            throw new RuntimeException("Provided node is not from list");
        }
    }

    public Node removeBottom() {
        if(this.head == null) {
            throw new RuntimeException("List is empty");
        }
        size--;
        Node node;
        if(this.head == this.tail) {
            node = this.head;
            this.head = null;
            this.tail = null;
            return node;
        }
        node = this.tail;
        this.tail = this.tail.left;
        this.tail.right = null;
        return node;
    }

    public void shiftTop(Node node) {
        this.remove(node);
        this.addTop(node);
    }

    public int size() {
        return size;
    }

    public void print() {
        Node temp = this.head;
        while (temp != null) {
            System.out.print(temp.data + ", ");
            temp = temp.right;
        }
        System.out.println();
    }
}
