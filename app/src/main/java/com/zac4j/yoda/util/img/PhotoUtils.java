package com.zac4j.yoda.util.img;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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

  public static void pickPhoto(Activity activity, int requestCode) {
    Intent intent = new Intent(Intent.ACTION_PICK);
    intent.setType("image/*");
    activity.startActivityForResult(intent, requestCode);
  }

  public static Uri capturePhoto(Activity activity, int requestCode) {
    File file = null;
    Uri imageUri = null;
    try {
      file = createImageFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (isCanCapture(activity, file)) {
      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

      imageUri = Uri.fromFile(file);
      intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
      activity.startActivityForResult(intent, requestCode);
    }
    return imageUri;
  }

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

  public static MultipartBody.Part prepareImagePart(Context context, String partName, Uri fileUri) {
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
        && new Intent(MediaStore.ACTION_IMAGE_CAPTURE).resolveActivity(context.getPackageManager())
        != null;
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
  public static File createImageFile() throws IOException {
    File storageDir = Environment.getExternalStorageDirectory();
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
      newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
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
}
