import java.io.IOException;

import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class QuicksortTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization.
     */
    public void setUp() {
        // Nothing Here
    }

    /**
     * Get code coverage of the class declaration.
     */
    public void testQInit() {
        FileGenerator generator = new FileGenerator();
        String[] args = {"-a", "testinput1", "10"};
        try {
            generator.generateFile(args);
            Quicksort tree = new Quicksort();
            assertNotNull(tree);
//            Quicksort.main(null);
        }
        catch (IOException e) {
            
            e.printStackTrace();
        }
        
    }
}
