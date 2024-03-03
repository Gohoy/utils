package com.gohoy.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionData {
    private Date transactionTime; //交易时间
    private String publicAccountId; //公众账号ID
    private String merchantNumber; //商户号
    private String subMerchantNumber; // 特约商户号
    private String deviceNumber; //设备号
    private String wechatOrderNumber; //微信订单号
    private String merchantOrderNumber; // 商户订单号
    private String userIdentifier; // 用户标识
    private String transactionType; // 交易类型
    private String transactionStatus; //交易状态
    private String paymentBank; // 付款银行
    private String currency; //货币种类
    private BigDecimal settlementOrderAmount; //应结订单金额
    private BigDecimal couponAmount; //代金券金额
    private String wechatRefundNumber; //微信退款单号
    private String merchantRefundNumber; //商户退款单号
    private BigDecimal refundAmount; //退款金额
    private BigDecimal rechargeRefundAmount; //充值券退款金额
    private String refundType; //退款类型
    private String refundStatus; //退款状态
    private String productName; //商品名称
    private String merchantDataPackage; //商户数据包
    private BigDecimal serviceFee; //手续费
    private String rate; //费率
    private BigDecimal orderAmount; //订单金额
    private BigDecimal refundRequestAmount; //申请退款金额
    private String rateRemark; //费率备注

    // Getter and Setter methods

    // You can also add constructor(s), equals, hashCode, and toString methods if needed
}
