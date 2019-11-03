package clint;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class Clint_soup{


    public Clint_soup(){

    }
    public  String send_soup(String url,String method,Object [] objects)throws ServiceException, RemoteException {

        //url是webService访问地址
        //创建服务
        String result  = "";
        Service service = new Service();
        //创建调用句柄
        System.out.println("这里11");
        Call call = null;
        try {
            System.out.println("这里22");

            call = (Call) service.createCall();
//设置请求地址
            System.out.println("这里33");

            call.setTargetEndpointAddress(new java.net.URL(url));

            System.out.println("url的值为："+url);
/**
 * 设置调用的方法和方法的命名空间；
 * 当然null也可以，因为本身它就没有设置命名空间，一般方法的命名空间是
 * 包名倒写组成，如com.hoo.service,ns=http://service.hoo.com
 */
            System.out.println("这里44");

            call.setOperationName(method);

            System.out.println("method的值为："+method);

/**
 * 用call调用getName方法，设置请求的参数，返回的就是返回值了
 */
            System.out.println("这里55");

            result = call.invoke(objects).toString();

            System.out.println("result的值为："+result);

            System.out.println("这里66");

/**
 * 用call调用getAge方法，设置请求的参数，返回的就是返回值了
 */
        } catch (ServiceException e) {
            System.out.println("call这里出错了！");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("sssdasfdsfbhjsdg hdso");
            e.printStackTrace();
        }
        System.out.println("这里77");

        return result;

    }


    public String  one_soup(String qq ,String title,String word)throws ServiceException, RemoteException{
        String url = "http://47.97.184.36:8080/myPost2/services/post?wsdl";

        String method = "sendOne";

        Object [] objects = new Object[]{qq,title,word};

        return send_soup(url,method,objects);


    }
    public String  two_soup(String qq,String  title,String word)throws ServiceException, RemoteException{
        String url = "http://47.97.184.36:8080/myPost2/services/manypost?wsdl";

        String method = "sendMany";

        Object [] objects = new Object[]{qq,title,word};

        return send_soup(url,method,objects);

    }

}
