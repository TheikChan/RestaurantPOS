package com.pos.theikchan.restaurantpos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.pos.theikchan.restaurantpos.adapter.AdapterTableGrid;
import com.pos.theikchan.restaurantpos.database.RestaurantDAO;
import com.pos.theikchan.restaurantpos.model.TableItem;

import java.util.ArrayList;


public class ActivityTableList extends AppCompatActivity {

    private GridView mGridView;
    private ArrayList<TableItem> mTableArrayList = new ArrayList<>();
    private AdapterTableGrid adapter_tableGrid;

    Toolbar toolbar;

    public static RestaurantDAO restaurantDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_grid_table_no);

        restaurantDAO=RestaurantDAO.getInstance(ActivityTableList.this);

        toolbar= (Toolbar) this.findViewById(R.id.toolbar_table);
        setSupportActionBar(toolbar);

        bindView();
        getTableList();
        bindAdapter();
        //OnGetExtra();
        //getTableFromServer(StringTable.URL_GET_TABLE);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ActivityTableList.this,ActivityFoodMenu.class)
                /*.putExtra(DatabaseContract.Table.TABLE_NO,mTableArrayList.get(position).getTableNo())
                        .putExtra(DatabaseContract.Table.STATE,mTableArrayList.get(position).getmState())*/);

            }
        });
    }

    private void bindView(){
        mGridView = (GridView) findViewById(R.id.gridTableList);
    }

    private void bindAdapter(){
        adapter_tableGrid = new AdapterTableGrid(ActivityTableList.this, mTableArrayList);
        mGridView.setAdapter(adapter_tableGrid);

    }
    private void OnGetExtra() {
        /*Intent it = getIntent();
        STATE = it.getStringExtra(Activity_Home.KEY_STATE);
        Log.d("extra", "Activity Table List Extra STATE  " + STATE);*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public void getTableFromServer(String uri) {
        /*
        final ProgressDialog Diag = new ProgressDialog(ActivityTableList.this);
        Diag.setMessage("get Table From Server");
        Diag.setCancelable(false);
        Diag.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (Diag.isShowing())
                    Diag.dismiss();
                Log.d("json", "onResponse " + response);
                // mTableArrayList = OnGetTableDable();
                Log.d("json", String.valueOf(OnParseJsonString(response).size()));
                mTableArrayList = OnParseJsonString(response);
                if (Activity_Home.ACTION_STATE.equals("order")) {
                    adapter_tableGrid = new AdapterTableGrid(ActivityTableList.this, OnParseJsonString(response));
                    Log.d("json", "Action State "+Activity_Home.ACTION_STATE);
                }
                else {
                    if (OnGetOrderedTable(OnParseJsonString(response)) != null) {
                        adapter_tableGrid = new AdapterTableGrid(ActivityTableList.this, OnGetOrderedTable(OnParseJsonString(response)));
                        Log.d("json", "Action State "+Activity_Home.ACTION_STATE);
                    }
                }
                mGridView.setAdapter(adapter_tableGrid);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("json", "onErrorResponse " + error.toString());
                if (Diag.isShowing())
                    Diag.dismiss();
                Toast.makeText(ActivityTableList.this, "ကြန္ရက္နေကာင္းပာ", Toast.LENGTH_SHORT).show();
                Intent connectionFail = new Intent(Activity_TableList.this, Activity_Connection_fail.class);
                finish();
                startActivity(connectionFail);
            }
        });
        NetworkHelper.getInstance().addToRequestQueue(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(StringTable.COnNECTIOn_TIME_OUT
                , DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
    }

    public void getTableList(){


        //mTableArrayList=restaurantDAO.getTableFromDB();

                TableItem table = new TableItem();
                table.setId(1);
                table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(2);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(3);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(4);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(5);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(6);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(7);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(8);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(9);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(10);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(11);
        table.setState(0);
        mTableArrayList.add(table);

        table = new TableItem();
        table.setId(12);
        table.setState(0);
        mTableArrayList.add(table);

    }

    /*
    public ArrayList<TableItem> OnParseJsonString(String jsonString) {
        ArrayList<TableItem> tableArrayListes = new ArrayList<>();
        try {

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                TableItem table = new TableItem();
                table.setId(jsonObject1.getInt("id"));
                table.setState(jsonObject1.getInt("freedom"));
                tableArrayListes.add(table);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tableArrayListes;
    }*/

    /*
    public ArrayList<TableItem> OnGetOrderedTable(ArrayList<TableItem> tableArrayList) {
        ArrayList<TableItem> tables = new ArrayList<>();
        for (int i = 0; i < tableArrayList.size(); i++) {
            if (tableArrayList.get(i).getState() == 0) {
                tables.add(tableArrayList.get(i));
            }
        }
        return tables;
    }*/
}
