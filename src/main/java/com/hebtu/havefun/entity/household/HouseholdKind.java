package com.hebtu.havefun.entity.household;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "household_kind")
@Component
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Proxy(lazy = false)
public class HouseholdKind implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //数据库表自增id
    private Integer Id;
    @Column(name = "name")
    //大类名称
    private String kindName;
    @OneToMany(mappedBy = "householdKind", cascade = CascadeType.ALL)
    //一对一小类对象
    private Set<HouseholdTypeOfKind> householdTypeOfKind;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public Set<HouseholdTypeOfKind> getHouseholdTypeOfKind() {
        return householdTypeOfKind;
    }

    public void setHouseholdTypeOfKind(Set<HouseholdTypeOfKind> householdTypeOfKind) {
        this.householdTypeOfKind = householdTypeOfKind;
    }

    @Override
    public String toString() {
        return "HouseholdKind{" +
                "Id=" + Id +
                ", kindName='" + kindName + '\'' +
                ", householdTypeOfKind=" + householdTypeOfKind +
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
