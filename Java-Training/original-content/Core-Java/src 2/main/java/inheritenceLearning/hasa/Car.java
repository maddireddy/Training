package inheritenceLearning.hasa;

public class Car {

    Engine engine;

    Car(){
        engine = new Engine();
    }

    public  void honk(){
        engine.start();
        System.out.println("Inside Car Honk");
    }
}
