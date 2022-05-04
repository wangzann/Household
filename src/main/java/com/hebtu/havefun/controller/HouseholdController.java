package com.hebtu.havefun.controller;

import com.hebtu.havefun.service.HouseholdService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("household")
public class HouseholdController {
    @Resource
    HouseholdService householdService;

    /**
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return List<household>集合的JSON串
     * @description 获取活动列表
     */
    @RequestMapping("/getHouseholdList")
    public String getHouseholdList(String city, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null || city == null) {
            System.out.println("getHouseholdList Error");
            return "ErrorParameter";
        }
        System.out.println("获取的字段:" + city + pageNum + pageSize);
        String households = householdService.getHouseholdList(city, pageNum, pageSize);
        return "empty".equals(households) ? "" : households;
    }

    @RequestMapping("/getHouseholdDetail")
    public String getHouseholdDetail(Integer householdId) {
        if (householdId == null) {
            System.out.println("getHouseholdDetail Error");
            return "ErrorParameter";
        }
        String householdDetail = householdService.getHouseholdDetail(householdId);
        return householdDetail != null ? householdDetail : "";
    }

    /**
     * @param id          用户id,注意不是getUserId,是getId
     * @param householdId 活动的id
     * @return 返回true或者false的字符串
     * @description 判断是否收藏
     */
    @RequestMapping("/judgeCollected")
    public String judgeCollected(Integer id, Integer householdId) {
        if (id == null || householdId == null) {
            System.out.println("judgeCollected Error");
            return "ErrorParameter";
        }
        boolean flag = householdService.judgeCollected(id, householdId);
        return flag ? "true" : "false";
    }


    /**
     * @param id       用户id,注意不是getUserId,是getId
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 返回List<UserCollecthousehold>集合的Json
     * @description 获取收藏的活动列表
     */
    @RequestMapping("/getCollectedActivities")
    public String getCollectedActivities(Integer id, Integer pageNum, Integer pageSize) {
        if (id == null || pageNum == null || pageSize == null) {
            System.out.println("getCollectedActivities Error");
            return "ErrorParameter";
        }
        return householdService.getCollectedActivities(id, pageNum, pageSize);
    }

    /**
     * @param city     所在城市
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 返回List<Household>集合的Json
     * @Param str 搜索的家政类型关键字
     * @description 获取满足条件的家政列表
     */
    @RequestMapping("/searchHouseholdList")
    public String searchHouseholdList(String str, Integer pageNum, Integer pageSize, String city) {
        if (str == null || pageNum == null || pageSize == null || city == null) {
            System.out.println("searchHouseholdList Error");
            return "ErrorParameter";
        }
        System.out.println("获取的字段:" + str + city + pageNum + pageSize);
        String households = householdService.searchHouseholdList(str, city, pageNum, pageSize);
        return "empty".equals(households) ? "" : households;
    }

    /**
     * @return List<HouseholdTypeOfKind>集合的JSON串
     * @description 获取所有家政分类
     */
    @RequestMapping("/getHouseholdClassify")
    public String getHouseholdClassify() {
        String householdClassify = householdService.getHouseholdClassify();
        return "empty".equals(householdClassify) ? "" : householdClassify;
    }

    /**
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return List<household>集合的JSON串
     * @description 获取活动列表
     */
    @RequestMapping("/getHouseholdTypeList")
    public String getHouseholdTypeList(Integer type, Integer pageNum, Integer pageSize, String city) {
        if (type == null || pageNum == null || pageSize == null || city == null) {
            System.out.println("getHouseholdTypeList Error");
            return "ErrorParameter";
        }
        System.out.println("获取的字段:" + type + city + pageNum + pageSize);
        String households = householdService.getHouseholdTypeList(type, city, pageNum, pageSize);
        return "empty".equals(households) ? "" : households;
    }
}
