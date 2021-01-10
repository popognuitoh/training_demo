
package com.dm.demo1.pay.payController;


import com.dm.demo1.pay.request.QueryOrder;
import com.dm.demo1.pay.service.handler.AliPayMessageHandler;
import com.dm.demo1.pay.service.interceptor.AliPayMessageInterceptor;
import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.*;
import com.egzosn.pay.common.bean.PayOrder;
import com.egzosn.pay.common.bean.RefundOrder;
import com.egzosn.pay.common.http.HttpConfigStorage;
import com.egzosn.pay.common.http.UriVariables;
import com.egzosn.pay.common.util.sign.SignUtils;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 发起支付入口
 *
 * @author: egan
 * email egzosn@gmail.com
 * date 2016/11/18 0:25
 */
@RestController
@RequestMapping("/ali/")
public class AliPayController {

    private AliPayService service = null;
    @Resource
    private AutowireCapableBeanFactory spring;

    @PostConstruct
    public void init() {
        AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
        //aliPayConfigStorage.setPid("2088931384267995");
       // aliPayConfigStorage.setAppid("2021001190637203");
        //aliPayConfigStorage.setKeyPublic("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB");
        //aliPayConfigStorage.setKeyPrivate("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKroe/8h5vC4L6T+B2WdXiVwGsMvUKgb2XsKix6VY3m2wcf6tyzpNRDCNykbIwGtaeo7FshN+qZxdXHLiIam9goYncBit/8ojfLGy2gLxO/PXfzGxYGs0KsDZ+ryVPPmE34ZZ8jiJpR0ygzCFl8pN3QJPJRGTJn5+FTT9EF/9zyZAgMBAAECgYAktngcYC35u7cQXDk+jMVyiVhWYU2ULxdSpPspgLGzrZyG1saOcTIi/XVX8Spd6+B6nmLQeF/FbU3rOeuD8U2clzul2Z2YMbJ0FYay9oVZFfp5gTEFpFRTVfzqUaZQBIjJe/xHL9kQVqc5xHlE/LVA27/Kx3dbC35Y7B4EVBDYAQJBAOhsX8ZreWLKPhXiXHTyLmNKhOHJc+0tFH7Ktise/0rNspojU7o9prOatKpNylp9v6kux7migcMRdVUWWiVe+4ECQQC8PqsuEz7B0yqirQchRg1DbHjh64bw9Kj82EN1/NzOUd53tP9tg+SO97EzsibK1F7tOcuwqsa7n2aY48mQ+y0ZAkBndA2xcRcnvOOjtAz5VO8G7R12rse181HjGfG6AeMadbKg30aeaGCyIxN1loiSfNR5xsPJwibGIBg81mUrqzqBAkB+K6rkaPXJR9XtzvdWb/N3235yPkDlw7Z4MiOVM3RzvR/VMDV7m8lXoeDde2zQyeMOMYy6ztwA6WgE1bhGOnQRAkEAouUBv1sVdSBlsexX15qphOmAevzYrpufKgJIRLFWQxroXMS7FTesj+f+FmGrpPCxIde1dqJ8lqYLTyJmbzMPYw==");

        aliPayConfigStorage.setPid("2088931384267995");
        aliPayConfigStorage.setAppid("2021001190637203");
        aliPayConfigStorage.setKeyPublic("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkjnOZe1aD978YczHn2bZOcMWHCydzo3o1mG/wUGTLYuHG/VMpWuFVkJAU+6wgUMFRazzjZLRphgGh0zsWAhwFiys4W2rgGBYZO4yaApYjOhWXeT4UWmcTNGQi7AB+9b6S/WxRdbrlPYTDLSVr9UjPfLyG/NEk2lU9pKIaiMa5xaJNfW1J5NiMey0++c39vB2az9KJYPLyhrp5LiuJd66Snse5ZoGi/axNPr5WmXsjGfzH+P/7WAH5wOWJGKNnzSMBNklru7JpAUKz4FrQOHXzfQ45ApHZCv2RBGy+EzQTggvZgrny5TXdKAuHwkHDbks4eR1RDQRoKh3j5AXWsUenQIDAQAB");
        aliPayConfigStorage.setKeyPrivate("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCc1ObzIvq8BAIb/p7i4Qat7gbbToD9O1C4Xra2v2NN9XMyPR7FFac8TaTAPWXaxdrSyD3GrdqDVx76DIwb4k8yGG8qVuKkPtltvONsNFPOez33F+hE6SaYEEcLoLBtulFfiyVlL5YT+VcuDwpfEQiF01Wgd1Dt7brwbxOvQhRzB2DT5Tt2aI2FUzAabWFqumb8JhOGDboY7GbBn3Y7+P1d+YOsB6oaEyLJEbKKjPdjh3RTKzuG80anS0357MP9qCv+KCNa1Umpt+1/UEc/bTQOi71Abnqa2BxpHR+bsEDgpVRcG1BAfwbtu350bTOyEYwVSG3xu7VTzlo9aUoo7QDJAgMBAAECggEARTCER0XqWmJmT9VwYWWnXbqCWeRzccTmYsCeWVdsRyt/S+29EnPwGOGkSo+LzQ228YEYB/wRSBp6dxRsB607BT3UZVh5mr6DjC9mIgmf45tphgaNGUHHHhUoUs/spiwp1D3+HHr2+g7p0E8gYzqPiQLoZufc02srKwHGLG65uNkYtidSpbH4U972W4IlJlrt/Hurl3rheVrJH0h9NuB/hr0ncS5jtpIU2K/lJom474gFbwq+z8wGBPhhNUzPnMrn9b5EbS3QNnolJIXQ0XmSKteLgqj2Qp+ePXOM+dlMe7sXYbMNGI4r7sBNnfsxSKhR0ZWUqgMINKwdAH6yWalI0QKBgQDoawHtIxc735hlBtPJoav/+VezZ2Q3DKPHETH7WAWLxwCm+p3TP3RYOf4oGYVoHwFY0g8zuzdmfhTCpC+4A78ZhNdj0p6KNe0oA6PONysko948aT4deniq6FMY+E0cDraZP6Av7r3yNyx2zVTxwTajxLXXuMxOvPXiQRwsQorXJQKBgQCsvo9zOHUiPmGoEmr82d6dD9euNkflM/c26twQXy07LcvocIOWi/GyoxSEJHN8zfVVZGcbJ7HNHaMcvZauulL/HOudVVp/WIAblXLSBdHYjYDv7oh7IMNzwqFmOV2CeFmbu+ephtdhVCkUeJOliuBkkNvqJ4xdex7Tnt/7X9tT1QKBgAoahMXrTZpDuRtDNbfBOHnzO/XHm9qsyP3haHvVssAF0ahwoqXJT2R6eOsDWQJSvrs+vnxwOhNRjdN8Ubq9JvSUCs0DHfNCLuIbbmaLjWuVWBbmOFYNuessGFiwiqGaniOBWZRb25QJA/zSJkwwKxDpf0I2fWM64bGJRRFDf0elAoGANlyBqDDMK8qV0N567LdjZunlZd2PIoIH+jBOsJhDE6yFztAZmPbRSPYpLqyJ62ylGApQqCsFuRbqjdDb6oCYPdBMFWcW45xmNEgDqjRKpWtieB8ItDbLS1KkmBbVZomZKXkoniDhBrAFfXu0exQJKZysmDN56LEmMlJ9Cg5QZ0ECgYAq0/CQsnlEiiJ21ijN7ODjcypwnUILWGTtJhSjWLDFlgG9484p6ky5LnRWtiGgpZP8CmwlqqjPeZHiojW6Phg2IN5g+M3m4/fF6j+1rWaKHXPxzugMIVoe0jykOmIpo98+dN4Bu79INoHkEP74sojM8iCABPV4eAArPsDapNMDTw==");
        aliPayConfigStorage.setNotifyUrl("http://www.chinaforwork.com/ali/payBack.json");
        aliPayConfigStorage.setReturnUrl("http://www.chinaforwork.com");
        aliPayConfigStorage.setSignType(SignUtils.RSA2.getName());
        aliPayConfigStorage.setSeller("2088931384267995");
        aliPayConfigStorage.setInputCharset("utf-8");
        //是否为测试账号，沙箱环境
        aliPayConfigStorage.setTest(true);

        //请求连接池配置
        HttpConfigStorage httpConfigStorage = new HttpConfigStorage();
        //最大连接数
        httpConfigStorage.setMaxTotal(20);
        //默认的每个路由的最大连接数
        httpConfigStorage.setDefaultMaxPerRoute(10);
        service =  new AliPayService(aliPayConfigStorage, httpConfigStorage);
        //增加支付回调消息拦截器
        service.addPayMessageInterceptor(new AliPayMessageInterceptor());
        //设置回调消息处理
        service.setPayMessageHandler(spring.getBean(AliPayMessageHandler.class));
    }



