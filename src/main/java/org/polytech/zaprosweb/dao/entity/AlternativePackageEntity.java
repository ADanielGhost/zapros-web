package org.polytech.zaprosweb.dao.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.polytech.zapros.bean.Alternative;
import org.polytech.zaprosweb.bean.AlternativePackage;
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
@Table(name = "alternativePackages")
public class AlternativePackageEntity implements IEntity<AlternativePackage> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "alternativePackage")
    @ToString.Exclude
    private Set<AlternativeEntity> alternativeSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @OneToMany(mappedBy = "alternativePackage")
    private Set<UserEntity> userSet;

    @Override
    public AlternativePackage toModel() {
        List<Alternative> alternatives = alternativeSet.stream()
            .map(AlternativeEntity::toModel)
            .collect(Collectors.toList());

        List<User> users = userSet.stream()
            .map(UserEntity::toModel)
            .collect(Collectors.toList());

        return new AlternativePackage(id, name, alternatives, users);
    }
}
