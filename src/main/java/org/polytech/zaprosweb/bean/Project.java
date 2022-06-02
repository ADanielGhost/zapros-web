package org.polytech.zaprosweb.bean;

import java.util.List;

import org.polytech.zapros.bean.Criteria;
import org.polytech.zapros.bean.QuasiExpertConfig;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Project {
    private final long id;
    private final String name;
    private final List<Criteria> criteriaList;
    private final QuasiExpertConfig quasiExpertConfig;
    private final List<AlternativePackage> alternativePackages;
}
