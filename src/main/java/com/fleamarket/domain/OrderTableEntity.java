package com.fleamarket.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "order_table", schema = "fleamarket", catalog = "")
public class OrderTableEntity {
    private int orderId;
    private String ordergName;
    private String orderbName;
    private String ordersName;
    private String orderAddr;
    private String orderMoblie;
    private Timestamp orderDate;
    private String orderbState;
    private String ordersState;
    private String orderUserN;
    private BigDecimal orderPrice;
    private int orderGid;
    private int ordergQuantity;

    @Id
    @Column(name = "OrderId")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "OrdergName")
    public String getOrdergName() {
        return ordergName;
    }

    public void setOrdergName(String ordergName) {
        this.ordergName = ordergName;
    }

    @Basic
    @Column(name = "OrderbName")
    public String getOrderbName() {
        return orderbName;
    }

    public void setOrderbName(String orderbName) {
        this.orderbName = orderbName;
    }

    @Basic
    @Column(name = "OrdersName")
    public String getOrdersName() {
        return ordersName;
    }

    public void setOrdersName(String ordersName) {
        this.ordersName = ordersName;
    }

    @Basic
    @Column(name = "OrderAddr")
    public String getOrderAddr() {
        return orderAddr;
    }

    public void setOrderAddr(String orderAddr) {
        this.orderAddr = orderAddr;
    }

    @Basic
    @Column(name = "OrderMoblie")
    public String getOrderMoblie() {
        return orderMoblie;
    }

    public void setOrderMoblie(String orderMoblie) {
        this.orderMoblie = orderMoblie;
    }

    @Basic
    @Column(name = "OrderDate")
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "OrderbState")
    public String getOrderbState() {
        return orderbState;
    }

    public void setOrderbState(String orderbState) {
        this.orderbState = orderbState;
    }

    @Basic
    @Column(name = "OrdersState")
    public String getOrdersState() {
        return ordersState;
    }

    public void setOrdersState(String ordersState) {
        this.ordersState = ordersState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderTableEntity that = (OrderTableEntity) o;

        if (orderId != that.orderId) return false;
        if (ordergName != null ? !ordergName.equals(that.ordergName) : that.ordergName != null) return false;
        if (orderbName != null ? !orderbName.equals(that.orderbName) : that.orderbName != null) return false;
        if (ordersName != null ? !ordersName.equals(that.ordersName) : that.ordersName != null) return false;
        if (orderAddr != null ? !orderAddr.equals(that.orderAddr) : that.orderAddr != null) return false;
        if (orderMoblie != null ? !orderMoblie.equals(that.orderMoblie) : that.orderMoblie != null) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (orderbState != null ? !orderbState.equals(that.orderbState) : that.orderbState != null) return false;
        if (ordersState != null ? !ordersState.equals(that.ordersState) : that.ordersState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (ordergName != null ? ordergName.hashCode() : 0);
        result = 31 * result + (orderbName != null ? orderbName.hashCode() : 0);
        result = 31 * result + (ordersName != null ? ordersName.hashCode() : 0);
        result = 31 * result + (orderAddr != null ? orderAddr.hashCode() : 0);
        result = 31 * result + (orderMoblie != null ? orderMoblie.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderbState != null ? orderbState.hashCode() : 0);
        result = 31 * result + (ordersState != null ? ordersState.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "OrderUserN")
    public String getOrderUserN() {
        return orderUserN;
    }

    public void setOrderUserN(String orderUserN) {
        this.orderUserN = orderUserN;
    }

    @Basic
    @Column(name = "OrderPrice")
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Basic
    @Column(name = "OrderGid")
    public int getOrderGid() {
        return orderGid;
    }

    public void setOrderGid(int orderGid) {
        this.orderGid = orderGid;
    }

    @Basic
    @Column(name = "OrdergQuantity")
    public int getOrdergQuantity() {
        return ordergQuantity;
    }

    public void setOrdergQuantity(int ordergQuantity) {
        this.ordergQuantity = ordergQuantity;
    }
}
