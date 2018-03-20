package com.zac4j.yoda.util.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import com.zac4j.yoda.util.FileUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Photo Utilities
 * Created by zac on 3/29/2017.
 */

public class PhotoUtils {

    public static final int LONG_IMAGE_LENGTH = 980;

    public static void pickPhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    public static String capturePhoto(Activity activity, int requestCode) {
        File file = null;
        try {
            file = createImageFile(activity);
            if (isCanCapture(activity, file)) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri imgUri = FileProvider.getUriForFile(activity,
                    activity.getPackageName() + ".fileprovider", file);
                System.out.println("file provider >> imgUri >> " + imgUri);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                activity.startActivityForResult(intent, requestCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file == null ? "" : file.getAbsolutePath();
    }

    /**
     * Compress image
     *
     * @param context context
     * @param uri image uri
     * @return compressed image in byte array.
     */
    private static byte[] compressImage(Context context, Uri uri) {
        String filePath = FileUtils.getPath(context, uri);

        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        int degree = getPhotoDegree(filePath);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (bitmap != null && degree != 0) {
            bitmap = rotateBitmap(bitmap, degree);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bos);
        }
        return bos.toByteArray();
    }

    /**
     * 准备 form-data 形式上传图片
     *
     * @param context 上下文
     * @param partName 文件名称
     * @param fileUri 文件路径
     * @return MultipartBody 图片部分
     */
    public static MultipartBody.Part prepareImagePart(Context context, String partName,
        Uri fileUri) {
        File file = FileUtils.getFile(context, fileUri);

        byte[] compressedFile = compressImage(context, fileUri);

        // Create RequestBody instance from file
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), compressedFile);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
    }

    /**
     * 检验是否可以调用相机
     *
     * @param context 下上文
     * @param photoFile 图片文件
     * @return true 假如可以调用相机，否则返回 false
     */
    public static boolean isCanCapture(Context context, File photoFile) {
        return photoFile != null
            && new Intent(MediaStore.ACTION_IMAGE_CAPTURE).resolveActivity(
            context.getPackageManager()) != null;
    }

    /**
     * 获取图片文件名
     *
     * @return 图片文件名
     */
    private static String getImageName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String timestamp = sdf.format(new Date());
        return "IMG_" + timestamp;
    }

    /**
     * 创建临时文件
     *
     * @return 临时文件
     */
    public static File createImageFile(Context context) throws IOException {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(getImageName(), /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */);
    }

    /**
     * 判断照片角度
     */
    private static int getPhotoDegree(String filePath) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转照片
     */
    private static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        Bitmap newBitmap = null;
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degree);
            newBitmap =
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
        }
        return newBitmap;
    }

    /**
     * 获取图片文件路径
     *
     * @param file 图皮文件
     * @return 图片文件路径
     */
    public static String getImageFilePath(File file) {
        return "file:" + file.getAbsolutePath();
    }

    public static float pixelToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
