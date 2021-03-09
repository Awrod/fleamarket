package com.fleamarket.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_table", schema = "fleamarket", catalog = "")
public class UserTableEntity {
    private int userId;
    private String userName;
    private String userPass;
    private String userSex;
    private String userMoblie;
    private String userType;
    private String userPhoto;
    private String userState;
    private Integer userLevel;
    private String userReallyn;
    private String useridcard;

    public UserTableEntity(int userId, String userName, String userPass, String userSex, String userMoblie, String userType, String userPhoto) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userSex = userSex;
        this.userMoblie = userMoblie;
        this.userType = userType;
        this.userPhoto = userPhoto;
    }

    public UserTableEntity() {

    }

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "userPass")
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Basic
    @Column(name = "userSex")
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Basic
    @Column(name = "userMoblie")
    public String getUserMoblie() {
        return userMoblie;
    }

    public void setUserMoblie(String userMoblie) {
        this.userMoblie = userMoblie;
    }

    @Basic
    @Column(name = "userType")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "userPhoto")
    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTableEntity that = (UserTableEntity) o;

        if (userId != that.userId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPass != null ? !userPass.equals(that.userPass) : that.userPass != null) return false;
        if (userSex != null ? !userSex.equals(that.userSex) : that.userSex != null) return false;
        if (userMoblie != null ? !userMoblie.equals(that.userMoblie) : that.userMoblie != null) return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;
        if (userPhoto != null ? !userPhoto.equals(that.userPhoto) : that.userPhoto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPass != null ? userPass.hashCode() : 0);
        result = 31 * result + (userSex != null ? userSex.hashCode() : 0);
        result = 31 * result + (userMoblie != null ? userMoblie.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (userPhoto != null ? userPhoto.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "userState")
    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    @Basic
    @Column(name = "userLevel")
    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    @Basic
    @Column(name = "userReallyn")
    public String getUserReallyn() {
        return userReallyn;
    }

    public void setUserReallyn(String userReallyn) {
        this.userReallyn = userReallyn;
    }

    @Basic
    @Column(name = "useridcard")
    public String getUseridcard() {
        return useridcard;
    }

    public void setUseridcard(String useridcard) {
        this.useridcard = useridcard;
    }
}
