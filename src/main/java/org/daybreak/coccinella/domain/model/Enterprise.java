package org.daybreak.coccinella.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业
 *
 * Created by Alan on 14-3-11.
 */
@Entity
@Table(name = "T_ENTERPRISE")
public class Enterprise implements Serializable {
    
    public static final String SEARCH_ENTERPRISE_NAME_KEY = "待查询企业名称";
    
    public static final String NAME_KEY = "企业名称";
    
    public static final String REGISTRATION_NUMBER_KEY = "注册编号";
    
    public static final String ADDRESS_KEY = "地址";
    
    public static final String POSTAL_CODE_KEY = "邮编";
    
    public static final String AGENT_KEY = "法人";
    
    public static final String TYPE_KEY = "企业类型";
    
    public static final String REGISTERED_CAPITAL_KEY = "注册资本";
    
    public static final String SCOPE_KEY = "经营范围及方式";
    
    public static final String CATEGORY_KEY = "行业分类";
    
    public static final String ESTABLISHMENT_DATE_KEY = "成立日期";
    
    public static final String APPROVAL_DATE_KEY = "核准日期";
    
    public static final String CURRENT_STATUS_KEY = "当前状态";
    
    public static final String REGISTRATION_AUTHORITY_KEY = "登记机关";

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    // 企业名称
    @Column(name = "NAME")
    private String name;

    // 注册编号
    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    // 地址
    @Column(name = "ADDRESS")
    private String address;

    // 邮编
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    // 法人
    @Column(name = "AGENT")
    private String agent;

    // 企业类型
    @Column(name = "TYPE")
    private String type;

    // 注册资本
    @Column(name = "REGISTERED_CAPITAL")
    private String registeredCapital;

    // 经营范围及方式
    @Column(name = "SCOPE")
    private String scope;

    // 行业分类
    @Column(name = "CATEGORY")
    private String category;

    // 成立日期
    @Column(name = "ESTABLISHMENT_DATE")
    private String establishmentDate;

    // 核准日期
    @Column(name = "APPROVAL_DATE")
    private String approvalDate;

    // 当前状态
    @Column(name = "CURRENT_STATUS")
    private String currentStatus;

    // 登记机关
    @Column(name = "REGISTRATION_AUTHORITY")
    private String registrationAuthority;

    @Column(name = "CREATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updateDate;

    @ManyToOne(optional = false, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "AIC_ID", nullable = false)
    private AIC aic;

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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getRegistrationAuthority() {
        return registrationAuthority;
    }

    public void setRegistrationAuthority(String registrationAuthority) {
        this.registrationAuthority = registrationAuthority;
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

    public AIC getAic() {
        return aic;
    }

    public void setAic(AIC aic) {
        this.aic = aic;
    }
}
