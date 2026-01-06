package exceptionHandling;

public class MainTesting {

    public static void main(String[] args) {
        ExceptionLearning1 exceptionLearning1 = new ExceptionLearning1();

        //Immutable Class
        String str1 = "Hi";
        String str2 = "Byeòooooooooo";

        System.out.println(str1 + "," + str2);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str1).append(",").append(str2);

        System.out.println(stringBuffer.toString());


        for (int i = 0; i < 10; i++) {
            try {
                exceptionLearning1.display();
            } catch (Exception exception) {
                System.out.println(exception);
                System.out.println(exception.getLocalizedMessage());
                exception.printStackTrace();
            }
        }
    }
}
