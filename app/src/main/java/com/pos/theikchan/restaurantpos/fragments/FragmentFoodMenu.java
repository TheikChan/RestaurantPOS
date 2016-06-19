package com.pos.theikchan.restaurantpos.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.pos.theikchan.restaurantpos.R;
import com.pos.theikchan.restaurantpos.adapter.AdapterGridFoodMenu;
import com.pos.theikchan.restaurantpos.adapter.GridViewFoodListAdapter;
import com.pos.theikchan.restaurantpos.model.FoodItem;

import java.util.ArrayList;


public class FragmentFoodMenu extends Fragment  {

    private ArrayList<FoodItem> mFoodArrayList=new ArrayList<>();

    private GridView mGridView;

    //private AdapterGridFoodMenu adapterGridFoodMenu;
    private GridViewFoodListAdapter adapterFoodList;

    public FragmentFoodMenu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onGetData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v=inflater.inflate(R.layout.lay_grid_food_menu, container, false);

        /*toolbar= (Toolbar) v.findViewById(R.id.toolbar_table);
        getActivity().setSupportActionBar(toolbar);*/

        bindView(v);

        bindAdapter();

        return v;

    }
    private void bindView(View v){
        mGridView = (GridView) v.findViewById(R.id.gridTableList);
    }

    private void bindAdapter(){
        adapterFoodList = new GridViewFoodListAdapter(getActivity(),R.layout.lay_grid_food_item, mFoodArrayList);
        //adapterGridFoodMenu = new AdapterGridFoodMenu(getActivity(), mFoodArrayList);


        mGridView.invalidateViews();
        mGridView.setAdapter(adapterFoodList);
        adapterFoodList.notifyDataSetChanged();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                adapterFoodList.notifyDataSetChanged();
            }
        });

    }

    public void onGetData() {
        FoodItem mFoodItem = new FoodItem();
        mFoodItem.setmName("Burger");
        mFoodItem.setmPrice("700");
        mFoodItem.setmQty(1);
        mFoodItem.setmCatId(2);
        mFoodItem.setmImg(R.drawable.img_burger);

        mFoodArrayList.add(mFoodItem);

        mFoodItem = new FoodItem();
        mFoodItem.setmName("Pizza");
        mFoodItem.setmPrice("1200");
        mFoodItem.setmQty(1);
        mFoodItem.setmCatId(2);
        mFoodItem.setmImg(R.drawable.img_breakfase);

        mFoodArrayList.add(mFoodItem);

        mFoodItem = new FoodItem();
        mFoodItem.setmName("Tom Yan");
        mFoodItem.setmPrice("2000");
        mFoodItem.setmQty(1);
        mFoodItem.setmCatId(2);
        mFoodItem.setmImg(R.drawable.img_vegetable);

        mFoodArrayList.add(mFoodItem);

    }


}
