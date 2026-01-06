package com.javatraining.core.oops.inheritance;

// Derived class
public class Car extends Vehicle {
    private int numberOfDoors;
    
    public Car(String brand, int year, int numberOfDoors) {
        super(brand, year);
        this.numberOfDoors = numberOfDoors;
    }
    
    @Override
    public void start() {
        System.out.println("Starting the car...");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of doors: " + numberOfDoors);
    }
    
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", 2022, 4);
        myCar.start();
        myCar.displayInfo();
    }
}
