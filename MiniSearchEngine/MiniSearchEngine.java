import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class IndexedFile {

    private String fileName;
    private List<String> lines;

    public IndexedFile(String fileName, List<String> lines) {
        this.fileName = fileName;
        this.lines = lines;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getLines() {
        return lines;
    }
}

class SearchResult {

    private String fileName;
    private int matchCount;
    private List<String> matchingLines;

    public SearchResult(String fileName, int matchCount, List<String> matchingLines) {
        this.fileName = fileName;
        this.matchCount = matchCount;
        this.matchingLines = matchingLines;
    }

    public String getFileName() {
        return fileName;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public List<String> getMatchingLines() {
        return matchingLines;
    }
}

class SearchEngine {

    private List<IndexedFile> index;
    private List<String> searchHistory;

    public SearchEngine() {
        index = new ArrayList<>();
        searchHistory = new ArrayList<>();
    }

    public void indexFolder(String folderPath) throws IOException {

        Path dir = Paths.get(folderPath);

        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            throw new IOException("Folder not found: " + folderPath);
        }

        index.clear();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path file : stream) {
                List<String> lines = Files.readAllLines(file);
                index.add(new IndexedFile(file.getFileName().toString(), lines));
            }
        }
    }

    public int getIndexedFileCount() {
        return index.size();
    }

    public List<SearchResult> search(String query) {

        searchHistory.add(query);

        String[] keywords = query.trim().toLowerCase().split("\\s+");

        List<SearchResult> results = new ArrayList<>();

        for (IndexedFile file : index) {

            int matchCount = 0;
            List<String> matchingLines = new ArrayList<>();

            for (String line : file.getLines()) {

                String lowerLine = line.toLowerCase();
                boolean lineMatches = false;

                for (String keyword : keywords) {
                    if (!keyword.isEmpty() && lowerLine.contains(keyword)) {
                        lineMatches = true;
                    }
                }

                if (lineMatches) {
                    matchCount++;
                    matchingLines.add(line);
                }
            }

            if (matchCount > 0) {
                results.add(new SearchResult(file.getFileName(), matchCount, matchingLines));
            }
        }

        results.sort(Comparator.comparingInt(SearchResult::getMatchCount).reversed());

        return results;
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }
}

public class MiniSearchEngine {

    private static final String DOCUMENTS_FOLDER = "documents";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SearchEngine engine = new SearchEngine();

        System.out.println("========================================");
        System.out.println(" Mini Search Engine");
        System.out.println("========================================");

        try {
            engine.indexFolder(DOCUMENTS_FOLDER);
            System.out.println("Indexed " + engine.getIndexedFileCount() + " file(s) from '" + DOCUMENTS_FOLDER + "'.\n");
        } catch (IOException e) {
            System.out.println("Error while indexing files: " + e.getMessage());
            return;
        }

        boolean running = true;

        while (running) {

            System.out.print("Enter search keyword (or 'history' to view past searches, 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                System.out.println("Please enter a keyword.\n");
                continue;
            }

            String command = input.trim();

            if (command.equalsIgnoreCase("exit")) {
                running = false;
                System.out.println("Goodbye!");
                continue;
            }

            if (command.equalsIgnoreCase("history")) {
                printHistory(engine.getSearchHistory());
                continue;
            }

            List<SearchResult> results = engine.search(command);
            printResults(results);
        }

        scanner.close();
    }

    private static void printResults(List<SearchResult> results) {

        System.out.println();
        System.out.println("Search Results");
        System.out.println("========================");

        if (results.isEmpty()) {
            System.out.println("No matches found.\n");
            return;
        }

        int rank = 1;

        for (SearchResult result : results) {

            System.out.println();
            System.out.println(rank + ". " + result.getFileName());
            System.out.println("   Matches: " + result.getMatchCount());
            System.out.println("   Matching lines:");

            for (String line : result.getMatchingLines()) {
                System.out.println("     - " + line.trim());
            }

            rank++;
        }

        System.out.println();
    }

    private static void printHistory(List<String> history) {

        System.out.println();
        System.out.println("Search History");
        System.out.println("========================");

        if (history.isEmpty()) {
            System.out.println("No searches yet.\n");
            return;
        }

        int i = 1;

        for (String query : history) {
            System.out.println(i + ". " + query);
            i++;
        }

        System.out.println();
    }
}
