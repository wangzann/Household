package com.hebtu.havefun.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hebtu.havefun.entity.household.Household;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "collection_household")
@Component
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Proxy(lazy = false)
public class UserCollectHousehold {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //数据库表自增id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    //多对一关联用户对象
    private User user;

    @ManyToOne
    @JoinColumn(name = "household_id")
    //多对一关联活动对象
    private Household household;

    @Column(name = "time")
    //收藏时间
    private Date collectTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "UserCollectHousehold{" +
                "id=" + id +
                ", user=" + user +
                ", household=" + household +
                ", collectTime=" + collectTime +
                '}';
    }
}
