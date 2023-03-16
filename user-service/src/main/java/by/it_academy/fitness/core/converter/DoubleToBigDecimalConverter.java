package by.it_academy.fitness.core.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleToBigDecimalConverter extends StdConverter<Double, BigDecimal> {
    @Override
    public BigDecimal convert(Double aDouble) {
        return BigDecimal.valueOf(aDouble).setScale(2, RoundingMode.CEILING);
    }
}
