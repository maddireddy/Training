package serializationanddeserializationLearning.serialization;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Countries implements Serializable {

    int countryId;
    String countryName;
    int region;
}
