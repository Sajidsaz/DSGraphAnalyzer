# Data Structure & Graph Performance Analyzer
 
CIT300 Data Structures and Algorithms - Graded Practical Assignment 2
SLTC Research University
 
A Java console application that demonstrates arrays, stacks, queues,
linked lists, searching, graphs (BFS and DFS) and algorithm performance
comparison.

## Group No - 47
## Team Members
 
### Member 1
- Student Name: MRM Ilham
- Student ID: 23DA2-1000
- Assigned Responsibility: Array and Searching
- Individual Contribution:
  - *Individual Contribution:*
  - Implemented the ArrayOps class (insert at end/position, delete, display)
  - Implemented insertion sort and the sorted-data check
  - Implemented SearchResult, linear search and binary search with step counting
  - Built the Array and Searching submenus with input validation
  - Added the linear vs binary comparison on large data
  - Recorded results in ResultLog and tested all array and search cases
  - Reviewed and merged Member 2's pull request


 
### Member 2
- Student Name: MF Hasan
- Student ID: 23DA2-1001
- Assigned Responsibility: Stack and Queue
- Individual Contribution:
  - Implemented MyStack (array-based, push, pop, peek, display)
  - Implemented MyQueue (circular array, enqueue, dequeue, peek, display)
  - Added automatic array growth and empty-structure (underflow) handling
  - Built the Stack and Queue submenus with input validation
  - Tested empty, wrap-around and growth cases
  - Reviewed and merged Member 1's pull request

 
### Member 3
- Student Name: S. Sajidh Ahamad
- Student ID: 23DA2-0840
- Assigned Responsibility: Linked List, Main Menu and Integration
- **Individual Contribution:**
  - Created the GitHub repository, .gitignore, README structure and project layout
  - Implemented InputUtil (shared input validation) and ResultLog (shared results store)
  - Implemented Node and MyLinkedList (insert at head/tail/position, delete, search, display)
  - Built the Linked List submenu with step counting and empty-list handling
  - Built the Main menu and integrated all components into one application
  - Wrote the README sections and TESTING.md, and tested the full system
  - Reviewed and merged Member 4's pull request

 
### Member 4
- Student Name: AM Naashir
- Student ID: 23DA2-0650
- Assigned Responsibility: Graph and Performance Comparison
- *Individual Contribution:*
  - Implemented the Graph class (adjacency list, add vertex, add edge, display)
  - Implemented BFS using MyQueue and DFS using MyStack with step counting
  - Implemented TraversalResult and the sample graph
  - Built the Graph submenu with input validation
  - Implemented PerformanceAnalyzer (searching and graph traversal comparison)
  - Tested graph and performance functionality
  - Reviewed and merged Member 3's pull request

## Technologies Used
- Java 17 or newer (console application, no external libraries)
- Git and GitHub (branches, commits, pull requests)
 
## Main System Features
- Array: insert, delete, search, display, insertion sort
- Stack: push, pop, peek, display (array based, LIFO)
- Queue: enqueue, dequeue, peek, display (circular array, FIFO)
- Linked List: insert (head, tail, position), delete, search, display
- Searching: linear search and binary search with step and time comparison
- Graph: add vertex, add edge, display, BFS and DFS (adjacency list)
- Performance Comparison: steps and execution time for searching and graph traversal
- Display All Results: history of every recorded operation
- Input validation and empty-structure handling in every menu
 
## How to Run
1. Install a JDK (Java 17 or newer). Check with: java -version
2. Clone the repository:
   git clone https://github.com/Sajidsaz/DSGraphAnalyzer.git
3. Go into the folder:
   cd DSGraphAnalyzer
4. Compile:
   javac -d bin src/*.java
5. Run:
   java -cp bin Main
 
## Performance Summary
| Operation | Algorithm | Complexity |
|---|---|---|
| Array / linked list search | Linear search | O(n) |
| Sorted array search | Binary search | O(log n) |
| Graph traversal | BFS (queue) | O(V + E) |
| Graph traversal | DFS (stack) | O(V + E) |
| Stack push/pop, queue enqueue/dequeue | - | O(1) |
