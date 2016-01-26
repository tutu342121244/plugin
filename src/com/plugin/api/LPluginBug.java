package com.plugin.api;

import com.plugin.bean.LActivityPlugin;

/**
 * Created by lody  on 2015/4/3.
 * bug监听
 */
public class LPluginBug {
    public Throwable error;
    public long errorTime;
    public Thread errorThread;
    public LActivityPlugin errorPlugin;
    public int processId;
}
