package com.noklin.fastentask.data.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cd", schema = "public", catalog = "d1")
public class CdEntity {
    private String mTitle;
    private String mArtist;
    private String mCountry;
    private String mCompany;
    private Float mPrice;
    private Short mYear;

    @Id
    @Column(name = "title")
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Basic
    @Column(name = "artist")
    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    @Basic
    @Column(name = "company")
    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    @Basic
    @Column(name = "price")
    public Float getPrice() {
        return mPrice;
    }

    public void setPrice(Float price) {
        mPrice = price;
    }

    @Basic
    @Column(name = "year")
    public Short getYear() {
        return mYear;
    }

    public void setYear(Short year) {
        mYear = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdEntity cdEntity = (CdEntity) o;

        if (mTitle != null ? !mTitle.equals(cdEntity.mTitle) : cdEntity.mTitle != null)
            return false;
        if (mArtist != null ? !mArtist.equals(cdEntity.mArtist) : cdEntity.mArtist != null)
            return false;
        if (mCountry != null ? !mCountry.equals(cdEntity.mCountry) : cdEntity.mCountry != null)
            return false;
        if (mCompany != null ? !mCompany.equals(cdEntity.mCompany) : cdEntity.mCompany != null)
            return false;
        if (mPrice != null ? !mPrice.equals(cdEntity.mPrice) : cdEntity.mPrice != null)
            return false;
        if (mYear != null ? !mYear.equals(cdEntity.mYear) : cdEntity.mYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mTitle != null ? mTitle.hashCode() : 0;
        result = 31 * result + (mArtist != null ? mArtist.hashCode() : 0);
        result = 31 * result + (mCountry != null ? mCountry.hashCode() : 0);
        result = 31 * result + (mCompany != null ? mCompany.hashCode() : 0);
        result = 31 * result + (mPrice != null ? mPrice.hashCode() : 0);
        result = 31 * result + (mYear != null ? mYear.hashCode() : 0);
        return result;
    }
}
