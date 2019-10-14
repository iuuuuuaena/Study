package _init_window_;
import _plugin_.api.MP3PlayerPlugin;
import _plugin_.api1.WAVPlayerPlugin;
import _plugin_.api3.OGGPlayerPlugin;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
//import java.awt.


public class AAPFrame {

    public AAPFrame (){
        /*初始化Frame*/
        Frame f = new Frame("音乐播放器");
        /*设置位置即大小*/
        f.setBounds(500,320,300,250);
        /*MenuBar的声明*/

        Button buttonPlay = new Button("播放");

        Label label1 = new Label();

        TextArea textArea = new TextArea();

        Button buttonParse = new Button("暂停");

        buttonParse.setBounds(150,90,70,40);

        buttonPlay.setBounds(60,90,70,40);

        label1.setBounds(30,70,230,160);

        textArea.setBounds(20,30,80,80);

        MenuBar mb = new MenuBar();

        Menu menufile = new Menu("文件");

        Menu menuIntroduction = new Menu("软件说明");

        MenuItem menuItemIntroduction = new MenuItem("说明",new MenuShortcut(KeyEvent.VK_Y));

        MenuItem menuopen = new MenuItem("打开", new MenuShortcut(KeyEvent.VK_O));

        MenuItem menuopen1 = new MenuItem("MP3", new MenuShortcut(KeyEvent.VK_1));

        MenuItem menuopen2 = new MenuItem("WAV", new MenuShortcut(KeyEvent.VK_2));

        MenuItem menuopen3 = new MenuItem("OGG", new MenuShortcut(KeyEvent.VK_3));

        menufile.add(menuopen);

        menufile.add(menuopen1);

        menufile.add(menuopen2);

        menufile.add(menuopen3);

        menuIntroduction.add(menuItemIntroduction);

        menuIntroduction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AboutBox A = new AboutBox();
            }
        });

        menuopen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


            }
        });
        menuopen1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AAPlayer.playerPlugin = AAPlayer.getPrinter("_plugin_.api.MP3PlayerPlugin");
                MP3PlayerPlugin mp3PlayerPlugin = new MP3PlayerPlugin();
                mp3PlayerPlugin.play("是");
                textArea.setText("这里是MP3插件");

            }
        });

        menuopen2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AAPlayer.playerPlugin = AAPlayer.getPrinter("_plugin_.api1.WAVPlayerPlugin");
                WAVPlayerPlugin wavPlayerPlugin = new WAVPlayerPlugin();
                wavPlayerPlugin.play("是");
                textArea.setText("这里是WAV插件");

            }
        });

        menuopen3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                AAPlayer.playerPlugin = AAPlayer.getPrinter("_plugin_.api3.OGGPlayerPlugin");
                OGGPlayerPlugin oggPlayerPlugin = new OGGPlayerPlugin();
                oggPlayerPlugin.play("是");
                textArea.setText("这里是OGG插件");
            }
        });


        mb.add(menufile);

        mb.add(menuIntroduction);



        f.setMenuBar(mb);

        f.add(buttonPlay);

        f.add(buttonParse);

        f.add(label1);

        f.add(textArea);

        f.setVisible(true);



    }



}





