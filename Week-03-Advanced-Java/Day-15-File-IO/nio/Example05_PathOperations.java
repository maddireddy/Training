/**
 * Path Operations - File system paths
 */
package nio;
import java.nio.file.*;
public class Example05_PathOperations {
    public static void main(String[] args) {
        Path path = Paths.get("example", "test", "file.txt");
        System.out.println("Path: " + path);
        System.out.println("Absolute: " + path.toAbsolutePath());
        System.out.println("Parent: " + path.getParent());
        System.out.println("File name: " + path.getFileName());
        System.out.println("Root: " + path.getRoot());

        // Path manipulation
        Path path1 = Paths.get("/home/user");
        Path path2 = Paths.get("/home/user/docs/file.txt");
        Path relative = path1.relativize(path2);
        System.out.println("\nRelative: " + relative);

        // Resolve
        Path resolved = path1.resolve("downloads/app.jar");
        System.out.println("Resolved: " + resolved);

        // Normalize
        Path messy = Paths.get("/home/user/../user/./docs");
        System.out.println("Normalized: " + messy.normalize());
    }
}
