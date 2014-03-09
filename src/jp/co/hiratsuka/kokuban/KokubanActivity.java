package jp.co.hiratsuka.kokuban;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import jp.co.hiratsuka.kokuban.conf.CodeDefine;
import jp.co.hiratsuka.kokuban.utils.AndroidUtils;
import jp.co.hiratsuka.kokuban.utils.ListDialog;
import jp.co.hiratsuka.kokuban.view.KokubanView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;



import android.view.Display;

import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;


public class KokubanActivity extends Activity implements OnFocusChangeListener,jp.co.hiratsuka.kokuban.utils.ListDialog.OnStringSelectListener {

    private static final String TAG = "KokubanActivity";
    public KokubanView kokuban_view;


    private final String siteId = "2494";
    private final String locationId = "2861";
    private Uri mImageUri;
    private Intent imgIntent = null;
    private SharedPreferences sp;

//    private AdView mAdView;

    private int dispwitdth;
    private int dispheight;

    private Bitmap bitmap = null;
    private Uri resultUri = null;

    private boolean billingmode = false;
    private boolean kokubanmode = true;


    private List<String> menuList = new ArrayList<String>();

    SharedPreferences pref;

    //viewを追加しないといけないので追加
    private LinearLayout layout;



    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        //初回起動時に10で保存しておく
        SharedPreferences pref = getSharedPreferences("kokuban_pref",MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
        Editor e = pref.edit();
        e.putInt("linedepth", 10);
        e.commit();

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.top);

//        //Admob初期設定
//        AdView adView = new AdView(this, AdSize.BANNER, "ca-app-pub-8789201169323567/1546598708");
//
//        // Add the adView to it
//        layout.addView(adView);
//
//        // Initiate a generic request to load it with an ad
//        AdRequest request = new AdRequest();
//        //request.setTesting(true);
//
//        adView.loadAd(request);
        Display disp = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).
        getDefaultDisplay();

        //幅
        dispwitdth = disp.getWidth();

        //高さ
        dispheight = disp.getHeight();

        //viewとボタンの定義
        kokuban_view = (KokubanView) findViewById(R.id.kokuban_view);

        Button elaseButton = (Button) this.findViewById(R.id.elaseButton);
        Button whiteButton = (Button) this.findViewById(R.id.whiteButton);
        Button redButton = (Button) this.findViewById(R.id.redButton);
        Button blueButton = (Button) this.findViewById(R.id.blueButton);
        Button yellowButton = (Button) this.findViewById(R.id.yellowButton);
        Button greenButton = (Button) this.findViewById(R.id.greenButton);
        Button tabButton = (Button) this.findViewById(R.id.tabButton);

        this.layout = (LinearLayout) this.findViewById(R.id.middle);

        elaseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//
