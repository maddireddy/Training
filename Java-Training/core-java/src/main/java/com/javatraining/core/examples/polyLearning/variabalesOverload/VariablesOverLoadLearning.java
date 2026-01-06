package polyLearning.variabalesOverload;

/*
In statement pl = new ProgrammingLanguage(), pl refer to the object of the ProgrammingLanguage class.
And, in statement pl = new JavaLang(), pl refer to the object of the Java class.
 */

public class VariablesOverLoadLearning {

    public static void main(String[] args) {

        // declare an object variable
        ProgrammingLanguage pl;

        // create object of ProgrammingLanguage
        pl = new ProgrammingLanguage();
        pl.display();

        // create object of Java class
        pl = new JavaLang();
        pl.display();
    }
}
