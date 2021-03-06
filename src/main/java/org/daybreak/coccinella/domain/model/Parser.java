package org.daybreak.coccinella.domain.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Alan
 */
@Entity
@Table(name = "T_PARSERS")
public class Parser implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DISCRIMINATION")
    private String discrimination;

    @Column(name = "NAME_KEY")
    private String nameKey;

    @Column(name = "CSS")
    private String css;

    @Column(name = "XPATH")
    private String xpath;

    @Column(name = "REGEX")
    private String regex;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updateDate;

    @ManyToOne(optional = false, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "CRAWLER_ID", nullable = false)
    private Crawler crawler;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscrimination() {
        return discrimination;
    }

    public void setDiscrimination(String discrimination) {
        this.discrimination = discrimination;
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

    public Crawler getCrawler() {
        return crawler;
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
}
