package tn.esprit.orderservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String message;

    private static final Gson gson = new Gson();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getJson(String msg) {
        String jsonMsg = null;
        try {
            jsonMsg = mapper.writeValueAsString(new Response(msg));
            return jsonMsg;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Not Working";
        }
    }

    public static String getJson(String msg, Object data) {
        String jsonData = null;
        String jsonMsg = null;
        try {
            jsonData = mapper.writeValueAsString(data);
            System.out.println("Data = " + jsonData);
            jsonMsg = mapper.writeValueAsString(new Response(msg));
            System.out.println("Msg = " + jsonMsg);
            return fusion(jsonMsg, jsonData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Not Working";
        }
    }

    public static ResponseEntity getResponseEntity(String msg, Object data) {
        return ResponseEntity.status(200).body(getJson(msg, data));
    }

    public static ResponseEntity getResponseEntity(String msg) {
        return ResponseEntity.status(200).body(getJson(msg));
    }

    public static String fusion(String s1, String s2) {
        s1 = s1.substring(0, s1.length() - 1);
        s1 += ",\"data\": ";
        return s1 + s2;
    }

}
