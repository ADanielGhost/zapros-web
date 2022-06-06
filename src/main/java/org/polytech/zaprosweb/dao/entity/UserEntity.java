package org.polytech.zaprosweb.dao.entity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.polytech.zapros.bean.Answer;
import org.polytech.zapros.bean.MethodType;
import org.polytech.zaprosweb.bean.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
public class UserEntity implements IEntity<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private MethodType methodType;

    private Double threshold;

    @ManyToOne
    @JoinColumn(name = "alternative_package_id", nullable = false)
    private AlternativePackageEntity alternativePackage;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<AnswerEntity> answerSet = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<QuasiExpertEntity> quasiExpertSet = new HashSet<>();

    @Override
    public User toModel() {
        List<Answer> answers = answerSet.stream()
            .map(AnswerEntity::toModel)
            .sorted(Comparator.comparing(Answer::getId))
            .collect(Collectors.toList());

        return new User(id, name, email, methodType, threshold, answers);
    }
}
