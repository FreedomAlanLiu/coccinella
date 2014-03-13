package org.daybreak.coccinella.domain.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 抓取任务
 *
 * Created by Alan on 14-3-11.
 */
@Entity
@Table(name = "T_CRAWL_TASK")
public class CrawlTask implements Serializable {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "DISCRIMINATION")
    private String discrimination;
    
    @ManyToOne(optional = false, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "AIC_ID", nullable = false)
    private AIC aic;
    
    // http -> url, params, encode, method
    
    public enum HttpMethod {
        GET,
        POST
    }
    
    @Column(name = "URL")
    private String url;
    
    @Column(name = "PARAMS")
    private String params;
    
    @Column(name = "ENCODE")
    private String encode;
    
    @Column(name = "METHOD")
    private HttpMethod method;

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
}
