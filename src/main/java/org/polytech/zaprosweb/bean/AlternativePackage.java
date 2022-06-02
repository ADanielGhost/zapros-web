package org.polytech.zaprosweb.bean;

import java.util.List;

import org.polytech.zapros.bean.Alternative;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class AlternativePackage {
    private final Long id;
    private final String name;
    private final List<Alternative> alternatives;
    private final List<User> users;
}
