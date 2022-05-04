package com.hebtu.havefun.dao;

import com.hebtu.havefun.entity.User.User;
import com.hebtu.havefun.entity.User.UserCollectActivity;
import com.hebtu.havefun.entity.User.UserCollectHousehold;
import com.hebtu.havefun.entity.household.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserCollectHouseholdDao  extends JpaSpecificationExecutor<UserCollectHousehold>, JpaRepository<UserCollectHousehold,Integer> {
    //通过用户和家政种类查询收藏家政类UserCollectHousehold
    UserCollectHousehold findUserCollectHouseholdsByUserAndHousehold(User user, Household household);
}
