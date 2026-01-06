/**
 * ClassLoader Hierarchy
 * Demonstrates: Bootstrap, Extension, Application ClassLoaders
 */
package classloaders;

import java.net.URL;
import java.util.ArrayList;

public class Example13_ClassLoaderHierarchy {

    public static void main(String[] args) {
        System.out.println("=== ClassLoader Hierarchy Demonstration ===\n");

        // 1. Understanding ClassLoader hierarchy
        explainClassLoaderHierarchy();

        // 2. Show ClassLoaders for different classes
        demonstrateClassLoaders();

        // 3. ClassLoader parent chain
        demonstrateParentChain();

        // 4. ClassPath and ClassLoader
        demonstrateClassPath();

        // 5. Loading classes programmatically
        demonstrateClassLoading();
    }

    /**
     * Explain ClassLoader Hierarchy
     */
    static void explainClassLoaderHierarchy() {
        System.out.println("=== ClassLoader Hierarchy ===");
        System.out.println();
        System.out.println("1. Bootstrap ClassLoader (null in Java)");
        System.out.println("   - Written in native code (C/C++)");
        System.out.println("   - Loads core Java classes (java.*, javax.*)");
        System.out.println("   - Located in: jre/lib/rt.jar");
        System.out.println();
        System.out.println("2. Platform/Extension ClassLoader (Java 9+)");
        System.out.println("   - Loads extension classes");
        System.out.println("   - Located in: jre/lib/ext/ or java.ext.dirs");
        System.out.println();
        System.out.println("3. Application/System ClassLoader");
        System.out.println("   - Loads application classes");
        System.out.println("   - Uses CLASSPATH environment variable");
        System.out.println();
        System.out.println("Delegation Model: Child -> Parent -> Bootstrap");
        System.out.println();
    }

    /**
     * Show ClassLoaders for different classes
     */
    static void demonstrateClassLoaders() {
        System.out.println("=== ClassLoaders for Different Classes ===");

        // Core Java class - loaded by Bootstrap ClassLoader
        Class<String> stringClass = String.class;
        ClassLoader stringLoader = stringClass.getClassLoader();
        System.out.println("String class loader: " + stringLoader);
        System.out.println("(null = Bootstrap ClassLoader)");

        // Another core class
        Class<ArrayList> arrayListClass = ArrayList.class;
        ClassLoader arrayListLoader = arrayListClass.getClassLoader();
        System.out.println("\nArrayList class loader: " + arrayListLoader);

        // Application class - loaded by Application ClassLoader
        Class<Example13_ClassLoaderHierarchy> appClass = Example13_ClassLoaderHierarchy.class;
        ClassLoader appLoader = appClass.getClassLoader();
        System.out.println("\nExample13 class loader: " + appLoader);
        System.out.println("Type: " + appLoader.getClass().getName());

        // Custom class in same package
        CustomClass custom = new CustomClass();
        ClassLoader customLoader = custom.getClass().getClassLoader();
        System.out.println("\nCustomClass loader: " + customLoader);
    }

    /**
     * Demonstrate parent chain
     */
    static void demonstrateParentChain() {
        System.out.println("\n=== ClassLoader Parent Chain ===");

        ClassLoader currentLoader = Example13_ClassLoaderHierarchy.class.getClassLoader();

        int level = 1;
        while (currentLoader != null) {
            System.out.println("Level " + level + ": " + currentLoader.getClass().getName());
            System.out.println("  toString: " + currentLoader);

            currentLoader = currentLoader.getParent();
            level++;
        }

        System.out.println("Level " + level + ": Bootstrap ClassLoader (null)");
    }

