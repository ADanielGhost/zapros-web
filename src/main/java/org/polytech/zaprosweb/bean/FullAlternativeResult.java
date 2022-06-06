package org.polytech.zaprosweb.bean;

import java.util.List;

import org.polytech.zapros.bean.alternative.AlternativeResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FullAlternativeResult {
    List<? extends AlternativeResult> rankOrderResult;
    List<? extends AlternativeResult> rankQVResult;
}
