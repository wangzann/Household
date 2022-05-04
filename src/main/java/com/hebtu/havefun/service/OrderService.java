package com.hebtu.havefun.service;

import com.alibaba.fastjson.JSON;
import com.hebtu.havefun.dao.OrderDao;
import com.hebtu.havefun.entity.User.UserPublishActivity;
import com.hebtu.havefun.entity.activity.*;
import com.hebtu.havefun.entity.household.Household;
import com.hebtu.havefun.entity.household.HouseholdDetail;
import com.hebtu.havefun.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class OrderService {
    @Resource
    OrderDao orderDao;

    /**
     * @param orderJson 客户端将封装好的Order类转换为Json串发送过来，
     * @return 返回一个添加成功或者失败"true"或者"false"
     * @description 添加订单，接收Order类的Json串数据
     */
    @Transactional
    @Rollback(value = false)
    public boolean addOrder(String orderJson) {
        //将OrderJson反序列化为Order
        Order order = JSON.parseObject(orderJson, Order.class);

        System.out.println(orderDao.save(order));
        return true;
    }

    /**
     * @Param orderId 订单id
     * @return 订单所有信息
     * */
    public String getOrderDetail(Integer orderId) {
        Order order = orderDao.getOne(orderId);
        return JSON.toJSONString(order);
    }

    /**
     * @param status 订单状态
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 返回符合条件的List<Order>集合的Json
     */
    public String getOrderList(Integer status, Integer pageNum, Integer pageSize) {
        List<Order> content = new ArrayList<>();
        Sort sort;
        Pageable pageable;
        Page<Order> page;
        Specification<Order> specification = (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            //筛选出满足条件的订单
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
            System.out.println(criteriaBuilder.and(statusPredicate).toString());
            return criteriaBuilder.and(statusPredicate);
        };
        //热门活动
        //根据活动收藏数量排序
        sort = Sort.by(Sort.Direction.DESC, "time");
        System.out.println(sort.toString());
        //分页显示
        pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        page = orderDao.findAll(specification, pageable);
        content = page.getContent();
        System.out.println(content.size() != 0 ? JSON.toJSONString(content) : "empty");
        return content.size() != 0 ? JSON.toJSONString(content) : "empty";
    }
}
