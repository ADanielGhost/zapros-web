package org.polytech.zaprosweb.dao.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class Matrix2StringConverter implements AttributeConverter<int[][], String> {

    private final static String COLUMN_DELIMITER = ",";
    private final static String ROW_DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(int[][] attribute) {
        return Arrays.stream(attribute)
            .map(row -> Arrays.stream(row)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(COLUMN_DELIMITER)))
            .collect(Collectors.joining(ROW_DELIMITER));
    }

    @Override
    public int[][] convertToEntityAttribute(String dbData) {
        return Arrays
            .stream(dbData.split(ROW_DELIMITER))
            .map(row -> Arrays.stream(row.split(COLUMN_DELIMITER))
                .mapToInt(Integer::valueOf)
                .toArray())
            .toArray(int[][]::new);
    }
}
