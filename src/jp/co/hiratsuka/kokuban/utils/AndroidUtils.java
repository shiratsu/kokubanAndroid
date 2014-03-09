package jp.co.hiratsuka.kokuban.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.widget.Toast;

public class AndroidUtils {


    /**
     * �t�@�C������r�b�g�}�b�v
     * @param path
     * @param maxW
     * @param maxH
     * @accss private
     * @return
     */
    public static Bitmap file2bmp(String path,int maxW,int maxH) {
        BitmapFactory.Options options;
        InputStream in=null;
        try {
            //
            options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            in=new FileInputStream(path);
            BitmapFactory.decodeStream(in,null,options);
            in.close();
            int scaleW=options.outWidth/maxW+1;
            int scaleH=options.outHeight/maxH+1;
            int scale =Math.max(scaleW,scaleH);

            //画像�?読み込み
            options=new BitmapFactory.Options();
            options.inJustDecodeBounds=false;
            options.inSampleSize=scale;
            in=new FileInputStream(path);
            Bitmap bmp=BitmapFactory.decodeStream(in,null,options);
            in.close();
            return bmp;
        } catch (Exception e) {
            try {
                if (in!=null) in.close();
            } catch (Exception e2) {
            }
            return null;
        }
    }

    /**
     * �f�[�^����t�@�C����
     * @param data
     * @param path
     * @access public
     * @return void
     */
    public static void data2file(byte[] data, String path)  throws Exception {
        FileOutputStream out=null;
        try {
            out=new FileOutputStream(path);
            out.write(data);
            out.close();
        } catch (Exception e) {
            if (out!=null) out.close();
            throw e;
        }
        // TODO Auto-generated method stub

    }

    /**
     * Bitmap to Data
     * @param src      Bitmap
     * @param format   Bitmap.CompressFormat
     * @param quality   int
     * @return         byte[]
     */
    public static byte[] chngBmpToData(Bitmap src, Bitmap.CompressFormat format, int quality) {
       ByteArrayOutputStream output = new ByteArrayOutputStream();
       src.compress(format, quality, output);
       return output.toByteArray();

    }
    /**
     * �ۑ�
     * @param data   byte[]   画像データ
     * @param dataName   String   保存パス
     * @return   boolean
     * @throws Exception
     */
    public static void saveDataToStorage(byte[] data, String dataName) throws Exception {
       FileOutputStream fileOutputStream = null;
       try {
          // �?��保存�?に保存す�?
           fileOutputStream = new FileOutputStream(dataName);
          fileOutputStream.write(data);
       } catch (Exception e) {
       } finally {
          if (fileOutputStream != null) {
             fileOutputStream.close();
             fileOutputStream = null;
          }
       }
    }

    /**
     * ���T�C�Y
     */
    public static Bitmap resizeBitMap(Bitmap baseBmp, int w,
            int i) {
        // TODO Auto-generated method stub
        int width = baseBmp.getWidth();
        int height = baseBmp.getHeight();
        int newWidth = w;

        //�?��基準にリサイズ
        // calculate the scale - in this case = 0.4f
        float scale = ((float) newWidth) / width;

        // createa matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scale, scale);


        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(baseBmp, 0, 0,
                          width, height, matrix, true);
        return resizedBitmap;
    }


