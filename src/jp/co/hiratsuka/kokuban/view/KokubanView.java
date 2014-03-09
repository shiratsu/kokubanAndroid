package jp.co.hiratsuka.kokuban.view;


import java.util.ArrayList;

import jp.co.hiratsuka.kokuban.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * こくばんのview
 * @author shiratsu
 *
 */
public class KokubanView extends View {

    ArrayList<Point> draw_list = new ArrayList<Point>();

    private Bitmap  mBitmap;

    private Canvas  mCanvas;
    private Bitmap stampImg;


    private Path    mPath;
    private Paint   mBitmapPaint;
    private int ALPHA;
    private int RED = 255;
    private int BLUE = 255;
    private int GREEN = 255;
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    private Paint paint;
    private Activity _context;
    private int dispwitdth;
    private int dispheight;

    public int linedepth = 10;
    private Context mContext;
    
    public boolean kokubanMode = true;

    public boolean isKokubanMode() {
		return kokubanMode;
	}

	public void setKokubanMode(boolean kokubanMode) {
		this.kokubanMode = kokubanMode;
	}

	/**
     * コンストラクタ
     */
    public KokubanView(Context context, AttributeSet attrs) {
         super(context,attrs);
         mContext = context;

         //
         Display disp = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).
         getDefaultDisplay();

         //
         dispwitdth = disp.getWidth();

         //
         dispheight = disp.getHeight();

         _context = (Activity)context;

         //
         mBitmap = Bitmap.createBitmap(dispwitdth, dispheight, Bitmap.Config.ARGB_8888);
         mCanvas = new Canvas(mBitmap);
         mPath = new Path();
         mBitmapPaint = new Paint(Paint.DITHER_FLAG);
         

     }

    /**
	 * setImgResource
	 * 追加する画像IDをセット
	 * @param resource_id
	 */
	public void setImgResource(int resource_id){
		stampImg = BitmapFactory.decodeResource( getResources(), resource_id );

	}

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public void setAnotherBitmap(Bitmap mBitmap){

        this.mBitmap = mBitmap;
        mCanvas = new Canvas(this.mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        invalidate();
    }
    public void setImageBitmap(Bitmap mBitmap){

//        int width = mBitmap.getWidth();
//        int height = mBitmap.getHeight();
//        int x = (dispwitdth-width)/2;
//        int y = (dispheight-height)/2;
//
//        final Paint  paint  = new Paint();
//        final Matrix matrix = new Matrix();
//        matrix.postTranslate(x, y);
//
//        this.mBitmap = mBitmap;
//        mCanvas = new Canvas();
//        mCanvas.drawBitmap(this.mBitmap, matrix, paint);
//        mPath = new Path();
//        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
//        invalidate();
        this.mBitmap = mBitmap;
        mCanvas = new Canvas(this.mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        invalidate();
    }
    public void setKokuban(Bitmap mBitmap){

        this.mBitmap = mBitmap;
        mCanvas = new Canvas(this.mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        invalidate();
    }

    /**
     * 保存する
     */
    public void saveToFile(){
         if(!sdcardWriteReady()){
             Toast.makeText(_context, "SDカードが利用できません", Toast.LENGTH_SHORT).show();
             return;
         }


        //
         long dateTaken = System.currentTimeMillis();
         String name = createName(dateTaken) + ".jpg";
         String uriStr = MediaStore.Images.Media.insertImage(_context.getContentResolver(), mBitmap, name,
                null);
         if(uriStr != null){
             Toast.makeText(_context, "保存しました", Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(_context, "保存できませんでした", Toast.LENGTH_SHORT).show();
         }
     }

    /**
     * ファイル名作成
     * @param dateTaken
     * @return
     */
     private static String createName(long dateTaken) {
         return DateFormat.format("yyyy-MM-dd_kk.mm.ss", dateTaken).toString();
     }

     /**
     * 描く
     */
    public void onDraw(Canvas canvas) {

    	SharedPreferences pref = mContext.getSharedPreferences("kokuban_pref",mContext.MODE_WORLD_READABLE|mContext.MODE_WORLD_WRITEABLE);
        linedepth = pref.getInt("linedepth", 10);	

        paint = new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.argb(ALPHA,RED, GREEN, BLUE));

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        //線の太さ
        paint.setStrokeWidth(linedepth);

        //
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

        canvas.drawPath(mPath, paint);


    }

    /**
     * タッチイベント
     * @param x
     * @param y
     */
    private void touch_start(float x, float y) {
        //
        mPath.reset();


        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }


    /**
     * タッチが動いたら
     * http://androside.com/page_contents/page_android_fingerDrawOverlay.html
     * http://sunfl0w3r.blog77.fc2.com/blog-entry-53.html
     * @param x
     * @param y
     */
    private void touch_move(float x, float y) {

        //Ç±ÇÃï”ÇÕíËå^ï∂Ç›ÇΩÇ¢Ç»Ç‡ÇÒÅBÇ±Ç§èëÇØÇŒÇ¢Ç¢Ç∆äoÇ¶Ç∆Ç≠
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }

    /**
     * 指を離したら
     */
    private void touch_up() {

        //moveTo()
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, paint);
        // kill this so we don't double draw
        mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
    	float x = event.getX();
        float y = event.getY();
        if(kokubanMode){
        	switch (event.getAction()) {
	            //
	            case MotionEvent.ACTION_DOWN:
	                touch_start(x, y);
	                invalidate();
	                break;
	            //
	            case MotionEvent.ACTION_MOVE:
	                touch_move(x, y);
	                invalidate();
	                break;
	            //
	            case MotionEvent.ACTION_UP:
	                touch_up();
	                invalidate();
	                break;
	        }
        }else{
        	switch (event.getAction()) {

                case MotionEvent.ACTION_UP:
                    mCanvas.drawBitmap( stampImg , x, y, null);
                    invalidate();
                    break;

            }

        }
        
        return true;
    }


    /**
     * 白
     */
    public void setWhiteColor() {
        // TODO Auto-generated method stub
        ALPHA = 255;
        RED = 255;
        BLUE = 255;
        GREEN = 255;
    }

    /**
     * 赤
     */
    public void setRedColor() {
        // TODO Auto-generated method stub
        ALPHA = 255;
        RED = 246;
        BLUE = 171;
        GREEN = 171;
    }

    /**
     * 青
     */
    public void setBlueColor() {
        // TODO Auto-generated method stub
        ALPHA = 255;
        RED = 171;
        BLUE = 244;
        GREEN = 177;
    }

    /**
     * 黄色
     */
    public void setYellowColor() {
        // TODO Auto-generated method stub
        ALPHA = 255;
        RED = 238;
        BLUE = 174;
        GREEN = 241;
    }

    /**
     * 初期化
     */
    public void setDefault() {


        // TODO Auto-generated method stub
        mBitmap = Bitmap.createBitmap(dispwitdth, dispheight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        invalidate();

    }

    /**
     * 消す
     */
    public void setElase() {


        // TODO Auto-generated method stub
        mBitmap = Bitmap.createBitmap(dispwitdth, dispheight, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        invalidate();

    }


    private boolean sdcardWriteReady(){
         String state = Environment.getExternalStorageState();
         return (Environment.MEDIA_MOUNTED.equals(state));
    }

    public void setBlackColor() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        ALPHA = 255;
        RED = 20;
        BLUE = 20;
        GREEN = 20;
    }

    /**
     * 緑
     */
    public void setGreenColor() {
        // TODO Auto-generated method stub
        ALPHA = 255;
        RED = 171;
        BLUE = 177;
        GREEN = 244;
    }


}

