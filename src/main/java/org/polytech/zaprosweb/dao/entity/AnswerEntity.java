package org.polytech.zaprosweb.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.polytech.zapros.bean.Answer;
import org.polytech.zapros.bean.Answer.AnswerAuthor;
import org.polytech.zapros.bean.Answer.AnswerType;
import org.polytech.zapros.bean.Assessment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "answers")
public class AnswerEntity implements IEntity<Answer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "assessment_i_id", nullable = false)
    private AssessmentEntity i;

    @ManyToOne
    @JoinColumn(name = "assessment_j_id", nullable = false)
    private AssessmentEntity j;

    @Column(nullable = false)
    private AnswerType answerType;

    @Column(nullable = false)
    private AnswerAuthor answerAuthor;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToOne(mappedBy = "firstAnswer")
    private QuasiExpertEntity quasiExpert;

    @Override
    public Answer toModel() {
        Answer answer = new Answer(i.toModel(), j.toModel(), answerType, answerAuthor);
        answer.setId(id);
        return answer;
    }
}
