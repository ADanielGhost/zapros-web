package org.polytech.zaprosweb.bean;

import java.util.List;

import org.polytech.zapros.bean.Answer;
import org.polytech.zapros.bean.MethodType;

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
public class User {
    private long id;
    private String name;
    private String email;
    private MethodType methodType;
    private Double threshold;
    private List<Answer> answers;
}
