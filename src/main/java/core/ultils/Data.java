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

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public static Data get(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), Data.class);
    }
}