//                //kokuban_view.setElase();
//
//
//                AlertDialog.Builder AlertDlgBldr = new AlertDialog.Builder(KokubanActivity.this);
//                AlertDlgBldr.setTitle(getText(R.string.elase_mode).toString());
//                AlertDlgBldr.setMessage(getText(R.string.select_elase).toString());
//                AlertDlgBldr.setPositiveButton(getText(R.string.default_elase).toString(), new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        kokuban_view.setDefault();
//                    }
//
//                });
//                AlertDlgBldr.setNeutralButton(getText(R.string.all_elase).toString(), new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                    	kokuban_view.setElase();
//                        if(resultUri != null){
//
//
//
//                            // ÉrÉbÉgÉ}ÉbÉvâÊëúÇéÊìæ
//                           try {
//
//                               String filePath = AndroidUtils.feedFilePath(KokubanActivity.this, resultUri.toString());
//
//
//                               bitmap = AndroidUtils.compressImage(filePath,dispwitdth,dispheight);
//                               bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//                               kokuban_view.setAnotherBitmap(bitmap);
//
//                           } catch (Exception e) {
//                               e.printStackTrace();
//                               AndroidUtils.toastMessage(getApplicationContext(), getText(R.string.image_get_error).toString());
//
//                           }
//                       }else{
//                           kokuban_view.setElase();
//                       }
//                    }
//
//                });
//                AlertDlgBldr.setNegativeButton(getText(R.string.yubi_elase).toString(), new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        kokuban_view.setBlackColor();
//                    }
//
//                });
//
//                AlertDialog AlertDlg = AlertDlgBldr.create();
//                AlertDlg.show();

                kokuban_view.setDefault();
            }
        });

        whiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(KokubanActivity.this, getText(R.string.white).toString(), Toast.LENGTH_SHORT).show();
                kokuban_view.setWhiteColor();
            }

        });
        redButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(KokubanActivity.this, getText(R.string.red).toString(), Toast.LENGTH_SHORT).show();
                kokuban_view.setRedColor();
            }

        });
        blueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(KokubanActivity.this, getText(R.string.blue).toString(), Toast.LENGTH_SHORT).show();
                kokuban_view.setBlueColor();
            }

        });
        yellowButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(KokubanActivity.this, getText(R.string.yellow).toString(), Toast.LENGTH_SHORT).show();
                kokuban_view.setYellowColor();
            }

        });
        greenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(KokubanActivity.this, getText(R.string.green).toString(), Toast.LENGTH_SHORT).show();
                kokuban_view.setGreenColor();
            }

        });
        tabButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ListDialog ld = new ListDialog(KokubanActivity.this,KokubanActivity.this);
                ld.show();
                return;
            }

        });




        kokuban_view.setWhiteColor();


    }

    /**
     * ギャラリーを扱う
     * @access private
     * @return void
     */
    @SuppressLint("NewApi")
    protected void handleGallery() {

        imgIntent = new Intent();
        imgIntent.setType("image/*");
        imgIntent.setAction(Intent.ACTION_PICK);
        startActivityForResult(imgIntent, CodeDefine.REQUEST_PICK_CONTACT);
    }

    /**
     * ギャラリーから戻ってきたときの処理
     * @access protected
     * @return void
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        boolean movieFlag = false;

        switch (requestCode) {
            case CodeDefine.REQUEST_PICK_CONTACT:

                if(data != null){
                    resultUri = data.getData();
                }
                break;
            case CodeDefine.REQUEST_PICK_CONTACT_MOVIE:
                movieFlag = true;
                if(data != null){
                    resultUri = data.getData();
                }
                break;
            case CodeDefine.REQUEST_IMAGE_CAPTURED:
                sp = getSharedPreferences("picture",MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);

                //ここは端末によって、データっていうオブジェクトにファイルパスが入ってることがあるので、そのため
                if(data != null){
                    resultUri = data.getData();

                }
                if(resultUri == null){
                    String pictureUri = sp.getString("pictureUri", null);
                    resultUri = Uri.parse(pictureUri);
                }
                break;
            case CodeDefine.REQUEST_VIDEO_CAPTURED:
                movieFlag = true;
                if(data != null){
                    resultUri = data.getData();
                }
                break;


        }

        if(resultUri != null){

             // ビットマップ画像を取得
            try {
                //
                String filePath = AndroidUtils.feedFilePath(KokubanActivity.this, resultUri.toString());

                //圧縮(端末によって、メモリーアウトで落ちることがあるため)
                //表示用
                bitmap = AndroidUtils.compressImage(filePath,dispwitdth,dispheight);
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

                kokuban_view.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
                AndroidUtils.toastMessage(getApplicationContext(), getText(R.string.image_get_error).toString());

            }
        }else{
            AndroidUtils.toastMessage(getApplicationContext(), getText(R.string.image_get_error).toString());
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onMenuSelect(int position) {
        // TODO Auto-generated method stub
        switch (position) {
            case CodeDefine.MENU_0:
                handleGallery();
                kokuban_view.kokubanMode = true;
                break;
            case CodeDefine.MENU_1:
                kokuban_view.saveToFile();
                kokuban_view.kokubanMode = true;
                break;
            case CodeDefine.MENU_2:
            	kokuban_view.kokubanMode = true;
                break;
            case CodeDefine.MENU_3:
            	kokuban_view.kokubanMode = false;
                break;
            case CodeDefine.MENU_4:
                this.modeKokuban();
                kokuban_view.kokubanMode = true;
                break;
            case CodeDefine.MENU_5:

                Intent intent = new Intent(KokubanActivity.this,LineDepth.class);
                startActivity(intent);

                break;

            default:
                break;
        }
    }

    /**
     * 黒板モードに戻る
     */
    private void modeKokuban() {

        Bitmap mBitmap = Bitmap.createBitmap(dispwitdth, dispheight, Bitmap.Config.ARGB_8888);
        kokuban_view.setAnotherBitmap(mBitmap);
        return;
    }

}
