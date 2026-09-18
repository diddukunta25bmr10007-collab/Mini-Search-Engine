# Project Statement

## Mini Search Engine

The **Mini Search Engine** is a console-based Java application developed to index and search a folder of text files by keyword. The system reads every `.txt` file inside a `documents` folder into memory, then lets the user repeatedly search across all of them, showing which files match, how many lines matched, and the actual matching lines, ranked from most to least relevant.

The project is developed using core Java concepts. It uses classes and objects, encapsulation, the Java Collections Framework (`ArrayList`), NIO-based file handling (`Path`, `Files`, `DirectoryStream`), checked exception handling, and `Comparator`-based sorting to rank results.

The system provides a single command-line interface for the user. A user can search using one keyword or several at once, with matching done case-insensitively so that `Java`, `JAVA`, and `java` are all treated the same. Every search performed during a session is recorded, and the user can recall that history at any time using the `history` command.

During searching, the system counts, per file, how many lines contain at least one of the query's keywords, and lists those lines beneath the file name. Results are then sorted so the file with the highest match count is shown first. If a query matches nothing, the system reports this cleanly instead of failing.

The indexing step validates that the target folder exists before reading any files, and reports a clear error message rather than crashing if the folder is missing. An empty folder (no `.txt` files) is also handled gracefully, and empty user input is rejected with a prompt to try again.

The main purpose of this project is to demonstrate how core Java — collections, file I/O, exception handling and sorting — can be combined to build a small but complete, practical command-line tool. The current version searches a single fixed folder and matches on individual keywords; it can be further enhanced with exact-phrase search, a configurable folder path, recursive subfolder indexing, and persistent search history.

## Technologies Used

- Java
- Object-Oriented Programming (Classes and Objects)
- Encapsulation
- Java Collections Framework
- ArrayList
- NIO File Handling (Path, Files, DirectoryStream)
- Exception Handling
- Comparator (Sorting)
- Scanner
- Command-Line Interface

## Main Features

1. Folder Indexing
2. Single-Keyword Search
3. Multi-Keyword Search
4. Case-Insensitive Matching
5. Match Counting per File
6. Matching-Line Display
7. Result Ranking by Match Count
8. Search History
9. No-Match Handling
10. Empty-Input Handling
11. Missing-Folder / Empty-Folder Handling
12. Command-Line Interface

## Conclusion

The Mini Search Engine provides a practical implementation of core Java programming concepts through a real-world text-search scenario. It combines folder indexing, keyword and multi-keyword search, case-insensitive matching, ranked results, search history, and graceful error handling in one console-based application. The project can serve as a foundation for a more advanced search tool with phrase search, persistent history, recursive indexing, and a graphical or web interface.
