package com.wld.mycamerax.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.wld.mycamerax.R;
import com.wld.mycamerax.activity.CameraActivity;

import java.io.File;

public class CameraParam implements Parcelable {
    private boolean front;//相机方向,试试前置摄像头
    private String picturePath;//最终保存路径
    private String pictureTempPath;//拍照的临时路径
    private boolean showMask;//是否显示裁剪框
    private boolean showSwitch;//是否显示切换相机正反面按钮

    private String backText;//返回键的文字颜色尺寸
    private int backColor;//
    private int backSize;//

    private int switchSize;//切换正反面按钮的大小，上左间距
    private int switchTop;//
    private int switchLeft;//

    private int takePhotoSize;//下面几个icon的尺寸

    private int maskMarginLeftAndRight;//剪切框左右边距
    private int maskMarginTop;//剪切框上下边距
    private int maskRatioW;//剪切框的宽高比,比如h,9:16，这里的值是16/9
    private int maskRatioH;//

    private int focusViewSize;//焦点框的大小
    private int focusViewColor;//焦点框的颜色
    private int focusViewTime;//焦点框显示的时长
    private int focusViewStrokeSize;//焦点框线条的尺寸
    private int cornerViewSize;//焦点框圆角尺寸

    //icon
    private int maskImgId;//
    private int switchImgId;//
    private int cancelImgId;//
    private int saveImgId;//
    private int takePhotoImgId;//

    private int resultBottom;//拍照到下面的距离
    private int resultLeftAndRight;//拍照到左右距离
    private int backLeft;//返回键到左边的距离

    private String focusSuccessTips;//聚焦成功提示
    private String focusFailTips;//聚焦失败提示
    private Activity mActivity;//

    private boolean showFocusTips;//是否显示聚焦成功的提示

    private int requestCode;

    private CameraParam(Builder mBuilder) {
        front = mBuilder.front;
        picturePath = mBuilder.picturePath;
        showMask = mBuilder.showMask;
        showSwitch = mBuilder.showSwitch;
        backText = mBuilder.backText;
        backColor = mBuilder.backColor;
        backSize = mBuilder.backSize;
        switchSize = mBuilder.switchSize;
        switchTop = mBuilder.switchTop;
        switchLeft = mBuilder.switchLeft;
        takePhotoSize = mBuilder.takePhotoSize;
        maskMarginLeftAndRight = mBuilder.maskMarginLeftAndRight;
        maskMarginTop = mBuilder.maskMarginTop;
        maskRatioW = mBuilder.maskRatioW;
        maskRatioH = mBuilder.maskRatioH;
        focusViewSize = mBuilder.focusViewSize;
        focusViewColor = mBuilder.focusViewColor;
        focusViewTime = mBuilder.focusViewTime;
        focusViewStrokeSize = mBuilder.focusViewStrokeSize;
        cornerViewSize = mBuilder.cornerViewSize;

        maskImgId = mBuilder.maskImgId;
        switchImgId = mBuilder.switchImgId;
        cancelImgId = mBuilder.cancelImgId;
        saveImgId = mBuilder.saveImgId;
        takePhotoImgId = mBuilder.takePhotoImgId;
        resultBottom = mBuilder.resultBottom;
        resultLeftAndRight = mBuilder.resultLeftAndRight;
        backLeft = mBuilder.backLeft;

        focusSuccessTips = mBuilder.focusSuccessTips;
        focusFailTips = mBuilder.focusFailTips;
        mActivity = mBuilder.mActivity;
        showFocusTips = mBuilder.showFocusTips;
        requestCode = mBuilder.requestCode;

        if (mActivity == null) {
            throw new NullPointerException("Activity param is null");
        }
    }

    private CameraParam startActivity(int requestCode) {
        Intent intent = new Intent(mActivity, CameraActivity.class);
        intent.putExtra(CameraConstant.CAMERA_PARAM_KEY, this);
        mActivity.startActivityForResult(intent, requestCode);
        return this;
    }

    protected CameraParam(Parcel in) {
        front = in.readByte() != 0;
        picturePath = in.readString();
        pictureTempPath = in.readString();
        showMask = in.readByte() != 0;
        showSwitch = in.readByte() != 0;
        backText = in.readString();
        backColor = in.readInt();
        backSize = in.readInt();
        switchSize = in.readInt();
        switchTop = in.readInt();
        switchLeft = in.readInt();
        takePhotoSize = in.readInt();
        maskMarginLeftAndRight = in.readInt();
        maskMarginTop = in.readInt();
        maskRatioW = in.readInt();
        maskRatioH = in.readInt();
        focusViewSize = in.readInt();
        focusViewColor = in.readInt();
        focusViewTime = in.readInt();
        focusViewStrokeSize = in.readInt();
        cornerViewSize = in.readInt();
        maskImgId = in.readInt();
        switchImgId = in.readInt();
        cancelImgId = in.readInt();
        saveImgId = in.readInt();
        takePhotoImgId = in.readInt();
        resultBottom = in.readInt();
        resultLeftAndRight = in.readInt();
        backLeft = in.readInt();
        focusSuccessTips = in.readString();
        focusFailTips = in.readString();
        showFocusTips = in.readByte() != 0;
        requestCode = in.readInt();
    }

