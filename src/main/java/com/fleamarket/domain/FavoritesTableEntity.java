package com.fleamarket.domain;

import javax.persistence.*;

@Entity
@Table(name = "favorites_table", schema = "fleamarket", catalog = "")
public class FavoritesTableEntity {
    private int favoritesId;
    private String goodsName;
    private String goodsPhoto;
    private String favoritesUserName;
    private int goodsId;

    @Id
    @Column(name = "FavoritesId")
    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoritesTableEntity that = (FavoritesTableEntity) o;

        if (favoritesId != that.favoritesId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return favoritesId;
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
    @Column(name = "FavoritesUserName")
    public String getFavoritesUserName() {
        return favoritesUserName;
    }

    public void setFavoritesUserName(String favoritesUserName) {
        this.favoritesUserName = favoritesUserName;
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
    @Column(name = "GoodsId")
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
}
