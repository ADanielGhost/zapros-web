package org.polytech.zaprosweb.bean;

import org.polytech.zapros.bean.MethodType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class User {
    private final long id;
    private final String name;
    private final String email;
    private final MethodType methodType;
    private final Double threshold;
}
