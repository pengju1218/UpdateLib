###此库基于https://github.com/czy1121/update  该项目进行修改，保留大部分代码。  
为什么有修改，引用项目当中对服务器返回格式进行了限制，无法满足自定义需求。为了能符合自己的设计规范，进行了微调整。 
####后台API设计，返回数据样例，仅供参考，可以按照需求自己定义
```
{
    "Code": 200,
    "Message": "获取成功！",
    "Data": {
        "APPID": "D04AC824-2174-4681-9D14-E08831D5DE04",
        "APPKey": "90ded202bc6c8d8d46c5e3f0d9a23bdc",
        "APPName": "更新app",
        "ClientVersion": "1.0.5",
        "DownloadUrl": "http://app.down/app.apk",
        "UpdateVersion": 6,
        "UpdateType": 1,
        "UpdateLog": "1．优化更新内容",
        "UpdateTime": "2016-12-15 12:10:52",
        "MD5": "B8B038414A8BFAF8333280D0FD243C6E",
        "Size": 7024539
    }
}
``` 
#####字段说明
`APPID`:就是id  
`APPKey`:设置该字段可以使多个app公用一个接口  
`APPName`:app的名字  
`ClientVersion`:对应Android中的versionName  
`DownloadUrl`:app下载地址  
`UpdateVersion`:对应Android中的versionCode，修改改字段才能使APP弹出提示  
`UpdateType`:控制更新类型：1 是普通更新 2 强制更新  
`UpdateLog`:更新日志  
`UpdateTime`:升级时间  
`MD5`:校验文件的MD5 ，保证下载完整性  
`Size`:文件大小 ( long )  
#####如何使用
step1
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
step2
```
dependencies {
	        compile 'com.github.wyh497823256:UpdateLib:1.0.0'
	}
```

```
@Override
public void onNext(ResultDataEntity<VersionInfoEntity> versionInfoEntityResultDataEntity) {
       VersionInfoEntity versionInfoEntity = versionInfoEntityResultDataEntity.getData();
           if (versionInfoEntity != null && versionInfoEntity.getUpdateVersion() > AppUtils.getAppVersionCode(Utils.context)) {
                            UpdateInfo updateInfo = new UpdateInfo();
                            updateInfo.size = versionInfoEntity.getSize();
                            updateInfo.hasUpdate = true;
                            updateInfo.isIgnorable = false;
                            if (versionInfoEntity.getUpdateType() == 3) {
                                updateInfo.isForce = true;
                            }
                            updateInfo.versionCode = versionInfoEntity.getUpdateVersion();
                            updateInfo.versionName = versionInfoEntity.getClientVersion();
                            updateInfo.md5 = versionInfoEntity.getMD5();
                            updateInfo.updateContent = versionInfoEntity.getUpdateLog();
                            updateInfo.url = versionInfoEntity.getDownloadUrl();
                            mView.getUpdateManager()
                                    .setWifiOnly(false)
                                    .setUpdateInfo(updateInfo)
                                    .check();
                        }
                  }
```
