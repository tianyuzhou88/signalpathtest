package core.ultils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Data {
    @JsonProperty("password")
    private String password;

    @JsonProperty("username")
    private String username;


    @JsonProperty("first")
    private String firstName;

    @JsonProperty("last")
    private String lastName;


    @JsonProperty("zip")
    private String zipCode;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public static Data get(String file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(file), Data.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
