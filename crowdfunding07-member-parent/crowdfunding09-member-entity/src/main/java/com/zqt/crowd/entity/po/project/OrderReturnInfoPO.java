package com.zqt.crowd.entity.po.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zqtao
 * @description: 回报信息实体类
 * @database_table: order_return_info
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderReturnInfoPO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 项目 id
     */
    private Integer projectId;

    /**
     * 回报类型：0 - 实物回报， 1 虚拟物品回报
     */
    private Integer type;

    /**
     * 支持金额
     */
    private Integer supportMoney;

    /**
     * 回报内容介绍
     */
    private String content;

    /**
     * 回报产品限额，“0”为不限回报数量
     */
    private Integer count;

    /**
     * 是否限制单笔购买数量，0表示不限购，1表示限购
     */
    private Integer signalPurchase;

    /**
     * 具体限购数量，如果单笔限购，那么具体的限购数量
     */
    private Integer purchase;

    /**
     * 运费，“0”为包邮
     */
    private Integer freight;

    /**
     * 是否开发票，0 - 不开发票， 1 - 开发票
     */
    private Integer invoice;

    /**
     * 项目结束后多少天向支持者发送回报
     *
     * 众筹结束后返还回报物品天数
     */
    private Integer returnDate;

    /**
     * 说明图片路径
     */
    private String describePicPath;
}