package org.polytech.zaprosweb.bean;

import java.util.List;

import org.polytech.zapros.bean.Answer;
import org.polytech.zapros.bean.BuildingQesCheckResult;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BuildingQesCheckResultWrapper {

    private long id;
    private boolean isOver;
    private Answer answerForReplacing;
    private List<Answer> answerList;

    public BuildingQesCheckResult unwrap() {
        BuildingQesCheckResult result = new BuildingQesCheckResult();
        result.setId(id);
        result.setOver(isOver);
        result.setAnswerForReplacing(answerForReplacing);
        result.setAnswerList(answerList);
        return result;
    }
}
