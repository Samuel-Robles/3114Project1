package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Coursemanager1 {

    /**
     * The three sections we will be working with for the project
     */
    private static Section section1;
    private static Section section2;
    private static Section section3;
    // Holds all sections
    private static Section[] allSects;
    // The current operational section
    private static int currSect;
    // The active Student from the last search/insert
    private static Student currStud;
    // Checks if there is a current student
    private static boolean isStud;
    // Holds string representations of grade values
    private static String[] gradeNames;


    /**
     * The main method that handles reading in from the file and calling the
     * appropriate methods
     * 
     * @param args
     *            are the target input file
     * @throws IOException
     *             when the FileWriter causes an exception
     */
    public static void main(String[] args) throws IOException {
        declareSections();
        // name of the command file to read from, not assigned immediately for
        // safety
        String fileName;
        // Checks if there is a command file
        if (args.length == 1) {
            fileName = args[0];
        }
        else {
            throw new FileNotFoundException("Please add a command file.");
        }
        // File containing commands to the program
        File cmdFile = new File(fileName);
        // Writes to the output file
        FileWriter outWriter = new FileWriter("Output.txt");
        // Wraps outWriter in order to write safely
        PrintWriter output = new PrintWriter(outWriter);
        // Scanner that parses the commands
        Scanner file = new Scanner(cmdFile);
        while (file.hasNextLine()) {
            // Next command line of the file
            String line = file.nextLine();
            // Removes the whitespace and makes an array of substrings
            String[] lineSpl = line.trim().split("\\s+");
            // The first string, the command to execute
            String cmd = lineSpl[0].toLowerCase();
            if (cmd.equals("section")) {
                currSect = Integer.parseInt(lineSpl[1]) - 1;
                output.println("Switched to section " + lineSpl[1]);
            }
            else if (cmd.equals("insert")) {
                currStud = allSects[currSect].insert(lineSpl[1].toLowerCase(),
                    lineSpl[2].toLowerCase(), output);
                isStud = true;
            }
            else if (cmd.equals("search")) {
                if (lineSpl.length == 2) {
                    // The set of students returned from search, possibly empty
                    Student[] results = allSects[currSect].search(lineSpl[1]
                        .toLowerCase());
                    output.println("search results found for name: ");
                    if (results[1] == null) {
                        currStud = results[0];
                        isStud = true;
                    }
                    else {
                        isStud = false;
                    }
                    // Iterator through results
                    int i = 0;
                    while (i < results.length && results[i] != null) {
                        output.println(results[i].toString());
                        i++;
                    }
                    output.println("name was found in " + Integer.toString(i)
                        + " records in section " + Integer.toString(currSect
                            + 1));
                }
                else if (lineSpl.length == 3) {
                    // The student returned by search, possibly null
                    Student result = allSects[currSect].search(lineSpl[1]
                        .toLowerCase(), lineSpl[2].toLowerCase());
                    if (result == null) {
                        output.println("Search failed. Student " + lineSpl[1]
                            + " " + lineSpl[2] + " doesn't exist "
                            + "in section " + Integer.toString(currSect + 1));
                        isStud = false;
                    }
                    else {
                        output.println("Found " + result.toString());
                        currStud = result;
                        isStud = true;
                    }
                }
            }
            else if (cmd.equals("score")) {
                // The new score to be assigned
                int newScore = Integer.parseInt(lineSpl[1]);
                if (!isStud) {
                    output.println(
                        "score command can only be called after an insert command"
                            + " or a successful search command with one exact output.");
                }
                else if (newScore >= 0 && newScore <= 100) {
                    currStud.setGrade(newScore);
                    output.println("Update " + currStud.getFirstName() + " "
                        + currStud.getLastName() + " record, score = "
                        + lineSpl[1]);
                }
                else {
                    output.println(
                        "Scores have to be integers in range 0 to 100.");
                }
            }
            else if (cmd.equals("remove")) {
                allSects[currSect].remove(lineSpl[1].toLowerCase(), lineSpl[2]
                    .toLowerCase(), output);
            }
            else if (cmd.equals("grade")) {
                // Holds the grade totals of the students
                int[] result = allSects[currSect].grade();
                output.println("grading completed:");
                // iterator through result
                int j = 0;
                while (j < result.length) {
                    if (result[j] > 0) {
                        output.println(Integer.toString(result[j])
                            + " students with grade " + gradeNames[j]);
                    }
                    j++;
                }
            }
            else if (cmd.equals("dumpsection")) {
                output.println("Section " + Integer.toString(currSect + 1)
                    + " dump:");
                output.println(allSects[currSect].dumpSection());
                output.println("Size: " + Integer.toString(allSects[currSect]
                    .getSize()));
            }
            else if (cmd.equals("removesection")) {
                if (lineSpl.length > 1) {
                    allSects[Integer.parseInt(lineSpl[1]) - 1].removeSection();
                    output.println("Section " + lineSpl[1] + " removed");
                }
                else {
                    allSects[currSect].removeSection();
                    output.println("Section " + Integer.toString(currSect + 1)
                        + " removed");
                }
            }
            else if (cmd.equals("findpair")) {
                output.println("Findpair does nothing right now");
            }
            if (!cmd.equals("insert") && !cmd.equals("search")) {
                isStud = false;
            }
        }
        output.close();
        file.close();
    }


    /**
     * Declares all the relevant fields
     */
    private static void declareSections() {
        allSects = new Section[3];
        section1 = new Section(1);
        section2 = new Section(2);
        section3 = new Section(3);
        allSects[0] = section1;
        allSects[1] = section2;
        allSects[2] = section3;
        currSect = 0;
        isStud = false;
        setGradeNames();
    }


    /**
     * Creates an array that holds string representations of grade names
     * in order to ease implementation of the grade() command
     */
    private static void setGradeNames() {
        gradeNames = new String[12];
        gradeNames[0] = "A";
        gradeNames[1] = "A-";
        gradeNames[2] = "B+";
        gradeNames[3] = "B";
        gradeNames[4] = "B-";
        gradeNames[5] = "C+";
        gradeNames[6] = "C";
        gradeNames[7] = "C-";
        gradeNames[8] = "D+";
        gradeNames[9] = "D";
        gradeNames[10] = "D-";
        gradeNames[11] = "F";
    }

}
