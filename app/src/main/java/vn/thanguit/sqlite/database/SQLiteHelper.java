package vn.thanguit.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import vn.thanguit.sqlite.model.Wallet;
import vn.thanguit.sqlite.utils.Common;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLiteHelper";

    // Database Version
    private int databaseVersion = 0;

    // ---------------------------------------------------------------------------------------------
    // Table name
    private static final String TABLE_WALLET = "wallet";
    private static final String TABLE_WALLET_TEMP = "wallet_temp";
    private static final String TABLE_TRANSACTION = "transactions";
    private static final String TABLE_TRANSACTION_TEMP = "transactions_temp";
    private static final String TABLE_NODE = "node";

    // Wallet Table Columns names
    private static final String COLUMN_WALLET_ID = "id";
    private static final String COLUMN_WALLET_ADDRESS = "address";
    private static final String COLUMN_WALLET_NAME = "name";
    private static final String COLUMN_WALLET_BALANCE = "balance";
    private static final String COLUMN_WALLET_PENDING_BALANCE = "pendingBalance";
    private static final String COLUMN_WALLET_PENDING_TOTAL_BALANCE = "totalBalance";


    // Transaction Table Columns names
    private static final String COLUMN_TRANSACTION_ID = "id";
    private static final String COLUMN_TRANSACTION_hash = "hash";
    private static final String COLUMN_TRANSACTION_address = "address";
    private static final String COLUMN_TRANSACTION_toAddress = "toAddress";
    private static final String COLUMN_TRANSACTION_pubKey = "pubKey";
    private static final String COLUMN_TRANSACTION_amount = "amount";
    private static final String COLUMN_TRANSACTION_pendingUse = "pendingUse";
    private static final String COLUMN_TRANSACTION_balance = "balance";
    private static final String COLUMN_TRANSACTION_fee = "fee";
    private static final String COLUMN_TRANSACTION_tip = "tip";
    private static final String COLUMN_TRANSACTION_message = "message";
    private static final String COLUMN_TRANSACTION_time = "time";
    private static final String COLUMN_TRANSACTION_status = "status";
    private static final String COLUMN_TRANSACTION_type = "type";
    private static final String COLUMN_TRANSACTION_prevHash = "prevHash";
    private static final String COLUMN_TRANSACTION_sign = "sign";
    private static final String COLUMN_TRANSACTION_receive_info = "receive_info";
    private static final String COLUMN_TRANSACTION_isDeploy = "isDeploy";
    private static final String COLUMN_TRANSACTION_isCall = "isCall";
    private static final String COLUMN_TRANSACTION_functionCall = "functionCall";
    private static final String COLUMN_TRANSACTION_data = "data";
    private static final String COLUMN_TRANSACTION_totalBalance = "totalBalance";

    // Node Table Columns names
    private static final String COLUMN_NODE_ID = "id";
    private static final String COLUMN_NODE_IP = "ip";
    private static final String COLUMN_NODE_PORT = "port";
    private static final String COLUMN_NODE_TIME = "time";

    // ---------------------------------------------------------------------------------------------

    private static final String CREATE_WALLET_TABLE =
            "CREATE TABLE " + TABLE_WALLET
                    + "("
                    + COLUMN_WALLET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_WALLET_ADDRESS + " TEXT,"
                    + COLUMN_WALLET_BALANCE + " INTEGER,"
                    + COLUMN_WALLET_PENDING_BALANCE + " INTEGER"
                    + ")";

    private static final String CREATE_TRANSACTION_TABLE =
            "CREATE TABLE " + TABLE_TRANSACTION
                    + "("
                    + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TRANSACTION_hash + " TEXT,"
                    + COLUMN_TRANSACTION_address + " TEXT,"
                    + COLUMN_TRANSACTION_toAddress + " TEXT,"
                    + COLUMN_TRANSACTION_pubKey + " TEXT,"
                    + COLUMN_TRANSACTION_amount + " INTEGER,"
                    + COLUMN_TRANSACTION_pendingUse + " INTEGER,"
                    + COLUMN_TRANSACTION_balance + " INTEGER,"
                    + COLUMN_TRANSACTION_fee + " INTEGER,"
                    + COLUMN_TRANSACTION_tip + " INTEGER,"
                    + COLUMN_TRANSACTION_message + " TEXT,"
                    + COLUMN_TRANSACTION_time + " INTEGER,"
                    + COLUMN_TRANSACTION_status + " INTEGER,"
                    + COLUMN_TRANSACTION_type + " TEXT,"
                    + COLUMN_TRANSACTION_prevHash + " TEXT,"
                    + COLUMN_TRANSACTION_sign + " TEXT,"
                    + COLUMN_TRANSACTION_receive_info + " TEXT,"
                    + COLUMN_TRANSACTION_isDeploy + " INTEGER,"
                    + COLUMN_TRANSACTION_isCall + " INTEGER,"
                    + COLUMN_TRANSACTION_functionCall + " TEXT"
                    + ")";
    // ---------------------------------------------------------------------------------------------

