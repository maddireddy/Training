package serializationanddeserializationLearning.serialization;

public class MainCountries {

    public static void main(String[] args) {
        Countries countries = new Countries();

        countries.setCountryId(1);
        countries.setCountryName("USA");
        countries.setRegion(1);

        System.out.println(countries.hashCode());

        Countries countries1 = new Countries();

        countries.setCountryId(2);
        countries.setCountryName("IND");
        countries.setRegion(2);

        System.out.println(countries1.hashCode());
    }
}

