package com.hebtu.havefun.dao;

import com.hebtu.havefun.entity.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author PengHuAnZhi
 * @createTime 2020/11/21 15:22
 * @projectName HaveFun
 * @className ActivityDao.java
 * @description TODO
 */
public interface ActivityDao extends JpaRepository<Activity, Integer>, JpaSpecificationExecutor<Activity> {
    //传入用户id和活动id判断用户是否收藏某活动
    @Query(value = "select * from collection_activity where user_id = ?1 and activity_id = ?2", nativeQuery = true)
    Object judgeCollectedActivity(Integer userId, Integer activityId);
}