package com.pos.theikchan.restaurantpos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pos.theikchan.restaurantpos.R;
import com.pos.theikchan.restaurantpos.model.TableItem;

import java.util.ArrayList;


public class AdapterTableGrid extends BaseAdapter {
    private Context mContext;
    private ArrayList<TableItem> mTableArrayList;

    public AdapterTableGrid(Context mContext, ArrayList<TableItem> mTable) {
        super();
        this.mContext = mContext;
        this.mTableArrayList=mTable;
    }

    @Override
    public int getCount() {
        return mTableArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (convertView == null) {
            view = inflater.inflate(R.layout.lay_row_table_item, null);
        } else {
            view = convertView;
        }
        TextView txtState = (TextView) view.findViewById(R.id.txtState);
        //txtState.setTypeface(FontHelper.get(mContext, "zawgyi"));
       // onSetTableStateBackGround(position, txtState);

        TextView txtTableNo= (TextView) view.findViewById(R.id.txtTableNoGrid);

        txtTableNo.setText(String.valueOf(mTableArrayList.get(position).getId()));
        txtState.setText(mTableArrayList.get(position).getmState());

        return view;
    }

    private void onSetTableStateBackGround(int position, TextView txtState) {
        if (mTableArrayList.get(position).getmState().equalsIgnoreCase("busy")){
     //       txtState.setText("Table "+mTableArrayList.get(position));
           txtState.setBackgroundResource(R.color.bg_text_disable);
        }else if(mTableArrayList.get(position).getmState().equalsIgnoreCase("free")){ // txtState.setText("Table "+mTableArrayList.get(position));
            txtState.setBackgroundResource(R.color.bg_text_enable);
        }
    }
}
