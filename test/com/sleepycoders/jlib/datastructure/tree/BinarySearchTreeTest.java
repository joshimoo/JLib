package com.sleepycoders.jlib.datastructure.tree;
import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

/**
 * BinarySearchTree Test Code that achieves 100% coverage
 * @author Joshua Moody (joshimoo@hotmail.de)
 */
public class BinarySearchTreeTest {

    // BST Parameters
    private static final Integer ROOT = 50;
    private static final Integer LEFT = 30;
    private static final Integer RIGHT = 70;
    private static final Integer MIN = 15;
    private static final Integer MAX = 85;
    private static final Integer UNKNOWN = 42;

    private BinarySearchTree<Integer> createBST() {
        // Orders
        // Integer[] inorder = new Integer[] { MIN ,20, 25, LEFT, 35, 40, 45, ROOT, 55, 60, 65, RIGHT, 75, 80, MAX };
        // Integer[] preorder = new Integer[] { ROOT, LEFT, 20, MIN, 25, 40, 35, 45, RIGHT, 60, 55, 65, 80, 75, MAX};
        // Integer[] postorder = new Integer[] { MIN, 25, 20, 35, 45, 40, LEFT, 55, 65, 60, 75, MAX, 80, RIGHT, ROOT};

        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(Comparator.<Integer>naturalOrder());
        bst.insert(ROOT); // root

        // left subtree
        bst.insert(LEFT); // left

        bst.insert(20); // left.left
        bst.insert(MIN); // left.left.left
        bst.insert(25); // left.left.right

        bst.insert(40); // left.right
        bst.insert(35); // left.right.left
        bst.insert(45); // left.right.right

        // right subtree
        bst.insert(RIGHT); // right

        bst.insert(60); // right.left
        bst.insert(55); // right.left.left
        bst.insert(65); // right.left.right

        bst.insert(80); // right.right
        bst.insert(75); // right.right.left
        bst.insert(MAX); // right.right.right

        return bst;
    }

    @Test
    public void testTraverseInorder() throws Exception {
        BinarySearchTree<Integer> bst = createBST();
        Integer[] inorder = new Integer[] { MIN ,20, 25, LEFT, 35, 40, 45, ROOT, 55, 60, 65, RIGHT, 75, 80, MAX };
        Integer[] actual = bst.traverse(BinarySearchTree.TraversalMethod.Inorder).toArray(new Integer[inorder.length]);
        assertArrayEquals(inorder, actual);
    }

    @Test
    public void testTraversePreorder() throws Exception {
        BinarySearchTree<Integer> bst = createBST();
        Integer[] preorder = new Integer[] { ROOT, LEFT, 20, MIN, 25, 40, 35, 45, RIGHT, 60, 55, 65, 80, 75, MAX};
        Integer[] actual = bst.traverse(BinarySearchTree.TraversalMethod.Preorder).toArray(new Integer[preorder.length]);
        assertArrayEquals(preorder, actual);
    }

    @Test
    public void testTraversePostorder() throws Exception {
        BinarySearchTree<Integer> bst = createBST();
        Integer[] postorder = new Integer[] { MIN, 25, 20, 35, 45, 40, LEFT, 55, 65, 60, 75, MAX, 80, RIGHT, ROOT};
        Integer[] actual = bst.traverse(BinarySearchTree.TraversalMethod.Postorder).toArray(new Integer[postorder.length]);
        assertArrayEquals(postorder, actual);
    }

    @Test
    public void testFind() throws Exception {
        BinarySearchTree<Integer> bst = createBST();
        assertTrue(bst.find(ROOT));
        assertTrue(bst.find(MIN));
        assertTrue(bst.find(MAX));

        // Make sure these are not in the tree
        assertFalse(bst.find(UNKNOWN));
        assertFalse(bst.find(-UNKNOWN));
    }

    @Test
    public void testMin() throws Exception {
        assertEquals(MIN, createBST().min());
    }

    @Test
    public void testMax() throws Exception {
        assertEquals(MAX, createBST().max());
    }

    @Test
    public void testInsert() throws Exception {
        BinarySearchTree<Integer> bst = createBST();

        // Positive
        assertFalse(bst.find(UNKNOWN));
        bst.insert(UNKNOWN);
        assertTrue(bst.find(UNKNOWN));

        // Negative
        assertFalse(bst.find(-UNKNOWN));
        bst.insert(-UNKNOWN);
        assertTrue(bst.find(-UNKNOWN));
    }

    @Test
    public void testInsertDuplicate() throws Exception {
        BinarySearchTree<Integer> bst = createBST();
        bst.insert(MIN);
        bst.insert(ROOT);
        bst.insert(MAX);
        bst.insert(MAX);
        Integer[] expected = new Integer[] { MIN, MIN, 20, 25, LEFT, 35, 40, 45, ROOT, ROOT, 55, 60, 65, RIGHT, 75, 80, MAX, MAX, MAX};
        Integer[] actual = bst.traverse().toArray(new Integer[expected.length]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testInsertOrder() throws Exception {
        BinarySearchTree<Integer> bst = createBST();
        bst.insert(2222);
        Integer[] expected = new Integer[] { MIN ,20, 25, LEFT, 35, 40, 45, ROOT, 55, 60, 65, RIGHT, 75, 80, MAX, 2222};
        Integer[] actual = bst.traverse().toArray(new Integer[expected.length]);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testRemove() throws Exception {
        BinarySearchTree<Integer> bst = createBST();

        // Leafs
        bst.remove(MIN);

        // Inner
        bst.remove(LEFT);

        // Root
        bst.remove(ROOT);

        // Immediate Predecessor as child
        bst.remove(80);

        // Deletions: MIN/LEFT/ROOT/80
        // Integer[] expectedInorder = new Integer[] { MIN ,20, 25, LEFT, 35, 40, 45, ROOT, 55, 60, 65, RIGHT, 75, 80, MAX };
        Integer[] expected = new Integer[] { 20, 25, 35, 40, 45, 55, 60, 65, RIGHT, 75, MAX };
        Integer[] actual = bst.traverse().toArray(new Integer[0]);
        assertArrayEquals(expected, actual);
    }

}