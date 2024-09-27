import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AICodingContextGenerator {
    private final Path projectRoot;
    private final List<Path> projectFiles;
    private final Set<Path> conversationFiles;
    private final Map<Path, String> fileContents;

    public AICodingContextGenerator(String projectRoot) throws IOException {
        this.projectRoot = Paths.get(projectRoot);
        this.projectFiles = getProjectFiles();
        this.conversationFiles = new HashSet<>();
        this.fileContents = new HashMap<>();
    }

    private List<Path> getProjectFiles() throws IOException {
        return Files.walk(projectRoot)
                .filter(Files::isRegularFile)
                .filter(path -> {
                    String fileName = path.getFileName().toString();
                    return fileName.endsWith(".java") || fileName.endsWith(".js") ||
                           fileName.endsWith(".html") || fileName.endsWith(".css") ||
                           fileName.endsWith(".json");
                })
                .collect(Collectors.toList());
    }

    public void addConversationFile(String filePath) {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            conversationFiles.add(path);
        } else {
            System.out.println("Warning: File " + filePath + " does not exist in the project.");
        }
    }

    private String readFileContent(Path filePath) throws IOException {
        if (!fileContents.containsKey(filePath)) {
            fileContents.put(filePath, new String(Files.readAllBytes(filePath)));
        }
        return fileContents.get(filePath);
    }

    private List<String> extractFunctions(String content) {
        List<String> functions = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?:public|private|protected|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *\\{?");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            functions.add(matcher.group(1));
        }
        return functions;
    }

    private List<String> extractClasses(String content) {
        List<String> classes = new ArrayList<>();
        Pattern pattern = Pattern.compile("class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            classes.add(matcher.group(1));
        }
        return classes;
    }

    public Map<Path, Map<String, Object>> generateContext() throws IOException {
        Map<Path, Map<String, Object>> context = new HashMap<>();

        Set<Path> allFiles = new HashSet<>(projectFiles);
        allFiles.addAll(conversationFiles);

        for (Path filePath : allFiles) {
            String fileContent = readFileContent(filePath);
            List<String> functions = extractFunctions(fileContent);
            List<String> classes = extractClasses(fileContent);

            Map<String, Object> fileContext = new HashMap<>();
            fileContext.put("functions", functions);
            fileContext.put("classes", classes);
            fileContext.put("isConversationFile", conversationFiles.contains(filePath));

            context.put(filePath, fileContext);
        }

        return context;
    }

    public String provideInsights() throws IOException {
        Map<Path, Map<String, Object>> context = generateContext();
        StringBuilder insights = new StringBuilder();

        int totalFiles = context.size();
        int totalFunctions = context.values().stream()
                .mapToInt(v -> ((List<?>) v.get("functions")).size())
                .sum();
        int totalClasses = context.values().stream()
                .mapToInt(v -> ((List<?>) v.get("classes")).size())
                .sum();

        insights.append("Project Overview:\n");
        insights.append("- Total files analyzed: ").append(totalFiles).append("\n");
        insights.append("- Total functions found: ").append(totalFunctions).append("\n");
        insights.append("- Total classes found: ").append(totalClasses).append("\n");

        if (!conversationFiles.isEmpty()) {
            insights.append("\nFiles mentioned in conversation:\n");
            for (Path file : conversationFiles) {
                if (context.containsKey(file)) {
                    Map<String, Object> fileContext = context.get(file);
                    insights.append("- ").append(file).append("\n");
                    insights.append("  Functions: ").append(String.join(", ", (List<String>) fileContext.get("functions"))).append("\n");
                    insights.append("  Classes: ").append(String.join(", ", (List<String>) fileContext.get("classes"))).append("\n");
                }
            }
        }

        return insights.toString();
    }

    public static void main(String[] args) {
        try {
            AICodingContextGenerator generator = new AICodingContextGenerator("/path/to/project/root");
            generator.addConversationFile("/path/to/project/root/specific_file.java");
            System.out.println(generator.provideInsights());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
