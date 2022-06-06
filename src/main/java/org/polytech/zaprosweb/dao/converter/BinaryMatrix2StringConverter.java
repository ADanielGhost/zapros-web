package org.polytech.zaprosweb.dao.converter;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BinaryMatrix2StringConverter implements AttributeConverter<int[][], String> {

    private final static String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(int[][] attribute) {
        return Arrays.stream(attribute)
            .map(row -> Arrays.stream(row).reduce(0, (acc, e) -> acc * 10 + e))
            .map(x -> Integer.parseInt(String.valueOf(x), 2))
            .map(Integer::toHexString)
            .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public int[][] convertToEntityAttribute(String dbData) {
        String[] split = dbData.split(DELIMITER);

        return Arrays.stream(split)
            .map(x -> Integer.parseInt(x, 16))
            .map(Integer::toBinaryString)
            .map(x -> String.format("%1$" + split.length + "s", x).replace(' ', '0'))
            .map(x -> Arrays.stream(x.split("")).mapToInt(Integer::valueOf).toArray())
            .toArray(int[][]::new);
    }
}
