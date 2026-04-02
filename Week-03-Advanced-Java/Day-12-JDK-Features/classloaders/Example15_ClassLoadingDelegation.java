/**
 * Class Loading Delegation Model
 * Demonstrates: Parent-first delegation, Class visibility, Namespace isolation
 */
package classloaders;

public class Example15_ClassLoadingDelegation {

    public static void main(String[] args) {
        System.out.println("=== Class Loading Delegation Model ===\n");

        // 1. Parent-first delegation
        demonstrateParentFirstDelegation();

        // 2. Class visibility rules
        demonstrateClassVisibility();

        // 3. Namespace isolation
        demonstrateNamespaceIsolation();

        // 4. Breaking delegation (anti-pattern)
        demonstrateBreakingDelegation();

        // 5. Class identity
        demonstrateClassIdentity();

        // 6. Real-world scenarios
        demonstrateRealWorldScenarios();
    }

    /**
     * Parent-first delegation
     */
    static void demonstrateParentFirstDelegation() {
        System.out.println("=== Parent-First Delegation ===");

        System.out.println("Loading Process:");
        System.out.println("1. Check if class already loaded");
        System.out.println("2. Ask parent ClassLoader");
        System.out.println("3. Parent asks its parent (up to Bootstrap)");
        System.out.println("4. If parent can't load, try to load yourself");
        System.out.println();

        // Demonstrate with actual class
        Class<?> clazz = Example15_ClassLoadingDelegation.class;
        ClassLoader loader = clazz.getClassLoader();

        System.out.println("Class: " + clazz.getName());
        System.out.println("Loaded by: " + loader);
        System.out.println();

        // Show delegation chain
        System.out.println("Delegation Chain:");
        int level = 1;
        ClassLoader current = loader;
        while (current != null) {
            System.out.println("  " + level + ". " + current.getClass().getSimpleName());
            current = current.getParent();
            level++;
        }
        System.out.println("  " + level + ". Bootstrap ClassLoader");
    }

    /**
     * Class visibility rules
     */
    static void demonstrateClassVisibility() {
        System.out.println("\n=== Class Visibility Rules ===");

        System.out.println("Rule 1: Child can see parent's classes");
        System.out.println("  - Application loader can see Bootstrap classes");
        System.out.println("  - Example: String, ArrayList, etc.");
        System.out.println();

        System.out.println("Rule 2: Parent CANNOT see child's classes");
        System.out.println("  - Bootstrap cannot see Application classes");
        System.out.println("  - Prevents core classes depending on app classes");
        System.out.println();

        System.out.println("Rule 3: Siblings cannot see each other");
        System.out.println("  - Two Application loaders are isolated");
        System.out.println("  - Used in plugin systems, app servers");
        System.out.println();

        // Demonstrate with example
        ClassLoader appLoader = ClassLoader.getSystemClassLoader();

        System.out.println("Example:");
        System.out.println("Application Loader: " + appLoader);
        System.out.println("Can load String? " + canLoadClass(appLoader, "java.lang.String"));
        System.out.println("Can load own classes? " + canLoadClass(appLoader,
            "classloaders.Example15_ClassLoadingDelegation"));
    }

