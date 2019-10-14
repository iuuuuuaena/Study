package _plugin_.api3;

import _plugin_.IPlayerPlugin;

public class OGGPlayerPlugin extends IPlayerPlugin {
    public OGGPlayerPlugin(){ }
    @Override
    public void play(String name){
        System.out.println("这里是OGG插件！"+name);
    }
}
