package com.bestlmc.lihuamao.commons.been;

import java.util.Date;

public class TRole {
    private String uid;

    private String roleName;

    private Date createTime;

    private Date updateTime;

    private Boolean status;

    private String introduce;

    private String menuUids;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getMenuUids() {
        return menuUids;
    }

    public void setMenuUids(String menuUids) {
        this.menuUids = menuUids == null ? null : menuUids.trim();
    }
}