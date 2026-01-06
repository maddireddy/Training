package collectionLearning.map.hashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@ToString
class Employee {
    private Integer id;
    private String name;
    private String city;
}

public class HashMapUserDefinedObjectLearning {
    public static void main(String[] args) {

        Map<Integer, Employee> employeesMap = new HashMap<>();
        employeesMap.put(1001, new Employee(1001, "Rajeev", "Bengaluru"));
        employeesMap.put(1002, new Employee(1002, "David", "New York"));
        employeesMap.put(1003, new Employee(1003, "Jack", "Paris"));

        System.out.println(employeesMap);
    }
}