//    private static final String UPDATE_VERSION_2 =
//            "ALTER TABLE " + TABLE_WALLET + " ADD COLUMN " + COLUMN_WALLET_NAME + " TEXT; "
//                    + "ALTER TABLE " + TABLE_WALLET + " ALTER COLUMN " + COLUMN_WALLET_BALANCE + " TEXT; "
//                    + "ALTER TABLE " + TABLE_WALLET + " ALTER COLUMN " + COLUMN_WALLET_PENDING_BALANCE + " TEXT; "
//
//                    + "ALTER TABLE " + TABLE_TRANSACTION + " ADD COLUMN " + COLUMN_TRANSACTION_data + " TEXT; "
//                    + "ALTER TABLE " + TABLE_TRANSACTION + " ADD COLUMN " + COLUMN_TRANSACTION_totalBalance + " TEXT; "
//                    + "ALTER TABLE " + TABLE_TRANSACTION + " ALTER COLUMN " + COLUMN_TRANSACTION_pendingUse + " TEXT; "
//                    + "ALTER TABLE " + TABLE_TRANSACTION + " ALTER COLUMN " + COLUMN_TRANSACTION_balance + " TEXT; "
//                    + "ALTER TABLE " + TABLE_TRANSACTION + " ALTER COLUMN " + COLUMN_TRANSACTION_fee + " TEXT; "
//                    + "ALTER TABLE " + TABLE_TRANSACTION + " ALTER COLUMN " + COLUMN_TRANSACTION_tip + " TEXT;";

    private static final String UPDATE_VERSION_2 = "ALTER TABLE " + TABLE_WALLET + " ADD COLUMN " + COLUMN_WALLET_NAME + " TEXT; ";
//    private static final String UPDATE_VERSION_2_1 = "ALTER TABLE " + TABLE_WALLET + " MODIFY COLUMN " + COLUMN_WALLET_BALANCE + " TEXT; ";
//    private static final String UPDATE_VERSION_2_2 = "ALTER TABLE " + TABLE_WALLET + " MODIFY COLUMN " + COLUMN_WALLET_PENDING_BALANCE + " TEXT; ";

