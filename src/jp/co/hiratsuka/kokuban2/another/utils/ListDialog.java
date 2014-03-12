package jp.co.hiratsuka.kokuban2.another.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.hiratsuka.kokuban2.R;
import jp.co.hiratsuka.kokuban2.another.adapter.MenuAdapter;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListDialog implements OnItemClickListener {

    private Context					m_parent;				// 呼び出し元
    private AlertDialog				m_dlg;					// ダイアログ
    private MenuAdapter				m_menuadapter; 			// menu情報アダプタ
    private List<String> 			listMenuInfo;
    private OnStringSelectListener	m_listener;			// 結果受取先

    public ListDialog(Context context,OnStringSelectListener listener) {
        // TODO Auto-generated constructor stub
        m_parent = context;
        listMenuInfo = Arrays.asList(m_parent.getText(R.string.select_photo).toString()
                , m_parent.getText(R.string.save_libraly).toString()
                , m_parent.getText(R.string.kokuban_mode).toString()
                , m_parent.getText(R.string.snow_image).toString()
                , m_parent.getText(R.string.use_chork).toString()
                , m_parent.getText(R.string.line_select).toString()
                );
        m_listener = (OnStringSelectListener)listener;
    }



    public void show(){

        String title = m_parent.getText(R.string.menu).toString();

        // リストビュー
        ListView listview = new ListView( m_parent );
        listview.setScrollingCacheEnabled( false );
        listview.setOnItemClickListener( this );



        m_menuadapter = new MenuAdapter( m_parent, 0, listMenuInfo );
        listview.setAdapter( m_menuadapter );

        Builder builder = new AlertDialog.Builder( m_parent );
        builder.setTitle( title );
        builder.setPositiveButton( "Cancel", null );
        builder.setView( listview );
        m_dlg = builder.show();
    }

    @Override
    public void onItemClick(AdapterView<?> l,
            View v,
            int position,
            long id ) {
        // TODO Auto-generated method stub

        if( null != m_dlg )
        {
            m_dlg.dismiss();
            m_dlg = null;
        }

//        String menu = m_menuadapter.getItem(position);
        m_listener.onMenuSelect( position );

    }

    // 選択した情報を取り出すためのリスナーインターフェース
     public interface OnStringSelectListener
     {
         // ファイルが選択されたときに呼び出される関数
         public void onMenuSelect( int position );
     }

}
