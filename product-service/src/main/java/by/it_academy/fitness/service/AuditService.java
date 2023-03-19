package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.audit.UserDTO;
import by.it_academy.fitness.core.dto.audit.UserHolder;
import by.it_academy.fitness.service.api.product.IAuditService;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class AuditService implements IAuditService {
    @Override
    public void checkUserAndSend(String actions) {
        UserHolder userHolder = new UserHolder();
        UserDTO user = userHolder.getUser();
        send(user, user.getUuid(), actions);
    }


    private void send(UserDTO userDto, UUID uuid, String actions) {
        try {
            JSONObject user = new JSONObject();
            user.put("uuidUser", userDto.getUuid());
            user.put("mail", userDto.getMail());
            user.put("fio", userDto.getFio());
            user.put("role", userDto.getRole());
            JSONObject object = new JSONObject();
            object.put("user", user);
            object.put("text", actions);
            object.put("type", "USER");
            object.put("uuidService", uuid);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://audit-service:8080/api/v1/audit"))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (JSONException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
