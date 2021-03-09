package com.fleamarket.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shoppingcart_table", schema = "fleamarket", catalog = "")
public class ShoppingcartTableEntity {
    private int shoppingId;
    private int shoppingGid;
    private String shoppingGname;
    private BigDecimal shoppingPrice;
    private String shoppingSname;
    private int shoppingQuantity;
    private String shoppingGphoto;
    private String shoppingUsername;

    @Id
    @Column(name = "ShoppingId")
    public int getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(int shoppingId) {
        this.shoppingId = shoppingId;
    }

    @Basic
    @Column(name = "ShoppingGid")
    public int getShoppingGid() {
        return shoppingGid;
    }

    public void setShoppingGid(int shoppingGid) {
        this.shoppingGid = shoppingGid;
    }

    @Basic
    @Column(name = "ShoppingGname")
    public String getShoppingGname() {
        return shoppingGname;
    }

    public void setShoppingGname(String shoppingGname) {
        this.shoppingGname = shoppingGname;
    }

    @Basic
    @Column(name = "ShoppingPrice")
    public BigDecimal getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(BigDecimal shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    @Basic
    @Column(name = "ShoppingSname")
    public String getShoppingSname() {
        return shoppingSname;
    }

    public void setShoppingSname(String shoppingSname) {
        this.shoppingSname = shoppingSname;
    }

    @Basic
    @Column(name = "ShoppingQuantity")
    public int getShoppingQuantity() {
        return shoppingQuantity;
    }

    public void setShoppingQuantity(int shoppingQuantity) {
        this.shoppingQuantity = shoppingQuantity;
    }

    @Basic
    @Column(name = "ShoppingGphoto")
    public String getShoppingGphoto() {
        return shoppingGphoto;
    }

    public void setShoppingGphoto(String shoppingGphoto) {
        this.shoppingGphoto = shoppingGphoto;
    }

    @Basic
    @Column(name = "ShoppingUsername")
    public String getShoppingUsername() {
        return shoppingUsername;
    }

    public void setShoppingUsername(String shoppingUsername) {
        this.shoppingUsername = shoppingUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingcartTableEntity that = (ShoppingcartTableEntity) o;

        if (shoppingId != that.shoppingId) return false;
        if (shoppingGid != that.shoppingGid) return false;
        if (shoppingQuantity != that.shoppingQuantity) return false;
        if (shoppingGname != null ? !shoppingGname.equals(that.shoppingGname) : that.shoppingGname != null)
            return false;
        if (shoppingPrice != null ? !shoppingPrice.equals(that.shoppingPrice) : that.shoppingPrice != null)
            return false;
        if (shoppingSname != null ? !shoppingSname.equals(that.shoppingSname) : that.shoppingSname != null)
            return false;
        if (shoppingGphoto != null ? !shoppingGphoto.equals(that.shoppingGphoto) : that.shoppingGphoto != null)
            return false;
        if (shoppingUsername != null ? !shoppingUsername.equals(that.shoppingUsername) : that.shoppingUsername != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shoppingId;
        result = 31 * result + shoppingGid;
        result = 31 * result + (shoppingGname != null ? shoppingGname.hashCode() : 0);
        result = 31 * result + (shoppingPrice != null ? shoppingPrice.hashCode() : 0);
        result = 31 * result + (shoppingSname != null ? shoppingSname.hashCode() : 0);
        result = 31 * result + shoppingQuantity;
        result = 31 * result + (shoppingGphoto != null ? shoppingGphoto.hashCode() : 0);
        result = 31 * result + (shoppingUsername != null ? shoppingUsername.hashCode() : 0);
        return result;
    }
}
