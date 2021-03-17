package com.wld.mycamerax.activity;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.permissionx.guolindev.PermissionX;
import com.wld.mycamerax.R;
import com.wld.mycamerax.util.CameraParam;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BitmapSizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_size);

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(v -> {
            PermissionX.init(this)
                    .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .request((boolean allGranted, List<String> grantedList, List<String> deniedList) -> {
                        if (allGranted) {
                            showDetails();
                        } else {
                            Toast.makeText(getApplicationContext(), "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }

    private void showDetails() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;        // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        Log.d("wld_________", "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.d("wld_________", "density=" + density + "; densityDPI=" + densityDPI);

        Bitmap bitmap = getBitmapFromPath(getPicturePath());//5.85m  3840*5120

        Log.d("wld_________", bitmap.toString());
        compressBmpFromBmp(bitmap);
    }

    public String getPicturePath() {
        String cameraPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM"
                + File.separator + "Camera"
                + File.separator + "IMG_20210316_112142.jpg";
        return cameraPath;
    }


    public void compressBmpFromBmp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);

        Log.d("wld_________", baos.toByteArray().length * 1.0 / 1024 / 1024 + "");

//        if (baos.toByteArray().length / 1024 < size)
//            return image;
//        options = size * 100 / (baos.toByteArray().length / 1024);
//        baos.reset();
//        if (options > 100) {
//            return image;
//        }
//        if (options < 1)
//            options = 1;
//        image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
//        if (!image.isRecycled())
//            image.recycle();
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        Log.d("wld_________", bitmap.toString());
        saveBitmap(bitmap);
    }

    private boolean saveBitmap(Bitmap bitmap) {
        String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM"
                + File.separator + "Camera"
                + File.separator + "IMG_20210316_112145.jpg";
        try {
            File file = new File(savePath);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            boolean b = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Bitmap getBitmapFromPath(String path) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            return getBitmapFromUri(Uri.parse(path));
//        } else {
        return BitmapFactory.decodeFile(path);
//        }
    }


    // 通过uri加载图片
    public Bitmap getBitmapFromUri(Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
