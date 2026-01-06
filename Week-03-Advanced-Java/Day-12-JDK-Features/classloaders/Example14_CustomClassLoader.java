/**
 * Custom ClassLoader Implementation
 * Demonstrates: Creating custom ClassLoader, Loading classes from custom sources
 */
package classloaders;

import java.io.*;
import java.nio.file.*;

public class Example14_CustomClassLoader {

    public static void main(String[] args) {
        System.out.println("=== Custom ClassLoader Demonstration ===\n");

        // 1. Basic Custom ClassLoader
        demonstrateBasicCustomLoader();

        // 2. Loading class from byte array
        demonstrateByteArrayLoader();

        // 3. Custom path ClassLoader
        demonstrateCustomPathLoader();

        // 4. Network ClassLoader concept
        demonstrateNetworkLoaderConcept();

        // 5. Class versioning with ClassLoaders
        demonstrateClassVersioning();
    }

    /**
     * Basic Custom ClassLoader
     */
    static void demonstrateBasicCustomLoader() {
        System.out.println("=== Basic Custom ClassLoader ===");

        try {
            // Create custom ClassLoader
            SimpleCustomClassLoader customLoader = new SimpleCustomClassLoader();

            // Load class using custom loader
            // Note: This will fail if class file doesn't exist at specified path
            System.out.println("Custom ClassLoader created: " + customLoader);
            System.out.println("Parent: " + customLoader.getParent());

        } catch (Exception e) {
            System.out.println("Note: " + e.getMessage());
        }
    }

