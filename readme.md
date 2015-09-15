#两句话实现沉浸式（API>=19)
##一、使用gradle 加载依赖
```gradle
compile 'com.linwoain.library:lin_library:1.0.11'
```
或者下载相应文件夹下的java文件

##二、使用Translucent类实现


```java
    Translucent translucent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translucent = new Translucent(this).inject();
        translucent.setStatusBarColor(Color.RED);

    }

    public void change(View view) {
        translucent.setStatusBarColor(Color.YELLOW);
    }
```


##1.0.9 修复了检测root权限错误问题
##1.0.10 修改了LinListView使用v4包刷新与上拉加载更多
##1.0.11 取消了CacheUtil中每次保存sharedpreference时默认打印log
