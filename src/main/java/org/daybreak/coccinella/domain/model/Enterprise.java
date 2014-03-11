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
    @Column(name = "POSTALCODE")
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
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "AIC_ID", insertable = false, updatable = false)
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

    public Date getCreateDate() { return createDate; }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public AIC getAic() { return aic; }

    public void setAic(AIC aic) {
        this.aic = aic;
    }
}
