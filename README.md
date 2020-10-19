# 获取已安装App列表
```
/**
 * appPackageName appName appIcon appVersionCode appVersionName appFirstInstallTime
 * appLastUpdateTime appLaunchActivityName appTargetSdkVersion appMinSdkVersion
 * appDataDir appSourceDir appUid appFlags
 */
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
    } catch (Exception e) {
	e.printStackTrace();
    }
}
```
