package com.scienceminer.datastructures;


public class Node<T> {

    // Data members
    // 1. Storing value of node
    T data;
    // 2. Storing address of next node
    Node<T> next;
    Node<T> previous;

    // Parameterized constructor to assign value
    public Node(T data) {
        super();

        // This keyword refers to current object itself
        this.data = data;
        this.next = null;
        this.previous = null;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", next=" + next + ", prev=" + previous + "]";
    }

    public void insertCopy(Node nodeToInsertCopyAfter) {
        T data = (T) nodeToInsertCopyAfter.getData();
        // Creating new node with given value
        Node<T> copiedNode = new Node<>(data);

        Node currentNextNode = nodeToInsertCopyAfter.next;

        copiedNode.next = currentNextNode;

        nodeToInsertCopyAfter.next = copiedNode;

    }
}