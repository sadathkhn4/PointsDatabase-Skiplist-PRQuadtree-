// *import static org.junit.Assert.*;
// import org.junit.Test;*/

/// import the external student JAR libraries for testing rectangle class
import student.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import student.TestableRandom;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * It is must to look into project description and test samples for test design
 * 
 * In this test file, we test the six classes CommandProcessor, Database,
 * KVPair, Coordinates, SkipList, and PRQuadTree in addition to the main method
 * 
 * we setup the test class with
 * Rectangle obejects named recXX
 * Rectangles objects where XX two digit numbering of rectangle names
 * 
 * testKVPair
 * KVPair object <String key, Rectangle value>
 * 
 * skipList
 * SkipList<String, xyCoordinates>
 * 
 * PRQuadTree
 * PRQuadTree<K key, xyCoordinates>
 *
 *
 * dataTest
 * Database object
 * 
 * dataCommand
 * Command processor object
 * 
 * We called the following assert methods to test all classes with their
 * associated method
 * assertTrue, assertFalse, assertNull, assertNotNull, assertSame, assertEquals,
 * and assertFuzzyEquals
 *
 * @author Sadath-Mohammed-msadath
 * @author Emadeldin-Abdrabou-emazied
 * 
 * @version 2024-02-18
 */
//// The test class
public class PointsDatabaseTest extends TestCase {
    // Data members
    // declare test objects and data parameters to be used throughout our tests

    // private Coordinates testXYCoordName;
    // private KVPair<K key, V value> testKVPairName; // private KVPair<String,
    // Rectangle>
    // private SkipList<KVPair<K key, V value>> testSkipListName; // private
    // SkipList<String, Rectangle> skipList;
    // private PRQuadTree<KVPair<K key, V value>> testPRQuadTreeName;
    // private Database testDataBaseObjectName; // private Database dataTest;
    // private CommandProcessor testCommandProcessorName; // private
    // CommandProcessor dataCommandsName;

    /**
     * USed to test the Main() function with a valid file name parameter
     */
    public void testMainValid() {
        PointsDatabase.main(new String[] { "src/validInput.txt" });
        assertTrue(systemOut().getHistory().toString().contains("alpha"));
        assertTrue(systemOut().getHistory().toString().contains("gamma"));

        systemOut().clearHistory();
        PointsDatabase.main(new String[] { "src/nonExisting.txt" });
        assertTrue(systemOut().getHistory().toString().contains(
            "Invalid file"));

    }


/// Testing coordinates
    /**
     * This method aims to test XYCoordinates getter methods
     */
    public void testCoordinatesGetters() {
        Coordinates c01 = new Coordinates(2, 4, "c01");
        assertEquals(2, c01.getxCoordinate());
        assertEquals(4, c01.getyCoordinate());
    }


// Testing coordinate methods
    /**
     * This method aims to test the xyCoordinatesAreInvalid method
     */
    public void testCoordinatesIsValid() {
        Coordinates c02 = new Coordinates(5, 8, "c02");
        assertFalse(c02.isInvalid());

        Coordinates c03 = new Coordinates(-1, 8, "c03");
        assertTrue(c03.isInvalid());

        Coordinates c04 = new Coordinates(1026, 8, "c04");
        assertTrue(c04.isInvalid());

        Coordinates c05 = new Coordinates(1, -8, "c05");
        assertTrue(c05.isInvalid());

        Coordinates c06 = new Coordinates(1, 1030, "c06");
        assertTrue(c06.isInvalid());
    }


    /**
     * This method tests if two Coordinates are equals
     */
    public void testIsEqual() {

        Coordinates c07 = new Coordinates(22, 10, "c07");
        Coordinates c08 = new Coordinates(22, 10, "c08");
        Coordinates c09 = new Coordinates(22, 5, "c09");
        Coordinates c10 = new Coordinates(4, 10, "c10");
        Coordinates c11 = null;
        Coordinates c12 = new Coordinates(4, -10, "c12");
        assertTrue(c07.equals(c08));
        assertFalse(c07.equals(c09));
        assertFalse(c07.equals(c10));
        assertFalse(c07.equals(c11));
        assertFalse(c12.equals(c07));
        assertFalse(c07.equals(c12));
        assertTrue(c07.equals(c07));
    }


    /**
     * This method tests if the coordinates object is printed in human readable
     * file
     */
    public void testtoString() {
        // assertEquals(rec02.toString(), "0, 0, 15, 15");
        Coordinates c13 = new Coordinates(55, 36, "c13");
        assertEquals(c13.toString(), "55, 36");
    }

}