//    private static final String UPDATE_VERSION_2_3 = "ALTER TABLE " + TABLE_TRANSACTION + " ADD COLUMN " + COLUMN_TRANSACTION_data + " TEXT; ";
//    private static final String UPDATE_VERSION_2_4 = "ALTER TABLE " + TABLE_TRANSACTION + " ADD COLUMN " + COLUMN_TRANSACTION_totalBalance + " TEXT; ";
//    private static final String UPDATE_VERSION_2_5 = "ALTER TABLE " + TABLE_TRANSACTION + " MODIFY COLUMN " + COLUMN_TRANSACTION_pendingUse + " TEXT; ";
//    private static final String UPDATE_VERSION_2_6 = "ALTER TABLE " + TABLE_TRANSACTION + " MODIFY COLUMN " + COLUMN_TRANSACTION_balance + " TEXT; ";
//    private static final String UPDATE_VERSION_2_7 = "ALTER TABLE " + TABLE_TRANSACTION + " MODIFY COLUMN " + COLUMN_TRANSACTION_fee + " TEXT; ";
//    private static final String UPDATE_VERSION_2_8 = "ALTER TABLE " + TABLE_TRANSACTION + " MODIFY COLUMN " + COLUMN_TRANSACTION_tip + " TEXT;";


    // ---------------------------------------------------------------------------------------------
    // https://www.tutlane.com/tutorial/sqlite/sqlite-alter-table
    private static final String UPDATE_VERSION_2_1 = "BEGIN TRANSACTION;";
    private static final String UPDATE_VERSION_2_2 = "ALTER TABLE " + TABLE_WALLET + " RENAME TO " + TABLE_WALLET_TEMP + ";";
    private static final String UPDATE_VERSION_2_3 =
            "CREATE TABLE " + TABLE_WALLET
                    + "("
                    + COLUMN_WALLET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_WALLET_ADDRESS + " TEXT,"
                    + COLUMN_WALLET_NAME + " TEXT,"
                    + COLUMN_WALLET_BALANCE + " TEXT,"
                    + COLUMN_WALLET_PENDING_BALANCE + " TEXT"
                    + ");";
    private static final String UPDATE_VERSION_2_4 =
            "INSERT INTO " + TABLE_WALLET
                    + "("
                    + COLUMN_WALLET_ID + ", "
                    + COLUMN_WALLET_ADDRESS + ", "
                    + COLUMN_WALLET_NAME + ", "
                    + COLUMN_WALLET_BALANCE + ", "
                    + COLUMN_WALLET_PENDING_BALANCE
                    + ")"
                    + " SELECT "
                    + COLUMN_WALLET_ID + ", "
                    + COLUMN_WALLET_ADDRESS + ", "
                    + COLUMN_WALLET_NAME + ", "
                    + COLUMN_WALLET_BALANCE + ", "
                    + COLUMN_WALLET_PENDING_BALANCE
                    + " FROM "
                    + TABLE_WALLET_TEMP + ";";
    private static final String UPDATE_VERSION_2_5 = "COMMIT;";

    private static final String UPDATE_VERSION_2_6 = "ALTER TABLE " + TABLE_TRANSACTION + " ADD COLUMN " + COLUMN_TRANSACTION_data + " TEXT; ";
    private static final String UPDATE_VERSION_2_7 = "ALTER TABLE " + TABLE_TRANSACTION + " ADD COLUMN " + COLUMN_TRANSACTION_totalBalance + " TEXT; ";
    private static final String UPDATE_VERSION_2_8 = "BEGIN TRANSACTION;";
    private static final String UPDATE_VERSION_2_9 = "ALTER TABLE " + TABLE_TRANSACTION + " RENAME TO " + TABLE_TRANSACTION_TEMP + ";";
    private static final String UPDATE_VERSION_2_10 =
            "CREATE TABLE " + TABLE_TRANSACTION
                    + "("
                    + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TRANSACTION_hash + " TEXT,"
                    + COLUMN_TRANSACTION_address + " TEXT,"
                    + COLUMN_TRANSACTION_toAddress + " TEXT,"
                    + COLUMN_TRANSACTION_pubKey + " TEXT,"
                    + COLUMN_TRANSACTION_amount + " INTEGER,"
                    + COLUMN_TRANSACTION_pendingUse + " TEXT,"
                    + COLUMN_TRANSACTION_balance + " TEXT,"
                    + COLUMN_TRANSACTION_fee + " TEXT,"
                    + COLUMN_TRANSACTION_tip + " TEXT,"
                    + COLUMN_TRANSACTION_message + " TEXT,"
                    + COLUMN_TRANSACTION_time + " INTEGER,"
                    + COLUMN_TRANSACTION_status + " INTEGER,"
                    + COLUMN_TRANSACTION_type + " TEXT,"
                    + COLUMN_TRANSACTION_prevHash + " TEXT,"
                    + COLUMN_TRANSACTION_sign + " TEXT,"
                    + COLUMN_TRANSACTION_receive_info + " TEXT,"
                    + COLUMN_TRANSACTION_isDeploy + " INTEGER,"
                    + COLUMN_TRANSACTION_isCall + " INTEGER,"
                    + COLUMN_TRANSACTION_functionCall + " TEXT,"
                    + COLUMN_TRANSACTION_data + " TEXT,"
                    + COLUMN_TRANSACTION_totalBalance + " TEXT"
                    + ")";
    private static final String UPDATE_VERSION_2_11 =
            "INSERT INTO " + TABLE_TRANSACTION
                    + "(" + COLUMN_TRANSACTION_ID + ", "
                    + COLUMN_TRANSACTION_hash + ", "
                    + COLUMN_TRANSACTION_address + ", "
                    + COLUMN_TRANSACTION_toAddress + ", "
                    + COLUMN_TRANSACTION_pubKey + ", "
                    + COLUMN_TRANSACTION_amount + ", "
                    + COLUMN_TRANSACTION_pendingUse + ", "
                    + COLUMN_TRANSACTION_balance + ", "
                    + COLUMN_TRANSACTION_fee + ", "
                    + COLUMN_TRANSACTION_tip + ", "
                    + COLUMN_TRANSACTION_message + ", "
                    + COLUMN_TRANSACTION_time + ", "
                    + COLUMN_TRANSACTION_status + ", "
                    + COLUMN_TRANSACTION_type + ", "
                    + COLUMN_TRANSACTION_prevHash + ", "
                    + COLUMN_TRANSACTION_sign + ", "
                    + COLUMN_TRANSACTION_receive_info + ", "
                    + COLUMN_TRANSACTION_isDeploy + ", "
                    + COLUMN_TRANSACTION_isCall + ", "
                    + COLUMN_TRANSACTION_functionCall + ", "
                    + COLUMN_TRANSACTION_data + ", "
                    + COLUMN_TRANSACTION_totalBalance
                    + ")"
                    + " SELECT "
                    + COLUMN_TRANSACTION_ID + ", "
                    + COLUMN_TRANSACTION_hash + ", "
                    + COLUMN_TRANSACTION_address + ", "
                    + COLUMN_TRANSACTION_toAddress + ", "
                    + COLUMN_TRANSACTION_pubKey + ", "
                    + COLUMN_TRANSACTION_amount + ", "
                    + COLUMN_TRANSACTION_pendingUse + ", "
                    + COLUMN_TRANSACTION_balance + ", "
                    + COLUMN_TRANSACTION_fee + ", "
                    + COLUMN_TRANSACTION_tip + ", "
                    + COLUMN_TRANSACTION_message + ", "
                    + COLUMN_TRANSACTION_time + ", "
                    + COLUMN_TRANSACTION_status + ", "
                    + COLUMN_TRANSACTION_type + ", "
                    + COLUMN_TRANSACTION_prevHash + ", "
                    + COLUMN_TRANSACTION_sign + ", "
                    + COLUMN_TRANSACTION_receive_info + ", "
                    + COLUMN_TRANSACTION_isDeploy + ", "
                    + COLUMN_TRANSACTION_isCall + ", "
                    + COLUMN_TRANSACTION_functionCall + ", "
                    + COLUMN_TRANSACTION_data + ", "
                    + COLUMN_TRANSACTION_totalBalance
                    + " FROM " + TABLE_TRANSACTION_TEMP + ";";
    private static final String UPDATE_VERSION_2_12 = "COMMIT;";

    // ---------------------------------------------------------------------------------------------

    private static final String UPDATE_VERSION_3_1 =
            "ALTER TABLE " + TABLE_WALLET + " ADD COLUMN " + COLUMN_WALLET_PENDING_TOTAL_BALANCE + " TEXT;";
    private static final String UPDATE_VERSION_3_2 =
            "CREATE TABLE " + TABLE_NODE
                    + "("
                    + COLUMN_NODE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NODE_IP + " TEXT,"
                    + COLUMN_NODE_PORT + " INTEGER,"
                    + COLUMN_NODE_TIME + " INTEGER"
                    + ")";
    // ---------------------------------------------------------------------------------------------

    public SQLiteHelper(@Nullable Context context, int version) {
        super(context, Common.DATABASE.DATABASE_NAME, null, version);
//        this.databaseVersion = version;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        if (databaseVersion <= 1) {
//            sqLiteDatabase.execSQL(CREATE_WALLET_TABLE);
//            sqLiteDatabase.execSQL(CREATE_TRANSACTION_TABLE);
//        }
        try {
            if (sqLiteDatabase != null) {
                sqLiteDatabase.execSQL(CREATE_WALLET_TABLE);
                sqLiteDatabase.execSQL(CREATE_TRANSACTION_TABLE);
                Log.d(TAG, "Create DB success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // https://stackoverflow.com/a/45191976
        // https://vntalking.com/loi-khi-nang-cap-database-nguy-hiem-nhu-mot-qua-bom-hat-nhan.html

        if (oldVersion < 2) {
            sqLiteDatabase.execSQL(UPDATE_VERSION_2);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_1);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_2);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_3);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_4);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_5);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_6);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_7);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_8);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_9);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_10);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_11);
            sqLiteDatabase.execSQL(UPDATE_VERSION_2_12);
            Log.d(TAG, "onUpgrade to version 2");
        }

        if (oldVersion < 3) {
            sqLiteDatabase.execSQL(UPDATE_VERSION_3_1);
            sqLiteDatabase.execSQL(UPDATE_VERSION_3_2);
            Log.d(TAG, "onUpgrade to version 3");
        }

