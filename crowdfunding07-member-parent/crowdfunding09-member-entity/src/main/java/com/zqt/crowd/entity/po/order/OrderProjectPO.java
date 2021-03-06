package com.zqt.crowd.entity.po.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zqtao
 * @description: 订单支持项目信息封装
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProjectPO {

    /**
     * 订单支持项目信息主键
     */
    private Integer id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 发起人
     */
    private String launchName;

    /**
     * 回报内容
     */
    private String returnContent;

    /**
     * 回报数量
     */
    private Integer returnCount;

    /**
     * 支持单价
     */
    private Integer supportPrice;

    /**
     * 配送费用
     */
    private Integer freight;

    /**
     * 订单 id
     */
    private Integer orderId;
}