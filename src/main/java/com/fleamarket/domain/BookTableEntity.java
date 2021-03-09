package com.fleamarket.domain;

import javax.persistence.*;

@Entity
@Table(name = "book_table", schema = "fleamarket", catalog = "")
public class BookTableEntity {
    private int bookId;
    private String bookName;
    private String bookType;
    private String bookSubarea;
    private int bookQuantity;
    private String bookPhoto;
    private String bookSynopsis;
    private String bookState;

    @Id
    @Column(name = "BookId")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "BookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "BookType")
    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    @Basic
    @Column(name = "BookSubarea")
    public String getBookSubarea() {
        return bookSubarea;
    }

    public void setBookSubarea(String bookSubarea) {
        this.bookSubarea = bookSubarea;
    }

    @Basic
    @Column(name = "BookQuantity")
    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    @Basic
    @Column(name = "BookPhoto")
    public String getBookPhoto() {
        return bookPhoto;
    }

    public void setBookPhoto(String bookPhoto) {
        this.bookPhoto = bookPhoto;
    }

    @Basic
    @Column(name = "BookSynopsis")
    public String getBookSynopsis() {
        return bookSynopsis;
    }

    public void setBookSynopsis(String bookSynopsis) {
        this.bookSynopsis = bookSynopsis;
    }

    @Basic
    @Column(name = "BookState")
    public String getBookState() {
        return bookState;
    }

    public void setBookState(String bookState) {
        this.bookState = bookState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookTableEntity that = (BookTableEntity) o;

        if (bookId != that.bookId) return false;
        if (bookQuantity != that.bookQuantity) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (bookType != null ? !bookType.equals(that.bookType) : that.bookType != null) return false;
        if (bookSubarea != null ? !bookSubarea.equals(that.bookSubarea) : that.bookSubarea != null) return false;
        if (bookPhoto != null ? !bookPhoto.equals(that.bookPhoto) : that.bookPhoto != null) return false;
        if (bookSynopsis != null ? !bookSynopsis.equals(that.bookSynopsis) : that.bookSynopsis != null) return false;
        if (bookState != null ? !bookState.equals(that.bookState) : that.bookState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (bookType != null ? bookType.hashCode() : 0);
        result = 31 * result + (bookSubarea != null ? bookSubarea.hashCode() : 0);
        result = 31 * result + bookQuantity;
        result = 31 * result + (bookPhoto != null ? bookPhoto.hashCode() : 0);
        result = 31 * result + (bookSynopsis != null ? bookSynopsis.hashCode() : 0);
        result = 31 * result + (bookState != null ? bookState.hashCode() : 0);
        return result;
    }
}
