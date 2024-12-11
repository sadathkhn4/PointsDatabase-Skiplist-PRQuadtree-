import java.util.ArrayList;

/**
 * Class to implement PR-QuadTree
 * 
 * @author Sadath-Mohammed-msadath
 * @author EmadEldin-Abdrabou-emazied
 * 
 * @version 2024-03-12
 * 
 */

public class PRQ {

    private int treeSize;
    private QNode rt;
    private QNode emptyWeight;
// private int area;
    private int nodesCovered2 = 0;

    /**
     * Getter method to get number of nodes traversed
     */
    public void resetNodesCovered2() {
        nodesCovered2 = 0;
    }


    /**
     * Constructor method for PR Quad Tree
     */
    public PRQ() {
        treeSize = 0;
        emptyWeight = new PRQEmptyNode();
        rt = emptyWeight;
    }


    /**
     * Insert method on Tree
     * 
     * @param coord
     *            point to be inserted
     */
    public void insert(Coordinates coord) {
        treeSize++;
        rt = rt.insert(coord, 0, 0, 1024);

    }


    /**
     * Remove method on Tree
     * 
     * @param coord
     *            coordinates of the point to be removed
     * @return the point that was removed
     */
    public Coordinates remove(Coordinates coord) {
        ArrayList<Coordinates> discarded = new ArrayList<>();
        rt = rt.remove(coord, 0, 0, 1024, discarded);
        if (discarded.size() == 0) {
            return null;
        }
        else {
            treeSize--; // check tree size logic
            return discarded.get(0);
        }
    }


    /**
     * Region search method on Quad Tree
     * 
     * @param x
     *            integer value of x coordinate of region top left corner point
     *            to be inserted
     * 
     * @param y
     *            integer value of y coordinate of region top left corner point
     *            to be inserted
     * 
     * @param w
     *            integer value of region search width
     * 
     * @param h
     *            integer value of region search height
     * 
     * @return regSearchResults list of objects
     */
    public ArrayList<String> regionsearch(int x, int y, int w, int h) {

        ArrayList<Coordinates> foundPoints = new ArrayList<>();
        // to reset the nodesCovered2 parameter and restart the counter when
        // this method called
        resetNodesCovered2();
        // Call region search
        rt.regionSearch(x, y, w, h, 0, 0, 1024, nodesCovered2, foundPoints);

        ArrayList<String> regSearchResults = new ArrayList<>();

        int i = 0;
        while (i < foundPoints.size()) {
            Coordinates inPoint = foundPoints.get(i);
            regSearchResults.add("Point found: (" + inPoint.getName() + ", "
                + inPoint.toString() + ")");
            i++;
        }
        regSearchResults.add(nodesCovered2 + " quadtree nodes visited");

        return regSearchResults;
    }


    /**
     * Dump method of the Tree
     * 
     * @return the list containing the dump text corresponding to each node
     */
    public ArrayList<String> dump() {

        ArrayList<String> tempDump = new ArrayList<>();
        resetNodesCovered2();

        tempDump.add("QuadTree dump:");

        if (treeSize != 0) {
            rt.createDump(0, 0, 1024, 0, nodesCovered2, tempDump);
            tempDump.add(nodesCovered2 + " quadtree nodes printed");
        }
        else {
            tempDump.add("Node at 0, 0, 1024: Empty");
            tempDump.add("1 quadtree nodes printed");
        }

        return tempDump;
    }


    /**
     * Method to get duplicates in a Tree
     * 
     * @return list containing the coordinates of the duplicate points
     */
    public ArrayList<Coordinates> duplicates() {

        ArrayList<Coordinates> duplicateList = new ArrayList<>();
        rt.getDuplicates(0, 0, 1024, duplicateList);

        return duplicateList;
    }

    // NODES
    // Start of PR Quad Tree Node TYPES

    /**
     * This class implemts the Empty Node in a QuadTree
     */
    public class PRQEmptyNode implements QNode {

        /**
         * This method returns true if the current node is a leaf node. False
         * otherwise
         * 
         * @return boolean value
         */
        public boolean isLeaf() {
            return false;
        }


