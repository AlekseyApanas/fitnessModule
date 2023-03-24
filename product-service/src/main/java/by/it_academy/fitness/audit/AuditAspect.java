package by.it_academy.fitness.audit;

import by.it_academy.fitness.core.dto.audit.UserDTO;
import by.it_academy.fitness.web.utils.UserHolder;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Aspect
@Component
public class AuditAspect {
    @Value("${spring.data.redis.urlaudit}")
    private String url;

    @AfterReturning(
            pointcut = "@annotation(audit)",
            argNames = "audit, uuid", returning = "uuid")
    public void checkUserAndSend(Audit audit, UUID uuid) {
        AuditEnum actions = audit.value();
        AuditTypeEnum type = audit.type();
        UserHolder userHolder = new UserHolder();
        UserDTO user = userHolder.getUser();
        sendAudit(user, uuid, actions.getDescription(), type.toString());
    }

    private void sendAudit(UserDTO userDto, UUID uuid, String actions, String type) {
        try {
            JSONObject user = new JSONObject();
            user.put("uuidUser", userDto.getUuid());
            user.put("mail", userDto.getMail());
            user.put("fio", userDto.getFio());
            user.put("role", userDto.getRole().substring(5));
            JSONObject object = new JSONObject();
            object.put("user", user);
            object.put("text", actions);
            object.put("type", type);
            object.put("uuidService", uuid);
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
