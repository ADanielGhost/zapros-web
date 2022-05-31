package org.polytech.zaprosweb.bean;

import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zaprosweb.entity.CriteriaEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    private long id;
    private String name;
    private List<Assessment> assessmentList;

    private Criteria() { }

    public static Criteria of(CriteriaEntity criteriaEntity) {
        Criteria criteria = new Criteria();
        criteria.id = criteriaEntity.getId();
        criteria.name = criteriaEntity.getName();

        criteria.assessmentList = criteriaEntity.getAssessmentSet()
            .stream()
            .map(Assessment::of)
            .collect(Collectors.toList());

        return criteria;
    }
}