    /**
     * 跳到支付页面
     * 针对实时支付,即时付款
     *
     * @param price       金额
     * @return 跳到支付页面
     */
    @RequestMapping(value = "toPay.html", produces = "text/html;charset=UTF-8")
    public String toPay( BigDecimal price) {
        //及时收款
        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), AliTransactionType.PAGE);
        //WAP
//        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), AliTransactionType.WAP);

//        Map orderInfo = service.orderInfo(order);
//        return service.buildRequest(orderInfo, MethodType.POST);

        return service.toPay(order);
    }




    /**
     * 获取支付预订单信息
     *
     * @return 支付预订单信息
     */
    @RequestMapping("app")
    public Map<String, Object> app() {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        PayOrder order = new PayOrder("订单title", "摘要", new BigDecimal(0.01), UUID.randomUUID().toString().replace("-", ""));

        //App支付
        order.setTransactionType(AliTransactionType.APP);
        data.put("orderInfo", UriVariables.getMapToParameters(service.app(order)));
        return data;
    }

    /**
     * 获取二维码图像
     * 二维码支付
     * @param price       金额
     * @return 二维码图像
     * @throws IOException IOException
     */
    @RequestMapping(value = "toQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
    public byte[] toQrPay( BigDecimal price) throws IOException {
        //获取对应的支付账户操作工具（可根据账户id）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(service.genQrPay( new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, System.currentTimeMillis()+"", AliTransactionType.SWEEPPAY)), "JPEG", baos);
        return baos.toByteArray();
    }
    /**
     * 获取二维码地址
     * 二维码支付
     * @param price       金额
     * @return 二维码图像
     */
    @RequestMapping(value = "getQrPay.json")
    public String getQrPay(BigDecimal price) {
        //获取对应的支付账户操作工具（可根据账户id）
        return service.getQrPay( new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, System.currentTimeMillis()+"", AliTransactionType.SWEEPPAY));
    }


    /**
     * 刷卡付,pos主动扫码付款(条码付)
     * @param authCode        授权码，条码等
     * @param price       金额
     * @return 支付结果
     */
    @RequestMapping(value = "microPay")
    public Map<String, Object> microPay(BigDecimal price, String authCode) {
        //获取对应的支付账户操作工具（可根据账户id）
        //条码付
        PayOrder order = new PayOrder("egan order", "egan order", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), AliTransactionType.BAR_CODE);
           //声波付
