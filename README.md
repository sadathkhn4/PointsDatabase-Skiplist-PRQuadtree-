# PointsDatabase: SkipList + PRQuadTree

## Overview
**PointsDatabase** is a spatial database implementation that organizes points using two data structures:
- **Skip List**: Efficiently organizes points by name for fast retrieval.
- **PRQuadTree**: Efficiently organizes points by position for spatial queries.

This dual-structure approach provides optimized operations for both non-spatial and spatial tasks.

---

## Features

### 1. Data Structures
- **Skip List**:
  - Organizes points by name.
  - Supports efficient insertion, deletion, and search operations.
- **PRQuadTree**:
  - Organizes points spatially by position.
  - Supports efficient spatial queries like region search and duplicate detection.

### 2. Operations
- **Insertion**:
  - Add a point to both the Skip List (by name) and the PRQuadTree (by position).
- **Deletion**:
  - Remove a point from both the Skip List and PRQuadTree.
- **Queries**:
  - Find points by name.
  - Perform region search to find points within a given rectangle.
  - Detect duplicate points within the database.

---

## PRQuadTree Variant
This implementation uses a variant of the PRQuadTree with the following characteristics:
- **Node Types**:
  - **Empty Node**: Represents a node with no data.
  - **Leaf Node**: Contains one or more points.
  - **Internal Node**: Splits the parent region into four quadrants but does not store data.
- **Splitting and Merging Rules**:
  - Leaf nodes split into internal nodes when capacity is exceeded.
  - Internal nodes merge back into leaf nodes when conditions allow.

---

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or later.
- Basic understanding of spatial databases and data structures.

### Installation
1. Clone the repository:
   ```bash
   git clone git@github.com:your-username/PointsDatabase-Skiplist-PRQuadtree-.git
   ```
2. Navigate to the project directory:
   ```bash
   cd PointsDatabase-Skiplist-PRQuadtree-
   ```
3. Compile the source code:
   ```bash
   javac -d bin src/**/*.java
   ```
4. Run the program:
   ```bash
   java -cp bin Main
   ```

---

## Usage

### Adding a Point
- Adds a point to the Skip List (by name) and the PRQuadTree (by position).

### Deleting a Point
- Removes a point from both the Skip List and PRQuadTree.

### Querying
- **Find by Name**:
  - Retrieve a point using its name from the Skip List.
- **Region Search**:
  - Find all points within a specified rectangle using the PRQuadTree.
- **Duplicate Detection**:
  - Identify duplicate points within the PRQuadTree.

---

## Project Structure
```
PointsDatabase/
├── src/
│   ├── SkipList.java
│   ├── PRQuadTree.java
│   ├── Point.java
│   ├── Main.java
│   └── ... (other supporting files)
├── bin/
├── README.md
└── LICENSE
```

---

## Future Enhancements
- Add support for additional spatial queries.
- Optimize splitting and merging rules for the PRQuadTree.
- Visualize the PRQuadTree structure for better debugging and analysis.

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgments
- **OpenDSA Modules**: Concepts of PRQuadTree and spatial data structures.
- Skip List implementation based on textbook algorithms.

