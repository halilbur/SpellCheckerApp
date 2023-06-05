package com.mycompany.mavenproject1;

public class Node<T extends Comparable<T>> {
    T data;
    Node<T> right;
    Node<T> left;
    int key;

    public Node(T data) {
        this.data = data;
    }
    // node constructor with int key
    public Node(int key, T data) {
        this.key = key;
        this.data = data;
    }

}