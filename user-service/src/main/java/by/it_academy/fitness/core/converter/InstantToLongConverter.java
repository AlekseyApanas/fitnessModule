package by.it_academy.fitness.core.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;

public class InstantToLongConverter extends StdConverter<Instant, Long> {
    @Override
    public Long convert(Instant instant) {
        return instant.toEpochMilli();
    }
}
