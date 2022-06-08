package org.polytech.zaprosweb.bean;

import java.util.List;

import org.polytech.zapros.bean.MethodType;
import org.polytech.zapros.bean.alternative.AlternativeRankingResult;
import org.polytech.zapros.bean.alternative.AlternativeResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FullAlternativeResult {
    private long id;
    private MethodType methodType;
    private AlternativeWebRankingResult rankOrderResult;
    private AlternativeWebRankingResult rankQVResult;

    public FullAlternativeResult(MethodType methodType, AlternativeWebRankingResult rankOrderResult, AlternativeWebRankingResult rankQVResult) {
        this.methodType = methodType;
        this.rankOrderResult = rankOrderResult;
        this.rankQVResult = rankQVResult;
    }
}