    /**
     * Demonstrate ClassPath
     */
    static void demonstrateClassPath() {
        System.out.println("\n=== ClassPath Information ===");

        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("System ClassLoader: " + systemLoader);

        // Get classpath
        String classPath = System.getProperty("java.class.path");
        System.out.println("\nClasspath entries:");
        String[] paths = classPath.split(System.getProperty("path.separator"));
        for (int i = 0; i < paths.length && i < 10; i++) {
            System.out.println("  " + (i + 1) + ". " + paths[i]);
        }
        if (paths.length > 10) {
            System.out.println("  ... (" + (paths.length - 10) + " more)");
        }

        // Get resource URLs
        System.out.println("\nResources from ClassLoader:");
        try {
            java.util.Enumeration<URL> resources = systemLoader.getResources("");
            int count = 0;
            while (resources.hasMoreElements() && count < 5) {
                System.out.println("  " + resources.nextElement());
                count++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Load classes programmatically
     */
    static void demonstrateClassLoading() {
        System.out.println("\n=== Programmatic Class Loading ===");

        try {
            // Method 1: Class.forName() - uses caller's ClassLoader
            Class<?> class1 = Class.forName("java.util.HashMap");
            System.out.println("Loaded: " + class1.getName());
            System.out.println("ClassLoader: " + class1.getClassLoader());

            // Method 2: ClassLoader.loadClass()
            ClassLoader loader = Example13_ClassLoaderHierarchy.class.getClassLoader();
            Class<?> class2 = loader.loadClass("java.util.ArrayList");
            System.out.println("\nLoaded: " + class2.getName());
            System.out.println("ClassLoader: " + class2.getClassLoader());

            // Method 3: findSystemClass()
            Class<?> class3 = ClassLoader.getSystemClassLoader()
                .loadClass("java.lang.StringBuilder");
            System.out.println("\nLoaded: " + class3.getName());

            // Create instance
            Object instance = class1.getDeclaredConstructor().newInstance();
            System.out.println("\nCreated instance: " + instance.getClass().getSimpleName());

        } catch (Exception e) {
            System.out.println("Error loading class: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class CustomClass {
    private String name = "CustomClass";

    public String getName() {
        return name;
    }
}

/*
 * ClassLoader Hierarchy Summary:
 *
 * 1. CLASSLOADER TYPES:
 *    ┌─────────────────────────┐
 *    │ Bootstrap ClassLoader   │ <- null (native code)
 *    │  - rt.jar, core libs    │
 *    └──────────┬──────────────┘
 *               │ parent
 *    ┌──────────▼──────────────┐
 *    │ Platform ClassLoader    │ <- Java 9+
 *    │  - ext libs             │
 *    └──────────┬──────────────┘
 *               │ parent
 *    ┌──────────▼──────────────┐
 *    │ Application ClassLoader │
 *    │  - CLASSPATH            │
 *    └─────────────────────────┘
 *
 * 2. CLASS LOADING PROCESS:
 *    a) Check if class already loaded
 *    b) Delegate to parent ClassLoader
 *    c) If parent can't load, load it yourself
 *    d) Link class (verify, prepare, resolve)
 *    e) Initialize class (static initializers)
 *
 * 3. DELEGATION MODEL:
 *    - Parent-first delegation (default)
 *    - Prevents class version conflicts
 *    - Ensures core classes loaded by Bootstrap
 *
 * 4. CLASS LOADING METHODS:
 *    - Class.forName(String name)
 *    - ClassLoader.loadClass(String name)
 *    - ClassLoader.getSystemClassLoader()
 *    - ClassLoader.findSystemClass(String name)
 *
 * 5. KEY PROPERTIES:
 *    - java.class.path: Application classpath
 *    - java.ext.dirs: Extension directories
 *    - java.endorsed.dirs: Endorsed standards
 *
 * 6. IMPORTANT METHODS:
 *    - getClassLoader(): Get class's ClassLoader
 *    - getParent(): Get parent ClassLoader
 *    - loadClass(String): Load class by name
 *    - findClass(String): Find class (override this)
 *    - defineClass(): Convert bytes to Class
 *
 * 7. JAVA 9+ CHANGES:
 *    - Extension ClassLoader -> Platform ClassLoader
 *    - Module system integration
 *    - Better encapsulation
 */
