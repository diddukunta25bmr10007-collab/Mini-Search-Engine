# Mini Search Engine

A simple command-line search engine written in Java. It indexes all `.txt`
files in a folder and lets you search them by keyword — with support for
multi-word queries, case-insensitive matching, ranked results, and a
search history.

## Project Structure

```
MiniSearchEngine/
│
├── MiniSearchEngine.java
├── README.md
├── REPORT.md
└── documents/
    ├── bitcoin.txt
    ├── market.txt
    ├── finance.txt
    ├── space.txt
    ├── ocean.txt
    ├── health.txt
    ├── movies.txt
    ├── sports.txt
    └── travel.txt
```

## Documents Included

| File | Topic |
|---|---|
| `bitcoin.txt` | Bitcoin and cryptocurrency basics |
| `market.txt` | The stock market |
| `finance.txt` | Finance and cryptocurrency trading |
| `space.txt` | Space exploration |
| `ocean.txt` | The ocean and marine life |
| `health.txt` | Health and fitness |
| `movies.txt` | Movies and film |
| `sports.txt` | Sports |
| `travel.txt` | Travel |

This list matches what should be in your `documents` folder — run
`dir documents` (Windows) or `ls documents` (Mac/Linux) to confirm.

## How to Run

1. Open a terminal and navigate into the `MiniSearchEngine` folder — the
   one that directly contains `MiniSearchEngine.java` and the `documents`
   folder.
2. Compile the program:
   ```
   javac MiniSearchEngine.java
   ```
3. Run it:
   ```
   java MiniSearchEngine
   ```
4. The program automatically indexes every `.txt` file inside the
   `documents` folder, then prompts you for a keyword.

If you see `Error while indexing files: Folder not found: documents`,
your terminal isn't sitting inside the `MiniSearchEngine` folder, or the
`documents` folder is missing — check both before running again.

## Using It

- **Single keyword:** `bitcoin`
- **Multiple keywords:** `ocean travel` — matches any line containing
  *either* word.
- **Case doesn't matter:** `Java`, `JAVA`, and `java` all match the same way.
- Type `history` at the prompt to see every search you've run so far.
- Type `exit` to quit.

### Example

```
Enter search keyword (or 'history' to view past searches, 'exit' to quit): bitcoin

Search Results
========================

1. bitcoin.txt
   Matches: 10
   Matching lines:
     - Bitcoin is a decentralized digital currency created in 2009.
     ...

2. finance.txt
   Matches: 2
   Matching lines:
     - Ethereum is another popular cryptocurrency besides bitcoin.
```

Files are ranked with the highest match count first.

## Adding Your Own Files

Drop any additional `.txt` files into the `documents` folder before
running the program — they'll be indexed automatically, no code changes
or recompiling needed. Avoid naming a file so its topic word collides
with the reserved commands `history` or `exit`, since those are always
treated as commands rather than search terms.

## Java Concepts Demonstrated

- Classes and objects (`IndexedFile`, `SearchResult`, `SearchEngine`)
- `ArrayList` for indexed files, matching lines, results, and history
- `File` / `Path` / `Files` (NIO) for reading directories and files
- File reading (`Files.readAllLines`)
- String processing (`split`, `toLowerCase`, `contains`, `trim`)
- Loops (`for`, `while`)
- Exception handling (`try`/`catch`, `IOException`)
- Sorting (`Comparator`, `List.sort`)
- Methods and separation of concerns across classes
- Command-line input (`Scanner`)
