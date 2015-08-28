package com.sleepycoders.jlib.datastructure.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A fully recursive generic BinarySearchTree
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class BinarySearchTree<T> {
    private Comparator<T> cmp;
    private BinarySearchTreeNode<T> root;
    public BinarySearchTree(Comparator<T> cmp) { this.cmp = cmp; }

    //region Traversal
    // TODO: Move the Traversal Part outside of the BST
    // TODO: Abstract the Traversal Part so that it can run on all Binary Trees
    public enum TraversalMethod { Inorder, Preorder,Postorder }
    public List<T> traverse() { return traverse(TraversalMethod.Inorder); }
    public List<T> traverse(TraversalMethod traversalMethod) {
        ArrayList<T> results = new ArrayList<>();

        // TODO: Think about replacing this with something different
        switch (traversalMethod) {
            case Inorder:
                inorderTraversal(root, results);
                break;
            case Preorder:
                preorderTraversal(root, results);
                break;
            case Postorder:
                postorderTraversal(root, results);
                break;
        }

        return results;
    }

    private void inorderTraversal(BinarySearchTreeNode<T> current, List<T> results) {
        // Base Case
        if (current == null) { return; }

        // traversal order
        inorderTraversal(current.left, results);
        results.add(current.key);
        inorderTraversal(current.right, results);
    }

    private void preorderTraversal(BinarySearchTreeNode<T> current, List<T> results) {
        // Base Case
        if (current == null) { return; }

        // traversal order
        results.add(current.key);
        preorderTraversal(current.left, results);
        preorderTraversal(current.right, results);
    }

    private void postorderTraversal(BinarySearchTreeNode<T> current, List<T> results) {
        // Base Case
        if (current == null) { return; }

        // traversal order
        postorderTraversal(current.left, results);
        postorderTraversal(current.right, results);
        results.add(current.key);
    }
    //endregion

    public boolean find(T item) {
        return find(item, root);
    }
    private boolean find(T item, BinarySearchTreeNode<T> node) {
        // Recursive Base Cases
        if (node == null) { return false; }

        // Comparisons
        int c = cmp.compare(item, node.key);
        if(c < 0) { return find(item, node.left); }
        else if(c > 0) { return find(item, node.right); }
        else { return true; }
    }

    public T min() {
        return min(root);
    }
    private T min(BinarySearchTreeNode<T> current) {
        if (current.left == null) { return current.key; }
        else { return min(current.left); }
    }

    public T max() {
        return max(root);
    }
    private T max(BinarySearchTreeNode<T> current) {
        if (current.right == null) { return current.key; }
        else { return max(current.right); }
    }

    public void insert(T item) {
        if (root == null) { root = new BinarySearchTreeNode<T>(item); }
        else { insert(item, root); }
    }

    private void insert(T item, BinarySearchTreeNode<T> node) {
        // Comparisons
        int c = cmp.compare(item, node.key);
        if(c < 0) { // Left
            if (node.left == null) { node.left = new BinarySearchTreeNode<T>(item); }
            else { insert(item, node.left); }
        } else if(c > 0) { // Right
            if (node.right == null) { node.right = new BinarySearchTreeNode<T>(item); }
            else { insert(item, node.right); }
        } else { // Implementation Dependent
            if (node.left == null) { node.left = new BinarySearchTreeNode<T>(item); }
            else if (node.right == null) { node.right = new BinarySearchTreeNode<T>(item); }
            else { insert(item, node.left); }
        }
    }

    public boolean remove(T item) {
        // Induction - Basis
        // replace root?
        if (cmp.compare(item, root.key) == 0) {
            if (root.left == null) { root = root.right; }
            else if (root.right == null) { root = root.left; }
            else { removeNode(root); }
            return true;
        } else {
            return remove(item, root);
        }
    }

    private boolean remove(T item, BinarySearchTreeNode<T> node) {
        // Induction - Step
        // Comparisons
        int c = cmp.compare(item, node.key);
        if(c < 0) { // left

            if (node.left == null) { return false; }
            else if(cmp.compare(item, node.left.key) == 0) { // left child has key
                if (node.left.left == null) { node.left = node.left.right; } // replace with right child
                else if (node.left.right == null) { node.left = node.left.left; } // replace with left child
                else { removeNode(node.left); } // replace with deep child
                return true;
            } else { // recurse down
                return remove(item, node.left);
            }

        } else { // right - c == 0 is not possible, since we only recurse if the children.keys != item;

            if (node.right == null) { return false; } // not in tree
            else if(cmp.compare(item, node.right.key) == 0) { // right child has key
                if (node.right.left == null) { node.right = node.right.right; } // replace with right child
                else if (node.right.right == null) { node.right = node.right.left; } // replace with left child
                else { removeNode(node.right); } // replace with deep child
                return true;
            } else { // recurse down
                return remove(item, node.right);
            }

        }

    }

    private void removeNode(BinarySearchTreeNode<T> node) {
        // Induction - Basis
        // Replace node with left child if immediate predecessor.
        if (node.left.right == null) {
            node.key = node.left.key;
            node.left = node.left.left;
        } else {
            removeNode(node, node.left);
        }
    }

    private void removeNode(BinarySearchTreeNode<T> node, BinarySearchTreeNode<T> current) {
        // Induction - Step
        // Prerequisites: node.left != null
        if (current.right.right == null) {
            node.key = current.right.key;
            current.right = current.right.left;
        } else {
            removeNode(node, current.right);
        }
    }

    private class BinarySearchTreeNode<T> {
        public T key;
        public BinarySearchTreeNode<T> left, right;
        public BinarySearchTreeNode(T item) { key = item; }
    }
}


