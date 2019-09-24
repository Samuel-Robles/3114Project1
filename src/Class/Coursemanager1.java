package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.


public class Coursemanager1 {

    /**
     * The three sections we will be working with for the project
     */
    private static Section section1;
    private static Section section2;
    private static Section section3;
    // Holds all sections
    private static Section[] allSects;
    //The current operational section
    private static int currSect;
    //The active Student from the last search/insert
    private static Student currStud;
    //Checks if there is a current student
    private static boolean isStud;
    
    /**
     * The main method that handles reading in from the file and calling the appropriate methods
     * 
     * @param args are the target input file
     * @throws FileNotFoundException when not given an input file
     */
    public static void main(String[] args) throws FileNotFoundException {
        declareSections();
        String fileName;
        if (args.length == 1) {
            fileName = args[0];
        }
        else {
            throw new FileNotFoundException("Please add a command file.");
        }
        File cmdFile = new File(fileName);
        Scanner file = new Scanner(cmdFile);
        while(file.hasNextLine()) {
            String line = file.nextLine();
            String[] lineSpl = line.split(" \t");
            String cmd = lineSpl[0].toLowerCase();
            if (cmd.equals("section")) {
                currSect = Integer.parseInt(lineSpl[1]) - 1;
                System.out.println("Switched to section " + lineSpl[1]);
            }
            else if (cmd.equals("insert")) {
                currStud = allSects[currSect].insert(lineSpl[1].toLowerCase(), lineSpl[2].toLowerCase());
            }
            else if (cmd.equals("search")) {
                if (lineSpl.length == 2) {
                    Student[] results = allSects[currSect].search(lineSpl[1].toLowerCase());
                    if (results.length == 1) {
                        currStud = results[0];
                    }
                }
                if (lineSpl.length == 3) {
                    //TODO: search with two names
                }
            }
            else if (cmd.equals("score")) {
                
            }
        }
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
        currSect = -1;
        isStud = false;
    }
}
