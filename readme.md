#两句话实现沉浸式（API>=19)
##一、使用gradle 加载依赖
```gradle
compile 'com.linwoain.library:lin_library:1.1.0'
```
或者下载相应文件夹下的java文件

##二、使用ScreenUtil类实现

```java

  ScreenUtil.setChenjinColor(this, getResources().getColor(R.color.red));
```


##1.0.9 修复了检测root权限错误问题
##1.0.10 修改了LinListView使用v4包刷新与上拉加载更多
##1.0.11 取消了CacheUtil中每次保存sharedpreference时默认打印log
##1.0.12 使用ScreenUtil完全替代Translucent
##1.1.0 增加了Once类