    public static final Creator<CameraParam> CREATOR = new Creator<CameraParam>() {
        @Override
        public CameraParam createFromParcel(Parcel in) {
            return new CameraParam(in);
        }

        @Override
        public CameraParam[] newArray(int size) {
            return new CameraParam[size];
        }
    };

    public boolean isFront() {
        return front;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public boolean isShowMask() {
        return showMask;
    }

    public boolean isShowSwitch() {
        return showSwitch;
    }

    public String getBackText() {
        return backText;
    }

    public int getBackColor() {
        return backColor;
    }

    public int getBackSize() {
        return backSize;
    }

    public int getSwitchSize() {
        return switchSize;
    }

    public int getSwitchTop() {
        return switchTop;
    }

    public int getSwitchLeft() {
        return switchLeft;
    }

    public int getTakePhotoSize() {
        return takePhotoSize;
    }

    public int getMaskMarginLeftAndRight() {
        return maskMarginLeftAndRight;
    }

    public int getMaskMarginTop() {
        return maskMarginTop;
    }

    public int getMaskRatioW() {
        return maskRatioW;
    }

    public int getMaskRatioH() {
        return maskRatioH;
    }

    public int getFocusViewSize() {
        return focusViewSize;
    }

    public int getFocusViewColor() {
        return focusViewColor;
    }

    public int getFocusViewTime() {
        return focusViewTime;
    }

    public int getFocusViewStrokeSize() {
        return focusViewStrokeSize;
    }

    public int getCornerViewSize() {
        return cornerViewSize;
    }

    public int getMaskImgId() {
        return maskImgId;
    }

    public int getSwitchImgId() {
        return switchImgId;
    }

    public int getCancelImgId() {
        return cancelImgId;
    }

    public int getSaveImgId() {
        return saveImgId;
    }

    public int getTakePhotoImgId() {
        return takePhotoImgId;
    }

    public int getResultBottom() {
        return resultBottom;
    }

    public int getResultLeftAndRight() {
        return resultLeftAndRight;
    }

    public int getBackLeft() {
        return backLeft;
    }

    public String getPictureTempPath() {
        File file = new File(getPicturePath());
        String pictureName = file.getName();
        String newName = null;
        if (pictureName.contains(".")) {
            int lastDotIndex = pictureName.lastIndexOf('.');
            newName = pictureName.substring(0, lastDotIndex) + "_temp" + pictureName.substring(lastDotIndex);
        }
        if (newName == null) {
            newName = pictureName;
        }
        return file.getParent() + File.separator + newName;
    }

    public String getFocusSuccessTips(Context context) {
        if (focusSuccessTips == null)
            return context.getString(R.string.focus_success);
        return focusSuccessTips;
    }

    public String getFocusFailTips(Context context) {
        if (focusFailTips == null)
            return context.getString(R.string.focus_fail);
        return focusFailTips;
    }

    public boolean isShowFocusTips() {
        return showFocusTips;
    }

    public int getRequestCode() {
        return requestCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (front ? 1 : 0));
        dest.writeString(picturePath);
        dest.writeString(pictureTempPath);
        dest.writeByte((byte) (showMask ? 1 : 0));
        dest.writeByte((byte) (showSwitch ? 1 : 0));
        dest.writeString(backText);
        dest.writeInt(backColor);
        dest.writeInt(backSize);
        dest.writeInt(switchSize);
        dest.writeInt(switchTop);
        dest.writeInt(switchLeft);
        dest.writeInt(takePhotoSize);
        dest.writeInt(maskMarginLeftAndRight);
        dest.writeInt(maskMarginTop);
        dest.writeInt(maskRatioW);
        dest.writeInt(maskRatioH);
        dest.writeInt(focusViewSize);
        dest.writeInt(focusViewColor);
        dest.writeInt(focusViewTime);
        dest.writeInt(focusViewStrokeSize);
        dest.writeInt(cornerViewSize);
        dest.writeInt(maskImgId);
        dest.writeInt(switchImgId);
        dest.writeInt(cancelImgId);
        dest.writeInt(saveImgId);
        dest.writeInt(takePhotoImgId);
        dest.writeInt(resultBottom);
        dest.writeInt(resultLeftAndRight);
        dest.writeInt(backLeft);
        dest.writeString(focusSuccessTips);
        dest.writeString(focusFailTips);
        dest.writeByte((byte) (showFocusTips ? 1 : 0));
        dest.writeInt(requestCode);
    }

    public static class Builder {
        private boolean front = false;
        private Activity mActivity;//
        private String picturePath = Tools.getPicturePath(mActivity);
        private boolean showMask = true;
        private boolean showSwitch = false;
        private String backText = null;
        private int backColor = -1;
        private int backSize = -1;

        private int switchSize = -1;
        private int switchTop = -1;
        private int switchLeft = -1;

        private int takePhotoSize = -1;