    /**
     * Load class from byte array
     */
    static void demonstrateByteArrayLoader() {
        System.out.println("\n=== Byte Array ClassLoader ===");

        try {
            // Simulate class bytes (in real scenario, read from file/network)
            byte[] classBytes = getClassBytes();

            if (classBytes != null) {
                ByteArrayClassLoader loader = new ByteArrayClassLoader();
                Class<?> loadedClass = loader.loadClassFromBytes("DynamicClass", classBytes);

                System.out.println("Loaded class: " + loadedClass.getName());
                System.out.println("ClassLoader: " + loadedClass.getClassLoader());

                // Create instance
                Object instance = loadedClass.getDeclaredConstructor().newInstance();
                System.out.println("Instance created: " + instance);
            } else {
                System.out.println("Class bytes not available (demo mode)");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Custom path ClassLoader
     */
    static void demonstrateCustomPathLoader() {
        System.out.println("\n=== Custom Path ClassLoader ===");

        try {
            // Create loader for specific directory
            String customPath = "/custom/classes/";
            PathClassLoader pathLoader = new PathClassLoader(customPath);

            System.out.println("PathClassLoader created for: " + customPath);
            System.out.println("Parent: " + pathLoader.getParent());

            // In real scenario, would load classes from this path
            System.out.println("Can load classes from custom path");

        } catch (Exception e) {
            System.out.println("Note: " + e.getMessage());
        }
    }

    /**
     * Network ClassLoader concept
     */
    static void demonstrateNetworkLoaderConcept() {
        System.out.println("\n=== Network ClassLoader Concept ===");

        System.out.println("Network ClassLoader can load classes from:");
        System.out.println("- HTTP/HTTPS URLs");
        System.out.println("- FTP servers");
        System.out.println("- Remote repositories");
        System.out.println();
        System.out.println("Example: URLClassLoader");
        System.out.println("URL[] urls = { new URL(\"http://example.com/classes/\") };");
        System.out.println("URLClassLoader loader = new URLClassLoader(urls);");
        System.out.println();
        System.out.println("Security: Requires appropriate permissions");
    }

    /**
     * Class versioning using ClassLoaders
     */
    static void demonstrateClassVersioning() {
        System.out.println("\n=== Class Versioning ===");

        System.out.println("Different ClassLoaders can load different versions:");
        System.out.println();
        System.out.println("ClassLoader v1 -> MyClass version 1.0");
        System.out.println("ClassLoader v2 -> MyClass version 2.0");
        System.out.println();
        System.out.println("Both versions can coexist in same JVM");
        System.out.println("Useful for plugin systems, OSGi, application servers");
    }

    /**
     * Get class bytes (simulated)
     */
    static byte[] getClassBytes() {
        // In real scenario, read from file or network
        // For demo, we'll return null
        return null;
    }
}

/**
 * Simple Custom ClassLoader
 */
class SimpleCustomClassLoader extends ClassLoader {

    public SimpleCustomClassLoader() {
        super(SimpleCustomClassLoader.class.getClassLoader());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Finding class: " + name);

        // In real implementation:
        // 1. Convert class name to file path
        // 2. Read bytes from file/network/database
        // 3. Call defineClass()

        byte[] classBytes = loadClassBytes(name);
        if (classBytes != null) {
            return defineClass(name, classBytes, 0, classBytes.length);
        }

        throw new ClassNotFoundException(name);
    }

    private byte[] loadClassBytes(String className) {
        try {
            // Convert class name to file path
            String fileName = className.replace('.', '/') + ".class";

            // Try to load from current classpath (for demonstration)
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                return null;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            return baos.toByteArray();

        } catch (Exception e) {
            System.out.println("Error loading bytes: " + e.getMessage());
            return null;
        }
    }
}

/**
 * ByteArray ClassLoader - Loads class from byte array
 */
class ByteArrayClassLoader extends ClassLoader {

    public ByteArrayClassLoader() {
        super(ByteArrayClassLoader.class.getClassLoader());
    }

    public Class<?> loadClassFromBytes(String name, byte[] classBytes) {
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("ByteArrayClassLoader: Finding " + name);
        throw new ClassNotFoundException(name);
    }
}

/**
 * Path ClassLoader - Loads from specific directory
 */
class PathClassLoader extends ClassLoader {

    private String classPath;

    public PathClassLoader(String classPath) {
        super(PathClassLoader.class.getClassLoader());
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("PathClassLoader: Finding " + name + " in " + classPath);

        try {
            // Convert class name to file path
            String fileName = name.replace('.', '/') + ".class";
            String fullPath = classPath + fileName;

            // Read class file
            byte[] classBytes = Files.readAllBytes(Paths.get(fullPath));

            // Define class
            return defineClass(name, classBytes, 0, classBytes.length);

        } catch (Exception e) {
            throw new ClassNotFoundException(name, e);
        }
    }
}

/**
 * Encrypted ClassLoader - Loads and decrypts classes
 */
class EncryptedClassLoader extends ClassLoader {

    private String encryptedPath;

    public EncryptedClassLoader(String path) {
        this.encryptedPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] encryptedBytes = loadEncryptedBytes(name);
            byte[] decryptedBytes = decrypt(encryptedBytes);
            return defineClass(name, decryptedBytes, 0, decryptedBytes.length);
        } catch (Exception e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    private byte[] loadEncryptedBytes(String name) throws IOException {
        // Load encrypted class file
        String fileName = encryptedPath + name.replace('.', '/') + ".encrypted";
        return Files.readAllBytes(Paths.get(fileName));
    }

    private byte[] decrypt(byte[] encrypted) {
        // Simple XOR decryption (for demonstration)
        byte[] decrypted = new byte[encrypted.length];
        byte key = 0x5A; // Simple key

        for (int i = 0; i < encrypted.length; i++) {
            decrypted[i] = (byte) (encrypted[i] ^ key);
        }

        return decrypted;
    }
}

/*
 * Custom ClassLoader Summary:
 *
 * 1. CREATING CUSTOM CLASSLOADER:
 *    - Extend java.lang.ClassLoader
 *    - Override findClass(String name)
 *    - Call defineClass() to create Class object
 *
 * 2. KEY METHODS TO OVERRIDE:
 *    - findClass(String name): Find and load class
 *    - defineClass(...): Convert bytes to Class
 *    - loadClass(String name): Load class (uses delegation)
 *    - findResource(String name): Find resource
 *    - getResourceAsStream(String): Get resource stream
 *
 * 3. CLASS LOADING STEPS:
 *    a) Override findClass()
 *    b) Locate class bytes (file, network, database)
 *    c) Read bytes into byte array
 *    d) Call defineClass(name, bytes, offset, length)
 *    e) Return Class object
 *
 * 4. USE CASES:
 *    - Load classes from custom locations
 *    - Load encrypted/obfuscated classes
 *    - Network class loading (applets, RMI)
 *    - Plugin systems
 *    - Hot deployment/reloading
 *    - Class versioning
 *    - Sandboxing/isolation
 *
 * 5. BEST PRACTICES:
 *    - Always call super() in constructor
 *    - Override findClass(), not loadClass()
 *    - Respect parent delegation model
 *    - Cache loaded classes
 *    - Handle ClassNotFoundException
 *    - Be aware of memory leaks
 *
 * 6. COMMON PATTERNS:
 *    - URLClassLoader: Load from URLs
 *    - SecureClassLoader: With security context
 *    - Plugin ClassLoader: Isolate plugins
 *    - Hot Reload: Discard and recreate loader
 *
 * 7. SECURITY CONSIDERATIONS:
 *    - SecurityManager checks permissions
 *    - defineClass() requires RuntimePermission
 *    - Be careful with untrusted sources
 *    - Validate class bytes
 *
 * 8. MEMORY LEAKS:
 *    - ClassLoader holds references to all loaded classes
 *    - Classes hold reference to ClassLoader
 *    - Static fields prevent GC
 *    - Use weak references when appropriate
 *
 * 9. EXAMPLE USE CASE - PLUGIN SYSTEM:
 *    PluginClassLoader loader = new PluginClassLoader("plugins/");
 *    Class<?> pluginClass = loader.loadClass("com.example.Plugin");
 *    Plugin plugin = (Plugin) pluginClass.newInstance();
 *    plugin.execute();
 *
 * 10. HOT RELOAD PATTERN:
 *     // Discard old loader
 *     oldLoader = null;
 *     // Create new loader (loads fresh classes)
 *     newLoader = new CustomClassLoader();
 *     Class<?> freshClass = newLoader.loadClass("MyClass");
 */
