package org.polytech.zaprosweb.bean;

import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zaprosweb.entity.AlternativePackageEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlternativePackage {
    private Long id;
    private String name;
    private List<Alternative> alternativeList;

    private AlternativePackage() { }

    public static AlternativePackage of(AlternativePackageEntity alternativePackageEntity) {
        AlternativePackage alternativePackage = new AlternativePackage();
        alternativePackage.id = alternativePackageEntity.getId();
        alternativePackage.name = alternativePackageEntity.getName();

        alternativePackage.alternativeList = alternativePackageEntity.getAlternativeSet()
            .stream()
            .map(Alternative::of)
            .collect(Collectors.toList());

        return alternativePackage;
    }
}