    static boolean canLoadClass(ClassLoader loader, String className) {
        try {
            loader.loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Namespace isolation
     */
    static void demonstrateNamespaceIsolation() {
        System.out.println("\n=== Namespace Isolation ===");

        System.out.println("Each ClassLoader has its own namespace:");
        System.out.println("- Same class name can exist in different loaders");
        System.out.println("- Classes are identified by: (ClassName, ClassLoader)");
        System.out.println("- Prevents version conflicts");
        System.out.println();

        System.out.println("Example Scenario:");
        System.out.println("App1ClassLoader -> com.example.Utils v1.0");
        System.out.println("App2ClassLoader -> com.example.Utils v2.0");
        System.out.println("Both can coexist without conflict!");
        System.out.println();

        // Create isolated loaders
        IsolatedClassLoader loader1 = new IsolatedClassLoader("Loader1");
        IsolatedClassLoader loader2 = new IsolatedClassLoader("Loader2");

        System.out.println("Created isolated loaders:");
        System.out.println("Loader1: " + loader1);
        System.out.println("Loader2: " + loader2);
        System.out.println("Loader1 != Loader2: " + (loader1 != loader2));
    }

    /**
     * Breaking delegation (anti-pattern)
     */
    static void demonstrateBreakingDelegation() {
        System.out.println("\n=== Breaking Delegation (Anti-Pattern) ===");

        System.out.println("Child-First Delegation:");
        System.out.println("- Load from child first, then ask parent");
        System.out.println("- Used in some web containers (Tomcat)");
        System.out.println("- Can cause ClassCastException");
        System.out.println("- Generally avoid unless necessary");
        System.out.println();

        System.out.println("Problems:");
        System.out.println("1. Same class loaded multiple times");
        System.out.println("2. Class cast issues");
        System.out.println("3. Hard to debug");
        System.out.println("4. Violates Java conventions");
        System.out.println();

        ChildFirstClassLoader childFirst = new ChildFirstClassLoader();
        System.out.println("Child-First Loader: " + childFirst);
        System.out.println("Warning: Use with caution!");
    }

    /**
     * Class identity
     */
    static void demonstrateClassIdentity() {
        System.out.println("\n=== Class Identity ===");

        System.out.println("Two classes are the same if:");
        System.out.println("1. Same fully qualified name");
        System.out.println("2. Loaded by same ClassLoader");
        System.out.println();

        System.out.println("Example:");
        Class<?> class1 = String.class;
        ClassLoader loader1 = class1.getClassLoader();

        System.out.println("String class: " + class1.getName());
        System.out.println("Loaded by: " + loader1);
        System.out.println();

        System.out.println("Identity Rules:");
        System.out.println("- class1 == class1: true (same instance)");
        System.out.println("- If loaded by different loaders:");
        System.out.println("  * class1 == class2: false");
        System.out.println("  * class1.equals(class2): false");
        System.out.println("  * (Class1) class2 instance: ClassCastException!");
    }

    /**
     * Real-world scenarios
     */
    static void demonstrateRealWorldScenarios() {
        System.out.println("\n=== Real-World Scenarios ===");

        System.out.println("1. Web Application Server (Tomcat, JBoss):");
        System.out.println("   Bootstrap");
        System.out.println("      └─> System");
        System.out.println("           └─> Common (shared libs)");
        System.out.println("                ├─> WebApp1 (isolated)");
        System.out.println("                └─> WebApp2 (isolated)");
        System.out.println();

        System.out.println("2. Plugin System:");
        System.out.println("   Application");
        System.out.println("      ├─> Plugin1ClassLoader");
        System.out.println("      ├─> Plugin2ClassLoader");
        System.out.println("      └─> Plugin3ClassLoader");
        System.out.println();

        System.out.println("3. OSGi Framework:");
        System.out.println("   - Each bundle has own ClassLoader");
        System.out.println("   - Explicit dependencies");
        System.out.println("   - Hot deployment/undeployment");
        System.out.println();

        System.out.println("4. Java 9+ Module System:");
        System.out.println("   - Module graph replaces classpath");
        System.out.println("   - Strong encapsulation");
        System.out.println("   - Explicit dependencies");
    }
}

/**
 * Isolated ClassLoader - Creates separate namespace
 */
class IsolatedClassLoader extends ClassLoader {
    private String name;

    public IsolatedClassLoader(String name) {
        super(IsolatedClassLoader.class.getClassLoader());
        this.name = name;
    }

    @Override
    public String toString() {
        return "IsolatedClassLoader[" + name + "]";
    }
}

/**
 * Child-First ClassLoader (Anti-Pattern Example)
 */
class ChildFirstClassLoader extends ClassLoader {

    public ChildFirstClassLoader() {
        super(ChildFirstClassLoader.class.getClassLoader());
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // DON'T DO THIS - Anti-pattern!
        // Load from child first, then parent

        // Exclude core Java classes
        if (name.startsWith("java.") || name.startsWith("javax.")) {
            return super.loadClass(name);
        }

        // Try to load ourselves first
        try {
            return findClass(name);
        } catch (ClassNotFoundException e) {
            // If we can't load, ask parent
            return super.loadClass(name);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // Custom loading logic here
        throw new ClassNotFoundException(name);
    }
}

/**
 * Logging ClassLoader - Demonstrates delegation
 */
class LoggingClassLoader extends ClassLoader {

    public LoggingClassLoader() {
        super(LoggingClassLoader.class.getClassLoader());
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("  [LoggingClassLoader] Loading: " + name);

        // Follow parent-first delegation
        Class<?> clazz = super.loadClass(name);

        System.out.println("  [LoggingClassLoader] Loaded by: " +
            clazz.getClassLoader());

        return clazz;
    }
}

/*
 * Class Loading Delegation Summary:
 *
 * 1. PARENT-FIRST DELEGATION:
 *    Step 1: Check if class already loaded (findLoadedClass)
 *    Step 2: Ask parent ClassLoader
 *    Step 3: Parent asks its parent (recursive)
 *    Step 4: Bootstrap tries to load
 *    Step 5: If parent fails, load yourself (findClass)
 *
 * 2. VISIBILITY RULES:
 *    ✓ Child sees parent classes
 *    ✗ Parent cannot see child classes
 *    ✗ Siblings cannot see each other
 *
 * 3. CLASS IDENTITY:
 *    Class identity = (ClassName, ClassLoader)
 *    - Same name, different loader = different classes
 *    - Can cause ClassCastException
 *
 * 4. BENEFITS OF DELEGATION:
 *    - Prevents duplicate class loading
 *    - Ensures core classes loaded once
 *    - Security (core classes from trusted source)
 *    - Consistency
 *
 * 5. WHEN TO BREAK DELEGATION:
 *    - Web containers (servlet spec allows child-first)
 *    - Override framework classes
 *    - Hot deployment
 *    Warning: Can cause subtle bugs!
 *
 * 6. COMMON ISSUES:
 *    - ClassNotFoundException: Class not in classpath
 *    - ClassCastException: Same class, different loaders
 *    - NoClassDefFoundError: Class was found but definition failed
 *    - LinkageError: Class loaded multiple times
 *
 * 7. DEBUGGING TIPS:
 *    - Print ClassLoader for each class
 *    - Use -verbose:class JVM flag
 *    - Check classpath carefully
 *    - Understand container ClassLoader hierarchy
 *
 * 8. BEST PRACTICES:
 *    - Respect parent-first delegation
 *    - Override findClass(), not loadClass()
 *    - Cache loaded classes
 *    - Understand container hierarchy
 *    - Be aware of class identity issues
 *
 * 9. JVM FLAGS:
 *    -verbose:class          # Print class loading
 *    -XX:+TraceClassLoading  # Detailed class loading trace
 *    -XX:+TraceClassUnloading # Class unloading trace
 *
 * 10. HIERARCHIES IN PRACTICE:
 *
 *     Tomcat:
 *     Bootstrap -> System -> Common
 *                              ├─> WebApp1
 *                              └─> WebApp2
 *
 *     OSGi:
 *     Bootstrap -> System -> Framework
 *                              ├─> Bundle1
 *                              ├─> Bundle2
 *                              └─> Bundle3
 *
 *     Java 9+ Modules:
 *     Bootstrap -> Platform -> Application
 *       (with module layer on top)
 */
