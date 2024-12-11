/**
 * This class holds the coordinates and dimensions of a point and methods to
 * check if it intersects or has the same coordinates as an other point.
 * 
 * @author Sadath-Mohammed-msadath
 * @author EmadEldin-Abdrabou
 * 
 * @version 2024-03-13
 */
public class Coordinates {
    // the x coordinate of the point
    private int xCoordinate;
    // the y coordinate of the point
    private int yCoordinate;
    // point name
    private String name;

    /**
     * Creates an Coordinates object with the values to the parameters given in
     * the
     * xCoordinate, yCoordinate
     * 
     * @param x
     *            x-coordinate of the point
     * @param y
     *            y-coordinate of the point
     * @param pointName
     *            name of the point
     */

    // Constructor
    public Coordinates(int x, int y, String pointName) {
        xCoordinate = x;
        yCoordinate = y;
        name = pointName;
    }


    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }


    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }


    /**
     * Getter for the name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Checks, if the invoking coordinates has the same coordinates as given
     * obj.
     * 
     * @param obj
     *            the coordinates parameter
     * @return true if the coordinates has the same coordinates as given obj,
     *         false if
     *         not
     */
    public boolean equals(Object obj) {
        boolean result = false;
        // If the other object is null
        // they are not equal
        if (obj == null)
            return false;

        // if (this.isInvalid())
        // return false;

        // casting the obj parameter to check the equality
        Coordinates otherCoord = (Coordinates)obj;

        // if (otherCoord.isInvalid())
        // return false;

        // If the objects are the same instance, they are equal
        if (this == otherCoord)
            return true;

        if (otherCoord.getxCoordinate() == this.getxCoordinate()) {
            if (otherCoord.getyCoordinate() == this.getyCoordinate()) {
                result = true;
            }
        }

        return result;
    }


    /**
     * Outputs a human readable string with information about the Coordinates
     * which includes the x and y coordinate
     * 
     * @return a human readable string containing information about the
     *         coordinates
     *         coordinates
     */
    public String toString() {
        /// This method handle a valid coordinates object and print out the
        /// object in
        /// human readable format
        // return coordinates info;
        return xCoordinate + ", " + yCoordinate;
    }


    /**
     * Checks if the coordinates has invalid parameters
     * This function assumes that the parameters that passed to the coordinates
     * object are integers
     * correct data type and this need to be asserted in the insert command
     * 
     * A coordinates would have invalid parameters if:
     * (i) it does not fit within the “world box” that is 1024 by 1024
     * units in size and has upper left corner at (0, 0).
     * 
     * @return true if the point has invalid parameters, false if not.
     */
    public boolean isInvalid() {
        return this.getxCoordinate() < 0 || this.getyCoordinate() < 0 || this
            .getxCoordinate() > 1024 || this.getyCoordinate() > 1024;
    }

    // /**
    // * Checks if this coordinates object contains another coordinates
    // coordinates r2
    // *
    // * @param r2
    // * the coordinates parameter r2 to be tested against this coordinates
    // * object
    // * @return true if the coordinates contains r2, false if not
    // */
    //
    // public boolean contains(Object r2) {
    // int x1 = this.getxCoordinate();
    // int y1 = this.getyCoordinate();
    //// int width1 = this.getWidth();
    //// int height1 = this.getHeight();
    //
    //// int x2 = r2.getxCoordinate();
    //// int y2 = r2.getyCoordinate();
    //// int width2 = r2.getWidth();
    //// int height2 = r2.getHeight();
    //
    // // if (r2.isInvalid() || this.isInvalid()) {
    // // // System.out.println("It seems that we have invalid rects.");
    // // return false;
    // // }
    //
    // // Check if r2 is completely inside this coordinates
    //// return x2 >= x1 && y2 >= y1 && x2 + width2 <= x1 + width1 && y2
    //// + height2 <= y1 + height1;
    // return false;
    // }

    // /**
    // * Checks if the invoking coordinates intersects with rec.
    // * It assumes correct entries for coordinatess x-y coordinates
    // * and its dimensions, i.e., x, y, w, h are >= 0
    // *
    // * @param r2
    // * coordinates parameter
    // * @return true if the coordinates intersects with rec, false if not
    // */
    // public boolean intersect(Object r2) { // object would be replaced by
    // // the coordinates class name
    // // First check if the given of this object are valid coordinatess with
    // // valid coordinates and dimension
    // // if (r2.isInvalid() || this.isInvalid()) {
    // // return false;
    // // }
    //
    // if (this.contains(r2)) {
    // return true;
    // }
    //
    // // Check for intersection along the edges of both coordinatess
    //// boolean horizontalOverlap = (this.getxCoordinate() <
    // r2.getxCoordinate()
    //// + r2.getWidth()) && (this.getxCoordinate() + this.getWidth() > r2
    //// .getxCoordinate());
    ////
    //// boolean verticalOverlap = (this.getyCoordinate() < r2.getyCoordinate()
    //// + r2.getHeight()) && (this.getyCoordinate() + this.getHeight() > r2
    //// .getyCoordinate());
    ////
    //// return horizontalOverlap && verticalOverlap;
    // return false;
    // }
}
