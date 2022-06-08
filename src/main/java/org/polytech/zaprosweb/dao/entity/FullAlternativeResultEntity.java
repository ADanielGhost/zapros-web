package org.polytech.zaprosweb.dao.entity;

import java.util.Set;

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

import org.polytech.zapros.bean.MethodType;
import org.polytech.zaprosweb.bean.AlternativeWebRankingResult;
import org.polytech.zaprosweb.bean.FullAlternativeResult;
import org.polytech.zaprosweb.bean.RankType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "fullAlternativeResults")
public class FullAlternativeResultEntity implements IEntity<FullAlternativeResult> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "fullAlternativeResult")
    private Set<AlternativeWebRankingResultEntity> rankResult;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @Override
    public FullAlternativeResult toModel() {
        FullAlternativeResult fullAlternativeResult = new FullAlternativeResult();
        fullAlternativeResult.setId(id);
        fullAlternativeResult.setMethodType(user.getMethodType());
        fullAlternativeResult.setRankOrderResult(rankResult.stream().filter(x -> x.getRankType() == RankType.ORDER).findFirst().get().toModel());
        fullAlternativeResult.setRankQVResult(rankResult.stream().filter(x -> x.getRankType() == RankType.QV).findFirst().get().toModel());

        return fullAlternativeResult;
    }
}
