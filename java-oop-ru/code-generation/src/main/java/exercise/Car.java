package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Getter
@AllArgsConstructor
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws Exception {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static Car deserialize(String json) throws Exception{
        return new ObjectMapper().readValue(json, Car.class);
    }
    // END
}
