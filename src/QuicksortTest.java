import student.TestCase;

import java.io.IOException;

/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 10/22/2016
 */
public class QuicksortTest extends TestCase {

    private CheckFile checker;

    /**
     * Sets up the tests that follow. In general, used for initialization.
     */
    public void setUp() {
        checker = new CheckFile();
    }

    /**
     * Get code coverage of the class declaration.
     */
    public void testQInit() {
        FileGenerator generator = new FileGenerator();
        final String[] argsFile1 = {
            "-a", "mainClassTest1", "20"
        };
        final String[] argsFile2 = {
            "-b", "mainClassTest2", "20"
        };
        try {
            generator.generateFile(argsFile1);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] argsSort1 = {
            "mainClassTest1", "10", "mainClassStat1"
        };
        Quicksort.main(argsSort1);
        try {
            assertTrue(checker.checkFile("mainClassTest1"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            generator.generateFile(argsFile2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] argsSort2 = {
            "mainClassTest2", "15", "mainClassStat2"
        };
        Quicksort.main(argsSort2);
        try {
            assertTrue(checker.checkFile("mainClassTest2"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
