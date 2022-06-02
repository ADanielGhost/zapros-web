package org.polytech.zaprosweb.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IterableListUtils {
    private IterableListUtils() { }

    public static <T> List<T> cast(Iterable<T> iterable) {
        return StreamSupport
            .stream(iterable.spliterator(),false)
            .collect(Collectors.toList());
    }
}
