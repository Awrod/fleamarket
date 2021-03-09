package com.fleamarket.domain;

import javax.persistence.*;

@Entity
@Table(name = "da_table", schema = "fleamarket", catalog = "")
public class DaTableEntity {
    private String daUserName;
    private String userMoblie;
    private String userAddr;
    private String daName;
    private int daId;

    @Basic
    @Id
    @Column(name = "DaUserName")
    public String getDaUserName() {
        return daUserName;
    }

    public void setDaUserName(String daUserName) {
        this.daUserName = daUserName;
    }

    @Basic
    @Column(name = "UserMoblie")
    public String getUserMoblie() {
        return userMoblie;
    }

    public void setUserMoblie(String userMoblie) {
        this.userMoblie = userMoblie;
    }

    @Basic
    @Column(name = "UserAddr")
    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    @Basic
    @Column(name = "DaName")
    public String getDaName() {
        return daName;
    }

    public void setDaName(String daName) {
        this.daName = daName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DaTableEntity that = (DaTableEntity) o;

        if (daUserName != null ? !daUserName.equals(that.daUserName) : that.daUserName != null) return false;
        if (userMoblie != null ? !userMoblie.equals(that.userMoblie) : that.userMoblie != null) return false;
        if (userAddr != null ? !userAddr.equals(that.userAddr) : that.userAddr != null) return false;
        if (daName != null ? !daName.equals(that.daName) : that.daName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = daUserName != null ? daUserName.hashCode() : 0;
        result = 31 * result + (userMoblie != null ? userMoblie.hashCode() : 0);
        result = 31 * result + (userAddr != null ? userAddr.hashCode() : 0);
        result = 31 * result + (daName != null ? daName.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "DaId")
    public int getDaId() {
        return daId;
    }

    public void setDaId(int daId) {
        this.daId = daId;
    }
}
