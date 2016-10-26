/**
 * {Project Description Here}
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

/**
 * The class containing the main method.
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 10-23-2016
 */

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

public class Quicksort {
    /**
     * @param args
     *      Command line parameters.
     */
    public static void main(String[] args) {
        // This is the main file for the program.
        if(args == null || args.length != 3){
            System.out.println("Usage: Quicksort <data-file-name> <numb-buffers> <stat-file-name> ");
            return;
        }

        String dataFileName = args[0];
        int numberOfBuffers = parseInt(args[1]);
        String statFileName = args[2];


        Sort quickSort = new Sort();
        BufferPool pool = new BufferPool(dataFileName, numberOfBuffers);
        long start = System.currentTimeMillis();
        quickSort.quickSort(pool, 0, pool.getNumRecord()-1);
        pool.close();
        long end = System.currentTimeMillis();
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(
                    new File(statFileName), true));
            writer.append(dataFileName+"\n");
            writer.append(pool.getCacheHit()+"\n");
            writer.append(pool.getCacheMiss()+"\n");
            writer.append(pool.getNumDiscWrite()+"\n");
            writer.append((end-start)+"\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}