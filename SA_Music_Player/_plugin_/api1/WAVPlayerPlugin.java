package _plugin_.api1;

import _plugin_.IPlayerPlugin;

public class WAVPlayerPlugin extends IPlayerPlugin {
    public WAVPlayerPlugin(){ }
    @Override
    public void play(String name){
        System.out.println("这里是WAV插件！"+name);
    }

}
