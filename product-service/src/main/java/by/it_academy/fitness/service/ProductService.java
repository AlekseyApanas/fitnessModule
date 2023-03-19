package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.audit.UserDTO;
import by.it_academy.fitness.core.dto.audit.UserHolder;
import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.product.AddProductDTO;
import by.it_academy.fitness.core.dto.product.ProductDTO;
import by.it_academy.fitness.core.dto.product.UpdateProductDTO;
import by.it_academy.fitness.core.exception.CheckDoubleException;
import by.it_academy.fitness.core.exception.CheckVersionException;
import by.it_academy.fitness.core.exception.NotFoundException;
import by.it_academy.fitness.dao.api.product.IProductDao;
import by.it_academy.fitness.entity.ProductEntity;
import by.it_academy.fitness.service.api.product.IProductService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductService implements IProductService {
    private final IProductDao dao;
    private final ConversionService conversionService;

    public ProductService(IProductDao dao, ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }

    @Override
    public void create(AddProductDTO productDTO) {
        ProductEntity productEntity = dao.findByTitle(productDTO.getTitle());
        if (productEntity != null) {
            throw new CheckDoubleException("Продукт с таким названием уже существует");
        } else {
            dao.save(Objects.requireNonNull(conversionService.convert(productDTO, ProductEntity.class)));
        }
        checkUserAndSend("Создана запись в журнале продуктов");
    }

    @Override
    public PageDTO<ProductDTO> get(int page, int size) {
        PageRequest paging = PageRequest.of(page, size);
        Page<ProductEntity> all = dao.findAll(paging);
        List<ProductDTO> productsPages = all.getContent().stream()
                .map(s -> conversionService.convert(s, ProductDTO.class))
                .collect(Collectors.toList());
        return new PageDTO<>(page, size, all.getTotalPages(), all.getTotalElements(), all.isFirst(), all.getNumberOfElements(), all.isLast(), productsPages);
    }

    @Override
    public void update(UpdateProductDTO productDTO) {
        ProductEntity productEntity = dao.findById(productDTO.getUuid()).orElseThrow(() -> new NotFoundException("Такого продукта не существует"));
        if (productEntity.getDtUpdate().toEpochMilli() == productDTO.getDtUpdate().toEpochMilli()) {
            productEntity.setTitle(productDTO.getAddProductDTO().getTitle());
            productEntity.setWeight(productDTO.getAddProductDTO().getWeight());
            productEntity.setCalories(productDTO.getAddProductDTO().getCalories());
            productEntity.setProteins(productDTO.getAddProductDTO().getProteins());
            productEntity.setFats(productDTO.getAddProductDTO().getFats());
            productEntity.setCarbohydrates(productDTO.getAddProductDTO().getCarbohydrates());
            dao.save(productEntity);
        } else throw new CheckVersionException("Такой версии не существует");
        checkUserAndSend("Обновлена запись в журнале продуктов");
    }

    @Override
    public ProductDTO get(UUID id) {
        ProductEntity productEntity = this.dao.findById(id).orElseThrow(() -> new NotFoundException("Такого продукта не существует"));
        return conversionService.convert(productEntity, ProductDTO.class);
    }

    private void checkUserAndSend(String actions) {
        UserHolder userHolder = new UserHolder();
        UserDTO user = userHolder.getUser();
        sendAudit(user, user.getUuid(), actions);
    }

    private void sendAudit(UserDTO userDto, UUID uuid, String actions) {
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