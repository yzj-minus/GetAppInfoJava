package com.yzj.minus.getallapp;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AppJsonData {

    /**
     * appPackageName appName appIcon appVersionCode appVersionName appFirstInstallTime
     * appLastUpdateTime appLaunchActivityName appTargetSdkVersion appMinSdkVersion
     * appDataDir appSourceDir appUid appFlags
     */
    public static List<JSONObject> get(Context context) {
        List<JSONObject> appJsonInfos = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> packageInfoList = pm.getInstalledPackages(0);
        for (PackageInfo info : packageInfoList) {
            JSONObject jsonObject = null;
            String appPackageName = info.packageName;
            String appName = info.applicationInfo.loadLabel(pm).toString();
            Drawable appIcon = info.applicationInfo.loadIcon(pm);
            int appVersionCode = info.versionCode;
            String appVersionName = info.versionName;
            long appFirstInstallTime = info.firstInstallTime;
            long appLastUpdateTime = info.lastUpdateTime;


            try {
                String appLaunchActivityName = pm.getLaunchIntentForPackage(appPackageName)
                        .getComponent().getClassName();
                ApplicationInfo applicationInfo = pm.getApplicationInfo(appPackageName, 0);
                int appTargetSdkVersion = applicationInfo.targetSdkVersion;
                int appMinSdkVersion = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N
                        ? applicationInfo.minSdkVersion : -1;
                String appDataDir = applicationInfo.dataDir;
                String appSourceDir = applicationInfo.sourceDir;
                int appUid = applicationInfo.uid;
                int appFlags = applicationInfo.flags;
                jsonObject = new JSONObject();
                jsonObject.put("app_package_name", appPackageName);
                jsonObject.put("app_name", appName);
                jsonObject.put("app_icon", appIcon);
                jsonObject.put("app_version_code", appVersionCode);
                jsonObject.put("app_version_name", appVersionName);
                jsonObject.put("app_first_install_time", appFirstInstallTime);
                jsonObject.put("app_last_update_time", appLastUpdateTime);
                jsonObject.put("app_launch_activity_name", appLaunchActivityName);
                jsonObject.put("app_target_sdk_version", appTargetSdkVersion);
                jsonObject.put("app_min_sdk_version", appMinSdkVersion);
                jsonObject.put("app_data_dir", appDataDir);
                jsonObject.put("app_source_dir", appSourceDir);
                jsonObject.put("app_uid", appUid);
                jsonObject.put("app_flags", appFlags);
                appJsonInfos.add(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appJsonInfos;
    }
}

