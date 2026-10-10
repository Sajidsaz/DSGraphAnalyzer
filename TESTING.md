# Manual Test Results
 
| No. | Area | Test | Expected | Result |
|---|---|---|---|---|
| 1 | Main menu | Type abc | Invalid input message, menu repeats | Pass |
| 2 | Main menu | Type 12 | Invalid choice message | Pass |
| 3 | Array | Insert, delete, search, sort | Correct values and step counts | Pass |
| 4 | Array | Insert when full / delete when empty | Clear message, no crash | Pass |
| 5 | Stack | Pop and peek on empty stack | Stack Underflow message | Pass |
| 6 | Queue | Dequeue on empty queue | Queue Underflow message | Pass |
| 7 | Queue | Wrap-around after dequeues | Correct order shown | Pass |
| 8 | Linked list | Delete head, middle, missing value | Correct list, message if missing | Pass |
| 9 | Searching | Binary search on unsorted array | Warning and option to sort | Pass |
| 10 | Searching | n = 1,000,000 | Linear 1,000,000 steps, binary 20 | Pass |
| 11 | Graph | Add edge with missing vertex | Error message, no crash | Pass |
| 12 | Graph | BFS and DFS on sample graph | Different visit orders, all 8 cities | Pass |
| 13 | Performance | Run both comparisons | Two tables, step counts as expected | Pass |
| 14 | Results | Option 8 after running things | All recorded results listed | Pass |
