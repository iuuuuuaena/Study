package _init_window_;

import _plugin_.IPlayerPlugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class AAPlayer {
    public AAPlayer(){

    }
    static IPlayerPlugin playerPlugin = new IPlayerPlugin();

    public static void main(String[] args){

        AAPFrame A = new AAPFrame();


        playerPlugin.play("正在使用的是：");



    }

    /**
     * 获得插件中的实现
     * @param className
     * @return
     */
    public static IPlayerPlugin getPrinter(String className) {
        String class_path = className;//Jar中的所需要加载的类的类名
        String str1=class_path.substring(0, class_path.indexOf("."));//获取.之后的字符串
        String str2=class_path.substring(str1.length()+1, class_path.length());
        String jar_path = "/Users/mac/Documents/ModelPlayer/out/artifacts/"+str2+"/"+str2+".jar";//jar所在的文件的URL
        try {
            ClassLoader cl = new URLClassLoader(new URL[]{new File(jar_path).toURI().toURL()});//从Jar文件得到一个Class加载器
            Class<?> c = cl.loadClass(class_path);//从加载器中加载Class
            IPlayerPlugin sif = (IPlayerPlugin) c.newInstance();//从Class中实例出一个对象
            return sif;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
