package org.polytech.zaprosweb.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @Override
    public User toModel() {
        return new User(id, name, email, methodType, threshold);
    }
}
