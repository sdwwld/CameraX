# CameraX


![ezgif.com-gif-maker](img/cameraxgif.gif)


### 注意：
**使用之前一定要先获取拍照和文件存储权限。**


### 使用方式
在根目录的buid.gradle文件中添加

```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

然后在使用的module的buid.gradle中添加
```java
    implementation 'com.github.sdwwld:CameraX:1.1.0'
 ```


使用方式很简单，几行代码即可

```java
            new CameraParam.Builder()
                    .setActivity(this)
                    .setTargetActivity(CameraActivity.class)
                    .build();
```

当然如果不能满足你的需求，还可以修改更多的属性


```java  

            String cameraPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "DCIM" + File.separator + "Camera";
            File cameraFolder = new File(cameraPath);
            if (!cameraFolder.exists()) {
                cameraFolder.mkdirs();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

            CameraParam mCameraParam =
                    new CameraParam.Builder()
                            .setActivity(this)//！！！参数必须要有
                            .setTargetActivity(CameraActivity.class)//！！！参数必须要有
                            .setFront(false)//是否是前置摄像头，true是前置摄像头，false是后置摄像头
                            .setShowMask(true)//是否显示裁剪区，如果显示，图片会进行裁剪
                            .setShowSwitch(true)//是否显示摄像头切换按钮，如果显示，可以点击切换前后摄像头
                            .setSwitchLeft(Tools.dp2px(this, 40))//摄像头切换按钮到屏幕左边的距离
                            .setSwitchTop(Tools.dp2px(this, 40))//摄像头切换按钮到屏幕上边的距离
                            .setSwitchSize(Tools.dp2px(this, 40))//摄像头切换按钮的大小（宽高一样，是正方形的）
                            .setBackText("返回")//下面返回键显示的文字
                            .setBackColor(Color.WHITE)//下面返回键的颜色
                            .setBackSize(16)//下面返回键的尺寸
                            .setBackLeft(Tools.dp2px(this, 10))//返回键到屏幕左边的距离
                            .setTakePhotoSize(Tools.dp2px(this, 60))//拍照按钮的大小（还有拍照完之后显示的保存和放弃保存的按钮，这3个按钮的大小是一样的）
                            .setMaskMarginLeftAndRight(Tools.dp2px(this, 20))//如果显示裁剪框，裁剪框左右两边倒屏幕的距离
                            .setMaskMarginTop(Tools.dp2px(this, 120))////如果显示裁剪框，裁剪框到屏幕上边的距离
                            .setMaskRatioW(8)//下面一行和当前行表示的是裁剪框的宽高比
                            .setMaskRatioH(5)
                            .setResultBottom(Tools.dp2px(this, 60))//拍照按钮到屏幕下边的距离（还有拍照完之后显示的保存和放弃保存的按钮）
                            .setResultLeftAndRight(Tools.dp2px(this, 60))//拍照完之后显示的保存和放弃保存的按钮到左边和右边的距离
                            .setSwitchImgId(R.drawable.ic_switch)//切换摄像头的按钮
                            .setMaskImgId(R.drawable.bg_bankcard)//剪切框
                            .setSaveImgId(R.mipmap.success)//拍照成功之后的保存按钮
                            .setCancelImgId(R.mipmap.failed)//拍照成功之后的放弃按钮
                            .setTakePhotoImgId(R.mipmap.take_button)//拍照按钮
                            .setPicturePath(cameraFolder.getAbsolutePath() + File.separator + "IMG_" + simpleDateFormat.format(new Date()) + ".jpg")//拍照保存的路径
                            .setShowFocusTips(true)//点击屏幕聚焦成功的时候是否需要提示
                            .setFocusFailTips("聚焦失败")//聚焦失败的提示
                            .setFocusSuccessTips("聚焦成功")//聚焦成功的提示
                            .setFocusViewTime(3)//聚焦的时间，是秒
                            .setFocusViewColor(ContextCompat.getColor(this, R.color.blue))//聚焦框的颜色
                            .setFocusViewSize(Tools.dp2px(this, 60))//聚焦框的大小（正方形的）
                            .setCornerViewSize(Tools.dp2px(this, 12))//聚焦框的圆角大小
                            .setFocusViewStrokeSize(Tools.dp2px(this, 2))//聚焦框线条宽度
                            .setRequestCode(CameraConstant.REQUEST_CODE)//请求码
                            .build();

            //前两个参数是必须要有的，后面的参数不是必须的，如果想获取默认参数可以像下面这样
            Log.d("wld______", mCameraParam.getPicturePath());

```

获取拍照后的文件路径

```java

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode默认是CameraConstant.REQUEST_CODE ，当然也可以在上面的CameraParam创建的时候
        //调用setRequestCode修改
        if (requestCode == CameraConstant.REQUEST_CODE && resultCode == RESULT_OK) {
            //获取图片路径
            String picturePath = data.getStringExtra(CameraConstant.PICTURE_PATH_KEY);
            //显示出来
            img_picture.setVisibility(View.VISIBLE);
            img_picture.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
```


如果大家喜欢算法题还可以关注我的微信公众号“**数据结构和算法**”，目前已经写了有500多道算法题了，大家可以一起来学习


![数据结构和算法](https://img-blog.csdnimg.cn/20201229141648443.png#pic_center)

