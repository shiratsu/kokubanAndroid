package jp.co.hiratsuka.kokuban2.another.adapter;

import java.util.List;

import jp.co.hiratsuka.kokuban2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter<String> {

    private List<String> m_listMenuInfo; // メニュー情報
    private LayoutInflater inflater;

    public MenuAdapter(Context context, int textViewResourceId,
            List<String> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.m_listMenuInfo = objects;
    }

    @Override
    public String getItem( int position )
    {
        return m_listMenuInfo.get( position );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        if(v == null){
            //1行分layoutからViewの塊を生成
            v = inflater.inflate(R.layout.menu_row, null);
        }
        String menu = (String)m_listMenuInfo.get(position);
        TextView menuText = (TextView)v.findViewById(R.id.menuname);
        menuText.setText(menu);

        return v;

    }

}
