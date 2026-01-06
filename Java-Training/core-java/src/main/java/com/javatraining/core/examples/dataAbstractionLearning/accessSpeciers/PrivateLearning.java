package dataAbstractionLearning.accessSpeciers;

import dataAbstractionLearning.privateLearning.PrivateGetSet;
import dataAbstractionLearning.privateLearning.Student;

//  Bean Objects and Pojo Objects[ORM - Object Relational Mapping]

public class PrivateLearning {

    public static void main(String[] args) {
        PrivateGetSet privateGetSet = new PrivateGetSet();

        privateGetSet.setA(10);
        privateGetSet.setB(20);

        System.out.println("A value >>> " + privateGetSet.getA() + "  B  value " + privateGetSet.getB());

        //Student

        Student student = new Student();
        student.setSno(1);
        student.setFname("Siva");
        student.setLname("M");

        student.display();

        System.out.println("SNO value >>> " + student.getSno() + "  FNAME  value " + student.getFname());

    }
}
