
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
import student.TestCase;

/**
 * Tests all of the methods of the section class
 * 
 * @author Sam Robles <robleshs>
 * @version 9/25/2019
 *
 */
public class SectionTest extends TestCase {

    /**
     * Section we are using to test our methods
     */
    private Section tester;

   /* // Writes to the output file
    // private FileWriter outWriter;
    // Wraps outWriter in order to write safely
    private PrintWriter outFile;*/


    /**
     * Sets up the tests
     * 
     * @throws IOException
     *             in the case of initialization errors with writers
     */
    public void setUp(){
        tester = new Section(1);
        /*FileWriter outWriter = new FileWriter("TestOutput.txt");
        outFile = new PrintWriter(outWriter);*/
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
    
    /**
     * Tests a specific removal case with two children when the right has an existing child
     */
    public void testRemoveBort() {
        //Creates a tree with Bart at the root
        tester.insert("Bart", "Simpson");
        tester.insert("Bort", "Simpson");
        tester.insert("Burt", "Simpson");
        tester.insert("Bjort", "Simpson");
        tester.insert("B", "Simpson");
        //removes the root with two children
        tester.remove("Bart", "Simpson");
        //Checks that remove has executed properly
        assertEquals(tester.getSize(), 4);
        String dump = tester.dumpSection();
        String comp = "010005, B Simpson, score = 0, " + '\n' +
            "010004, Bjort Simpson, score = 0, " + '\n' +
            "010002, Bort Simpson, score = 0, " + '\n' +
            "010003, Burt Simpson, score = 0, " + '\n';
        assertTrue(dump.equalsIgnoreCase(comp));
    }


    /**
     * Tests the removeSection method
     */
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


    /**
     * Tests the search method for the single parameter version
     */
    public void testSearchSingle() {

        // Tests when the section is empty
        Student[] test1 = tester.search("Simpson");
        assertNull(test1[0]);
        // Tests for when there is one element that has the name
        tester.insert("Bart", "Simpson");
        test1 = tester.search("Simpson");
        assertTrue(test1[0].getLastName().equalsIgnoreCase("simpson"));
        assertTrue(test1[0].getFirstName().equalsIgnoreCase("bart"));

        // Removes the student, then tests for when there is a single
        // non-matching student
        tester.remove("Bart", "Simpson");

        tester.insert("Bart", "Simps");
        test1 = tester.search("Simpson");
        assertNull(test1[0]);

        // Adds more non matches into the section, then tests to ensure that the
        // array is still empty
        tester.insert("Bruh", "SoundEffect#2");
        tester.insert("Big", "Lion");
        tester.insert("sir", "chief");
        tester.insert("Black", "Dynamite");
        test1 = tester.search("Simpson");
        assertNull(test1[0]);

        // Adds a single instance of a matching name
        tester.insert("OJ", "Simpson");
        test1 = tester.search("Simpson");
        assertTrue(test1[0].getLastName().equalsIgnoreCase("simpson"));
        assertTrue(test1[0].getFirstName().equalsIgnoreCase("oj"));
        assertNull(test1[1]);

        // Adds 2 more people in with the name, one who's first name is the
        // match and one who's last name is the match
        tester.insert("Bart", "Simpson");
        tester.insert("Simpson", "Homer");
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
        tester.insert("Jotaro", "Kujo");
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
        tester.insert("Kujo", "Jolyne");
        tester.insert("Jolyne", "Kujo");
        tester.insert("Jotaro", "Higashikata");
        tester.insert("Josuke", "Higashikata");
        assertNull(tester.search("Kujo", "Jotaro"));

        // tests for when there are multiple elements and the student we are
        // looking for is in there
        tester.insert("Kujo", "Jotaro");
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
        tester.insert("Jotaro", "Kujo").setGrade(0);
        assertEquals(tester.grade()[11], 1);
        assertEquals(tester.grade()[0], 0);

        // Adds another student with a 100
        tester.insert("Dio", "Brando").setGrade(100);
        assertEquals(tester.grade()[0], 1);
        assertEquals(tester.grade()[11], 1);
        assertEquals(tester.grade()[5], 0);

        // Adds a student to each grade level and tests whether grade updates
        // correctly
        tester.insert("Jolyene", "Kujo").setGrade(89);
        tester.insert("D I O", " ").setGrade(84);
        tester.insert("Jonathan", "Joestar").setGrade(79);
        tester.insert("Perfect", "Kars").setGrade(74);
        tester.insert("Joseph", "Joestar").setGrade(69);
        tester.insert("Josuke", "Higashikata").setGrade(64);
        tester.insert("Giorno", "Giovanna").setGrade(59);
        tester.insert("Diavolo", " ").setGrade(56);
        tester.insert("Yoshikage", "Kira").setGrade(53);
        tester.insert("Enrico", "Pucci").setGrade(50);
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

        // Adds another student to prove that it updates correctly
        tester.insert("Caesar", "Zeppeli").setGrade(92);
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


    /**
     * Tests the dumpSection method to ensure that it creates proper strings
     * representing
     * a section
     */
    public void testDumpSection() {

        // tries to dumpSection on an empty section
        String test = tester.dumpSection();
        String base = "";
        assertTrue(test.equalsIgnoreCase(base));

        // Inserts students into the section then tests dumpSection
        tester.insert("Josuke", "Higashikata").setGrade(100);
        tester.insert("Giorno", "Giovanna").setGrade(90);
        tester.insert("Jolyne", "Kujo").setGrade(80);
        tester.insert("Jotaro", "Kujo").setGrade(70);
        tester.insert("Crazy", "Diamond").setGrade(60);
        tester.insert("Vinegar", "Doppio").setGrade(50);
        tester.insert("Dio", "Brando").setGrade(40);
        test = tester.dumpSection();
        base = "010007, Dio Brando, score = 40, " + '\n'
            + "010005, Crazy Diamond, score = 60, " + '\n'
            + "010006, Vinegar Doppio, score = 50, " + '\n'
            + "010002, Giorno Giovanna, score = 90, " + '\n'
            + "010001, Josuke Higashikata, score = 100, " + '\n'
            + "010003, Jolyne Kujo, score = 80, " + '\n'
            + "010004, Jotaro Kujo, score = 70, " + '\n';
        assertTrue(test.equals(base));
    }

}
