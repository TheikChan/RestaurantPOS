package com.pos.theikchan.restaurantpos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pos.theikchan.restaurantpos.R;
import com.pos.theikchan.restaurantpos.model.FoodItem;
import com.pos.theikchan.restaurantpos.model.TableItem;

import java.util.ArrayList;


public class AdapterGridFoodMenu extends BaseAdapter /*implements View.OnClickListener*/{
    private Context mContext;
    private ArrayList<FoodItem> mFoodArrayList;


    private ViewHolder vHolder;

    public AdapterGridFoodMenu(Context mContext, ArrayList<FoodItem> mFoodArrayList) {
        super();
        this.mContext = mContext;
        this.mFoodArrayList=mFoodArrayList;
    }

    @Override
    public int getCount() {
        return mFoodArrayList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        vHolder=new ViewHolder();

        if (convertView == null) {
            view = inflater.inflate(R.layout.lay_grid_food_item, null);
        } else {
            view = convertView;
        }

        vHolder.txtName = (TextView) view.findViewById(R.id.lay_grid_food_item_txt_name);
        vHolder.txtPrice = (TextView) view.findViewById(R.id.lay_grid_food_item_txt_price);
        vHolder.txtOrderCount = (TextView) view.findViewById(R.id.lay_grid_food_item_txt_order_count);

        vHolder.imgBtnDec = (ImageView) view.findViewById(R.id.lay_grid_food_item_img_btn_dec);
        vHolder.imgBtnInc= (ImageView) view.findViewById(R.id.lay_grid_food_item_img_btn_inc);

        vHolder.btnAddOrder = (Button) view.findViewById(R.id.lay_grid_food_item_btn_add_order);

        vHolder.imgPic = (ImageView) view.findViewById(R.id.lay_grid_food_item_img);


        showData(position,vHolder);
       // addClickEvent(vHolder);

        //txtState.setTypeface(FontHelper.get(mContext, "zawgyi"));
       // onSetTableStateBackGround(position, txtState);

        vHolder.imgBtnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int qty=Integer.valueOf(vHolder.txtOrderCount.getText().toString());

                if(qty>1){
                    vHolder.txtOrderCount.setText(String.valueOf(--qty));

                    int price=Integer.valueOf(vHolder.txtPrice.getText().toString())*qty;

                    vHolder.txtPrice.setText(String.valueOf(price));
                }

                Toast.makeText(mContext,"Add Order "+position+"qty"+qty,Toast.LENGTH_SHORT).show();
            }
        });

        vHolder.imgBtnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int  qty=Integer.valueOf(vHolder.txtOrderCount.getText().toString());
                vHolder.txtOrderCount.setText(String.valueOf(++qty));

                int price=Integer.valueOf(vHolder.txtPrice.getText().toString())*qty;

                vHolder.txtPrice.setText(String.valueOf(price));

                Toast.makeText(mContext,"Add Order "+position+"qty"+qty,Toast.LENGTH_SHORT).show();
            }
        });

        vHolder.btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Add Order "+position,Toast.LENGTH_SHORT).show();
            }
        });

        vHolder.imgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Image Click",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showData(int position,ViewHolder viewHolder){
        viewHolder.txtName.setText(mFoodArrayList.get(position).getmName());
        viewHolder.txtPrice.setText(mFoodArrayList.get(position).getmPrice());
        viewHolder.imgPic.setImageResource(mFoodArrayList.get(position).getmImg());
    }

   /* private void onSetTableStateBackGround(int position, TextView txtState) {
        if (mFoodArrayList.get(position).getmState().equalsIgnoreCase("busy")){
     //       txtState.setText("Table "+mTableArrayList.get(position));
           txtState.setBackgroundResource(R.color.bg_text_disable);
        }else if(mFoodArrayList.get(position).getmState().equalsIgnoreCase("free")){ // txtState.setText("Table "+mTableArrayList.get(position));
            txtState.setBackgroundResource(R.color.bg_text_enable);
        }
    }*/

    /////////////////////// view holder ////////////////////////////////

    public class ViewHolder {
        public TextView txtName;
        public TextView txtPrice;
        public TextView txtOrderCount;
        public ImageView imgPic;
        public ImageView imgBtnDec,imgBtnInc;
        public Button btnAddOrder;

        public ViewHolder() {
        }
    }
}
