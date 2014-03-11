package org.daybreak.coccinella.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 工商局
 *
 * Created by Alan on 14-3-11.
 */
@Entity
@Table(name = "T_AIC")
public class AIC implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    // 工商局名称
    @Column(name = "NAME")
    @NotNull
    private String name;

    // 省份或直辖市
    @Column(name = "PROVINCE")
    @NotNull
    private String province;

    // 官网
    @Column(name = "WEBSITE")
    private String website;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
