package com.pos.theikchan.restaurantpos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.pos.theikchan.restaurantpos.model.FoodItem;
import com.pos.theikchan.restaurantpos.model.OrderItem;
import com.pos.theikchan.restaurantpos.model.TableItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Theik Chan on 6/9/2016.
 */
public class RestaurantDAO {
    /**
     * Singleton pattern
     */
    private static RestaurantDAO mInstane = null;

    public static DBOpenHelper dbOpenHelper;


/**
 * Get an instance of the Database Access Object
 *
 * @return instance
 */

    public static RestaurantDAO getInstance(Context context){
        if (mInstane == null){
            mInstane = new RestaurantDAO();

                dbOpenHelper=new DBOpenHelper(context);

                try {
                    dbOpenHelper.createDatabase();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                dbOpenHelper.openDataBase();
        }
        return mInstane;
    }


    public Boolean checkFoodAlreadyOrder( String prodName,int tblNo) {
        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();
        String query = "SELECT * FROM "+DatabaseContract.Order.TABLE_NAME+" WHERE "+
                DatabaseContract.Order.PRODUCT_NAME+" = '" + prodName + "' AND "+
                DatabaseContract.Order.TABLE_NO+"="+tblNo+";";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    private Boolean checkOrderAlreadyExist(int orderNo, String prodName) {
        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();
        String query = "SELECT * FROM "+DatabaseContract.Order.TABLE_NAME+" WHERE "+
                DatabaseContract.Order.ORDER_NO+"=" + orderNo + " AND "+
                DatabaseContract.Order.PRODUCT_NAME+" ='" + prodName + "';";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean removeOrder(int orderNo,String prodName){
        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

        int row=-1;
        String query = "SELECT * FROM "+DatabaseContract.Order.TABLE_NAME+" WHERE "+
                DatabaseContract.Order.ORDER_NO+"=" + orderNo + " AND "+
                DatabaseContract.Order.PRODUCT_NAME+" ='" + prodName + "';";
        Cursor cursor=db.rawQuery(query,null);

        if(cursor.getCount()>0){
            row=db.delete(DatabaseContract.Order.TABLE_NAME,DatabaseContract.Order.PRODUCT_NAME+" = ?",
                    new String[] { prodName });
        }


        db.close();
        if(row>0){
            return true;
        }else{
            return  false;
        }
    }

    public void clearTableOrder(int tableNo){
        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

        int row=-1;
        String sql="SELECT * FROM "+DatabaseContract.Order.TABLE_NAME+" WHERE "+
                DatabaseContract.Order.TABLE_NO+"="+tableNo+" ;";
        Cursor cursor=db.rawQuery(sql,null);

        if(cursor.getCount()>0){
            String del="DELETE FROM "+DatabaseContract.Order.TABLE_NAME+" WHERE "+
                    DatabaseContract.Order.TABLE_NO+"="+tableNo+" ;";
            db.rawQuery(del,null);
        }

        db.close();

    }

    public int getOrderCount(){
        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

        String sql="SELECT * FROM "+DatabaseContract.Order.TABLE_NAME+";";
        Cursor cursor=db.rawQuery(sql,null);

        if(cursor.getCount()>0){
           return cursor.getCount();

        }else {
            return  0;
        }
    }

    public boolean storeOrder( OrderItem order){

        try {
            SQLiteDatabase db = dbOpenHelper.
                    getWritableDatabase();

            db.beginTransaction();

                ContentValues cv_order = new ContentValues();
                //cv_home.put(DatabaseContract.Order.ID, home.getmName());
                cv_order.put(DatabaseContract.Order.ORDER_NO, order.getmOrderNo());
                cv_order.put(DatabaseContract.Order.TABLE_NO, order.getmTableNo());
                cv_order.put(DatabaseContract.Order.PRODUCT_NAME, order.getmProdName());
                cv_order.put(DatabaseContract.Order.QTY, order.getmQty());
                cv_order.put(DatabaseContract.Order.PRICE, order.getmPrice());
                cv_order.put(DatabaseContract.Order.IMAGE, order.getImg());

                /////////// need to check home already exist ///////////

                if (!checkOrderAlreadyExist(order.getmOrderNo(), order.getmProdName())) {
                    db.insert(DatabaseContract.Order.TABLE_NAME,null,cv_order);
                } /*else {
                    //Toast.makeText(myContext,"Product already exist in order list!",Toast.LENGTH_SHORT).show();
                }*/



                /*int id = findCompanyId(db,offer);

                if (id < 0) {
                    id = (int) db.insert(DatabaseContract.CompanyTable.TABLE_
                            NAME,null,cv_company);
                }*/

                /*ContentValues cv = new ContentValues();
                cv.put(DatabaseContract.JobOfferTable.TITLE,offer.getTitle());
                cv.put(DatabaseContract.JobOfferTable.DESC,offer.
                        getDescription());

                cv.put(DatabaseContract.JobOfferTable.TYPE, offer.getType());
                cv.put(DatabaseContract.JobOfferTable.DESC, offer.
                        getDescription());
                cv.put(DatabaseContract.JobOfferTable.SALARY,offer.getSalary());
                cv.put(DatabaseContract.JobOfferTable.LOCATION,offer.
                        getLocation());
                cv.put(DatabaseContract.JobOfferTable.COMPANY_ID,id);
                db.insert(DatabaseContract.JobOfferTable.TABLE_NAME,null,cv);*/

            db.setTransactionSuccessful();
            db.endTransaction();
            db.close();
        } catch ( Exception e){
            Log.d("RestaurantDAO", e.toString());
            return false;
        }
        return true;
    }
    public ArrayList<OrderItem> getOrdersFromDB(int tableNo){

        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();//new DBOpenHelper(context).getWritableDatabase();

        /*String join = DatabaseContract.JobOfferTable.TABLE_NAME + " JOIN " +
                      DatabaseContract.CompanyTable.TABLE_NAME + " ON " +
                      DatabaseContract.JobOfferTable.TABLE_NAME+"."+DatabaseContract.
                      JobOfferTable.COMPANY_ID
                      +" = " + DatabaseContract.CompanyTable.TABLE_NAME+".rowid";*/

        String sql="SELECT * FROM "+DatabaseContract.Order.TABLE_NAME+" WHERE "+
                DatabaseContract.Order.TABLE_NO+"="+tableNo+" ;";
        Cursor cursor=db.rawQuery(sql,null);

        //Cursor cursor = db.query(DatabaseContract.Order.TABLE_NAME,null,DatabaseContract.Order.TABLE_NO+"=?",null,null,null,null);

        ArrayList<OrderItem> orderList = new ArrayList<>();

        while (cursor.moveToNext()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setmNo(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Order.ID)));
            orderItem.setmOrderNo(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Order.ORDER_NO)));
            orderItem.setmTableNo(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Order.TABLE_NO)));
            orderItem.setmProdName(cursor.getString(cursor.getColumnIndex(DatabaseContract.Order.PRODUCT_NAME)));
            orderItem.setmQty(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Order.QTY)));
            orderItem.setmPrice(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Order.PRICE)));
            orderItem.setImg(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Order.IMAGE)));
            orderList.add(orderItem);
        }
        cursor.close();
        db.close();
        return orderList;
    }

    //////////////// get category ////////////////////////////////////

    public List<String> getCategoryFromDB(){

        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

        /*String join = DatabaseContract.JobOfferTable.TABLE_NAME + " JOIN " +
                      DatabaseContract.CompanyTable.TABLE_NAME + " ON " +
                      DatabaseContract.JobOfferTable.TABLE_NAME+"."+DatabaseContract.
                      JobOfferTable.COMPANY_ID
                      +" = " + DatabaseContract.CompanyTable.TABLE_NAME+".rowid";*/

        Cursor cursor = db.query(DatabaseContract.Category.TABLE_NAME,null,null,null,null,null,null);

        List<String> categoryList = new ArrayList<>();

        while (cursor.moveToNext()) {
            categoryList.add(cursor.getString(cursor.getColumnIndex(DatabaseContract.Category.CATEGORY)));
        }
        cursor.close();
        db.close();
        return categoryList;
    }


    /////////////////////////  table operation //////////////////////////////////////

    public ArrayList<TableItem> getTableFromDB(){

        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

        /*String join = DatabaseContract.JobOfferTable.TABLE_NAME + " JOIN " +
                      DatabaseContract.CompanyTable.TABLE_NAME + " ON " +
                      DatabaseContract.JobOfferTable.TABLE_NAME+"."+DatabaseContract.
                      JobOfferTable.COMPANY_ID
                      +" = " + DatabaseContract.CompanyTable.TABLE_NAME+".rowid";*/

        Cursor cursor = db.query(DatabaseContract.Table.TABLE_NAME,null,null,null,null,null,null);

        ArrayList<TableItem> tableList = new ArrayList<>();

        while (cursor.moveToNext()) {
            TableItem tableItem = new TableItem();
            tableItem.setId(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Table.ID)));
            tableItem.setTableNo(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Table.TABLE_NO)));

            if(checkTableBusy(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Table.TABLE_NO)))){
                tableItem.setmState("busy");
            }else{
                tableItem.setmState(cursor.getString(cursor.getColumnIndex(DatabaseContract.Table.STATE)));
            }

            tableList.add(tableItem);
        }
        cursor.close();
        db.close();
        return tableList;
    }


    public int getTableOrderNo(int tableNo){

        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

        int order_no=0;

        String sql="SELECT * FROM "+DatabaseContract.Order.TABLE_NAME+" WHERE "+
                DatabaseContract.Order.TABLE_NO+"="+tableNo+";";
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            order_no=cursor.getInt(cursor.getColumnIndex(DatabaseContract.Order.ORDER_NO));
        }

        cursor.close();
        db.close();
        return order_no;
    }

    public int getTableOrderCount(int tableNo){

        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

        String sql="SELECT * FROM "+DatabaseContract.Order.TABLE_NAME+" WHERE "+
                DatabaseContract.Order.TABLE_NO+"="+tableNo+";";
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            int count=cursor.getCount();

            cursor.close();
            db.close();

            return count;

        }else{
            return 0;
        }


    }

    ///////////////update table state /////////////////////////
    public void updateTableState(int tableNo){
        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

            String update="UPDATE "+DatabaseContract.Table.TABLE_NAME+
                    " SET "+DatabaseContract.Table.STATE+" = 'busy'"+
                    " WHERE "+DatabaseContract.Table.TABLE_NO+"="+tableNo+";";
            db.rawQuery(update,null);

        db.close();

    }

    public boolean checkTableBusy(int tableNo) {
        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();

        String sql = "SELECT * FROM " + DatabaseContract.Order.TABLE_NAME + " WHERE " +
                DatabaseContract.Table.TABLE_NO + "=" + tableNo + " ;";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {//not busy state
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.close();
            db.close();
            return false;
        }
    }

    ///////////////////////// get food list //////////////////////////////////////////

    public ArrayList<FoodItem> getFoodsFromDB(int catId){

        SQLiteDatabase db = dbOpenHelper.
                getWritableDatabase();//new DBOpenHelper(context).getWritableDatabase();

        /*String join = DatabaseContract.JobOfferTable.TABLE_NAME + " JOIN " +
                      DatabaseContract.CompanyTable.TABLE_NAME + " ON " +
                      DatabaseContract.JobOfferTable.TABLE_NAME+"."+DatabaseContract.
                      JobOfferTable.COMPANY_ID
                      +" = " + DatabaseContract.CompanyTable.TABLE_NAME+".rowid";*/

        String sql="SELECT * FROM "+DatabaseContract.Product.TABLE_NAME+" WHERE "+
                DatabaseContract.Product.CATEGORY_ID+"="+catId+" ;";
        Cursor cursor=db.rawQuery(sql,null);

        //Cursor cursor = db.query(DatabaseContract.Order.TABLE_NAME,null,DatabaseContract.Order.TABLE_NO+"=?",null,null,null,null);

        ArrayList<FoodItem> orderList = new ArrayList<>();

        while (cursor.moveToNext()) {
            FoodItem foodItem = new FoodItem();
            foodItem.setmNo(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Product.ID)));
            foodItem.setmName(cursor.getString(cursor.getColumnIndex(DatabaseContract.Product.PRODUCT_NAME)));
            foodItem.setmPrice(cursor.getString(cursor.getColumnIndex(DatabaseContract.Product.PRICE)));

            //orderItem.setImg(cursor.getInt(cursor.getColumnIndex(DatabaseContract.Product.IMAGE)));
            orderList.add(foodItem);
        }
        cursor.close();
        db.close();
        return orderList;
    }
}

