package com.hebtu.havefun.dao;

import com.hebtu.havefun.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
}
