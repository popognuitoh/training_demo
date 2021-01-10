


package com.dm.demo1.pay.dao;

import com.dm.demo1.pay.entity.ApyAccount;
import com.dm.demo1.pay.entity.PayType;
import com.egzosn.pay.common.bean.MsgType;
import com.egzosn.pay.common.util.sign.SignUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 账户
 * @author: egan
 * email egzosn@gmail.com
 * date 2016/11/18 1:21
 */
//@Repository
public class ApyAccountRepository {

    // 这里简单模拟，引入orm等框架之后可自行删除
    public static Map<Integer, ApyAccount> apyAccounts = new HashMap<>();

    /**
     * 这里简单初始化，引入orm等框架之后可自行删除
     */
    {
        ApyAccount apyAccount1 = new ApyAccount();
        apyAccount1.setPayId(1);
        apyAccount1.setPartner("2088931384267995");
        apyAccount1.setAppid("2021001190637203");
        // TODO 2017/2/9 16:20 author: egan  sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5的情况
        apyAccount1.setPublicKey("fmDrAeqGhMiyRGyioz3Y4d0Uys7hvNGp0tN+ezD/agr/igjWtVJqbftf1BHP200Dou9QG56mtgcaR0fm7BA4KVUXBtQQH8G7bt+dG0zshGMFUht8bu1U85aPWlKKO0AyQIDAQAB");
        apyAccount1.setPrivateKey("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCc1ObzIvq8BAIb/p7i4Qat7gbbToD9O1C4Xra2v2NN9XMyPR7FFac8TaTAPWXaxdrSyD3GrdqDVx76DIwb4k8yGG8qVuKkPtltvONsNFPOez33F+hE6SaYEEcLoLBtulFfiyVlL5YT+VcuDwpfEQiF01Wgd1Dt7brwbxOvQhRzB2DT5Tt2aI2FUzAabWFqumb8JhOGDboY7GbBn3Y7+P1d+YOsB6oaEyLJEbKKjPdjh3RTKzuG80anS0357MP9qCv+KCNa1Umpt+1/UEc/bTQOi71Abnqa2BxpHR+bsEDgpVRcG1BAfwbtu350bTOyEYwVSG3xu7VTzlo9aUoo7QDJAgMBAAECggEARTCER0XqWmJmT9VwYWWnXbqCWeRzccTmYsCeWVdsRyt/S+29EnPwGOGkSo+LzQ228YEYB/wRSBp6dxRsB607BT3UZVh5mr6DjC9mIgmf45tphgaNGUHHHhUoUs/spiwp1D3+HHr2+g7p0E8gYzqPiQLoZufc02srKwHGLG65uNkYtidSpbH4U972W4IlJlrt/Hurl3rheVrJH0h9NuB/hr0ncS5jtpIU2K/lJom474gFbwq+z8wGBPhhNUzPnMrn9b5EbS3QNnolJIXQ0XmSKteLgqj2Qp+ePXOM+dlMe7sXYbMNGI4r7sBNnfsxSKhR0ZWUqgMINKwdAH6yWalI0QKBgQDoawHtIxc735hlBtPJoav/+VezZ2Q3DKPHETH7WAWLxwCm+p3TP3RYOf4oGYVoHwFY0g8zuzdmfhTCpC+4A78ZhNdj0p6KNe0oA6PONysko948aT4deniq6FMY+E0cDraZP6Av7r3yNyx2zVTxwTajxLXXuMxOvPXiQRwsQorXJQKBgQCsvo9zOHUiPmGoEmr82d6dD9euNkflM/c26twQXy07LcvocIOWi/GyoxSEJHN8zfVVZGcbJ7HNHaMcvZauulL/HOudVVp/WIAblXLSBdHYjYDv7oh7IMNzwqFmOV2CeFmbu+ephtdhVCkUeJOliuBkkNvqJ4xdex7Tnt/7X9tT1QKBgAoahMXrTZpDuRtDNbfBOHnzO/XHm9qsyP3haHvVssAF0ahwoqXJT2R6eOsDWQJSvrs+vnxwOhNRjdN8Ubq9JvSUCs0DHfNCLuIbbmaLjWuVWBbmOFYNuessGFiwiqGaniOBWZRb25QJA/zSJkwwKxDpf0I2fWM64bGJRRFDf0elAoGANlyBqDDMK8qV0N567LdjZunlZd2PIoIH+jBOsJhDE6yFztAZmPbRSPYpLqyJ62ylGApQqCsFuRbqjdDb6oCYPdBMFWcW45xmNEgDqjRKpWtieB8ItDbLS1KkmBbVZomZKXkoniDhBrAFfXu0exQJKZysmDN56LEmMlJ9Cg5QZ0ECgYAq0/CQsnlEiiJ21ijN7ODjcypwnUILWGTtJhSjWLDFlgG9484p6ky5LnRWtiGgpZP8CmwlqqjPeZHiojW6Phg2IN5g+M3m4/fF6j+1rWaKHXPxzugMIVoe0jykOmIpo98+dN4Bu79INoHkEP74sojM8iCABPV4eAArPsDapNMDTw==");
        apyAccount1.setNotifyUrl("http://www.chinaforwork.com/ali/payBack.json");
        // 无需同步回调可不填
        apyAccount1.setReturnUrl("http://www.chinaforwork.com");
        apyAccount1.setInputCharset("UTF-8");
        apyAccount1.setSeller("2088931384267995");
        apyAccount1.setSignType(SignUtils.RSA2.name());
        apyAccount1.setPayType(PayType.aliPay);
        apyAccount1.setMsgType(MsgType.text);
        //设置测试环境
        apyAccount1.setTest(true);
        apyAccounts.put(apyAccount1.getPayId(), apyAccount1);

        ApyAccount apyAccount2 = new ApyAccount();
        apyAccount2.setPayId(2);
        apyAccount2.setPartner("1469188802");
        apyAccount2.setAppid("wx3344f4aed352deae");
        // TODO 2017/2/9 16:20 author: egan  sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5的情况
        apyAccount2.setPublicKey("991ded080***************f7fc61095");
        apyAccount2.setPrivateKey("991ded080***************f7fc61095");
        apyAccount2.setNotifyUrl("http://pay.egzosn.com/payBack2.json");
        // 无需同步回调可不填
        apyAccount2.setReturnUrl("http://pay.egzosn.com");
        apyAccount2.setInputCharset("UTF-8");
        apyAccount2.setSeller("1469188802");
        apyAccount2.setSignType(SignUtils.MD5.name());
        apyAccount2.setPayType(PayType.wxPay);
        apyAccount2.setMsgType(MsgType.xml);
        //设置测试环境
        apyAccount2.setTest(false);
        apyAccounts.put(apyAccount2.getPayId(), apyAccount2);

        ApyAccount apyAccount3 = new ApyAccount();
        apyAccount3.setPayId(3);
        apyAccount3.setPartner("12****601");
        apyAccount3.setAppid("wxa39*****ba9e9");
        apyAccount3.setPublicKey("48gf0i************h9eiut9");
        apyAccount3.setPrivateKey("48gf0i************h9eiut9");
        apyAccount3.setNotifyUrl("http://pay.egan.in/payBack3.json");
        // 无需同步回调可不填  app填这个就可以
        apyAccount3.setReturnUrl("http://pay.egan.in/payBack3.json");
        apyAccount3.setSeller("12****601");
        apyAccount3.setInputCharset("UTF-8");
        apyAccount3.setSignType(SignUtils.MD5.name());
        apyAccount3.setPayType(PayType.wxPay);
        apyAccount3.setMsgType(MsgType.xml);
        apyAccounts.put(apyAccount3.getPayId(), apyAccount3);

        ApyAccount apyAccount4 = new ApyAccount();
        apyAccount4.setPayId(4);
        apyAccount4.setPartner("700000000000001");
        //公钥，验签证书链格式： 中级证书路径;根证书路径
        apyAccount4.setPublicKey("D:/certs/acp_test_middle.cer;D:/certs/acp_test_root.cer");
        //私钥, 私钥证书格式： 私钥证书路径;私钥证书对应的密码
        apyAccount4.setPrivateKey("D:/certs/acp_test_sign.pfx;000000");
        apyAccount4.setNotifyUrl("http://127.0.0.1/payBack4.json");
        // 无需同步回调可不填  app填这个就可以
        apyAccount4.setReturnUrl("http://127.0.0.1/payBack4.json");
        apyAccount4.setSeller("");
        apyAccount4.setInputCharset("UTF-8");
        apyAccount4.setSignType(SignUtils.RSA2.name());
        apyAccount4.setPayType(PayType.unionPay);
        apyAccount4.setMsgType(MsgType.json);
        apyAccount4.setTest(true);
        apyAccounts.put(apyAccount4.getPayId(), apyAccount4);

//        ApyAccount apyAccount5 = new ApyAccount();
//        apyAccount5.setPayId(5);
//        apyAccount5.setPartner("100086190");//Program ID
//        apyAccount5.setSeller("egan6190");//Username
//        apyAccount5.setStorePassword("12BkDT8152Zj");//API password
//        apyAccount5.setInputCharset("UTF-8");
//        apyAccount5.setPayType(PayType.payoneer);
//        apyAccount5.setMsgType(MsgType.json);
//        apyAccount5.setTest(true);
//        apyAccounts.put(apyAccount5.getPayId(), apyAccount5);

        ApyAccount apyAccount6 = new ApyAccount();
        apyAccount6.setPayId(6);
        apyAccount6.setAppid("1AZ7HTcvrEAxYbzYx_iDZAi06GdqbjhqqQzFgPBFLxm2VUMzwlmiNUBk_y_5QNP4zWKblTuM6ZBAmxScd");//Program ID
        apyAccount6.setPrivateKey("1EBMIjAag6NiRdXZxteTv0amEsmKN345xJv3bN7f_HRXSqcRJlW7PXhYXjI9sk5I4nKYOHgeqzhXCXKFo");//API password
        apyAccount6.setInputCharset("UTF-8");
        apyAccount6.setPayType(PayType.payPal);
        apyAccount6.setMsgType(MsgType.json);
        apyAccount6.setTest(true);
        apyAccounts.put(apyAccount6.getPayId(), apyAccount6);
    }
    //_____________________________________________________________


    /**
     * 根据id获取对应的账户信息
     * @param payId 账户id
     * @return 账户信息
     */
    public ApyAccount findByPayId(Integer payId){
        // TODO 2016/11/18 1:23 author: egan  这里简单模拟 具体实现 略。。
        return apyAccounts.get(payId);
    }
}