        private int maskMarginLeftAndRight = -1;
        private int maskMarginTop = -1;
        private int maskRatioW = -1;
        private int maskRatioH = -1;

        private int focusViewSize = -1;//焦点框的大小
        private int focusViewColor = -1;//焦点框的颜色
        private int focusViewTime = 3;//焦点框显示的时长
        private int focusViewStrokeSize = -1;//焦点框线条的尺寸
        private int cornerViewSize = -1;


        private int maskImgId = -1;//
        private int switchImgId = -1;//
        private int cancelImgId = -1;//
        private int saveImgId = -1;//
        private int takePhotoImgId = -1;//

        private int resultBottom = -1;//拍照到下面的距离
        private int resultLeftAndRight = -1;//拍照到左右距离
        private int backLeft = -1;//返回键到左边的距离

        private String focusSuccessTips;//聚焦成功提示
        private String focusFailTips;//聚焦失败提示
        private boolean showFocusTips = true;
        private int requestCode = CameraConstant.REQUEST_CODE;

        public Builder setFront(boolean front) {
            this.front = front;
            return this;
        }

        public Builder setPicturePath(String picturePath) {
            this.picturePath = picturePath;
            return this;
        }

        public Builder setShowMask(boolean showMask) {
            this.showMask = showMask;
            return this;
        }

        public Builder setShowSwitch(boolean showSwitch) {
            this.showSwitch = showSwitch;
            return this;
        }

        public Builder setBackText(String backText) {
            this.backText = backText;
            return this;
        }

        public Builder setBackColor(int backColor) {
            this.backColor = backColor;
            return this;
        }

        public Builder setBackSize(int backSize) {
            this.backSize = backSize;
            return this;
        }

        public Builder setSwitchSize(int switchSize) {
            this.switchSize = switchSize;
            return this;
        }

        public Builder setSwitchTop(int switchTop) {
            this.switchTop = switchTop;
            return this;
        }

        public Builder setSwitchLeft(int switchLeft) {
            this.switchLeft = switchLeft;
            return this;
        }

        public Builder setTakePhotoSize(int takePhotoSize) {
            this.takePhotoSize = takePhotoSize;
            return this;
        }

        public Builder setMaskMarginLeftAndRight(int maskMarginLeftAndRight) {
            this.maskMarginLeftAndRight = maskMarginLeftAndRight;
            return this;
        }

        public Builder setMaskMarginTop(int maskMarginTop) {
            this.maskMarginTop = maskMarginTop;
            return this;
        }

        public Builder setMaskRatioW(int maskRatioW) {
            this.maskRatioW = maskRatioW;
            return this;
        }

        public Builder setMaskRatioH(int maskRatioH) {
            this.maskRatioH = maskRatioH;
            return this;
        }

        public Builder setFocusViewSize(int focusViewSize) {
            this.focusViewSize = focusViewSize;
            return this;
        }

        public Builder setFocusViewColor(int focusViewColor) {
            this.focusViewColor = focusViewColor;
            return this;
        }

        public Builder setFocusViewTime(int focusViewTime) {
            this.focusViewTime = focusViewTime;
            return this;
        }

        public Builder setFocusViewStrokeSize(int focusViewStrokeSize) {
            this.focusViewStrokeSize = focusViewStrokeSize;
            return this;
        }

        public Builder setCornerViewSize(int cornerViewSize) {
            this.cornerViewSize = cornerViewSize;
            return this;
        }

        public Builder setMaskImgId(int maskImgId) {
            this.maskImgId = maskImgId;
            return this;
        }

        public Builder setSwitchImgId(int switchImgId) {
            this.switchImgId = switchImgId;
            return this;
        }

        public Builder setCancelImgId(int cancelImgId) {
            this.cancelImgId = cancelImgId;
            return this;
        }

        public Builder setSaveImgId(int saveImgId) {
            this.saveImgId = saveImgId;
            return this;
        }

        public Builder setTakePhotoImgId(int takePhotoImgId) {
            this.takePhotoImgId = takePhotoImgId;
            return this;
        }

        public Builder setResultBottom(int resultBottom) {
            this.resultBottom = resultBottom;
            return this;
        }

        public Builder setResultLeftAndRight(int resultLeftAndRight) {
            this.resultLeftAndRight = resultLeftAndRight;
            return this;
        }

        public Builder setBackLeft(int backLeft) {
            this.backLeft = backLeft;
            return this;
        }

        public Builder setFocusSuccessTips(String focusSuccessTips) {
            this.focusSuccessTips = focusSuccessTips;
            return this;
        }

        public Builder setFocusFailTips(String focusFailTips) {
            this.focusFailTips = focusFailTips;
            return this;
        }

        public Builder setActivity(Activity mActivity) {
            this.mActivity = mActivity;
            return this;
        }

        public Builder setShowFocusTips(boolean showFocusTips) {
            this.showFocusTips = showFocusTips;
            return this;
        }

        public Builder setRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public CameraParam build() {
            return new CameraParam(this).startActivity(requestCode);
        }
    }

}