        /**
         * Constructor of the Empty node
         */
        public PRQEmptyNode() {
            // do nothing
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param coord
         *            the point to be inserted
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @return node
         */
        public QNode insert(Coordinates coord, int boxX, int boxY, int area) {
            PRQLeafNode leaf = new PRQLeafNode();
            leaf.insert(coord, boxX, boxY, area);
            return leaf;
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param coord
         *            Point to be removed
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param discarded
         *            list containing the discarded contents
         * @return the current node
         */
        public QNode remove(
            Coordinates coord,
            int boxX,
            int boxY,
            int area,
            ArrayList<Coordinates> discarded) {
            // no action to do. return self
            return this;
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param depth
         *            depth of the current node in the tree
         * @param nodesCovered
         *            Number of nodes traversed
         * @param tempDump
         *            list containing the temporary dump content of the point
         * @return tempDump
         */
        public ArrayList<String> createDump(
            int boxX,
            int boxY,
            int area,
            int depth,
            int nodesCovered,
            ArrayList<String> tempDump) {
            nodesCovered2++; // Since this node will be covered now
            String cache = new String("");
            int i = 0;
            while (i < depth) {
                cache = cache + "  ";
                i++;
            }
            cache = cache + "Node at " + boxX + ", " + boxY + ", " + area
                + ": Empty";
            tempDump.add(cache);
            return tempDump;
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param duplicateList
         *            list containing the duplicate point
         * @return duplicate list
         */
        public ArrayList<Coordinates> getDuplicates(
            int boxX,
            int boxY,
            int area,
            ArrayList<Coordinates> duplicateList) {

            return duplicateList;
        }


        /**
         * Method to get Intersects from a QuadNode in a QuadTree with given
         * region search
         * 
         * @param regSearchX
         *            integer value of x coordinate of region top left corner
         *            point
         *            to be inserted
         * 
         * @param regSearchY
         *            integer value of y coordinate of region top left corner
         *            point
         *            to be inserted
         * 
         * @param regSearchW
         *            integer value of region search width
         * 
         * @param regSearchH
         *            integer value of region search height
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * 
         * @param nodesVisited
         *            integer value that repesents number of nodes visited
         * @param searchOutput
         *            list containing the found point
         * @return searchOutput
         */
        public ArrayList<Coordinates> regionSearch(
            int regSearchX,
            int regSearchY,
            int regSearchW,
            int regSearchH,
            int boxX,
            int boxY,
            int area,
            int nodesVisited,
            ArrayList<Coordinates> searchOutput) {
            // Do nothing ... nothing found in the empty node ... don't visit it
            nodesCovered2++;
            return searchOutput;
        }
    }


    /**
     * Class to implement the Leaf Node of a QuadTree
     */
    public class PRQLeafNode implements QNode {

        /**
         * This method returns true if the current node is a leaf node. False
         * otherwise
         * 
         * @return boolean
         */
        public boolean isLeaf() {
            return true;
        }

        private ArrayList<Coordinates> leafNodeContents;

        /**
         * Initiates the list to store the points in a given leaf node
         */
        public PRQLeafNode() {
            leafNodeContents = new ArrayList<>();
        }


        /**
         * Method to construct a leaf node with given points in it
         * 
         * @param givenleafNodeContents
         *            the list containing the points to be in a new leafnode
         * 
         */
        public PRQLeafNode(ArrayList<Coordinates> givenleafNodeContents) {
            leafNodeContents = givenleafNodeContents;
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param coord
         *            the point to be inserted
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @return node
         */
        public QNode insert(Coordinates coord, int boxX, int boxY, int area) {

            if (leafNodeContents.size() < 3) {
                leafNodeContents.add(coord);
                return this;
            }
            else {
                boolean repeat = true;
                int leafLen = leafNodeContents.size();
                int i = 0;
                while (i < leafLen - 1) {
                    Coordinates c01 = leafNodeContents.get(i);
                    Coordinates c02 = leafNodeContents.get(i + 1);
                    if (!(c01.equals(c02))) {
                        repeat = false;
                        break;
                    }
                    i++;
                }
                if (repeat) {
                    if (coord.equals(leafNodeContents.get(0))) {
                        leafNodeContents.add(coord);
                        return this;
                    }
                }

                // else splitting logic
                QNode internal = new PRQInternalNode();
                // loop to add all existing leaf coords first
                i = 0;
                while (i < leafLen) {
                    Coordinates currCoord = this.leafNodeContents.get(i);
                    internal.insert(currCoord, boxX, boxY, area);
                    i++;
                }
                internal.insert(coord, boxX, boxY, area);
                return internal;

            }
            // PRQLeafNode leaf = new PRQLeafNode();
            // leaf.insert(coord, boxX, boxY, area);
            // return leaf;
        }


        /**
         * Method to get remove from a QuadNode in a QuadTree
         * 
         * @param coord
         *            Point to be removed
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param discarded
         *            list containing the discarded contents
         * @return the current node
         */
        public QNode remove(
            Coordinates coord,
            int boxX,
            int boxY,
            int area,
            ArrayList<Coordinates> discarded) {
            int leafLen = this.leafNodeContents.size();
            int i = 0;
            while (i < leafLen) {
                Coordinates currCoord = this.leafNodeContents.get(i);
                if (coord.equals(currCoord) && coord.getName() == currCoord
                    .getName()) {
                    this.leafNodeContents.remove(i);
                    discarded.add(currCoord); // discarded[0]=currCoord
                    break;
                }
                i++;
            }
            if (this.leafNodeContents.size() != 0) {
                return this;
            }
            else {
                return emptyWeight;
            }

        }


        /**
         * Method to get createDump from a QuadNode in a QuadTree
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param depth
         *            depth of the current node in the tree
         * @param nodesCovered
         *            Number of nodes traversed
         * @param tempDump
         *            list containing the temporary dump content of the point
         * @return tempDump
         */
        public ArrayList<String> createDump(
            int boxX,
            int boxY,
            int area,
            int depth,
            int nodesCovered,
            ArrayList<String> tempDump) {

            nodesCovered2++; // Since this node will be covered now

            String indentCache = new String(""); // to store common indentation
                                                 // within leafNode
            int i = 0;
            while (i < depth) {
                indentCache = indentCache + "  ";
                i++;
            }
            String cache = new String("");
            cache = indentCache + "Node at " + boxX + ", " + boxY + ", " + area
                + ":";
            tempDump.add(cache);

            int j = 0;
            int leafLen = this.leafNodeContents.size();
            while (j < leafLen) {
                cache = "";
                String coordValueString = this.leafNodeContents.get(j)
                    .toString();
                String coordName = this.leafNodeContents.get(j).getName();
                cache = indentCache + "(" + coordName + ", " + coordValueString
                    + ")";
                tempDump.add(cache);
                j++;
            }

            return tempDump;
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param duplicateList
         *            list containing the duplicate point
         * @return duplicate list
         */
        public ArrayList<Coordinates> getDuplicates(
            int boxX,
            int boxY,
            int area,
            ArrayList<Coordinates> duplicateList) {

            int leafLen = this.leafNodeContents.size();
            if (leafLen <= 3) {
                int i = 0;
                while (i < leafLen) {
                    int j = i + 1;
                    while (j < leafLen) {
                        Coordinates c01 = this.leafNodeContents.get(i);
                        Coordinates c02 = this.leafNodeContents.get(j);

                        // Since there cannot be 2 distinct coordinates that are
                        // repeating within a leafNode
                        if (c01.equals(c02)) {
                            duplicateList.add(c01);
                            break;
                        }
                        j++;
                    }
                    i++;
                }
            }

            else { // (if leafLen>3) -> all are same points
                duplicateList.add(this.leafNodeContents.get(0));
            }
            return null;
        }


        /**
         * Method to get Intersects from a QuadNode in a QuadTree with given
         * region search
         * 
         * @param regSearchX
         *            integer value of x coordinate of region top left corner
         *            point
         *            to be inserted
         * 
         * @param regSearchY
         *            integer value of y coordinate of region top left corner
         *            point
         *            to be inserted
         * 
         * @param regSearchW
         *            integer value of region search width
         * 
         * @param regSearchH
         *            integer value of region search height
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * 
         * @param nodesVisited
         *            integer value that repesents number of nodes visited
         * @param searchOutput
         *            list containing the found point
         * @return searchOutput
         */
        public ArrayList<Coordinates> regionSearch(
            int regSearchX,
            int regSearchY,
            int regSearchW,
            int regSearchH,
            int boxX,
            int boxY,
            int area,
            int nodesVisited,
            ArrayList<Coordinates> searchOutput) {

            nodesCovered2++; // Since this node will be visited now

            int leafLen = this.leafNodeContents.size();

            int minRegX = regSearchX;
            int maxRegX = regSearchX + regSearchW;
            int minRegY = regSearchY;
            int maxRegY = regSearchY + regSearchH;

            int i = 0;
            while (i < leafLen) {
                Coordinates currPoint = this.leafNodeContents.get(i);
                int currX = currPoint.getxCoordinate();
                int currY = currPoint.getxCoordinate();

                if (minRegX <= currX && currX < maxRegX) { // xcoord overlaps
                                                           // with region
                    if (minRegY <= currY && currY < maxRegY) { // ycoord
                                                               // overlaps
                                                               // with region
                        searchOutput.add(currPoint);
                    }
                }
                i++;
            }
            return searchOutput;
        }
    }


    /**
     * Class that implements QNode for Internal Node functionality
     */
    public class PRQInternalNode implements QNode {

        private QNode nw;
        private QNode ne;
        private QNode sw;
        private QNode se;

        /**
         * This method returns true if the current node is a leaf node. False
         * otherwise
         * 
         * @return boolean
         */
        public boolean isLeaf() {
            return false;
        }


        /**
         * Constructor for Internal Node
         */
        public PRQInternalNode() {
            nw = (QNode)emptyWeight;
            ne = (QNode)emptyWeight;
            sw = (QNode)emptyWeight;
            se = (QNode)emptyWeight;
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param coord
         *            the point to be inserted
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @return node
         */
        public QNode insert(Coordinates coord, int boxX, int boxY, int area) {
            int x = coord.getxCoordinate();
            int y = coord.getyCoordinate();
            int halfArea = area / 2;
            int newboxX = boxX + halfArea;
            int newboxY = boxY + halfArea;

            if (x < newboxX && y < newboxY) {
                nw = nw.insert(coord, boxX, boxY, halfArea);
                return this;
            }
            else if (x >= newboxX && y < newboxY) {
                ne = ne.insert(coord, newboxX, boxY, halfArea);
                return this;
            }
            else if (x < newboxX && y >= newboxY) {
                sw = sw.insert(coord, boxX, newboxY, halfArea);
                return this;
            }
            else {
                se = se.insert(coord, newboxX, newboxY, halfArea);
                return this;
            }

        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param coord
         *            Point to be removed
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param discarded
         *            list containing the discarded contents
         * @return the current node
         */
        public QNode remove(
            Coordinates coord,
            int boxX,
            int boxY,
            int area,
            ArrayList<Coordinates> discarded) {

            int x = coord.getxCoordinate();
            int y = coord.getyCoordinate();
            int halfArea = area / 2;
            int newboxX = boxX + halfArea;
            int newboxY = boxY + halfArea;

            if (x < newboxX && y < newboxY) {
                nw = nw.remove(coord, boxX, boxY, halfArea, discarded);
                // return this;
            }
            else if (x >= newboxX && y < newboxY) {
                ne = ne.remove(coord, newboxX, boxY, halfArea, discarded);
                // return this;
            }
            else if (x < newboxX && y >= newboxY) {
                sw = sw.remove(coord, boxX, newboxY, halfArea, discarded);
                // return this;
            }
            else {
                se = se.remove(coord, newboxX, newboxY, halfArea, discarded);
                // return this;
            }

            return merge();
        }


        // verification needed
        /////////////////////
        /**
         * Merge method on Quad Tree
         * 
         * @return Quad node after removal
         */
        private QNode merge() {
            boolean containsInternal = false;
            if (!(nw.isLeaf()) && !(nw == emptyWeight)) {
                containsInternal = true;
            }
            if (!(ne.isLeaf()) && !(ne == emptyWeight)) {
                containsInternal = true;
            }
            if (!(se.isLeaf()) && !(se == emptyWeight)) {
                containsInternal = true;
            }
            if (!(sw.isLeaf()) && !(sw == emptyWeight)) {
                containsInternal = true;
            }
            if (containsInternal) {
                return this;
            }
            else if (nw.isLeaf() && ne == emptyWeight && sw == emptyWeight
                && se == emptyWeight) {
                return nw;
            }
            else if (nw == emptyWeight && ne.isLeaf() && sw == emptyWeight
                && se == emptyWeight) {
                return ne;
            }
            else if (nw == emptyWeight && ne == emptyWeight && sw.isLeaf()
                && se == emptyWeight) {
                return sw;
            }
            else if (nw == emptyWeight && ne == emptyWeight && sw == emptyWeight
                && se.isLeaf()) {
                return se;
            }

            else {
                ArrayList<Coordinates> results = new ArrayList<>();
                collectLeafNodePoints(nw, results);

                collectLeafNodePoints(ne, results);

                collectLeafNodePoints(sw, results);

                collectLeafNodePoints(se, results);

                if (results.size() <= 3) {
                    return new PRQLeafNode(results);
                }

                // Else, returning current node (no merge required)

                return this;
            }

        }


        // Helper method to collect points from a leaf node
        /**
         * Helper method to collect points from a leaf node
         * 
         * @param node
         *            child nodes of current node
         * @param results
         */
        private void collectLeafNodePoints(
            QNode node,
            ArrayList<Coordinates> results) {
            if (node.isLeaf()) {
                PRQLeafNode leafNode = (PRQLeafNode)node;
                for (int i = 0; i < leafNode.leafNodeContents.size(); i++) {
                    results.add(leafNode.leafNodeContents.get(i));
                }
            }
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param depth
         *            depth of the current node in the tree
         * @param nodesCovered
         *            Number of nodes traversed
         * @param tempDump
         *            list containing the temporary dump content of the point
         * @return tempDump
         */
        public ArrayList<String> createDump(
            int boxX,
            int boxY,
            int area,
            int depth,
            int nodesCovered,
            ArrayList<String> tempDump) {

            int halfArea = area / 2;

            String indentCache = new String("");
            String cache = new String("");

            int i = 0;
            while (i < depth) {
                indentCache = indentCache + "  ";
                i++;
            }

            cache = indentCache + "Node at " + boxX + ", " + boxY + ", " + area
                + ": Internal";
            tempDump.add(cache);
            nw.createDump(boxX, boxY, halfArea, depth + 1, nodesCovered2,
                tempDump);
            ne.createDump(boxX + halfArea, boxY, halfArea, depth + 1,
                nodesCovered2, tempDump);
            sw.createDump(boxX, boxY + halfArea, halfArea, depth + 1,
                nodesCovered2, tempDump);
            se.createDump(boxX + halfArea, boxY + halfArea, halfArea, depth + 1,
                nodesCovered2, tempDump);
            nodesCovered2++; // Since this node will be covered now

            return tempDump;
        }


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param duplicateList
         *            list containing the duplicate point
         * @return duplicate list
         */

        public ArrayList<Coordinates> getDuplicates(
            int boxX,
            int boxY,
            int area,
            ArrayList<Coordinates> duplicateList) {

            int halfArea = area / 2;
            nw.getDuplicates(boxX, boxY, halfArea, duplicateList);
            ne.getDuplicates(boxX + halfArea, boxY, halfArea, duplicateList);
            sw.getDuplicates(boxX, boxY + halfArea, halfArea, duplicateList);
            se.getDuplicates(boxX + halfArea, boxY + halfArea, halfArea,
                duplicateList);

            return null;
        }


        /**
         * Method to get Intersects from a QuadNode in a QuadTree with given
         * region search
         * 
         * @param regSearchX
         *            integer value of x coordinate of region top left corner
         *            point
         *            to be inserted
         * 
         * @param regSearchY
         *            integer value of y coordinate of region top left corner
         *            point
         *            to be inserted
         * 
         * @param regSearchW
         *            integer value of region search width
         * 
         * @param regSearchH
         *            integer value of region search height
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * 
         * @param nodesVisited
         *            integer value that repesents number of nodes visited
         * @param searchOutput
         *            list containing the found point
         * @return searchOutput
         */
        public ArrayList<Coordinates> regionSearch(
            int regSearchX,
            int regSearchY,
            int regSearchW,
            int regSearchH,
            int boxX,
            int boxY,
            int area,
            int nodesVisited,
            ArrayList<Coordinates> searchOutput) {

            nodesCovered2++; // if this internal node visited, we need to update
                             // the
            // number of visited nodes

            int halfArea = area / 2;

            int boxX2 = boxX + halfArea;
            int boxY2 = boxY + halfArea;

            if (overlaps(boxX, boxY, halfArea, halfArea, regSearchX, regSearchY,
                regSearchW, regSearchH)) {
                nw.regionSearch(regSearchX, regSearchY, regSearchW, regSearchH,
                    boxX, boxY, halfArea, nodesCovered2, searchOutput);
            }

            if (overlaps(boxX2, boxY, halfArea, halfArea, regSearchX,
                regSearchY, regSearchW, regSearchH)) {
                ne.regionSearch(regSearchX, regSearchY, regSearchW, regSearchH,
                    boxX2, boxY, halfArea, nodesCovered2, searchOutput);
            }

            if (overlaps(boxX, boxY2, halfArea, halfArea, regSearchX,
                regSearchY, regSearchW, regSearchH)) {
                sw.regionSearch(regSearchX, regSearchY, regSearchW, regSearchH,
                    boxX, boxY2, halfArea, nodesCovered2, searchOutput);
            }

            if (overlaps(boxX2, boxY2, halfArea, halfArea, regSearchX,
                regSearchY, regSearchW, regSearchH)) {
                se.regionSearch(regSearchX, regSearchY, regSearchW, regSearchH,
                    boxX2, boxY2, halfArea, nodesCovered2, searchOutput);
            }

            return searchOutput;
        }


        /**
         * Creates overlap pairs of Coordinatess
         *
         * @param x1
         *            x-Coordinate of the Coordinates-1
         * @param y1
         *            y-Coordinate of the Coordinates-1
         * @param w1
         *            width of the Coordinates-1
         * @param h1
         *            height of the Coordinates-1
         * @param x2
         *            x-Coordinate of the Coordinates-2
         * @param y2
         *            y-Coordinate of the Coordinates-2
         * @param w2
         *            width of the Coordinates-2
         * @param h2
         *            height of the Coordinates-2
         * 
         * @return boolean
         */
        public boolean overlaps(
            int x1,
            int y1,
            int w1,
            int h1,
            int x2,
            int y2,
            int w2,
            int h2) {

            if (((x2 < x1) && (x1 <= x2 + w2)) || ((x1 < x2) && (x2 <= x1
                + w1))) { // condition for overlap on x-axis

                if (((y2 < y1) && (y1 <= y2 + h2)) || ((y1 < y2) && (y2 <= y1
                    + h1))) { // condition for overlap on y-axis

                    return true;

                }
            }

            return false;

        }

    }


    /**
     * Interface for the Quad Node methods
     */
    public interface QNode {
        /**
         * This method returns true if the current node is a leaf node. False
         * otherwise
         * 
         * @return boolean
         */
        public boolean isLeaf();


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param coord
         *            the point to be inserted
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @return node
         */
        public abstract QNode insert(
            Coordinates coord,
            int boxX,
            int boxY,
            int area);


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param coord
         *            Point to be removed
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param discarded
         *            list containing the discarded contents
         * @return the current node
         */
        public abstract QNode remove(
            Coordinates coord,
            int boxX,
            int boxY,
            int area,
            ArrayList<Coordinates> discarded);


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param depth
         *            depth of the current node in the tree
         * 
         * @param nodesCovered
         *            Number of nodes traversed
         * @param tempDump
         *            list containing the temporary dump content of the point
         * @return tempDump
         */
        public abstract ArrayList<String> createDump(
            int boxX,
            int boxY,
            int area,
            int depth,
            int nodesCovered,
            ArrayList<String> tempDump);


        /**
         * Method to get Duplicates from a QuadNode in a QuadTree
         * 
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param duplicateList
         *            list containing the duplicate point
         * @return duplicate list
         */
        public abstract ArrayList<Coordinates> getDuplicates(
            int boxX,
            int boxY,
            int area,
            ArrayList<Coordinates> duplicateList);


        /**
         * Method to get Intersects from a QuadNode in a QuadTree with given
         * region search
         * 
         * @param regSearchX
         *            Top left corner's x-coordinate of current space
         * @param regSearchY
         *            Top left corner's y-coordinate of current space
         * @param regSearchW
         *            size of the box of current space
         * @param regSearchH
         *            list containing the found point
         * @param boxX
         *            Top left corner's x-coordinate of current space
         * @param boxY
         *            Top left corner's y-coordinate of current space
         * @param area
         *            size of the box of current space
         * @param nodesVisited
         *            number of nodes visited
         * @param searchOutput
         *            list containing the found point
         * @return search output
         * 
         */
        public abstract ArrayList<Coordinates> regionSearch(
            int regSearchX,
            int regSearchY,
            int regSearchW,
            int regSearchH,
            int boxX,
            int boxY,
            int area,
            int nodesVisited,
            ArrayList<Coordinates> searchOutput);

    }

}
