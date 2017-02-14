###此库基于https://github.com/czy1121/update  该项目进行修改，保留大部分代码。  
为什么有修改，引用项目当中对服务器返回格式进行了限制(或许我没找到方法)，无法满足自定义需求。为了能符合自己的设计规范，进行了微调整。 
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
//RxJava 获取服务器数据
@Override
public void onNext(ResultDataEntity<VersionInfoEntity> versionInfoEntityResultDataEntity) {
	//获取ReturnData中的数据
       VersionInfoEntity versionInfoEntity = versionInfoEntityResultDataEntity.getData();
       	   //服务器版本是否大于本地版本，是的话进行更新
           if (versionInfoEntity != null && versionInfoEntity.getUpdateVersion() > AppUtils.getAppVersionCode(Utils.context)) {
	   		 //更新数据		
                            UpdateInfo updateInfo = new UpdateInfo();
			    //填充apk大小
                            updateInfo.size = versionInfoEntity.getSize();
			    //是否更新，当然可以从服务器配置，在这默认为true
                            updateInfo.hasUpdate = true;
			    //是否忽略更新，true的话启用一个选择框
                            updateInfo.isIgnorable = false;
			    //是否强制更新
                            if (versionInfoEntity.getUpdateType() == 2) {
                                updateInfo.isForce = true;
                            }
			    // 版本号
                            updateInfo.versionCode = versionInfoEntity.getUpdateVersion();
                            updateInfo.versionName = versionInfoEntity.getClientVersion();
			   // md5校验,
                            updateInfo.md5 = versionInfoEntity.getMD5();
                            updateInfo.updateContent = versionInfoEntity.getUpdateLog();
                            updateInfo.url = versionInfoEntity.getDownloadUrl();
			  //mView.getUpdateManager() = UpdateManager.create(getContext());
                            mView.getUpdateManager()
                                    .setWifiOnly(false)// 是否只在wifi环境下更新
                                    .setUpdateInfo(updateInfo) //将以上的数据填充进去
                                    .check();
                        }
                  }
```
###Q&A
######如何获取文件MD5和大小
```简单点的就是使用 
压缩（好压）软件自带的工具，一键可以提取出MD5和SIZE
```
######不符合我的需求怎么办
```
可以查看https://github.com/czy1121/update  自己定制
```
