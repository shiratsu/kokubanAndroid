package jp.co.hiratsuka.kokuban.another.adapter;

import java.util.HashMap;
import java.util.List;

import jp.co.hiratsuka.kokuban.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LineDepthAdapter extends ArrayAdapter<String> {

    private List<String> item; // メニュー情報
    private LayoutInflater inflater;

    public LineDepthAdapter(Context context,
            int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub

        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.item = objects;
    }

    @Override
    public String getItem( int position )
    {
        return item.get( position );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        if(v == null){
            //1行分layoutからViewの塊を生成
            v = inflater.inflate(R.layout.linedepth_row, null);
        }

        String lineName = (String)item.get(position);

        ImageView lineimage = (ImageView)v.findViewById(R.id.lineimage);
        TextView depthname = (TextView)v.findViewById(R.id.depthname);
        switch (position) {
            case 0:
                lineimage.setImageResource(R.drawable.sen5);
                break;
            case 1:
                lineimage.setImageResource(R.drawable.sen10);
                break;

            default:
                break;
        }
        depthname.setText(lineName);
        
        return v;

    }

}
