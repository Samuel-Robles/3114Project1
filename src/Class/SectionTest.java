package Class;

import student.TestCase;

/**
 * Tests all of the methods of the section class
 * 
 * @author robleshs
 *
 */
public class SectionTest extends TestCase {

    /**
     * Section we are using to test our methods
     */
    private Section tester;


    /**
     * Sets up the tests
     */
    public void setUp() {
        tester = new Section(1);
    }


    /**
     * Tests that the initial set up is correct and that insert works correctly
     */
    public void testInsert() {

        // Tests that initial values are set correctly
        assertEquals(10001, tester.getID());
        assertEquals(0, tester.getSize());

        // Tests insert for a student that isn't in the section
        Student stu1 = tester.insert("Bart", "Simpson");
        assertTrue(stu1.getID().equalsIgnoreCase("010001"));
        // Checks that the sections values update
        assertEquals(10002, tester.getID());
        assertEquals(1, tester.getSize());

        // Tests insert on a second new student
        Student stu2 = tester.insert("OJ", "Simpson");
        assertTrue(stu2.getID().equalsIgnoreCase("010002"));
        // Checks that the sections values update
        assertEquals(10003, tester.getID());
        assertEquals(2, tester.getSize());

        // Tests insert on a repeat student
        Student stu3 = tester.insert("Bart", "Simpson");
        assertTrue(stu3.getID().equalsIgnoreCase("010001"));
        // Checks that the sections values update
        assertEquals(10003, tester.getID());
        assertEquals(2, tester.getSize());

    }


    /**
     * Tests the sections remove method
     */
    public void testRemove() {

        // Tests removing on an empty section
        tester.remove("Bart", "Simpson");
        assertEquals(0, tester.getSize());
        assertEquals(10001, tester.getID());

        // Inserts a student and tries to remove another student that isn't in
        // the section
        Student stu1 = tester.insert("Bart", "Simpson");
        assertEquals(10002, tester.getID());
        assertEquals(1, tester.getSize());
        tester.remove("Homer", "Simpson");
        assertEquals(1, tester.getSize());
        assertEquals(10002, tester.getID());

        // Tests the removal of the student
        tester.remove("Bart", "Simpson");
        assertEquals(0, tester.getSize());
        assertEquals(10002, tester.getID());
        // Inserts a previously inserted student to show that it was removed (as
        // duplicates can't be inserted)
        stu1 = tester.insert("Bart", "Simpson");
        assertTrue(stu1.getID().equalsIgnoreCase("010002"));
        // Checks that the sections values update
        assertEquals(10003, tester.getID());
        assertEquals(1, tester.getSize());

    }


    // Tests the removeSection method
    public void testRemoveSection() {

        // Sets up a few students in the section
        tester.insert("Bart", "Simps");
        tester.insert("OJ", "Simpson");
        tester.insert("Big", "Lion");
        // Ensures that the section is correct
        assertEquals(3, tester.getSize());
        assertEquals(10004, tester.getID());

        // Removes the section, then checks that the values updated
        tester.removeSection();
        assertEquals(0, tester.getSize());
        assertEquals(10001, tester.getID());
        // Tries to remove a previously inserted student (to show that students
        // were cleared)
        tester.remove("OJ", "Simpson");
        assertEquals(0, tester.getSize());

    }
}