//            // Drop older table if existed
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WALLET);
//            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
//            Log.d(TAG, "Drop DB success");
//
//            // Create tables again
//            onCreate(sqLiteDatabase);
    }

    // Adding new data
    public void addWallet(Wallet wallet) {
        SQLiteDatabase db = getWritableDatabase(); // Cho phép ghi đọc vào CSDL
//        String nullColumnHack = null; // Allow null value

        ContentValues values = new ContentValues(); // Đối tượng ContentValues được sử dụng để chèn 1 hàng mới vào trong bảng cơ sở dữ liệu. Mỗi đối tượng ContentValues biểu diễn một hàng và ánh xạ cột sang giá trị tương ứng.
        if (wallet.getId() != -1) { // -1 Add dữ liệu vào hết rồi mới đóng kết nối
            values.put(COLUMN_WALLET_ADDRESS, wallet.getAddress()); // put là để đặt giá trị cho mỗi cột
//            values.put(COLUMN_WALLET_NAME, wallet.getAddress());
            values.put(COLUMN_WALLET_BALANCE, wallet.getBalance());
            values.put(COLUMN_WALLET_PENDING_BALANCE, wallet.getPendingBalance());
//            values.put(COLUMN_WALLET_PENDING_TOTAL_BALANCE, wallet.getAddress());

            db.insert(TABLE_WALLET, null, values); // Insert vào bảng TABLE_WALLET (contacts)
        }
        db.close(); // Nhớ đóng kết nối nha bro
    }

    // Getting All Contacts
    public List<Wallet> getAllWallets() {
        SQLiteDatabase db = getReadableDatabase(); // Cho phép đọc vào CSDL
        List<Wallet> wallets = new ArrayList<>();

        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_WALLET;

        Cursor cursor = db.rawQuery(selectQuery, criterial);
        if (cursor.moveToFirst()) {
            do {
                Wallet wallet = new Wallet();
                wallet.setId(cursor.getInt(0));
                wallet.setAddress(cursor.getString(1));
//                wallet.setName(cursor.getString(2));
                wallet.setBalance(cursor.getString(2));
                wallet.setPendingBalance(cursor.getString(3));
//                wallet.setTotalBalance(cursor.getString(5));

                wallets.add(wallet);
            } while (cursor.moveToNext());
        }
        return wallets;
    }
}
