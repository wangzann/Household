package com.hebtu.havefun.service;

import com.alibaba.fastjson.JSON;
import com.hebtu.havefun.dao.*;
import com.hebtu.havefun.entity.User.User;
import com.hebtu.havefun.entity.User.UserCollectHousehold;
import com.hebtu.havefun.entity.household.Household;
import com.hebtu.havefun.entity.household.HouseholdDetail;
import com.hebtu.havefun.entity.household.HouseholdTypeOfKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HouseholdService {
    @Resource
    HouseholdDao householdDao;
    @Resource
    HouseholdDetailDao householdDetailDao;
    @Resource
    HouseholdTypeOfKindDao householdTypeOfKindDao;
    @Resource
    UserDao userDao;
    @Resource
    UserCollectHouseholdDao userCollectHouseholdDao;

    /**
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 返回符合条件的List<Household>集合的Json
     */
    public String getHouseholdList(String city, Integer pageNum, Integer pageSize) {
        List<Household> content = new ArrayList<>();
        Sort sort;
        Pageable pageable;
        Page<Household> page;
        Specification<Household> specification = (Specification<Household>) (root, criteriaQuery, criteriaBuilder) -> {
            //所有能够返回给客户端用户业务需要的地方都需要筛选出还未开始的活动,展示用户发布的活动不需要筛选
            Predicate timePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("householdTime"), new Date());
            //筛选出有效的活动，即1表示有效
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), 1);
            Predicate locationPredicate = criteriaBuilder.equal(root.get("householdLocation").get("city"), city);
            System.out.println("时间：" + root.get("householdTime") + "地点：" + root.get("status") + "城市："+city);
            System.out.println(criteriaBuilder.and(timePredicate, statusPredicate, locationPredicate).toString());
            return criteriaBuilder.and(timePredicate, statusPredicate, locationPredicate);
        };
        //热门活动
        //根据活动收藏数量排序
        sort = Sort.by(Sort.Direction.DESC, "collectNum");
        System.out.println(sort.toString());
        //分页显示
        pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        page = householdDao.findAll(specification, pageable);
        content = page.getContent();
        System.out.println(content.size() != 0 ? JSON.toJSONString(content) : "empty");
        return content.size() != 0 ? JSON.toJSONString(content) : "empty";
    }

    /**
     * @param str 搜索的关键字
     * @param city 所在城市
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 返回符合条件的List<Household>集合的Json
     */
    public String  searchHouseholdList(String str,String city, Integer pageNum, Integer pageSize) {
        List<Household> content = new ArrayList<>();
        Sort sort;
        Pageable pageable;
        Page<Household> page;
        Specification<Household> specification = (Specification<Household>) (root, criteriaQuery, criteriaBuilder) -> {
            //所有能够返回给客户端用户业务需要的地方都需要筛选出还未开始的活动,展示用户发布的活动不需要筛选
            Predicate timePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("householdTime"), new Date());
            //筛选出有效的活动，即1表示有效
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), 1);
            Predicate locationPredicate = criteriaBuilder.equal(root.get("householdLocation").get("city"), city);
            Predicate findPredicate = criteriaBuilder.like(root.get("householdTitle"),"%"+str+"%");
            System.out.println(criteriaBuilder.and(timePredicate, statusPredicate, locationPredicate).toString());
            return criteriaBuilder.and(timePredicate, statusPredicate, locationPredicate,findPredicate);
        };
        //热门活动
        //根据活动收藏数量排序
        sort = Sort.by(Sort.Direction.DESC, "collectNum");
        System.out.println(sort.toString());
        //分页显示
        pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        page = householdDao.findAll(specification, pageable);
        content = page.getContent();
        System.out.println(content.size() != 0 ? JSON.toJSONString(content) : "empty");
        return content.size() != 0 ? JSON.toJSONString(content) : "empty";
    }

    public String getHouseholdDetail(Integer householdId) {
        Household household = householdDao.getOne(householdId);
        HouseholdDetail householdDetail = householdDetailDao.findHouseholdDetailByHousehold(household);
        return JSON.toJSONString(householdDetail);
    }

    /**
     * @param userId     用户id,注意不是getUserId,是getId
     * @param householdId 活动的id
     * @return 返回true或者false的字符串
     * @description 判断是否收藏
     */
    public boolean judgeCollected(Integer userId, Integer householdId) {
        Object object = householdDao.judgeCollectedHousehold(userId, householdId);
        return object != null;
    }

    /**
     * @param id       用户id,注意不是getUserId,是getId
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 返回List<UserCollecthousehold>集合的Json
     * @description 获取收藏的活动列表
     */
    public String getCollectedActivities(Integer id, Integer pageNum, Integer pageSize) {
        User user = userDao.getOne(id);
        Specification<UserCollectHousehold> spec = (Specification<UserCollectHousehold>) (root, criteriaQuery, criteriaBuilder) -> {
            //用户为user
            Predicate userPredicate = criteriaBuilder.equal(root.get("user"), user);
            //有效活动
            Predicate householdPredicate = criteriaBuilder.equal(root.get("household").get("status"), 1);
            return criteriaBuilder.and(householdPredicate, userPredicate);
        };
        //分页
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<UserCollectHousehold> page = userCollectHouseholdDao.findAll(spec, pageable);
        List<UserCollectHousehold> content = page.getContent();
        return content.size() == 0 ? "empty" : JSON.toJSONString(content);
    }

    /*
    * @return 返回List<HouseholdTypeOfKind>集合的Json
    * @description 获取所有家政分类
    * */
    public String getHouseholdClassify() {
        return JSON.toJSONString(householdTypeOfKindDao.findAll());
    }

    public String getHouseholdTypeList(Integer type, String city, Integer pageNum, Integer pageSize) {
        List<Household> content = new ArrayList<>();
        Pageable pageable;
        Page<Household> page;
        Specification<Household> specification = (Specification<Household>) (root, criteriaQuery, criteriaBuilder) -> {
            //所有能够返回给客户端用户业务需要的地方都需要筛选出还未开始的活动,展示用户发布的活动不需要筛选
            Predicate timePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("householdTime"), new Date());
            //筛选出有效的活动，即1表示有效
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), 1);
            Predicate locationPredicate = criteriaBuilder.equal(root.get("householdLocation").get("city"), city);
            Predicate typePredicate = criteriaBuilder.equal(root.get("householdTypeOfKind").get("id"),type);
            System.out.println("城市："+city+"，分类："+type);
            return criteriaBuilder.and(timePredicate, statusPredicate, locationPredicate,typePredicate);
        };
        //分页显示
        pageable = PageRequest.of(pageNum - 1, pageSize);
        page = householdDao.findAll(specification, pageable);
        content = page.getContent();
        System.out.println(content.size() != 0 ? JSON.toJSONString(content) : "empty");
        return content.size() != 0 ? JSON.toJSONString(content) : "empty";
    }
}