//        PayOrder order = new PayOrder("egan order", "egan order", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), AliTransactionType.WAVE_CODE);
        //设置授权码，条码等
        order.setAuthCode(authCode);
        //支付结果
        Map<String, Object> params = service.microPay(order);
        //校验
        if (service.verify(params)) {

            //支付校验通过后的处理
            //......业务逻辑处理块........


        }
        //这里开发者自行处理
        return params;
    }

    /**
     * 支付回调地址 方式一
     *
     * 方式二，{@link #payBack(HttpServletRequest)} 是属于简化方式， 试用与简单的业务场景
     *
     *
     * @param request 请求
     *
     * @return 返回对应的响应码
     * @see #payBack(HttpServletRequest)
     * @throws IOException IOException
     */
    @Deprecated
    @RequestMapping(value = "payBackBefore.json")
    public String payBackBefore(HttpServletRequest request) throws IOException {

        //获取支付方返回的对应参数
        Map<String, Object> params = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null == params) {
            return service.getPayOutMessage("fail", "失败").toMessage();
        }

        //校验
        if (service.verify(params)) {
            //这里处理业务逻辑
            //......业务逻辑处理块........
            return service.successPayOutMessage(null).toMessage();
        }

        return service.getPayOutMessage("fail", "失败").toMessage();
    }
    /**
     * 支付回调地址
     *
     * @param request 请求
     *
     * @return 返回对应的响应码
     *
     * 业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看{@link com.egzosn.pay.common.api.PayService#setPayMessageHandler(com.egzosn.pay.common.api.PayMessageHandler)}
     *
     * 如果未设置 {@link com.egzosn.pay.common.api.PayMessageHandler} 那么会使用默认的 {@link com.egzosn.pay.common.api.DefaultPayMessageHandler}
     * @throws IOException IOException
     */
    @RequestMapping(value = "payBack.json")
    public String payBack(HttpServletRequest request) throws IOException {
        //业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看com.egzosn.pay.common.api.PayService.setPayMessageHandler()
        return service.payBack(request.getParameterMap(), request.getInputStream()).toMessage();
    }


    /**
     * 查询
     *
     * @param order 订单的请求体
     * @return 返回查询回来的结果集，支付方原值返回
     */
    @RequestMapping("query")
    public Map<String, Object> query(QueryOrder order) {
        return service.query(order.getTradeNo(), order.getOutTradeNo());
    }

    /**
     * 统一收单交易结算接口
     *
     * @param order 订单的请求体
     * @return 返回查询回来的结果集，支付方原值返回
     */
    @RequestMapping("settle")
    public Map<String, Object> settle(OrderSettle order) {
       /* OrderSettle order = new OrderSettle();
        order.setTradeNo("支付宝单号");
        order.setOutRequestNo("商户单号");
        order.setAmount(new BigDecimal(100));
        order.setDesc("线下转账");*/
        return service.settle(order);
    }


    /**
     * 交易关闭接口
     *
     * @param order 订单的请求体
     * @return 返回支付方交易关闭后的结果
     */
    @RequestMapping("close")
    public Map<String, Object> close(QueryOrder order) {
        return service.close(order.getTradeNo(), order.getOutTradeNo());
    }
    /**
     * 交易c撤销接口
     *
     * @param order 订单的请求体
     * @return 返回支付方交易关闭后的结果
     */
    @RequestMapping("cancel")
    public Map<String, Object> cancel(QueryOrder order) {
        return service.cancel(order.getTradeNo(), order.getOutTradeNo());
    }

    /**
     * 申请退款接口
     *
     * @param order 订单的请求体
     * @return 返回支付方申请退款后的结果
     */
    @RequestMapping("refund")
    public Map<String, Object> refund(RefundOrder order) { return service.refund(order); }

    /**
     * 查询退款
     *
     * @param order 订单的请求体
     * @return 返回支付方查询退款后的结果
     */
    @RequestMapping("refundquery")
    public Map<String, Object> refundquery(RefundOrder order) {
        return service.refundquery(order);
    }

    /**
     * 下载对账单
     *
     * @param order 订单的请求体
     * @return 返回支付方下载对账单的结果
     */
    @RequestMapping("downloadbill")
    public Object downloadbill(QueryOrder order) {
        return service.downloadbill(order.getBillDate(), order.getBillType());
    }


    /**
     * 转账
     *
     * @param order 转账订单
     *
     * @return 对应的转账结果
     */
    @RequestMapping("transfer")
    public Map<String, Object> transfer(AliTransferOrder order) {
        order.setOutBizNo("转账单号");
        order.setTransAmount(new BigDecimal(10));
        order.setOrderTitle("转账业务的标题");
        order.setIdentity("参与方的唯一标识");
        order.setIdentityType("参与方的标识类型，目前支持如下类型：");
        order.setName("参与方真实姓名");
        order.setRemark("转账备注, 非必填");
        //单笔无密转账到支付宝账户
        order.setTransferType(AliTransferType.TRANS_ACCOUNT_NO_PWD);
        //单笔无密转账到银行卡
//        order.setTransferType(AliTransferType.TRANS_BANKCARD_NO_PWD);
        return service.transfer(order);
    }

    /**
     * 转账查询
     *
     * @param outNo   商户转账订单号
     * @param tradeNo 支付平台转账订单号
     *
     * @return 对应的转账订单
     */
    @RequestMapping("transferQuery")
    public Map<String, Object> transferQuery(String outNo, String tradeNo) {
        return service.transferQuery(outNo, tradeNo);
    }
}
