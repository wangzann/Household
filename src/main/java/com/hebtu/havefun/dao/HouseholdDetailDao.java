package com.hebtu.havefun.dao;

import com.hebtu.havefun.entity.household.Household;
import com.hebtu.havefun.entity.household.HouseholdDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HouseholdDetailDao extends JpaRepository<HouseholdDetail, Integer>, JpaSpecificationExecutor<HouseholdDetail> {
    HouseholdDetail findHouseholdDetailByHousehold(Household household);

}
