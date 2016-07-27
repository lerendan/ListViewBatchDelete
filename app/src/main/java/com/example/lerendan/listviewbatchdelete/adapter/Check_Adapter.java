package com.example.lerendan.listviewbatchdelete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lerendan.listviewbatchdelete.R;
import com.example.lerendan.listviewbatchdelete.bean.Check;

import java.util.List;

/**
 * Created by lerendan on 2016/7/27.
 */

public class Check_Adapter extends BaseAdapter {

    private List<Check> mData;
    private LayoutInflater mInflater;

    public Check_Adapter(Context context, List<Check> data) {
        mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public void setChecks(List<Check> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.lv_check_item, null);
            viewHolder.iv_click = (ImageView) convertView.findViewById(R.id.iv_click);
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);


            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mData.get(position).isChecked()) {
            viewHolder.iv_click.setVisibility(View.VISIBLE);
        }else {
            viewHolder.iv_click.setVisibility(View.INVISIBLE);
        }

        viewHolder.tv_id.setText(mData.get(position).getId());
        viewHolder.tv_name.setText(mData.get(position).getName());

        // viewHolder.iv_click.setVisibility(View.INVISIBLE);


        return convertView;
    }

    public class ViewHolder {
        public TextView tv_id, tv_name;
        public ImageView iv_click;
    }
}
