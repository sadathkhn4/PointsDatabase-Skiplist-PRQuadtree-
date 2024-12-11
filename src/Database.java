import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * Also note that the Database class will have a clearer role in Project2,
 * where we will have two data structures. The Database class will then
 * determine
 * which command should be directed to which data structure.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the Coordinates and then
    // a Coordinates object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Coordinates> list;
    private PRQ tree;

    // This is an Iterator object over the SkipList to loop through it from
    // outside the class.
    // You will need to define an extra Iterator for the intersections method.
    // private Iterator<KVPair<String, Coordinates>> itr1;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Coordinates a its parameters.
     */
    public Database() {
        list = new SkipList<String, Coordinates>();
        tree = new PRQ();
    }


    /**
     * Inserts the KVPair in the SkipList if the Coordinates has valid
     * coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the Coordinates object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Coordinates> pair) {
        // Delegates the decision mostly to SkipList, only
        // writing the correct message to the console from
        // that

        String name = pair.getKey();
        int x = pair.getValue().getxCoordinate();
        int y = pair.getValue().getyCoordinate();

        if (!pair.getValue().isInvalid()) {
            // searching all with same name
            ArrayList<KVPair<String, Coordinates>> searchResult = list.search(
                name);

            int i = 0;
            boolean hasExact = false;

            while (searchResult != null && i < searchResult.size()) {
                Coordinates curr = searchResult.get(i).getValue();
                if (pair.getValue().equals(curr)) {
                    hasExact = true;
                }

                i++;
            }

            if (!(hasExact)) {

                list.insert(pair);
                tree.insert(pair.getValue());
                System.out.println("Point inserted: " + "(" + name + ", " + x
                    + ", " + y + ")");
            }
            else {
                System.out.println("Point rejected: " + "(" + name + ", " + x
                    + ", " + y + ")");
            }
        }
        else {
            System.out.println("Point rejected: " + "(" + name + ", " + x + ", "
                + y + ")");
        }

    }


    /**
     * Removes a Coordinates with the name "name" if available. If not an error
     * message is printed to the console.
     *
     * @param name
     *            the name of the Coordinates to be removed
     */
    public void remove(String name) {
        KVPair<String, Coordinates> removedPair = list.remove(name);

        if (removedPair == null) {
            System.out.println("Point not removed: " + name);
        }
        else {
            tree.remove(removedPair.getValue());
            System.out.println("Point removed: (" + name + ", " + removedPair
                .getValue().toString() + ")");
        }
    }


    /**
     * Removes a Coordinates with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param x
     *            x-coordinate of the Coordinates to be removed
     * @param y
     *            x-coordinate of the Coordinates to be removed
     */
    public void remove(int x, int y) {
        Coordinates rect = new Coordinates(x, y, "dummy");
        if (!rect.isInvalid()) {
            KVPair<String, Coordinates> removedPair = list.removeByValue(rect);

            if (removedPair != null) {
                Coordinates removedCoord = tree.remove(removedPair.getValue());
                String name = removedPair.getKey();
                System.out.print("Point removed: (");
                System.out.println(name + ", " + removedCoord.toString() + ")");
            }
            else {
                System.out.print("Point not found: (");
                System.out.println(rect.toString() + ")");
            }
        }

        else {
            System.out.print("Point rejected: (");
            System.out.println(rect.toString() + ")");
        }
    }


    /**
     * Displays all the Coordinatess inside the specified region. The
     * Coordinates
     * must have some area inside the area that is created by the region,
     * meaning, Coordinatess that only touch a side or corner of the region
     * specified will not be said to be in the region.
     *
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        int x1 = x;
        int y1 = y;
        int w1 = w;
        int h1 = h;

        // ArrayList<Object> regSearchResults = tree.regionsearch(x1, y1, w1,
        // h1);
        if (w1 > 0 && h1 > 0) {
// Rectangle rec = new Rectangle(x1, y1, w1, h1);
            System.out.println("Points intersecting region (" + x1 + ", " + y1
                + ", " + w1 + ", " + h1 + "):");
            ArrayList<String> regSearchResults = tree.regionsearch(x1, y1, w1,
                h1);

            int i = 0;
            while (i < regSearchResults.size()) {

                System.out.println(regSearchResults.get(i));
                i++;
            }

        }
        else {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
        }

        // logic here for region search call
        ///////////////////////////////////

    }

// if (w > 0 && h > 0) {
// System.out.println("Coordinatess intersecting region (" + rec1
// .toString() + "):");
//
// SkipList<String, Coordinates>.SkipNode p = list.getHead();
// int level = list.getLevel();
// int baseLevel = 0;
// while (p != null) { // loop through skiplist
// if (p.getForward()[baseLevel] != null) {
// KVPair<String, Coordinates> currentPair;
// currentPair = p.getForward()[baseLevel].element();
// Coordinates rec2 = currentPair.getValue();
// String name2 = currentPair.getKey();
// int x2 = rec2.getxCoordinate();
// int y2 = rec2.getyCoordinate();

// Logic - decide regionsearch for coordinates
// if (((x2 <= x1) && (x1 < x2 + w2)) || ((x1 <= x2)
// && (x2 < x1 + w1))) { // condition for overlap on x-axis
//
// if (((y2 <= y1) && (y1 < y2 + h2)) || ((y1 <= y2)
// && (y2 < y1 + h1))) { // condition for overlap on
// // y-axis
//
// System.out.println("(" + name2 + ", " + rec2
// .toString() + ")");
//
// }
// }
// }
// p = p.getForward()[baseLevel];
// }
//
// }
// else {
// System.out.println("Coordinates rejected: (" + rec1.toString()
// + ")");
// }
// }


    /**
     * Prints out all the Coordinatess that intersect each other. Note that
     * it is better not to implement an intersections method in the SkipList
     * class
     * as the SkipList needs to be agnostic about the fact that it is storing
     * Coordinatess.
     */
    public void duplicates() {

        System.out.println("Duplicate points:");
        ArrayList<Coordinates> duplicateDump = tree.duplicates();
        int duplicateDumpSize = duplicateDump.size();
        int i = 0;
        while (i < duplicateDumpSize) {
            Coordinates curr = duplicateDump.get(i);
            System.out.println("(" + curr.toString() + ")");
            i++;
        }

    }


    /**
     * Prints out all the Coordinatess with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     *
     * @param name
     *            name of the Coordinates to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Coordinates>> result = list.search(name);
        if (result != null) {
            // System.out.println("Found:");
            for (KVPair<String, Coordinates> pair : result) {
                String key = pair.getKey(); // Get the key (String)
                Coordinates rect = pair.getValue(); // Get the value
                                                    // (Coordinates)
                System.out.println("Found (" + name + ", " + rect.toString()
                    + ")");
            }
        }

        else {
            System.out.println("Point not found: " + name);
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
        ArrayList<String> finalDump = tree.dump();
        int dumpSize = finalDump.size();

        for (int i = 0; i < dumpSize; i++) {
            System.out.println(finalDump.get(i));
        }
    }

}
