package com.hebtu.havefun.dao;

import com.hebtu.havefun.entity.household.Household;
import com.hebtu.havefun.entity.household.HouseholdTypeOfKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HouseholdTypeOfKindDao  extends JpaRepository<HouseholdTypeOfKind, Integer>, JpaSpecificationExecutor<HouseholdTypeOfKind> {
}
