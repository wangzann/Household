package com.hebtu.havefun.dao;

import com.hebtu.havefun.entity.household.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdDao extends JpaRepository<Household, Integer>, JpaSpecificationExecutor<Household> {
    //传入用户id和家政类型id判断用户是否收藏某家政类型
    @Query(value = "select * from collection_household where user_id = ?1 and household_id = ?2", nativeQuery = true)
    Object judgeCollectedHousehold(Integer userId, Integer householdId);
}
