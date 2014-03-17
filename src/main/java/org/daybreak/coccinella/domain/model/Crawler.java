package org.daybreak.coccinella.domain.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 * 抓取任务
 *
 * Created by Alan on 14-3-11.
 */
@Entity
@Table(name = "T_CRAWLER")
public class Crawler implements Serializable {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private long id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "DISCRIMINATION")
    private String discrimination;
    
    @ManyToOne(optional = false, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "AIC_ID", nullable = false)
    private AIC aic;

    @OneToMany(mappedBy = "crawler", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Parser> parsers = new ArrayList<>();

    @Column(name = "URL")
    private String url;
    
    @Column(name = "PARAMS")
    private String params;
    
    @Column(name = "ENCODE")
    private String encode = "utf-8";
    
    @Column(name = "METHOD")
    private HttpMethod method;
    
    @Column(name = "REFERER")
    private String referer;

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

    public AIC getAic() {
        return aic;
    }

    public void setAic(AIC aic) {
        this.aic = aic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public List<Parser> getParsers() {
        return parsers;
    }

    public void setParsers(List<Parser> parsers) {
        this.parsers = parsers;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }
}
