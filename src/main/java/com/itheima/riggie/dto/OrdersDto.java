package com.itheima.riggie.dto;

import com.itheima.riggie.entity.OrderDetail;
import com.itheima.riggie.entity.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private int sunNum;

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
