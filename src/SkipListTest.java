import student.TestCase;

/**
 * This class is created to test the Command processor with Quad Tree and
 * SkipList methods
 * 
 * @author Sadath-Mohammed-msadath
 * @author EmadEldin-Abdrabou-emazied
 * 
 * @version 2021-08-23
 */
public class SkipListTest extends TestCase {

    private CommandProcessor cmd = new CommandProcessor();

    /**
     * Tests the output for a Dump() with non-empty Skiplist
     */
    public void testDump1() {
        cmd.processor("insert r1 1 2");
        cmd.processor("insert r2 6 5");
        cmd.processor("insert r3 -1 5");
        cmd.processor("insert r12 4 3");
        cmd.processor("");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "Value (r1, 1, 2)"));
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "SkipList size is: 3"));
    }


    /**
     * Tests the output for a Dump() with an empty Skiplist
     */
    public void testDumpEmpty() {
        cmd.processor("insert r3 -1 5");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "depth 1, Value (null)"));
    }


    /**
     * Tests the output for a Search() with valid input
     */
    public void testSearch1() {
        cmd.processor("insert r1 1 2");
        cmd.processor("insert r2 6 5");
        cmd.processor("insert r1 1 5");
        cmd.processor("insert r12 4 3");
        cmd.processor("insert r2 7 9");
        cmd.processor("search r1");
        assertTrue(systemOut().getHistory().toString().contains("(r1, 1, 2)"));
        assertTrue(systemOut().getHistory().toString().contains("(r1, 1, 5)"));
    }


    /**
     * Tests the output for a Search() with valid input
     */
    public void testSearch2() {
        cmd.processor("insert r1 1 2");
        cmd.processor("insert r2 6 5");
        cmd.processor("insert r1 1 5");
        cmd.processor("insert r12 4 3");
        cmd.processor("insert r2 7 9");
        cmd.processor("search r2");
        assertTrue(systemOut().getHistory().toString().contains("(r2, 6, 5)"));
        assertTrue(systemOut().getHistory().toString().contains("(r2, 7, 9)"));
    }


    /**
     * Tests the output for a Search() with Invalid input
     */
    public void testSearch3() {
        cmd.processor("insert r1 1 2");
        cmd.processor("insert r2 6 5");
        cmd.processor("insert r1 1 5");
        cmd.processor("insert r12 4 3");
        cmd.processor("insert r2 7 9");
        cmd.processor("search r5");
        assertTrue(systemOut().getHistory().toString().contains(
            "Point not found: r5"));
    }


    /**
     * Tests the output for a Remove by name with valid input
     */
    public void testRemoveByName() {
        cmd.processor("insert r1 1 2");
        cmd.processor("insert r2 6 5");
        cmd.processor("insert r4 1 5");
        cmd.processor("insert r12 4 3");
        cmd.processor("insert r5 7 9");
        cmd.processor("remove r4");
        assertTrue(systemOut().getHistory().toString().contains(
            "Point removed: (r4, 1, 5)"));
        cmd.processor("dump");
    }


    /**
     * Tests the output for a Remove last by name with valid input
     */
    public void testRemoveByNameLast() {
        cmd.processor("insert r1 1 2");
        cmd.processor("insert r2 6 5");
        cmd.processor("insert r4 1 5");
        cmd.processor("insert r12 4 3");
        cmd.processor("insert r5 7 9");
        cmd.processor("remove r5");
        assertTrue(systemOut().getHistory().toString().contains(
            "Point removed: (r5, 7, 9)"));
        cmd.processor("dump");
    }


    /**
     * Tests the output for a Remove by name with Invalid input
     */
    public void testRemoveByName2() {
        cmd.processor("insert r1 1 2");
        cmd.processor("insert r2 6 5");
        cmd.processor("insert r4 1 5");
        cmd.processor("insert r12 4 3");
        cmd.processor("insert r5 7 9");
        cmd.processor("remove a");
        assertTrue(systemOut().getHistory().toString().contains(
            "Point not removed: a"));
    }


    /**
     * Tests the output for a Remove by vale with Valid input
     */
    public void testRemoveByVal1() {
        cmd.processor("insert r1 1 2");
        cmd.processor("insert r2 6 5");
        cmd.processor("insert r4 1 5");
        cmd.processor("insert r12 4 3");
        cmd.processor("insert r13 14 3");
        cmd.processor("insert r16 42 3");
        cmd.processor("insert r25 34 3");
        cmd.processor("insert r12 44 3");
        cmd.processor("insert r5 7 9");
        cmd.processor("remove 7 9");
        cmd.processor("dump");
        assertTrue(systemOut().getHistory().toString().contains(
            "Point removed: (r5, 7, 9)"));
    }


}