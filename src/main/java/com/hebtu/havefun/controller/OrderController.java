package com.hebtu.havefun.controller;

import com.hebtu.havefun.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Resource
    OrderService orderService;

    /**
     * @param orderJson 客户端将封装好的Order类转换为Json串发送过来，
     * @return 返回一个添加成功或者失败"true"或者"false"
     * @description 添加订单，接收Order类的Json串数据
     */
    @RequestMapping("/addOrder")
    public String addOrder(String orderJson) {
        if (orderJson == null) {
            System.out.println("addOrder Error");
            return "ErrorParameter";
        }
        return orderService.addOrder(orderJson) ? "true" : "false";
    }

    /**
     * @Param orderId 订单id
     * @return 订单所有信息
     * */
    @RequestMapping("/getOrderDetail")
    public String getOrderDetail(Integer orderId) {
        if (orderId == null) {
            System.out.println("getOrderDetail Error");
            return "ErrorParameter";
        }
        String orderDetail = orderService.getOrderDetail(orderId);
        return orderDetail != null ? orderDetail : "";
    }

    /**
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return List<order>集合的JSON串
     * @description 获取订单列表
     */
    @RequestMapping("/getOrderList")
    public String getOrderList(Integer status, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null || status == null) {
            System.out.println("getOrderList Error");
            return "ErrorParameter";
        }
        System.out.println("获取的字段:" + status + pageNum + pageSize);
        String orders = orderService.getOrderList(status, pageNum, pageSize);
        return "empty".equals(orders) ? "" : orders;
    }
}
