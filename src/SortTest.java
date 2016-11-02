import student.TestCase;

/**
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 10/23/2016.
 */
public class SortTest extends TestCase {
    private FileGenerator generator;
    private CheckFile checker;
    private String filename;
    private String[] args;

    /*******************
     * Variable setup  *
     *******************/
    public void setUp() {

        checker = new CheckFile();
        generator = new FileGenerator();
        filename = "testFile";
        args = new String[3];
        args[0] = filename;
    }

    /**
     * test sorting with one buffer and one block of Ascii No Time Limit
     * 
     * 
     */
    public void testSort12() throws Exception {

        final String[] argsFile = {
            "-a", filename, "1"
        };
        generator.generateFile(argsFile);
        args[1] = "1"; // number of buffers
        args[2] = "test12Stat"; // stat file name
        Quicksort.main(args);
        try {
            assertTrue(checker.checkFile(filename));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * test sorting with 10 buffer and 10 block of binary No Time Limit
     * 
     * 
     */
    public void testSort3() throws Exception {
        final String[] argsFile = {
            "-b", filename, "10"
        };
        generator.generateFile(argsFile);
        args[1] = "10"; // number of buffers
        args[2] = "test3Stat"; // stat file name
        Quicksort.main(args);
        try {
            assertTrue(checker.checkFile(filename));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * test sorting with 4 buffer and 10 block of binary Generous Time: 420ms
     * Result Time: 61ms
     * 
     */
    public void testSort4() throws Exception {
        final String[] argsFile = {
            "-b", filename, "10"
        };
        generator.generateFile(argsFile);
        args[1] = "4"; // number of buffers
        args[2] = "test4Stat"; // stat file name
        Quicksort.main(args);
        try {
            assertTrue(checker.checkFile(filename));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * test sorting with 1 buffer and 10 block of binary Generous Time: 1s
     * Result Time: 259ms
     * 
     */
    public void testSort5() throws Exception {
        final String[] argsFile = {
            "-b", filename, "10"
        };
        generator.generateFile(argsFile);
        args[1] = "1"; // number of buffers
        args[2] = "test5Stat"; // stat file name
        Quicksort.main(args);
        try {
            assertTrue(checker.checkFile(filename));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * test sorting with 10 buffer and 100 block of binary Reasonable Time
     * Result: 307ms;
     * 
     */
    public void testSort67() throws Exception {
        final String[] argsFile = {
            "-b", filename, "100"
        };
        generator.generateFile(argsFile);
        assertFalse(checker.checkFile(filename));
        args[1] = "10"; // number of buffers
        args[2] = "test67Stat"; // stat file name
        Quicksort.main(args);
        try {
            assertTrue(checker.checkFile(filename));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * test sorting with 10 buffer and 1000 block of binary Tighter Time Limit
     * G: 12.585s R: 4.426s T: 2.445s Result Time: 1869ms
     * 
     */
    public void testSort8910() throws Exception {
        final String[] argsFile = {
            "-b", filename, "1000"
        };
        generator.generateFile(argsFile);
        args[1] = "10"; // number of buffers
        args[2] = "test8910Stat"; // stat file name
        Quicksort.main(args);
        try {
            assertTrue(checker.checkFile(filename));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * test sorting with 10 buffer and 1000 block of ASCii Optimization Time
     * Limit G: 4.5s O: 2.445s
     * 
     */
    public void testSort1112() throws Exception {
        final String[] argsFile = {
            "-a", filename, "1000"
        };
        generator.generateFile(argsFile);
        args[1] = "10"; // number of buffers
        args[2] = "test1112Stat"; // stat file name
        Quicksort.main(args);
        try {
            assertTrue(checker.checkFile(filename));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
