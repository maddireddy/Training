package threadLearning;

public class ThreadLearning extends Thread {

    public static void main(String[] args) {
        System.out.println("Hi");

        Thread t1 = new Thread(); // New
        t1.start(); // Ready to RUn
        t1.setName("Siva");
        t1.setPriority(10);
        System.out.println(t1.isAlive());
        System.out.println(t1.getName() + " >>> " + t1.getState());

        t1.run(); // Running

        // Terminated

        System.out.println(t1.getName() + " >>> " + t1.getState() + " >>> " + t1.isAlive());

        Thread t2 = new Thread();
        t2.setName("Another Helper");
        t2.start();
        t2.setPriority(5);

        System.out.println(t2.getName() + " >>> " + t2.getState());

        t2.run();

        System.out.println(t2.getName() + " >>> " + t2.getState() + " >>> " + t2.isAlive());
    }
}
