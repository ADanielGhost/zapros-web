package org.polytech.zaprosweb.bean;

import org.polytech.zaprosweb.entity.AssessmentEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Assessment {
    private long id;
    private String name;
    private int rank;

    private Assessment() { }

    public static Assessment of (AssessmentEntity assessmentEntity) {
        Assessment assessment = new Assessment();
        assessment.id = assessmentEntity.getId();
        assessment.name = assessmentEntity.getName();
        assessment.rank = assessmentEntity.getRank();
        return assessment;
    }
}
