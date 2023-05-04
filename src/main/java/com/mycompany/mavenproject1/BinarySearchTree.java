package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    public static void main(String[] args) {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.loadDictionary("src/main/java/com/mycompany/mavenproject1/output.txt");

        BinarySearchTree<String> suggestions = tree.getSuggestions("uza", 2);
        suggestions.inorder();
        System.out.println(suggestions.findMin());

    }

    void loadDictionary(String filename) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                insert((T) line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void insertFINAL(T data, String targetWord) {
        root = insertHelper(root, data, targetWord);
    }

    private Node<T> insertHelper(Node<T> root, T data, String targetWord) {
        String s1 = data.toString();
        if (root == null) {
            int distance = LevenshteinDistance.levenshteinDistance(s1, targetWord);
            root = new Node<T>(distance, data);
            return root;
        }

        int distance = LevenshteinDistance.levenshteinDistance(s1, targetWord);
        if (distance < root.key) {
            root.left = insertHelper(root.left, data, targetWord);
        } else if (distance >= root.key) {
            root.right = insertHelper(root.right, data, targetWord);
        }

        return root;
    }

    Node<T> insertRec(Node<T> root, T data) {
        Node<T> newNode = new Node<T>(data);
        if (root == null) {
            root = newNode;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    T findMin() {
        if (root == null) {
            return null;
        } else {
            Node<T> temp = root;
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp.data;
        }
    }

    // * Left,Root,Right
    void inorder() {
        System.out.print("inorder: ");
        inorder(root);
        System.out.println();
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private void inorder(Node<T> node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " " + node.key + ", " + " ");
            inorder(node.right);
        }
    }

    public BinarySearchTree<T> getSuggestions(String targetWord, int maxDistance) {
        BinarySearchTree<T> suggestions = new BinarySearchTree<T>();
        getSuggestionsHelper(root, targetWord, suggestions, maxDistance);
        return suggestions;
    }

    private void getSuggestionsHelper(Node<T> node, String targetWord, BinarySearchTree<T> suggestions, int maxDistance) {
        if (node == null) {
            return;
        }
        String s1 = node.data.toString();
        int distance = LevenshteinDistance.levenshteinDistance(s1, targetWord);
        if (distance <= maxDistance) {
            suggestions.insertFINAL(node.data, targetWord);
        }

        getSuggestionsHelper(node.left, targetWord, suggestions, maxDistance);
        getSuggestionsHelper(node.right, targetWord, suggestions, maxDistance);
    }

    public String getValues() {
        StringBuilder sb = new StringBuilder();
        getValuesHelper(root, sb);
        return sb.toString();
    }

    private void getValuesHelper(Node<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        getValuesHelper(node.left, sb);
        sb.append(node.data).append(",");
        getValuesHelper(node.right, sb);
    }

}

