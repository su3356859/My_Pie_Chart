##前言
  第一次写文章,先写一些简单的功能,大家在开发过程中应该有很多app中会有隐藏入口,主要是为了使用测试帐号对功能进行测试,下面进入正文。
##正文
惯例,先亮出效果图(这里的隐藏入口是随意写的一个demo,真正的隐藏入口肯定是不会就这么亮出来的)。
![image](http://upload-images.jianshu.io/upload_images/3516640-85d498dd1e1bdbe6.gif?imageMogr2/auto-orient/strip)
大家看到了,只有当连续点击五次的时候才会弹出提示,当你点击两次之后延时两秒钟再次点击的话,还是需要再次点击五次才能弹出提示。
因为需要点击次数五次,所以我们肯定需要一个变量用来存贮点击次数
```
private int mClickNum;//点击次数
```
这里我们使用```System.currentTimeMillis();```进行获取当前时间。
首先屡清整个五次点击的逻辑
  - 第一次点击的时候只进行保存当前时间
```
    private long mTempTime;//临时时间
    private int mClickNum;//点击次数
    //五次点击之后进入隐藏入口
    public void onClick(View view) {//点击隐藏入口
        long time = System.currentTimeMillis();
        if (mTempTime == 0) {//第一次点击隐藏入口,保存临时时间
            mTempTime = time;//
        } else {
              //这里走第二次点击
        }
    }
```
  - 第二次一直到第五次点击，这里首先判断一下超时时间,并且记录点击次数``` mClickNum++```
```
    private long mTempTime;//临时时间
    private int mClickNum;//点击次数
//五次点击之后进入隐藏入口
    public void onClick(View view) {//点击隐藏入口
        long time = System.currentTimeMillis();
        if (mTempTime == 0) {//第一次点击隐藏入口,保存临时时间
            mTempTime = time;//
        } else {
            if (time - mTempTime > 2000) {//这里设置两秒的超时时间,如果超过两秒,重置状态
                mTempTime = time;//给临时时间和点击次数进行初始化
                mClickNum = 0;
                return;
            }
            mClickNum++;//在每一次点击的时候就++,记录点击的次数
            mTempTime = time;//把每一次的当前时间对临时时间进行赋值
        }
    }
```
- 最后一步就是判断点击次数了,只要判断次数是不是点击超过五次就可以了
``` 
if (mClickNum == 4) {//因为一次点击走if语句
     Toast.makeText(this, "连续点击五次", Toast.LENGTH_SHORT).show();
     mTempTime = 0;//给临时时间和点击次数进行初始化
     mClickNum = 0;
}
```
这里因为次数是从0开始的,所以判断是不是等于四就可以了,当然,在点击完之后,不要忘了把状态给初始化。
下面贴出全部代码
```
private long mTempTime;//临时时间
    private int mClickNum;//点击次数
    //五次点击之后进入隐藏入口
    public void onClick(View view) {//点击隐藏入口
        long time = System.currentTimeMillis();
        if (mTempTime == 0) {//第一次点击隐藏入口,保存临时时间
            mTempTime = time;//
        } else {
            if (time - mTempTime > 2000) {//这里设置两秒的超时时间,如果超过两秒,重置状态
                mTempTime = time;//给临时时间和点击次数进行初始化
                mClickNum = 0;
                return;
            }
            mClickNum++;
            mTempTime = time;
            if (mClickNum == 4) {//因为一次点击走if语句
                Toast.makeText(this, "连续点击五次", Toast.LENGTH_SHORT).show();
                mTempTime = 0;//给临时时间和点击次数进行初始化
                mClickNum = 0;
            }
        }
    }
```
文章到这里就完了,第一次写文章还望大家多多支持!

