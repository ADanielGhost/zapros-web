package org.polytech.zaprosweb.dao.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.polytech.zapros.bean.QuasiExpertConfig;
import org.polytech.zaprosweb.dao.converter.ListInteger2StringConverter;
import org.polytech.zaprosweb.dao.converter.Matrix2StringConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "quasiExpertConfigs")
public class QuasiExpertConfigEntity implements IEntity<QuasiExpertConfig> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int len;

    @Column(nullable = false)
    @Convert(converter = ListInteger2StringConverter.class)
    private List<Integer> indexes;

    @Column(nullable = false)
    @Convert(converter = Matrix2StringConverter.class)
    private int[][] initData;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Override
    public QuasiExpertConfig toModel() {
        return new QuasiExpertConfig(id, len, indexes, initData);
    }
}
