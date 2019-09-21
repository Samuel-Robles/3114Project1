/**
 * 
 */
package Class;

/**
 * @author samue
 *
 */
public class Section {
    private BinarySearchTree students;
    private int currID;
    private int size;
    
    /**
     * Creates a new Section with a BST and an integer counting the current id
     * @param section is the section number
     */
    public Section(int section) {
        students = new BinarySearchTree();
        currID = (section * 10000) + 1;
        size = 0;
        
    }
    
    /**
     * Adds a student to a section. If a student is already in the BST/section, then
     * it calls an exception. On successful insertion it generates a new id.
     * @return the student record that was just created (or the students exisiting
     * record if the student was already in the section)
     */
    public Student insert() {
        //TODO implement
        size += 1;
    }
    
    /**
     * Removes a student from the section
     * @param first The students first name
     * @param last The students last name
     */
    public void remove(String first, String last) {
        //TODO implement
        size -= 1;
    }
    
    /**
     * Completely removes all students from a section and resets the sections student
     * ids
     */
    public void removeSection() {
        //TODO
        currID = currID - size;
        size = 0;
        
    }
    
    /**
     * Searches for all students that have a given name
     * @param name the name of the student (can be either first or last name)
     * @return an array of all students who have the name
     */
    public Student[] search(String name) {
        //TODO
    }
    
    /**
     * Prints out the contents of the section
     * @return a string representing the contents of the section
     */
    public String dumpSection() {
        //TODO
    }
    
    /**
     * Goes through the section and assigns every student a grade based on 
     * their score. Records the total number of students with each letter grade
     * @return an array containing the number of students with each letter grade
     */
    public int[] grade() {
        
    }
    
}
