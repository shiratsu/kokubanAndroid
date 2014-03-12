package jp.co.hiratsuka.kokuban2.another;

import java.util.ArrayList;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import jp.co.hiratsuka.kokuban2.R;
import jp.co.hiratsuka.kokuban2.another.adapter.LineDepthAdapter;
import jp.co.hiratsuka.kokuban2.another.view.KokubanView;
import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.ListView;

public class LineDepth extends Activity implements OnItemClickListener  {

    private List<String> depthList = new ArrayList<String>();

    private LineDepthAdapter la;
//    private AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linedepth);

//        RelativeLayout layout = (RelativeLayout)findViewById(R.id.top);

//        // adView を取得する
//        mAdView = (AdView) findViewById(R.id.adView);
//        // 一般的なリクエストを行って広告を読み込む
//        mAdView.loadAd(new AdRequest());

        
        
        depthList = Arrays.asList(getText(R.string.sen5).toString()
                , getText(R.string.sen10).toString()
                );
        la = new LineDepthAdapter(LineDepth.this, 0, depthList);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter( la );
        listview.setScrollingCacheEnabled( false );
        listview.setOnItemClickListener( this );

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

        SharedPreferences pref = getSharedPreferences("kokuban_pref",MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
        Editor e = pref.edit();

        // TODO Auto-generated method stub
        switch (position) {
            case 0:
                e.putInt("linedepth", 5);
                break;
            case 1:
                e.putInt("linedepth", 10);
                break;
            case 2:
                e.putInt("linedepth", 15);
                break;
            case 3:
                e.putInt("linedepth", 20);
                break;

            default:
                e.putInt("linedepth", 10);
                break;
        }
        e.commit();
        this.finish();
    }

}
