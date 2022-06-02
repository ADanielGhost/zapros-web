package org.polytech.zaprosweb.dao.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ListInteger2StringConverter implements AttributeConverter<List<Integer>, String> {

    private final static String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        return attribute.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(DELIMITER))
            .map(Integer::valueOf)
            .collect(Collectors.toList());
    }
}
