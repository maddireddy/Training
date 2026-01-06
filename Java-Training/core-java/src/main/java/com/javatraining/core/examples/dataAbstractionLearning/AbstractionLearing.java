package dataAbstractionLearning;

public class AbstractionLearing {

    public static void main(String[] args) {

        MyBean bean = new MyBean();
        bean.setA(10);
        bean.setB(20);

        System.out.println("Get Values" + bean.getA() + " ::: " + bean.getB());

    }
}
