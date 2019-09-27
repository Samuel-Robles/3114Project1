
import student.TestCase;

/**
 * Tests all of the methods of the Student class
 * 
 * @author Sam Robles <robleshs>
 * @version 9/25/2019
 *
 */
public class StudentTest extends TestCase {

    /**
     * Student we are using to test our methods
     */
    private Student tester;


    /**
     * Sets up the tests
     */
    public void setUp() {
        tester = new Student("first", "last", "ID");
    }


    /**
     * Tests getGrade, setGrade, and the other getters
     */
    public void testGettersSetters() {
        assertEquals(tester.getGrade(), 0);
        tester.setGrade(100);
        assertEquals(tester.getGrade(), 100);

        assertTrue(tester.getID().equals("ID"));
        assertTrue(tester.getFirstName().equals("first"));
        assertTrue(tester.getLastName().equals("last"));
    }


    /**
     * Tests toString for student
     */
    public void testToString() {
        String testString = tester.toString();
        assertTrue(testString.equals("ID, first last, score = 0"));

        Student test2 = new Student("John", null, "0000");
        test2.setGrade(50);
        String testString2 = test2.toString();
        assertTrue(testString2.equals("0000, John null, score = 50"));
    }


    /**
     * Tests the student compareTo method
     */
    public void testCompareTo() {
        Student test1 = new Student("Bart", "Simpson", "0000");
        Student test2 = new Student("Bart", "Simpson", "0001");
        Student test3 = new Student("OJ", "Simpson", "0002");
        Student test4 = new Student("Nikola", "Bart", "0003");

        assertEquals(0, test1.compareTo(test2));
        assertTrue(0 != test1.compareTo(test3));
        assertTrue(0 != test1.compareTo(test4));
    }

}
