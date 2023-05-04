package com.mycompany.mavenproject1;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    T data;
    Node<T> right;
    Node<T> left;
    int key;

    public Node(T data) {
        this.data = data;
    }

    public Node(int key, T data) {
        this.key = key;
        this.data = data;
    }

    @Override
    public int compareTo(Node<T> o) {
        return 0;
    }
}