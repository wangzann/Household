package com.hebtu.havefun.entity.household;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@Entity
@Table(name = "household_location")
@Component
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Proxy(lazy = false)
public class HouseholdLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //数据库表自增id
    private Integer locationId;
    @Column(name = "province")
    //省
    private String Province;
    @Column(name = "city")
    //市
    private String city;
    @Column(name = "county")
    //县
    private String county;
    //详细地址
    @Column(name = "detailed_address")
    private String detailedAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "household_id")
    //一对一关联household对象
    private Household household;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Household gethousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    @Override
    public String toString() {
        return "householdLocation{" +
                "locationId=" + locationId +
                ", Province='" + Province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", household=" + household +
                '}';
    }
}
