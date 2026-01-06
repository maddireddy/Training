package ioPackage;

public class HRDailyInputLoad {

    public static void main(String[] args) {

        String name = "DailyInput_";

        try {

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Hi");
            stringBuffer.append("-");
            stringBuffer.append("Bye");

            System.out.println(stringBuffer.toString());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Hi");
            stringBuilder.append("-");
            stringBuilder.append("Bye");

            System.out.println(stringBuilder.toString());

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
