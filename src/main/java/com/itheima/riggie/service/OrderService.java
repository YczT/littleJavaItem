package com.itheima.riggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.riggie.entity.Orders;

public interface OrderService extends IService<Orders> {


    /**
     * 用于用户下单
     * @param orders
     */
    void submit(Orders orders);
}
