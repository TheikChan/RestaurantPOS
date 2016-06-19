package com.pos.theikchan.restaurantpos.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pos.theikchan.restaurantpos.R;
import com.pos.theikchan.restaurantpos.model.FoodItem;

import java.util.ArrayList;

/**
 * Created by TheikChan on 10/2/2015.
 */
public class GridViewFoodListAdapter extends ArrayAdapter<FoodItem> {

    Activity context;

    int layoutResourceId;

    ArrayList<FoodItem> mFoodArrayList = new ArrayList<>();
    ViewHolder holder;


    public GridViewFoodListAdapter(Activity context, int layoutResourceId,
                                        ArrayList<FoodItem> mFoodArrayList) {
        super(context, layoutResourceId, mFoodArrayList);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.mFoodArrayList = mFoodArrayList;
    }


    @Override

    public View getView(final int position, final View convertView,final ViewGroup parent) {

        View row = convertView;
        holder = new ViewHolder();

        if (row == null) {

            LayoutInflater inflater = ( context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            //holder.imageItem = (ImageView) row.findViewById(R.id.fri_lst_it_img);

            holder.txtName = (TextView) row.findViewById(R.id.lay_grid_food_item_txt_name);
            holder.txtPrice = (TextView) row.findViewById(R.id.lay_grid_food_item_txt_price);
            holder.txtOrderCount = (TextView) row.findViewById(R.id.lay_grid_food_item_txt_order_count);

            holder.imgBtnDec = (ImageView) row.findViewById(R.id.lay_grid_food_item_img_btn_dec);
            holder.imgBtnInc= (ImageView) row.findViewById(R.id.lay_grid_food_item_img_btn_inc);

            holder.btnAddOrder = (Button) row.findViewById(R.id.lay_grid_food_item_btn_add_order);

            holder.imgPic = (ImageView) row.findViewById(R.id.lay_grid_food_item_img);

            row.setTag(holder);

        } else {

            holder = (ViewHolder) row.getTag();

        }




        FoodItem item = mFoodArrayList.get(position);

       final String name=item.getmName();
       final String price=item.getmPrice();
        int img=item.getmImg();
/*
        byte[] friImageByte=item.getFriImage();
        String friImgLink=item.getStrFriLinkImg();

        if(friImgLink.equals("no")){
            holder.imgFri.setImageBitmap(Utility.decodeImage(friImageByte));
        }else{
            imgLoader.DisplayImage(friImgLink,
                    holder.imgFri);
        }*/

        holder.txtName.setText(name);
        holder.txtPrice.setText(price);
        holder.imgPic.setImageResource(img);

        holder.imgBtnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.txtOrderCount= (TextView)parent.getChildAt(position).findViewById(R.id.lay_grid_food_item_txt_order_count);
                //holder.txtPrice= (TextView)parent.getChildAt(position).findViewById(R.id.lay_grid_food_item_txt_price);

                int qty=Integer.valueOf(holder.txtOrderCount.getText().toString());

                if(qty>1){

                    holder.txtOrderCount.setText(String.valueOf(--qty));

                    int price=Integer.valueOf(holder.txtPrice.getText().toString())*qty;

                    //holder.txtPrice= (TextView)parent.getChildAt(position).findViewById(R.id.lay_grid_food_item_txt_price);

                    holder.txtPrice.setText(String.valueOf(price));
                }

                Toast.makeText(context,"Add Order "+position+"qty"+qty,Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgBtnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txtOrderCount= (TextView)parent.getChildAt(position).findViewById(R.id.lay_grid_food_item_txt_order_count);
                //holder.txtPrice= (TextView)parent.getChildAt(position).findViewById(R.id.lay_grid_food_item_txt_price);

                int  qty=Integer.valueOf(holder.txtOrderCount.getText().toString());
                holder.txtOrderCount.setText(String.valueOf(++qty));

                int price=Integer.valueOf(holder.txtPrice.getText().toString())*qty;

                //holder.txtPrice= (TextView)parent.getChildAt(position).findViewById(R.id.lay_grid_food_item_txt_price);
                holder.txtPrice.setText(String.valueOf(price));

                Toast.makeText(context,"Add Order "+price+","+position+"qty"+qty,Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Add Order "+position,Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Image Click",Toast.LENGTH_SHORT).show();
            }
        });

        //row.invalidate();
        notifyDataSetChanged();
        return row;

    }


    @Override
    public int getCount() {
        return mFoodArrayList.size();
    }



   /* @Override
    public Object getItem(int position) {
        return mFoodArrayList.get(position) ;
    }*/

    @Override
    public long getItemId(int position) {
        return position;
    }

   /* public void fragmentUserProile(){
        Fragment fragment = null;
        fragment = new FragmentUserProfile();

		*//*LinearLayout mlayFriLst=(LinearLayout)inflater.inflate(R.layout.lay_fri_list,parent,false);

		mlayFriLst.setVisibility(View.GONE);*//*

        FragmentManager frgManager = context.getFragmentManager();// get fragment
        frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }*/

    /*static class RecordHolder {

        TextView txtTitle;
       // ImageView imageItem;
        ImageButton mImgBtnFav;

        CircleImageView imgFri;

    }*/

    private class ViewHolder {
        private TextView txtName;
        private TextView txtPrice;
        private TextView txtOrderCount;
        private ImageView imgPic;
        private ImageView imgBtnDec,imgBtnInc;
        private Button btnAddOrder;
    }
}