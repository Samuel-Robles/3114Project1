package Class;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import student.TestCase;;

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

    // Writes to the output file
    private FileWriter outWriter;
    // Wraps outWriter in order to write safely
    private PrintWriter outFile;


    /**
     * Sets up the tests
     * 
     * @throws IOException
     */
    public void setUp() throws IOException {
        tester = new Section(1);
        outWriter = new FileWriter("TestOutput.txt");
        outFile = new PrintWriter(outWriter);
    }


    /**
     * Tests that the initial set up is correct and that insert works correctly
     */
    public void testInsert() {

        // Tests that initial values are set correctly
        assertEquals(10001, tester.getID());
        assertEquals(0, tester.getSize());

        // Tests insert for a student that isn't in the section
        Student stu1 = tester.insert("Bart", "Simpson", outFile);
        assertTrue(stu1.getID().equalsIgnoreCase("010001"));
        // Checks that the sections values update
        assertEquals(10002, tester.getID());
        assertEquals(1, tester.getSize());

        // Tests insert on a second new student
        Student stu2 = tester.insert("OJ", "Simpson", outFile);
        assertTrue(stu2.getID().equalsIgnoreCase("010002"));
        // Checks that the sections values update
        assertEquals(10003, tester.getID());
        assertEquals(2, tester.getSize());

        // Tests insert on a repeat student
        Student stu3 = tester.insert("Bart", "Simpson", outFile);
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
        tester.remove("Bart", "Simpson", outFile);
        assertEquals(0, tester.getSize());
        assertEquals(10001, tester.getID());

        // Inserts a student and tries to remove another student that isn't in
        // the section
        Student stu1 = tester.insert("Bart", "Simpson", outFile);
        assertEquals(10002, tester.getID());
        assertEquals(1, tester.getSize());
        tester.remove("Homer", "Simpson", outFile);
        assertEquals(1, tester.getSize());
        assertEquals(10002, tester.getID());

        // Tests the removal of the student
        tester.remove("Bart", "Simpson", outFile);
        assertEquals(0, tester.getSize());
        assertEquals(10002, tester.getID());
        // Inserts a previously inserted student to show that it was removed (as
        // duplicates can't be inserted)
        stu1 = tester.insert("Bart", "Simpson", outFile);
        assertTrue(stu1.getID().equalsIgnoreCase("010002"));
        // Checks that the sections values update
        assertEquals(10003, tester.getID());
        assertEquals(1, tester.getSize());

    }


    // Tests the removeSection method
    public void testRemoveSection() {

        // Sets up a few students in the section
        tester.insert("Bart", "Simps", outFile);
        tester.insert("OJ", "Simpson", outFile);
        tester.insert("Big", "Lion", outFile);
        // Ensures that the section is correct
        assertEquals(3, tester.getSize());
        assertEquals(10004, tester.getID());

        // Removes the section, then checks that the values updated
        tester.removeSection();
        assertEquals(0, tester.getSize());
        assertEquals(10001, tester.getID());
        // Tries to remove a previously inserted student (to show that students
        // were cleared)
        tester.remove("OJ", "Simpson", outFile);
        assertEquals(0, tester.getSize());

    }


    /**
     * Tests the search method for the single parameter version
     */
    public void testSearchSingle() {

        // Tests when the section is empty
        Student[] test1 = tester.search("Simpson");
        assertNull(test1[0]);
        // Tests for when there is one element that has the name
        tester.insert("Bart", "Simpson", outFile);
        test1 = tester.search("Simpson");
        assertTrue(test1[0].getLastName().equalsIgnoreCase("simpson"));
        assertTrue(test1[0].getFirstName().equalsIgnoreCase("bart"));

        // Removes the student, then tests for when there is a single
        // nonmatching student
        tester.remove("Bart", "Simpson", outFile);

        tester.insert("Bart", "Simps", outFile);
        test1 = tester.search("Simpson");
        assertNull(test1[0]);

        // Adds more non matches into the section, then tests to ensure that the
        // array is still empty
        tester.insert("Bruh", "SoundEffect#2", outFile);
        tester.insert("Big", "Lion", outFile);
        tester.insert("sir", "chief", outFile);
        tester.insert("Black", "Dynamite", outFile);
        test1 = tester.search("Simpson");
        assertNull(test1[0]);

        // Adds a single instance of a matching name
        tester.insert("OJ", "Simpson", outFile);
        test1 = tester.search("Simpson");
        assertTrue(test1[0].getLastName().equalsIgnoreCase("simpson"));
        assertTrue(test1[0].getFirstName().equalsIgnoreCase("oj"));
        assertNull(test1[1]);

        // Adds 2 more people in with the name, one who's first name is the
        // match and one who's last name is the match
        tester.insert("Bart", "Simpson", outFile);
        tester.insert("Simpson", "Homer", outFile);
        test1 = tester.search("Simpson");
        assertTrue(test1[1].getLastName().equalsIgnoreCase("simpson"));
        assertTrue(test1[1].getFirstName().equalsIgnoreCase("bart"));

        assertTrue(test1[0].getFirstName().equalsIgnoreCase("simpson"));
        assertTrue(test1[0].getLastName().equalsIgnoreCase("homer"));

        assertTrue(test1[2].getLastName().equalsIgnoreCase("simpson"));
        assertTrue(test1[2].getFirstName().equalsIgnoreCase("oj"));
        assertNull(test1[3]);

    }


    /**
     * Tests the search method that takes a first and last name
     */
    public void testSearchTwoNames() {

        // Tests it when the section is empty
        assertNull(tester.search("K", "Dot"));

        // Tests when there is a single student who isn't who we're looking for
        tester.insert("Jotaro", "Kujo", outFile);
        assertNull(tester.search("Giorno", "Giovanna"));
        assertNull(tester.search("Giorno", "Kujo"));
        assertNull(tester.search("Jotaro", "Giovanna"));

        // Tests for when the single student in the section is the one we're
        // looking for
        Student stu1 = tester.search("Jotaro", "Kujo");
        assertTrue(stu1.getFirstName().equalsIgnoreCase("jotaro"));
        assertTrue(stu1.getLastName().equalsIgnoreCase("kujo"));

        // Tests for when there are multiple elements but the one you're looking
        // for isn't there
        tester.insert("Kujo", "Jolyne", outFile);
        tester.insert("Jolyne", "Kujo", outFile);
        tester.insert("Jotaro", "Higashigata", outFile);
        tester.insert("Josuke", "Higashigata", outFile);
        assertNull(tester.search("Kujo", "Jotaro"));

        // tests for when there are multiple elements and the student we are
        // looking for is in there
        tester.insert("Kujo", "Jotaro", outFile);
        stu1 = tester.search("Kujo", "Jotaro");
        assertTrue(stu1.getLastName().equalsIgnoreCase("jotaro"));
        assertTrue(stu1.getFirstName().equalsIgnoreCase("kujo"));

    }


    /**
     * Tests the grade method
     */
    public void testGrade() {

        // Tests grade on an empty section
        assertEquals(tester.grade()[0], 0);
        assertEquals(tester.grade()[11], 0);

        // Tests grade on a section with one student
        tester.insert("Jotaro", "Kujo", outFile).setGrade(0);
        assertEquals(tester.grade()[11], 1);
        assertEquals(tester.grade()[0], 0);

        // Adds another student with a 100
        tester.insert("Dio", "Brando", outFile).setGrade(100);
        assertEquals(tester.grade()[0], 1);
        assertEquals(tester.grade()[11], 1);
        assertEquals(tester.grade()[5], 0);

        // Adds a student to each grade level and tests whether grade updates
        // correctly
        tester.insert("Jolyene", "Kujo", outFile).setGrade(89);
        tester.insert("D I O", " ", outFile).setGrade(84);
        tester.insert("Jonathan", "Joestar", outFile).setGrade(79);
        tester.insert("Perfect", "Kars", outFile).setGrade(74);
        tester.insert("Joseph", "Joestar", outFile).setGrade(69);
        tester.insert("Josuke", "Higashigata", outFile).setGrade(64);
        tester.insert("Giorno", "Giovanna", outFile).setGrade(59);
        tester.insert("Diavolo", " ", outFile).setGrade(56);
        tester.insert("Yoshikage", "Kira", outFile).setGrade(53);
        tester.insert("Enrico", "Pucci", outFile).setGrade(50);
        // Tests themselves
        assertEquals(tester.grade()[0], 1);
        assertEquals(tester.grade()[1], 1);
        assertEquals(tester.grade()[2], 1);
        assertEquals(tester.grade()[3], 1);
        assertEquals(tester.grade()[4], 1);
        assertEquals(tester.grade()[5], 1);
        assertEquals(tester.grade()[6], 1);
        assertEquals(tester.grade()[7], 1);
        assertEquals(tester.grade()[8], 1);
        assertEquals(tester.grade()[9], 1);
        assertEquals(tester.grade()[10], 1);
        assertEquals(tester.grade()[11], 1);

        // Adds another student to prove that it updates correclty
        tester.insert("Caesar", "Zeppeli", outFile).setGrade(92);
        assertEquals(tester.grade()[0], 2);
        assertEquals(tester.grade()[1], 1);
        assertEquals(tester.grade()[2], 1);
        assertEquals(tester.grade()[3], 1);
        assertEquals(tester.grade()[4], 1);
        assertEquals(tester.grade()[5], 1);
        assertEquals(tester.grade()[6], 1);
        assertEquals(tester.grade()[7], 1);
        assertEquals(tester.grade()[8], 1);
        assertEquals(tester.grade()[9], 1);
        assertEquals(tester.grade()[10], 1);
        assertEquals(tester.grade()[11], 1);

    }

}
