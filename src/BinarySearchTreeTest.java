

import student.TestCase;

/**
 * Tests the methods of BinarySearchTree
 * @author Sam Robles <robleshs>
 * @author John Hoskinson <johnh98>
 * @version 9/22/2019
 * 
 * 
 */
public class BinarySearchTreeTest extends TestCase {

    /**
     * The tree being tested
     */
    private BinarySearchTree<String> bSTree;


    /**
     * Sets up the tests
     */
    public void setUp() {
        bSTree = new BinarySearchTree<String>();
    }


    /**
     * Tests isEmpty and insert
     */
    public void testIn() {
        assertTrue(bSTree.isEmpty());
        bSTree.insert("Test");
        assertFalse(bSTree.isEmpty());
        Exception exception = null;
        try {
            bSTree.insert("Test");
        }
        catch (DuplicateItemException e) {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * Tests remove
     */
    public void testRemove() {
        assertTrue(bSTree.isEmpty());
        bSTree.insert("Test");
        bSTree.insert("Test1");
        bSTree.remove("Test");
        bSTree.remove("Test1");
        assertTrue(bSTree.isEmpty());
        for (int i = 0; i < 20; i++) {
            bSTree.insert("Test " + i);
        }
        bSTree.remove("Test 9");
        bSTree.remove("Test 10");
        bSTree.remove("Test 3");
        Exception exception = null;
        try {
            bSTree.remove("Test");
        }
        catch (ItemNotFoundException e) {
            exception = e;
        }
        assertNotNull(exception);
    }


    /**
     * Tests findMin and findMax
     */
    public void testMinMax() {
        assertNull(bSTree.findMin());
        assertNull(bSTree.findMax());
        for (int i = 0; i < 5; i++) {
            bSTree.insert("" + i);
        }
        assertTrue(bSTree.findMax().equals("4"));
        assertTrue(bSTree.findMin().equals("0"));
        bSTree.remove("0");
        bSTree.insert("0");
        assertTrue(bSTree.findMin().equals("0"));
    }


    /**
     * Tests makeEmpty
     */
    public void testMakeEmpty() {
        for (int i = 0; i < 5; i++) {
            bSTree.insert("" + i);
        }
        assertFalse(bSTree.isEmpty());
        bSTree.makeEmpty();
        assertTrue(bSTree.isEmpty());
    }


    /**
     * Tests find()
     */
    public void testFind() {
        for (int i = 1; i < 5; i++) {
            bSTree.insert("" + i);
        }
        bSTree.insert("0");
        assertTrue(bSTree.find("3").equals("3"));
        assertTrue(bSTree.find("0").equals("0"));
        assertNull(bSTree.find("6"));
    }


    /**
     * Tests toString()
     */
    public void testToString() {
        String none = "()";
        String full = "(Test 0, Test 1, Test 2)";
        String strE = bSTree.toString();
        for (int i = 0; i < 3; i++) {
            bSTree.insert("Test " + i);
        }
        String strF = bSTree.toString();
        assertTrue(strE.equals(none));
        assertTrue(strF.equals(full));
    }

}
