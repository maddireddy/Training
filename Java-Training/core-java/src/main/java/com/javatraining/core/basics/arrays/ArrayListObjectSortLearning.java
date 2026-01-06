package collectionLearning.list.arrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}

public class ArrayListObjectSortLearning {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Sachin", 47));
        people.add(new Person("Chris", 34));
        people.add(new Person("Rajeev", 25));
        people.add(new Person("David", 31));

        System.out.println("Person List : " + people);

        // Sort People by their Age
        people.sort(Comparator.comparingInt(Person::getAge));

        // A more concise way of writing the above sorting function
        people.sort(Comparator.comparingInt(Person::getAge));

        System.out.println("Sorted Person List by Age : " + people);

        // You can also sort using Collections.sort() method by passing the custom Comparator
        people.sort(Comparator.comparing(Person::getName));
        System.out.println("Sorted Person List by Name : " + people);
    }
}
