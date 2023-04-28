package com.mycompany.mavenproject1;

import java.io.*;

public class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    void loadDictionary(String filename) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                insert2((T) line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //    void checkSpelling(T word) {
//        boolean isCorrect = search(word);
//
//        if (isCorrect) {
//            System.out.println("The word is spelled correctly.");
//        } else {
//            T suggestion = findClosestMatch(word);
//            System.out.println("The word is misspelled. Did you mean '" + suggestion + "'?");
//        }
//
//    }
    public String getNodesWithKeyUpToInitial(int key) {
        StringBuilder sb = new StringBuilder();
        getNodesWithKeyUpTo(root, key, sb);
        return sb.toString();
    }

    private void getNodesWithKeyUpToInitial(Node<T> node, int key, StringBuilder sb) {
        if (node == null) {
            return;
        }

        if (node.key < key) {
            getNodesWithKeyUpTo(node.left, key, sb);
            sb.append(node.data);
            sb.append(" ");
            getNodesWithKeyUpTo(node.right, key, sb);
        } else if (node.key == key) {
            sb.append(node.data);
            sb.append(" ");
            getNodesWithKeyUpTo(node.right, key, sb);
        } else {
            getNodesWithKeyUpTo(node.left, key, sb);
        }
    }

    public String getNodesWithKeyUpTo(int key) {
        StringBuilder sb = new StringBuilder();
        getNodesWithKeyUpTo(root, key, sb);
        return sb.toString();
    }

    private void getNodesWithKeyUpTo(Node<T> node, int key, StringBuilder sb) {
        if (node == null) {
            return;
        }

        if (node.key <= key) {
            getNodesWithKeyUpTo(node.left, key, sb);
            sb.append(node.data);
            sb.append("\n");
            getNodesWithKeyUpTo(node.right, key, sb);
        } else {
            getNodesWithKeyUpTo(node.left, key, sb);
        }
    }


    BinarySearchTree<T> findClosestMatches(T data) {
        BinarySearchTree<T> matches = new BinarySearchTree<>();
        findClosestMatchesRec(root, data, matches);
        return matches;
    }

    void findClosestMatchesRec(Node<T> root, T data, BinarySearchTree<T> matches) {
        if (root == null) {
            return;
        }
        String s1 = root.data.toString();
        String s2 = data.toString();
        int distance = LevenshteinDistance.levenshteinDistance2(s1, s2);

        int compareResult = data.compareTo(root.data);

        if (compareResult < 0) {
            findClosestMatchesRec(root.left, data, matches);
        } else {
            findClosestMatchesRec(root.right, data, matches);
        }

        matches.insertQ(distance, root.data);
    }

    //    BinarySearchTree<T> checkSpellingReturn(T word) {
//
//        if (search(word)) {
//            return word;
//        } else {
//            return findClosestMatches(word);
//        }
//    }
    T findClosestMatch(T data) {
        return findClosestMatchRec(root, data, root.data, Integer.MAX_VALUE);
    }

    T findClosestMatchRec(Node<T> root, T data, T closestMatch, int minDistance) {
        if (root == null) {
            return closestMatch;
        }
        String s1 = root.data.toString();
        String s2 = data.toString();
        int distance = LevenshteinDistance.levenshteinDistance2(s1, s2);

        if (distance < minDistance) {
            minDistance = distance;
            closestMatch = root.data;
        }

        int compareResult = data.compareTo(root.data);

        if (compareResult < 0) {
            return findClosestMatchRec(root.left, data, closestMatch, minDistance);
        } else if (compareResult > 0) {
            return findClosestMatchRec(root.right, data, closestMatch, minDistance);
        } else {
            // compareResult == 0
            return root.data;
        }
    }

    public void insertQ(int key, T data) {
        root = insertRecQ(root, key, data);
    }

    private Node<T> insertRecQ(Node<T> root, int key, T data) {
        Node<T> newNode = new Node<T>(key, data);
        if (root == null) {
            root = newNode;
            return root;
        }
        if (key < root.key) {
            root.left = insertRecQ(root.left, key, data);
        } else if (key > root.key) {
            root.right = insertRecQ(root.right, key, data);
        }
        return root;
    }

    public void insert2(T data) {
        root = insertRec(root, data);
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

    void insert(T data) {
        Node<T> newNode = new Node<T>(data);

        if (root == null) {
            root = newNode;
        } else {
            Node<T> temp = root;
            while (temp != null) {
                if (data.compareTo(temp.data) > 0) {
                    if (temp.right == null) {
                        temp.right = newNode;
                        return;
                    }
                    temp = temp.right;
                } else if (data.compareTo(temp.data) < 0) {
                    if (temp.left == null) {
                        temp.left = newNode;
                        return;
                    }
                }
            }
        }
    }

    boolean search(T searchdata) {
        if (root == null) {
            return false;
        } else {
            Node<T> temp = root;
            while (temp != null) {
                if (searchdata.compareTo(temp.data) > 0) {
                    temp = temp.right;
                } else if (searchdata.compareTo(temp.data) < 0) {
                    temp = temp.left;
                } else {
                    return true;
                }
            }
        }
        return false;
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

    T findMax() {
        if (root == null) {
            return null;
        } else {
            Node<T> temp = root;
            while (temp.right != null) {
                temp = temp.right;
            }
            return temp.data;
        }
    }

    String printInOrder() {
        StringBuilder sb = new StringBuilder();
        printInOrderRec(root, sb);
        return sb.toString();
    }

    void printInOrderRec(Node<T> node, StringBuilder sb) {
        if (node != null) {
            printInOrderRec(node.left, sb);
            sb.append(node.data).append(node.key).append("\n");
            printInOrderRec(node.right, sb);
        }
    }

    // * Left,Root,Right
    void inorder() {
        System.out.print("inorder: ");
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<T> node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

}
