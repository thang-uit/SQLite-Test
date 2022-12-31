package vn.thanguit.sqlite.activity;

import android.os.Bundle;
import android.util.Log;

import vn.thanguit.sqlite.base.BaseActivity;
import vn.thanguit.sqlite.database.SQLiteHelper;
import vn.thanguit.sqlite.databinding.ActivityMainBinding;
import vn.thanguit.sqlite.model.Wallet;
import vn.thanguit.sqlite.utils.Common;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Lấy đường dẫn thư mục chứa database
        String pathDB = getDatabasePath(Common.DATABASE.DATABASE_NAME).getPath(); // Lấy đường dẫn thư mục chứa database
        Log.d("MainActivity", "DB_Path: " + pathDB); //  /data/user/0/vn.thanguit.sqlite/databases/TEST

        // Gọi Database
        SQLiteHelper db = new SQLiteHelper(this, 1);
//        SQLiteHelper db = new SQLiteHelper(this, 2);
//        SQLiteHelper db = new SQLiteHelper(this, 3);

        // Thêm dữ liệu
        Log.d("Insert: ", "Inserting ..");
        db.addWallet(new Wallet("VN", "123", "1234"));
        db.addWallet(new Wallet("EN", "123", "1234"));
        db.addWallet(new Wallet("KM", "123", "1234"));
        db.addWallet(new Wallet("FR", "123", "1234"));
        db.addWallet(new Wallet("AF", "123", "1234"));
        db.addWallet(new Wallet("JP", "123", "1234"));
        db.addWallet(new Wallet("KO", "123", "1234"));
        db.addWallet(new Wallet("BZ", "123", "1234"));
        db.addWallet(new Wallet("VN", "123", "1234"));

        // Ghi Log ra tất cả các dữ liệu
        for (Wallet item : db.getAllWallets()) {
            Log.d("MainActivity", item.toString());
        }
    }
}