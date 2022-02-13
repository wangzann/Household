package com.hebtu.havefun.entity.household;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Component
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Table(name = "householdPicture")
@Proxy(lazy = false)
public class HouseholdPicture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //数据库表自增id
    private Integer pictureId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "household_id")
    //一对一关联activity对象
    private Household household;
    @ManyToOne
    @JoinColumn(name = "household_detail_id")
    //多对一关联活动详情对象
    private HouseholdDetail householdDetail;
    @Column(name = "picture_name")
    //图片路径
    private String pictureName;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public HouseholdDetail getHouseholdDetail() {
        return householdDetail;
    }

    public void setHouseholdDetail(HouseholdDetail householdDetail) {
        this.householdDetail = householdDetail;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
