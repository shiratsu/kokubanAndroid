package jp.co.hiratsuka.kokuban.another.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class AddImageView extends View {
	
	private Bitmap mBitmap;
	private Bitmap stampImg;
	private Canvas mCanvas;


	public AddImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * setImgResource
	 * 追加する画像IDをセット
	 * @param resource_id
	 */
	public void setImgResource(int resource_id){
		
	}

}
