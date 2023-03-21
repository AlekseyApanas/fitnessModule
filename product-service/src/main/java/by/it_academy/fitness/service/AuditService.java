package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.audit.UserDTO;
import by.it_academy.fitness.web.utils.UserHolder;
import by.it_academy.fitness.service.api.product.IAuditService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class AuditService implements IAuditService {
    @Value("${spring.data.redis.urlaudit}")
    private String url;
    @Override
    public void checkUserAndSend(String actions,String type,UUID uuidService ) {
        UserHolder userHolder = new UserHolder();
        UserDTO user = userHolder.getUser();
        send(user, actions,type,uuidService);
    }


    private void send(UserDTO userDto, String actions,String type,UUID uuidService) {
        try {
            JSONObject user = new JSONObject();
            user.put("uuidUser", userDto.getUuid());
            user.put("mail", userDto.getMail());
            user.put("fio", userDto.getFio());
            user.put("role", userDto.getRole().substring(5));
            JSONObject object = new JSONObject();
            object.put("user", user);
            object.put("text", actions);
            object.put("type",type);
            object.put("uuidService", uuidService);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (JSONException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
