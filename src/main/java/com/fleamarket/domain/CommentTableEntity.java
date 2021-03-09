package com.fleamarket.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment_table", schema = "fleamarket", catalog = "")
public class CommentTableEntity {
    private int commentId;
    private String commentMain;
    private String commentUsername;
    private int commentGoodsid;
    private Timestamp commentDate;

    @Id
    @Column(name = "CommentId")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "CommentMain")
    public String getCommentMain() {
        return commentMain;
    }

    public void setCommentMain(String commentMain) {
        this.commentMain = commentMain;
    }

    @Basic
    @Column(name = "CommentUsername")
    public String getCommentUsername() {
        return commentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        this.commentUsername = commentUsername;
    }

    @Basic
    @Column(name = "CommentGoodsid")
    public int getCommentGoodsid() {
        return commentGoodsid;
    }

    public void setCommentGoodsid(int commentGoodsid) {
        this.commentGoodsid = commentGoodsid;
    }

    @Basic
    @Column(name = "CommentDate")
    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentTableEntity that = (CommentTableEntity) o;

        if (commentId != that.commentId) return false;
        if (commentGoodsid != that.commentGoodsid) return false;
        if (commentMain != null ? !commentMain.equals(that.commentMain) : that.commentMain != null) return false;
        if (commentUsername != null ? !commentUsername.equals(that.commentUsername) : that.commentUsername != null)
            return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (commentMain != null ? commentMain.hashCode() : 0);
        result = 31 * result + (commentUsername != null ? commentUsername.hashCode() : 0);
        result = 31 * result + commentGoodsid;
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        return result;
    }
}
