package by.it_academy.fitness.core.dto.page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageDTO<T> {
    @JsonProperty("number")
    @NotEmpty
    private int number;
    @JsonProperty("size")
    @NotEmpty
    @Size(min = 0, max = 20, message = "В странице от 0 до 20 пользователей")
    private int size;
    @JsonProperty("total_pages")
    @NotEmpty
    private int totalPages;
    @JsonProperty("total_elements")
    @NotEmpty
    private long totalElements;
    @JsonProperty("first")
    @NotEmpty
    private boolean first;
    @JsonProperty("number_of_elements")
    @NotEmpty
    private long numberOfElements;
    @JsonProperty("last")
    @NotEmpty
    private boolean last;
    @JsonProperty("content")
    @NotEmpty
    private List<T> content;

    public PageDTO(int number, int size, int totalPages, long totalElements, boolean first, long numberOfElements, boolean last, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public PageDTO() {
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public List<T> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageDTO that = (PageDTO) o;
        return number == that.number && size == that.size && totalPages == that.totalPages && totalElements == that.totalElements && first == that.first && numberOfElements == that.numberOfElements && last == that.last && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size, totalPages, totalElements, first, numberOfElements, last, content);
    }

    @Override
    public String toString() {
        return "UserPageDTO{" +
                "number=" + number +
                ", size=" + size +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", first=" + first +
                ", numberOfElements=" + numberOfElements +
                ", last=" + last +
                ", user=" + content +
                '}';
    }
}

