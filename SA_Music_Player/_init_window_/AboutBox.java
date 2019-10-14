package _init_window_;
import java.awt.*;


public class AboutBox {

    public AboutBox (){
        Frame f = new Frame("软件信息");
        f.setBounds(300,300,400,300);
        Label label = new Label();
        label.setText("      模拟播放器-111172-江悦—制作-可以加载三个插件");
        label.setBounds(0,0,400,300);
        f.add(label);
        f.setVisible(true);




    }




}
