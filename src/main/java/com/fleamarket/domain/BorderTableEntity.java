package com.fleamarket.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "border_table", schema = "fleamarket", catalog = "")
public class BorderTableEntity {
    private int borderId;
    private String borderbName;
    private String borderbokName;
    private String borderSubarea;
    private String borderState;
    private Timestamp borderTime;

    @Id
    @Column(name = "BorderId")
    public int getBorderId() {
        return borderId;
    }

    public void setBorderId(int borderId) {
        this.borderId = borderId;
    }

    @Basic
    @Column(name = "BorderbName")
    public String getBorderbName() {
        return borderbName;
    }

    public void setBorderbName(String borderbName) {
        this.borderbName = borderbName;
    }

    @Basic
    @Column(name = "BorderbokName")
    public String getBorderbokName() {
        return borderbokName;
    }

    public void setBorderbokName(String borderbokName) {
        this.borderbokName = borderbokName;
    }

    @Basic
    @Column(name = "BorderSubarea")
    public String getBorderSubarea() {
        return borderSubarea;
    }

    public void setBorderSubarea(String borderSubarea) {
        this.borderSubarea = borderSubarea;
    }

    @Basic
    @Column(name = "BorderState")
    public String getBorderState() {
        return borderState;
    }

    public void setBorderState(String borderState) {
        this.borderState = borderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorderTableEntity that = (BorderTableEntity) o;

        if (borderId != that.borderId) return false;
        if (borderbName != null ? !borderbName.equals(that.borderbName) : that.borderbName != null) return false;
        if (borderbokName != null ? !borderbokName.equals(that.borderbokName) : that.borderbokName != null)
            return false;
        if (borderSubarea != null ? !borderSubarea.equals(that.borderSubarea) : that.borderSubarea != null)
            return false;
        if (borderState != null ? !borderState.equals(that.borderState) : that.borderState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = borderId;
        result = 31 * result + (borderbName != null ? borderbName.hashCode() : 0);
        result = 31 * result + (borderbokName != null ? borderbokName.hashCode() : 0);
        result = 31 * result + (borderSubarea != null ? borderSubarea.hashCode() : 0);
        result = 31 * result + (borderState != null ? borderState.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "BorderTime")
    public Timestamp getBorderTime() {
        return borderTime;
    }

    public void setBorderTime(Timestamp borderTime) {
        this.borderTime = borderTime;
    }
}
