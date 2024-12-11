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
public class CommandProcessorTest extends TestCase {

    private CommandProcessor cmd = new CommandProcessor();

    /**
     * Tests the insertion methods
     */
    public void testValidInsert() {
        cmd.processor("insert c01 1 2");
        cmd.processor("insert c02 -1 8");
        cmd.processor("insert c03 102 104");
        cmd.processor("insert c04 6 8");
        cmd.processor("insert c09 1 2");
        cmd.processor("insert c21 1 2");
        cmd.processor("insert c22 1 2");
        cmd.processor("insert b01 8 9");
        cmd.processor("insert c11 1 3");
        cmd.processor("insert c12 1 4");
        cmd.processor("insert c13 1 5");

        cmd.processor("search c08");
        cmd.processor("search c04");
        cmd.processor("search b01");
        cmd.processor("search a1");

        assertTrue(systemOut().getHistory().toString().contains(
            "Point not found: c08"));
        assertTrue(systemOut().getHistory().toString().contains(
            "Point not found: a1"));
        assertTrue(systemOut().getHistory().toString().contains(
            "Found (b01, 8, 9)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "Found (c04, 6, 8)"));

        cmd.processor("insert i1 1 1");
        cmd.processor("insert i2 500 1");
        cmd.processor("insert i3 1 500");
        cmd.processor("insert i4 500 500");
        cmd.processor("insert i11 4 4");
        cmd.processor("search i11");

        assertTrue(systemOut().getHistory().toString().contains(
            "Found (i11, 4, 4)"));

    }


    /**
     * Tests the remove methods
     */

    public void testRemove() {
        cmd.processor("insert c01 1 2");
        cmd.processor("insert c02 -1 8");
        cmd.processor("insert c03 102 104");
        cmd.processor("insert c04 6 8");
        cmd.processor("insert b01 8 9");

        cmd.processor("remove cnon");
        cmd.processor("remove c01");

        assertTrue(systemOut().getHistory().toString().contains(
            "Point not removed: cnon"));
        assertTrue(systemOut().getHistory().toString().contains(
            "Point removed: (c01, 1, 2)"));
    }


    /**
     * Tests the DUMP methods
     */
    public void testDump() {
        cmd.processor("insert r1 10 10");
        cmd.processor("insert r2 15 15");
        cmd.processor("insert r3 7 7");
        cmd.processor("insert r4 20 25");
        cmd.processor("insert r4 20 12");
        cmd.processor("insert r5 6 7");
        cmd.processor("insert r12 108 136");
        cmd.processor("insert r14 120 117");
        cmd.processor("insert r15 120 117");
        cmd.processor("dump");

        assertTrue(systemOut().getHistory().toString().contains(
            "              (r2, 15, 15)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "            Node at 0, 0, 16: Internal"));
        assertTrue(systemOut().getHistory().toString().contains(
            "      (r12, 108, 136)"));

    }


    /**
     * Tests the REMOVE methods
     */
    public void testRemoveDump() {
        cmd.processor("insert r1 10 10");
        cmd.processor("insert r2 15 15");
        cmd.processor("insert r3 7 7");
        cmd.processor("insert r4 20 25");
        cmd.processor("insert r4 20 12");
        cmd.processor("insert r5 6 7");
        cmd.processor("insert r12 108 136");
        cmd.processor("insert r14 120 117");
        cmd.processor("insert r15 120 117");
        cmd.processor("remove r_r");
        cmd.processor("remove inExistRec");
        cmd.processor("search     r4");
        cmd.processor("remove r4");
        cmd.processor("dump");

        assertFalse(systemOut().getHistory().toString().contains(
            "            (r4, 20, 12)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "        (r15, 120, 117)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "            (r4, 20, 25)"));

    }


    /**
     * Tests edge cases on remove methods
     */
    public void testRemoveValueDump() {
        cmd.processor("insert r1 10 10");
        cmd.processor("insert r2 15 15");
        cmd.processor("insert r3 7 7");
        cmd.processor("insert r4 20 25");
        cmd.processor("insert r4 20 12");
        cmd.processor("insert r5 6 7");
        cmd.processor("insert r12 108 136");
        cmd.processor("insert r14 120 117");
        cmd.processor("insert r15 120 117");
        cmd.processor("remove r_r");
        cmd.processor("remove inExistRec");
        cmd.processor("search     r4");
        cmd.processor("remove r4");
        cmd.processor("remove 6 7");
        cmd.processor("dump");
        cmd.processor("remove r5");

        assertTrue(systemOut().getHistory().toString().contains(
            "\n            (r2, 15, 15)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "\n            (r3, 7, 7)"));
        assertTrue(systemOut().getHistory().toString().contains(
            "Point not removed: r5"));

    }


    /**
     * Tests the completely remove functionaity
     */
    public void testRemoveAll() {
        cmd.processor("insert p1 1 2");
        cmd.processor("insert p2 50 12");
        cmd.processor("insert p3 5 5");
        cmd.processor("insert p4 120 100");
        cmd.processor("dump");
        cmd.processor("remove p1");
        cmd.processor("remove p3");
        cmd.processor("remove 50 12");
        cmd.processor("remove 120 100");
        cmd.processor("dump");
    }


    /**
     * Tests the duplicates methods
     */
    public void testDuplicates() {
        cmd.processor("insert a1 10 10");
        cmd.processor("insert a2 10 10");
        cmd.processor("insert a3 10 10");
        cmd.processor("insert a4 10 10");
        cmd.processor("insert a5 10 10");
        cmd.processor("insert x1 20 20");
        cmd.processor("insert x2 20 20");
        cmd.processor("insert b1 200 200");
        cmd.processor("insert b2 200 201");
        cmd.processor("insert b3 200 200");
        cmd.processor("insert c1 600 600");
        cmd.processor("insert c2 600 600");
        cmd.processor("insert c3 600 601");
        cmd.processor("insert d1 200 600");
        cmd.processor("insert d2 200 601");
        cmd.processor("insert d3 200 601");
        cmd.processor("insert e1 600 200");
        cmd.processor("insert e2 600 201");
        cmd.processor("insert e3 600 201");
        cmd.processor("insert f1 0 1000");
        cmd.processor("insert f2 0 1001");
        cmd.processor("insert f3 1 1000");

        systemOut().clearHistory();

        cmd.processor("duplicates");
        cmd.processor("insert f1 600 202");
        cmd.processor("remove 600 202");
        cmd.processor("regionsearch 11 10 2 2");
        cmd.processor("regionsearch 10 11 2 2");
        // cmd.processor("dump");

        assertTrue(systemOut().getHistory().toString().contains(
            "Duplicate points:"));
        assertTrue(systemOut().getHistory().toString().contains("(10, 10)"));
        assertTrue(systemOut().getHistory().toString().contains("(20, 20)"));
        assertTrue(systemOut().getHistory().toString().contains("(200, 200)"));
        assertTrue(systemOut().getHistory().toString().contains("(600, 201)"));
        assertTrue(systemOut().getHistory().toString().contains("(200, 601)"));
        assertTrue(systemOut().getHistory().toString().contains("(600, 600)"));

    }


    /**
     * Tests the edge cases of duplicates methods
     */
    public void testDuplicateSplit() {
        cmd.processor("insert a1 2 2");
        cmd.processor("insert a2 2 2");
        cmd.processor("insert a4 5 2");
        cmd.processor("insert a3 2 2");
        // cmd.processor("insert a6 2 2");
        cmd.processor("insert a7 5 2");
        cmd.processor("remove 5 2");
        cmd.processor("remove a1");
        cmd.processor("remove a3");
        cmd.processor("remove 100 -100");
        cmd.processor("remove not");
        cmd.processor("remove 100 105");
        cmd.processor("remove 1030 1030");

        cmd.processor("dump");
// cmd.processor("insert a6 2 2");
// cmd.processor("insert a1 2 2");
        cmd.processor("duplicates");
    }


    /**
     * Testing the region search
     */
    public void testRegSearch() {
        cmd.processor("insert r1 10 10");
        cmd.processor("regionsearch 11 11 0 0");
        assertTrue(systemOut().getHistory().contains(
            "Rectangle rejected: (11, 11, 0, 0)"));

        cmd.processor("insert r2 15 15");
        cmd.processor("insert r3 7 7");
        cmd.processor("insert r4 20 25");
        cmd.processor("insert r4 20 12");
        cmd.processor("insert r5 6 7");
        cmd.processor("insert r12 108 136");
        cmd.processor("insert r14 120 117");
        cmd.processor("insert r15 120 117");
        // cmd.processor("dump");
        cmd.processor("remove r_r");
        cmd.processor("remove inExistRec");
        cmd.processor("search r4");
        cmd.processor("remove r4");
// cmd.processor("dump");
        cmd.processor("remove 6 7");
// cmd.processor("dump");
        cmd.processor("remove r5");
// cmd.processor("dump");
        cmd.processor("search r14");
        cmd.processor("search r11");
        cmd.processor("search R11");
        cmd.processor("remove r10");
        cmd.processor("remove r11");
        cmd.processor("remove r12");
        cmd.processor("remove r13");
// cmd.processor("dump");
        cmd.processor("remove 120 117");
// cmd.processor("dump");
        cmd.processor("remove 100 1000");
        cmd.processor("remove r14");
        systemOut().clearHistory();

        cmd.processor("regionsearch -5 -5 20 20");
        assertTrue(systemOut().getHistory().contains(
            "Points intersecting region (-5, -5, 20, 20)"));
        assertTrue(systemOut().getHistory().contains(
            "Point found: (r1, 10, 10)"));
        assertTrue(systemOut().getHistory().contains(
            "Point found: (r3, 7, 7)"));

        cmd.processor("insert big 10 10");
        cmd.processor("duplicates");
        cmd.processor("search r2");
        cmd.processor("search r4");
        cmd.processor("remove 20 25");
        systemOut().clearHistory();

        cmd.processor("regionsearch 10 10 100 100");
        assertTrue(systemOut().getHistory().contains(
            "Points intersecting region (10, 10, 100, 100)"));
        assertTrue(systemOut().getHistory().contains(
            "Point found: (r1, 10, 10)"));
        assertTrue(systemOut().getHistory().contains(
            "Point found: (r2, 15, 15)"));
        assertTrue(systemOut().getHistory().contains(
            "Point found: (big, 10, 10)"));
        assertTrue(systemOut().getHistory().contains(
            "17 quadtree nodes visited"));

        cmd.processor("dump");

    }


    /**
     * tests regionsearch edge cases
     */
    public void testRegSearch2() {
        cmd.processor("insert a1 10 10");
        cmd.processor("insert a2 10 10");
        cmd.processor("insert a3 10 10");
        cmd.processor("insert a4 10 10");
        cmd.processor("insert a5 10 10");
        cmd.processor("insert x1 20 20");
        cmd.processor("insert x2 20 20");
        cmd.processor("insert b1 200 200");
        cmd.processor("insert b2 200 201");
        cmd.processor("insert b3 200 200");
        cmd.processor("insert c1 600 600");
        cmd.processor("insert c2 600 600");
        cmd.processor("insert c3 600 601");
        cmd.processor("insert d1 200 600");
        cmd.processor("insert d2 200 601");
        cmd.processor("insert d3 200 601");
        cmd.processor("insert e1 600 200");
        cmd.processor("insert e2 600 201");
        cmd.processor("insert e3 600 201");
        cmd.processor("insert f1 0 1000");
        cmd.processor("insert f2 0 1001");
        cmd.processor("insert f3 1 1000");
        cmd.processor("duplicates");
        cmd.processor("insert f1 600 202");
        cmd.processor("remove 600 202");
        systemOut().clearHistory();
        cmd.processor("regionsearch 11 10 2 2");
        assertTrue(systemOut().getHistory().toString().contains(
            "7 quadtree nodes visited"));

        systemOut().clearHistory();
        cmd.processor("regionsearch 10 11 2 2");
        assertTrue(systemOut().getHistory().toString().contains(
            "7 quadtree nodes visited"));
        cmd.processor("dump");


    }

// public void testMerge() {
// cmd.processor("insert a 1 1");
// cmd.processor("insert b 5 5");
// cmd.processor("insert k 32 15");
// cmd.processor("insert s 100 100");
// //cmd.processor("insert g 136 136");
// cmd.processor("insert h 128 128");
// cmd.processor("remove h");
// cmd.processor("dump");
// }

}
