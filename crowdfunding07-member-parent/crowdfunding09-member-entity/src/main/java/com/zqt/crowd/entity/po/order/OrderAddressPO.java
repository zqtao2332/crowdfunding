package com.zqt.crowd.entity.po.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 订单收货地址信息封装
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderAddressPO {

    /**
     * 收货地址主键
     */
    private Integer id;

    /**
     * 收件人
     */
    private String receiveName;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 会员 id
     */
    private Integer memberId;
}