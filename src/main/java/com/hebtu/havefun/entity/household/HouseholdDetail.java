package com.hebtu.havefun.entity.household;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Component
@Entity
@Table(name = "household_detail")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Proxy(lazy = false)
public class HouseholdDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //数据库表自增id
    private Integer householdDetailId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "household_id")
    private Household household;
    //家政详细信息
    @Column(name = "household_info")
    private String householdInfo;
    //其他信息
    @Column(name = "other_info")
    private String otherInfo;
    //家政图片
    @OneToMany(mappedBy = "householdDetail", cascade = CascadeType.ALL)
    private Set<HouseholdPicture> householdPictures;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getHouseholdDetailId() {
        return householdDetailId;
    }

    public void setHouseholdDetailId(Integer householdDetailId) {
        this.householdDetailId = householdDetailId;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public String getHouseholdInfo() {
        return householdInfo;
    }

    public void setHouseholdInfo(String householdInfo) {
        this.householdInfo = householdInfo;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Set<HouseholdPicture> getHouseholdPictures() {
        return householdPictures;
    }

    public void setHouseholdPictures(Set<HouseholdPicture> householdPictures) {
        this.householdPictures = householdPictures;
    }

    @Override
    public String toString() {
        return "HouseholdDetail{" +
                "householdDetailId=" + householdDetailId +
                ", household=" + household +
                ", householdInfo='" + householdInfo + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", householdPictures=" + householdPictures +
                '}';
    }
}
