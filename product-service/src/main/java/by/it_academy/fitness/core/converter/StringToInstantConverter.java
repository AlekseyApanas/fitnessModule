package by.it_academy.fitness.core.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class StringToInstantConverter implements Converter<String, Instant> {
    @Override
    public Instant convert(String value) {
        return Instant.ofEpochMilli(Long.decode(value));
    }
}
