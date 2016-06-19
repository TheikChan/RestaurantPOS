package com.pos.theikchan.restaurantpos.database;

/**
 * Created by Theik Chan on 6/9/2016.
 */
public class DatabaseContract {
    public static final String DB_NAME = "tbl_restaurant";

    public abstract class Category{
        public static final String TABLE_NAME = "tbl_category";
        public static final String ID = "id";
        public static final String CATEGORY = "category";
        /*public static final String SUB_CATEGORY = "sub_category";*/

    }

    public abstract class Table{
        public static final String TABLE_NAME = "tbl_table";
        public static final String ID = "id";
        public static final String TABLE_NO = "table_no";
        public static final String STATE = "state";

    }

    public abstract class Order{
        public static final String TABLE_NAME = "tbl_order";
        public static final String ID = "id";
        public static final String ORDER_NO = "order_no";
        public static final String TABLE_NO = "table_no";
        public static final String PRODUCT_NAME= "prod_name";
        public static final String QTY= "qty";
        public static final String PRICE= "price";
        public static final String IMAGE= "img";

    }

    public abstract class Product{
        public static final String TABLE_NAME = "tbl_product";
        public static final String ID = "id";
        public static final String CATEGORY_ID = "cat_id";
        public static final String PRODUCT_NAME= "prod_name";
        public static final String PRICE= "price";
        public static final String IMAGE= "image";

    }



}