    /**
     * Toast��\��
     * @param string
     */
    public static void toastMessage(Context context,String string) {
        // TODO Auto-generated method stub
        Toast.makeText(context,
                string, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * �A���[�g��\��
     * @param errorTitle
     * @param errorStr
     * @param context
     */
    public static void showAlert(Context context,String title, String str) {
        // TODO Auto-generated method stub
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // タイトルを設�?        alertDialogBuilder.setTitle(title);
        // メ�?��ージを設�?        alertDialogBuilder.setMessage(str);
        // アイコンを設�?        alertDialogBuilder.setIcon(R.drawable.icon72);
        // Positiveボタンとリスナを設�?
        alertDialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        // アラートダイアログを表示しま�?
        alertDialog.show();
    }


    /**
     * ���ݓ��Ԃ�
     * * @return
     */
    public static List<String> feedNowDate() {
        Date date1 = new Date();  //(1)Dateオブジェクトを生�?
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd");

        String yyyymmdd = sdf1.format(date1);
        String yyyy = sdf2.format(date1);
        String mm = sdf3.format(date1);
        String dd = sdf4.format(date1);

        List<String> dateAry = new ArrayList<String>();
        dateAry.add(yyyymmdd);
        dateAry.add(yyyy);
        dateAry.add(mm);
        dateAry.add(dd);
        return dateAry;
    }

    /**
     * 画像を圧縮(OutOfMemory)対策
     * @param defaultimageheight
     * @param defaultimagewidth
     * @param string
     * @return
     */
    public static Bitmap compressImage(String path, int defaultimagewidth, int defaultimageheight) {
        // TODO Auto-generated method stub
        //読み込み用のオプションオブジェクトを生�?

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(path, options);


        int scaleW = 0;
        int scaleH = 0;

        if(defaultimagewidth > 0 && defaultimageheight > 0){
            scaleW = options.outWidth / defaultimagewidth + 1;
            scaleH = options.outHeight / defaultimageheight + 1;
        }else{
            scaleW = 1;
            scaleH = 1;
        }


        int scale = Math.max(scaleW, scaleH);


        options.inJustDecodeBounds = false;

        options.inSampleSize = scale;


        Bitmap image = BitmapFactory.decodeFile(path, options);

        return image;

    }

    /**
     * outof memory�΍�
     * @param defaultimageheight
     * @param defaultimagewidth
     * @param string
     * @return
     * @throws FileNotFoundException
     */
    public static Bitmap compressImageForList(File f) throws FileNotFoundException {
        // TODO Auto-generated method stub
        //読み込み用のオプションオブジェクトを生�?

        BitmapFactory.Options options = new BitmapFactory.Options();
        //こ�?値をtrueにすると実際には画像を読み込まず�?
        //画像�?サイズ�??�?��を取得することができます�?
        options.inJustDecodeBounds = true;
        //画像ファイル読み込み
        //ここでは上記�?オプションがtrueのため実際の
        //画像�?読み込まれな�?��す�?
        BitmapFactory.decodeStream(new FileInputStream(f), null, options);

        //読み込んだサイズはoptions.outWidthとoptions.outHeightに
        //格納されるので、その値から読み込�?��の縮尺を計算します�?
        //こ�?サンプルではどんな大きさの画像で�?VGAに収まるサイズ�?        //計算して�?��す�?
        int scaleW = 2;
        int scaleH = 2;



        //縮尺は整数値で�?なら画像�?縦横のピクセル数�?/2にしたサイズ�?        //3な�?/3にしたサイズで読み込まれます�?
        int scale = Math.max(scaleW, scaleH);

        //今度は画像を読み込みたいのでfalseを指�?
        options.inJustDecodeBounds = false;

        //先程計算した縮尺値を指�?
        options.inSampleSize = scale;

        //これで�?��した縮尺で画像を読み込めます�?
        //もちろん容量も小さくなる�?で扱�?��すいです�?
        Bitmap image = BitmapFactory.decodeStream(new FileInputStream(f), null, options);

        return image;

    }

//    /**
//     * Exif�??を確認して、回転が�?��なら回転
//     * @param bitmap
//     * @param string
//     * @return
//     * @throws IOException
//     */
//    public static Bitmap checkImgOrientation(Bitmap bitmap, String path) throws IOException {
//        //exif�??から写真�??を取�?        ExifInterface exifInterface = new ExifInterface(path);
//
//        String orientationStr = getExifString(exifInterface,ExifInterface.TAG_ORIENTATION);
//        int orientation = Integer.valueOf(orientationStr);
//        Matrix matrix = new Matrix();
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        switch (orientation) {
//            case ExifInterface.ORIENTATION_ROTATE_90:
//                // createa matrix for the manipulation
//                matrix.postRotate(90);
//                //画像�?サイズを取�?
//
//                // recreate the new Bitmap
//                bitmap = Bitmap.createBitmap(bitmap, 0, 0,
//                                  width, height, matrix, true);
//                break;
//
//            case ExifInterface.ORIENTATION_ROTATE_180:
//                // createa matrix for the manipulation
//
//                matrix.postRotate(180);
//                //画像�?サイズを取�?
//                // recreate the new Bitmap
//                bitmap = Bitmap.createBitmap(bitmap, 0, 0,
//                                  width, height, matrix, true);
//                break;
//
//            default:
//                break;
//        }
//
//
//
//        return bitmap;
//    }



//    /**
//     * exif�??から�??タを取�?     * @param exifInterface
//     * @param tagDatetime
//     * @return
//     */
//    public static String getExifString(ExifInterface ei, String tag) {
//        // TODO Auto-generated method stub
//        return ei.getAttribute(tag);
//    }

    /**
     * ファイルパスを取�?     * @param string
     * @return
     */
    public static String feedFilePath(Context context,String string) {
        // TODO Auto-generated method stub
        ContentResolver cr = context.getContentResolver();
        String[] columns = {MediaStore.Images.Media.DATA };
        Cursor c = cr.query(Uri.parse(string), columns, null, null, null);
        c.moveToFirst();
        return c.getString(0);
    }

    /**
     * ファイル名を作�?
     * @param dateTaken
     * @return
     */
    public static String createName(long dateTaken) {
        return DateFormat.format("yyyy-MM-dd_kk.mm.ss", dateTaken).toString();
    }

    public static String feedNowDateTime() {
        // TODO Auto-generated method stub
        Date date1 = new Date();  //(1)Dateオブジェクトを生�?
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf1.format(date1);
    }

//    /**
//     * sha256の暗号化キーを返す
//     * @param password
//     * @param passwordSalt
//     * @return
//     */
//    public static String convertSha256(String password, String passwordSalt) {
//        // TODO Auto-generated method stub
//        return DigestUtils.sha256Hex(password+passwordSalt);
//    }

    /**
     * InputStreamをバイト�?列に変換する
     *
     * @param is
     * @return バイト�?�?     */
    public static byte[] getBytes(InputStream is) {
        // byte型�?配�?を�?力�?とするクラス�?        // 通常、バイト�?力ストリー�??ファイル�?��ケ�?��を�?力�?とするが�?
        // ByteArrayOutputStreamクラスはbyte[]変数、つまりメモリを�?力�?とする�?
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        OutputStream os = new BufferedOutputStream(b);
        int c;
        try {
            while ((c = is.read()) != -1) {
                os.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 書き込み先�?ByteArrayOutputStreamクラス�?��となる�?
        // こ�?書き込まれたバイトデータをbyte型�?列として取り出す�?合には�?        // toByteArray()メソ�?��を呼び出す�?
        return b.toByteArray();
    }



}
