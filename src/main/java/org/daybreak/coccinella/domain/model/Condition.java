package org.daybreak.coccinella.domain.model;

/**
 * Created by Alan on 14-3-17.
 */
public class Condition {

    private String province;

    private String enterpriseName;

    private boolean cache;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }
}
