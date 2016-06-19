package com.pos.theikchan.restaurantpos.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Theik Chan on 6/9/2016.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    /**
     * TABLE STRINGS
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA = ", ";
    /**
     * SQL CREATE TABLE sentences
     */
    /*private static final String CREATE_JOB_OFFER_TABLE = "CREATE TABLE "
            + DatabaseContract.JobOfferTable.TABLE_NAME + " ("
            + DatabaseContract.JobOfferTable.TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.JobOfferTable.DESC + TEXT_TYPE + COMMA
            + DatabaseContract.JobOfferTable.TYPE + TEXT_TYPE +
            COMMA + DatabaseContract.JobOfferTable.SALARY + TEXT_TYPE +
            COMMA + DatabaseContract.JobOfferTable.LOCATION + TEXT_TYPE +
            COMMA + DatabaseContract.JobOfferTable.COMPANY_ID +
            INTEGER_TYPE + " )";*/

    /*private static final String CREATE_COMPANY_TABLE = "CREATE TABLE "
            + DatabaseContract.CompanyTable.TABLE_NAME + " ("
            + DatabaseContract.CompanyTable.NAME + TEXT_TYPE + COMMA
            + DatabaseContract.CompanyTable.IMAGE_LINK + TEXT_TYPE + " )";*/


/**
 * SQL DELETE TABLE SENTENCES
 */

   /* public static final String DROP_JOB_OFFER_TABLE = "DROP TABLE IF
    EXISTS "+ DatabaseContract.JobOfferTable.TABLE_NAME;

    public static final String DROP_COMPANY_TABLE = "DROP TABLE IF
    EXISTS "+ DatabaseContract.CompanyTable.TABLE_NAME;
*/

   /* public DBOpenHelper(Context context){
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_JOB_OFFER_TABLE);
        //db.execSQL(CREATE_COMPANY_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        //db.execSQL(DROP_COMPANY_TABLE);
        //db.execSQL(DROP_JOB_OFFER_TABLE);
        onCreate(db);
    }*/

    private static String DB_PATH = "data/data/com.pos.theikchan.restaurantpos/databases/";
    private SQLiteDatabase db;
    private final Context myContext;


    public DBOpenHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
        this.myContext = context;

        Log.i("DB_PATH", String.valueOf(myContext.getFilesDir().getPath()));
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
            Log.e("Tag", "in if...");
        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.

            Log.e("Tag", "in else...");

            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                //throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DatabaseContract.DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DatabaseContract.DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DatabaseContract.DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DatabaseContract.DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
