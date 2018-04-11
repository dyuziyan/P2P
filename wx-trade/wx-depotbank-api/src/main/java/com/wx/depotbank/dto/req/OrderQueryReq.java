/******************************************************************************
 * Copyright (C) 2015 ShenZhen WuXingCaiFu Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳五星财富互联网金融服务有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.wx.depotbank.dto.req;

/**
 * @ClassName: OrderQueryReq
 * @version 1.0
 * @Desc: OrderQueryReq
 * @author xiaojun.zhou
 * @date 2018年3月21日下午5:57:02
 * @history v1.0
 */
public class OrderQueryReq extends BaseReq
{
    private static final long serialVersionUID = 7642810367822076991L;

    public String order_id;// 订单号

    public String getOrder_id()
    {
        return order_id;
    }

    public void setOrder_id(String order_id)
    {
        this.order_id = order_id;
    }

    @Override
    public String toString()
    {
        return super.toString() + "OrderQueryReq [order_id=" + order_id + "]";
    }
}
