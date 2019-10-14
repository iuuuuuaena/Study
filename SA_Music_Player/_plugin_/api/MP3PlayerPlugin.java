package _plugin_.api;

import _init_window_.AAPFrame;
import _plugin_.IPlayerPlugin;

import java.util.Map;

public class MP3PlayerPlugin extends IPlayerPlugin {
    public MP3PlayerPlugin(){}

    public void play(String name){



        System.out.println("这里是MP3插件！"+name);
    }



}
