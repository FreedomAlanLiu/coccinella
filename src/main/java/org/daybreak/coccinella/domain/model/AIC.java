package org.daybreak.coccinella.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updateDate;
    
    @OneToMany(mappedBy = "aic", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CrawlTask> crawlTaskList;
    
    @OneToMany(mappedBy = "aic", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Parser> parserSet;
    
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<CrawlTask> getCrawlTaskList() {
        return crawlTaskList;
    }

    public void setCrawlTaskList(List<CrawlTask> crawlTaskList) {
        this.crawlTaskList = crawlTaskList;
    }

    public Set<Parser> getParserSet() {
        return parserSet;
    }

    public void setParserSet(Set<Parser> parserSet) {
        this.parserSet = parserSet;
    }
}
