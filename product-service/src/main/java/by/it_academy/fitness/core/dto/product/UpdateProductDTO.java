package by.it_academy.fitness.core.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.UUID;

public class UpdateProductDTO {
    @NotEmpty
    private AddProductDTO addProductDTO;
    @NonNull
    private Instant dtUpdate;
    @NotBlank
    private UUID uuid;

    public UpdateProductDTO(AddProductDTO addProductDTO, Instant dtUpdate, UUID uuid) {
        this.addProductDTO = addProductDTO;
        this.dtUpdate = dtUpdate;
        this.uuid = uuid;
    }

    public AddProductDTO getAddProductDTO() {
        return addProductDTO;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }
}
