package org.polytech.zaprosweb.bean;

import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zaprosweb.entity.AlternativeEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Alternative {
    private long id;
    private String name;
    private List<Assessment> assessmentList;

    private Alternative() { }

    public static Alternative of(AlternativeEntity alternativeEntity) {
        Alternative alternative = new Alternative();
        alternative.id = alternativeEntity.getId();
        alternative.name = alternativeEntity.getName();

        alternative.assessmentList = alternativeEntity.getAssessmentSet()
            .stream()
            .map(Assessment::of)
            .collect(Collectors.toList());

        return alternative;
    }
}
