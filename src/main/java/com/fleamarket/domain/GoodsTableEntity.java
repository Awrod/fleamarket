package com.fleamarket.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "goods_table", schema = "fleamarket", catalog = "")
public class GoodsTableEntity {
    private int goodsId;
    private String goodsPhoto;
    private String goodsName;
    private String goodsType;
    private int goodsQuantity;
    private String goodsSynopsis;
    private String goodsState;
    private String userName;
    private BigDecimal goodsPrice;
    private String goodsPhotoo;
    private String goodsPhotot;
    private String goodsPhotos;

    @Id
    @Column(name = "GoodsId")
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "GoodsPhoto")
    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    @Basic
    @Column(name = "GoodsName")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "GoodsType")
    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Basic
    @Column(name = "GoodsQuantity")
    public int getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(int goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    @Basic
    @Column(name = "GoodsSynopsis")
    public String getGoodsSynopsis() {
        return goodsSynopsis;
    }

    public void setGoodsSynopsis(String goodsSynopsis) {
        this.goodsSynopsis = goodsSynopsis;
    }

    @Basic
    @Column(name = "GoodsState")
    public String getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(String goodsState) {
        this.goodsState = goodsState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsTableEntity that = (GoodsTableEntity) o;

        if (goodsId != that.goodsId) return false;
        if (goodsQuantity != that.goodsQuantity) return false;
        if (goodsPhoto != null ? !goodsPhoto.equals(that.goodsPhoto) : that.goodsPhoto != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (goodsType != null ? !goodsType.equals(that.goodsType) : that.goodsType != null) return false;
        if (goodsSynopsis != null ? !goodsSynopsis.equals(that.goodsSynopsis) : that.goodsSynopsis != null)
            return false;
        if (goodsState != null ? !goodsState.equals(that.goodsState) : that.goodsState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodsId;
        result = 31 * result + (goodsPhoto != null ? goodsPhoto.hashCode() : 0);
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (goodsType != null ? goodsType.hashCode() : 0);
        result = 31 * result + goodsQuantity;
        result = 31 * result + (goodsSynopsis != null ? goodsSynopsis.hashCode() : 0);
        result = 31 * result + (goodsState != null ? goodsState.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "UserName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "GoodsPrice")
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    @Basic
    @Column(name = "GoodsPhotoo")
    public String getGoodsPhotoo() {
        return goodsPhotoo;
    }

    public void setGoodsPhotoo(String goodsPhotoo) {
        this.goodsPhotoo = goodsPhotoo;
    }

    @Basic
    @Column(name = "GoodsPhotot")
    public String getGoodsPhotot() {
        return goodsPhotot;
    }

    public void setGoodsPhotot(String goodsPhotot) {
        this.goodsPhotot = goodsPhotot;
    }

    @Basic
    @Column(name = "GoodsPhotos")
    public String getGoodsPhotos() {
        return goodsPhotos;
    }

    public void setGoodsPhotos(String goodsPhotos) {
        this.goodsPhotos = goodsPhotos;
    }
}
