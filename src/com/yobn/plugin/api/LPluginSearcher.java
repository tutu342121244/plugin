package com.yobn.plugin.api;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.yobn.plugin.EasyFor;
import com.yobn.plugin.LPluginTool;
import com.yobn.plugin.bean.LPluginInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lody  on 2015/4/4.
 */
public class LPluginSearcher {

    public static List<LPluginInfo> listAllPluginFromDirectory(final Context context,String directory){
        File dir = new File(directory);
        if(!dir.isDirectory() || !dir.exists()){
            return null;
        }

        File[] files = dir.listFiles();
        if (files.length == 0){
            return null;
        }
        final List<File> apks  = filterApk(files);
        if(apks.size() == 0){
            return null;
        }
        final List<LPluginInfo> infos = new ArrayList<LPluginInfo>();
        new EasyFor<File>(apks){

            @Override
            public void onNewElement(File element) {
                String filePath = element.getAbsolutePath();
                Drawable drawable = null;
                try {
                    drawable = LPluginTool.getAppIcon(context, filePath);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();

                }
                LPluginInfo info = new LPluginInfo();
                info.setApkIcon(drawable);
                info.setApkPath(filePath);
                infos.add(info);

            }
        };

        return infos;

    }

    public static List<File> filterApk(File[] files) {
        final List<File> apks = new ArrayList<File>();
        for(File file : files){
            //根据后缀判断是否是一个apk
            if(!file.getName().toLowerCase().endsWith(".apk")){
                continue;
            }
            apks.add(file);
        }
        return apks;
    }
}
