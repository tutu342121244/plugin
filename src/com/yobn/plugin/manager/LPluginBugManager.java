package com.yobn.plugin.manager;

import com.yobn.plugin.EasyFor;
import com.yobn.plugin.api.LPluginBug;
import com.yobn.plugin.api.LPluginBugListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lody  on 2015/4/3.
 */
public class LPluginBugManager {
    private static final List<LPluginBugListener> errorCollection = new ArrayList<LPluginBugListener>();

    /**
     * 加入一个插件异常监听器
     * @param lPluginBugListener
     */
    public static void addBugListener(LPluginBugListener lPluginBugListener){
        errorCollection.add(lPluginBugListener);
    }

    /**
     * 移除一个插件异常监听器
     * @param lPluginBugListener
     */
    public static void removeBugListener(LPluginBugListener lPluginBugListener){
        errorCollection.remove(lPluginBugListener);
    }

    public static void callAllBugListener(final LPluginBug error){
        if(errorCollection.size() == 0) return;
        new EasyFor<LPluginBugListener>(errorCollection){
            @Override
            public void onNewElement(LPluginBugListener element) {
                element.OnError(error);
            }
        };
    }

}
