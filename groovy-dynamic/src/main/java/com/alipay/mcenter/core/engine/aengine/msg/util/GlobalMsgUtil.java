///**
// * Alipay.com Inc.
// * Copyright (c) 2004-2017 All Rights Reserved.
// */
//package com.alipay.mcenter.core.engine.aengine.msg.util;
//
//import com.alibaba.common.lang.StringUtil;
//import com.alipay.common.event.UniformEvent;
//import com.alipay.dto.product.unitradeprod.RefundDTO;
//import com.alipay.mcenter.core.util.MdataConstants;
//import com.alipay.mcenter.core.util.MdataParamUtil;
//import com.alipay.merchantsettle.service.common.facade.dto.SettleBill;
//import com.alipay.paycore.service.payment.result.PayOrderItem;
//import com.alipay.paycore.service.payment.result.RefundOrderItem;
//import com.iwallet.biz.event.business.TradeEvent;
//
///**
// * 国际消息工具类,可以在脚本中调用
// * @author chingsung.zihong
// * @version $Id: 2017-05-31 $
// */
//public class GlobalMsgUtil {
//
//    static final String[] tp = { "TP_S_TRADE", "TP_M_UTP", "TP_S_REFUND", "TP_M_SETTLE",
//            "TP_S_PAY", "TP_S_CHARGE_MESSAGE_SENDER" };
//
//    static final String[] ev = { "*", "EC-utp-refund,EC-utp-cancel", "EC_REFUND_FINISH",
//            "EC-MS_SETTLE_RESULT,MS_SETTLE_BATCH", "EC_PAY_FINISH,EC_INST_PAY_FINISH,EC_PAY_ACK",
//            "EC_CHARGE_BILL" };
//
//    public static Boolean isGlobalEvent(UniformEvent uniformEvent) {
//        if (uniformEvent != null) {
//            String topic = uniformEvent.getTopic();
//            int index = GlobalMsgUtil.arrayin(tp, topic);
//            if(index<0){
//               return false;
//            }else{
//                String eventcode = uniformEvent.getEventCode();
//                String events = ev[index];
//                if("*".equalsIgnoreCase(events)){
//                    return true;
//                }
//                if(events.indexOf(eventcode)>=0){
//                    return true;
//                }
//                return false;
//            }
//        }
//        return false;
//    }
//
//    public static int arrayin(String[] ss ,String key){
//        if(ss==null){
//            return -1;
//        }
//        for(int i=0;i<ss.length;i++){
//            if(key.equalsIgnoreCase(ss[i])){
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public static String getPid(UniformEvent uniformEvent) {
//        if (uniformEvent != null) {
//            if (uniformEvent.getEventPayload() instanceof TradeEvent) {
//                TradeEvent tradeEvent = (TradeEvent) uniformEvent.getEventPayload();
//                return tradeEvent.getSellerUserId();
//            }
//            if (uniformEvent.getEventPayload() instanceof SettleBill) {
//                SettleBill settleBill = (SettleBill) uniformEvent.getEventPayload();
//                return settleBill.getPartnerId();
//            }
//        }
//        return "";
//    }
//
//    public static String getBizProduct(UniformEvent uniformEvent) {
//        if (uniformEvent != null) {
//            if (uniformEvent.getEventPayload() instanceof TradeEvent) {
//                TradeEvent tradeEvent = (TradeEvent) uniformEvent.getEventPayload();
//                if(tradeEvent.getExtProperties()!=null && tradeEvent.getExtProperties().containsKey("bizProduct")){
//                    return tradeEvent.getExtProperties().get("bizProduct").toString();
//                }
//            }
//            if (uniformEvent.getEventPayload() instanceof SettleBill) {
//                SettleBill settleBill = (SettleBill) uniformEvent.getEventPayload();
//                return settleBill.getBizProduct();
//            }
//        }
//        return "";
//    }
//
//    public static String getGatheringType(UniformEvent uniformEvent){
//        if (uniformEvent != null) {
//            if (uniformEvent.getEventPayload() instanceof TradeEvent) {
//                TradeEvent tradeEvent = (TradeEvent) uniformEvent.getEventPayload();
//                return tradeEvent.getGatheringType();
//            }
//        }
//        return "";
//    }
//
//    public static Boolean isGlobalBiz(UniformEvent uniformEvent) {
//        if (uniformEvent != null) {
//            if (uniformEvent.getEventPayload() instanceof TradeEvent) {
//                TradeEvent tradeEvent = (TradeEvent) uniformEvent.getEventPayload();
//                if (MdataParamUtil.isContainSpecificValue(tradeEvent.getGatheringType(),
//                    MdataConstants.FOREX_GATHING_TYPE_KEY)) {
//                    return true;
//                }
//                return false;
//            }
//            if (uniformEvent.getEventPayload() instanceof SettleBill) {
//                SettleBill settleBill = (SettleBill) uniformEvent.getEventPayload();
//                try {
//                    if (MdataParamUtil.isContainSpecificValue(settleBill.getBizProduct(),
//                        MdataConstants.FOREX_SETTLE_ORDER_PRODUCTS_KEY)) {
//                        return true;
//                    }
//                } catch (Exception e) {
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static String getTradeNo(UniformEvent uniformEvent) {
//        if (uniformEvent != null) {
//            if (uniformEvent.getEventPayload() instanceof TradeEvent) {
//                TradeEvent tradeEvent = (TradeEvent) uniformEvent.getEventPayload();
//                return tradeEvent.getTradeNo();
//            }
//
//            if (uniformEvent.getEventPayload() instanceof SettleBill) {
//                SettleBill settleBill = (SettleBill) uniformEvent.getEventPayload();
//                return settleBill.getOrderNo();
//            }
//
//            if (uniformEvent.getEventPayload() instanceof RefundDTO) {
//                RefundDTO refundDTO = (RefundDTO) uniformEvent.getEventPayload();
//                return refundDTO.getTradeNo();
//            }
//
//            if (uniformEvent.getEventPayload() instanceof PayOrderItem) {
//                PayOrderItem payOrderItem = (PayOrderItem) uniformEvent.getEventPayload();
//                return payOrderItem.getBizNo();
//            }
//
//            if (uniformEvent.getEventPayload() instanceof RefundOrderItem) {
//                RefundOrderItem refundOrderItem = (RefundOrderItem) uniformEvent.getEventPayload();
//                return refundOrderItem.getBizNo();
//            }
//
//            return "";
//        }
//        return "";
//    }
//
//    public static String getGlobalType(UniformEvent uniformEvent) {
//        if (uniformEvent != null) {
//            if (uniformEvent.getEventPayload() instanceof TradeEvent) {
//                return "tradeEvent";
//            }
//
//            if (uniformEvent.getEventPayload() instanceof SettleBill) {
//                return "settleBill";
//            }
//
//            if (uniformEvent.getEventPayload() instanceof RefundDTO) {
//                return "refundDTO";
//            }
//
//            if (uniformEvent.getEventPayload() instanceof PayOrderItem) {
//                return "payOrderItem";
//            }
//
//            if (uniformEvent.getEventPayload() instanceof RefundOrderItem) {
//                return "payOrderItem";
//            }
//
//            return "";
//        }
//        return "";
//    }
//
//    public static boolean isRefund4TradeEvent(UniformEvent ue) {
//        TradeEvent te;
//        if (ue.getEventPayload() instanceof TradeEvent) {
//            te = (TradeEvent) ue.getEventPayload();
//        } else {
//            return false;
//        }
//        String bizProduct = te.getExtProperties().get("bizProduct").toString();
//        if ("EC_RefundFP".equals(ue.getEventCode()) || "EC-reverse".equals(ue.getEventCode())) {
//            String nodeExtraMemo = te.getExtProperties().getProperty("NOTE_EXTRA_MEMO");
//            if (StringUtil.contains(nodeExtraMemo, "ACTION^refund")) {
//                return true;
//            } else if (StringUtil.equals("OVERSEAS_MBARCODE_PAY", bizProduct)
//                       && (StringUtil.contains(nodeExtraMemo, "ACTION^reverse") || StringUtil
//                           .contains(nodeExtraMemo, "ACTION^cancel"))) {
//                return true;
//            }
//        } else if ("0301038".equals(ue.getEventCode())
//                   && ("NEW".equals(te.getExtProperties().get("FP_FOREX_PAY_MODE")) || "MC402001000000000001"
//                       .equals(bizProduct))) {
//            return true;
//        }
//        return false;
//    }
//
//}
//
//
