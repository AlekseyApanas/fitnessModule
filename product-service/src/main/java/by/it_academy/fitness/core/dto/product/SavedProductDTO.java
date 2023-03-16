package by.it_academy.fitness.core.dto.product;

import java.time.Instant;

public class SavedProductDTO {
    AddProductDTO addProductDTO;
    private Instant dtUpdate;
    private Instant dtCreate;

    public SavedProductDTO(AddProductDTO addProductDTO) {
        this.addProductDTO = addProductDTO;
        this.dtCreate = Instant.now();
        this.dtUpdate = this.dtCreate;
    }

    public AddProductDTO getAddProductDTO() {
        return addProductDTO;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }
}
