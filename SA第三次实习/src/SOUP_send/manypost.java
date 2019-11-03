package SOUP_send;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class manypost {


    public String sendMany(String URL,String nameOne,String nameTwo) {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        //下面填写密钥
        System.out.println("多人发送的邮箱地址数据为："+URL);
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FpapeWsdgAT7key9fVW", "Okxok9d8iEdAWecOmnMZgNtV5XXSIj");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        //使用https加密连接
        request.setProtocol(com.aliyuncs.http.ProtocolType.HTTPS);
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("onlyiu@jxdgogogo.xyz");
            request.setFromAlias("onlyiu");
            request.setAddressType(1);
            //可以不需要
            //request.setTagName("控制台创建的标签");
            //是否需要回信功能
            request.setReplyToAddress(true);
           // request.setToAddress(URL);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            request.setToAddress(URL);

            request.setSubject(nameOne);
            request.setHtmlBody(nameTwo);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
        return "seccesed";
    }
}
