package serializationanddeserializationLearning.serialization;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Employee implements Serializable {

    private String name;
    private int id;
    transient private int salary;

    //private String password;
}
