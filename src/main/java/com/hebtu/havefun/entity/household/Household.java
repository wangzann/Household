package com.hebtu.havefun.entity.household;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hebtu.havefun.entity.activity.ActivityLocation;
import com.hebtu.havefun.entity.activity.Picture;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "household")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Proxy(lazy = false)
public class Household implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //数据库表自增id
    private Integer householdId;
    //名称
    @Column(name = "household_title")
    private String householdTitle;
    //服务有效期
    @Column(name = "household_time")
    private Date householdTime;
    //价格
    @Column(name = "household_cost")
    private String householdCost;
    //家政种类
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "household_type_of_kind")
    private HouseholdTypeOfKind householdTypeOfKind;
    //状态字段
    @Column(name = "status")
    private Integer status;
    //发布时间
    @Column(name = "release_time")
    private Date releaseTime;
    //收藏人数
    @Column(name = "collect_num")
    private Integer collectNum;
    //购买人数
    @Column(name = "buy_num")
    private Integer buyNum;
    //Todo
    //活动位置
    @OneToOne(mappedBy = "household", cascade = CascadeType.ALL)
    private HouseholdLocation householdLocation;
    //家政图片
    @OneToOne(mappedBy = "household", cascade = CascadeType.ALL)
    private HouseholdPicture householdPicture;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Integer householdId) {
        this.householdId = householdId;
    }

    public String getHouseholdTitle() {
        return householdTitle;
    }

    public void setHouseholdTitle(String householdTitle) {
        this.householdTitle = householdTitle;
    }

    public Date getHouseholdTime() {
        return householdTime;
    }

    public void setHouseholdTime(Date householdTime) {
        this.householdTime = householdTime;
    }

    public String getHouseholdCost() {
        return householdCost;
    }

    public void setHouseholdCost(String householdCost) {
        this.householdCost = householdCost;
    }

    public HouseholdTypeOfKind getHouseholdTypeOfKind() {
        return householdTypeOfKind;
    }

    public void setHouseholdTypeOfKind(HouseholdTypeOfKind householdTypeOfKind) {
        this.householdTypeOfKind = householdTypeOfKind;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public HouseholdPicture getHouseholdPicture() {
        return householdPicture;
    }

    public void setHouseholdPicture(HouseholdPicture householdPicture) {
        this.householdPicture = householdPicture;
    }

    public HouseholdLocation getHouseholdLocation() {
        return householdLocation;
    }

    public void setHouseholdLocation(HouseholdLocation householdLocation) {
        this.householdLocation = householdLocation;
    }

    @Override
    public String toString() {
        return "Household{" +
                "householdId=" + householdId +
                ", householdTitle='" + householdTitle + '\'' +
                ", householdTime='" + householdTime + '\'' +
                ", householdCost='" + householdCost + '\'' +
                ", status=" + status +
                ", releaseTime=" + releaseTime +
                ", collectNum=" + collectNum +
                ", buyNum=" + buyNum +
                '}';
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
