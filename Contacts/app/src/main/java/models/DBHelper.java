package models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "Contacts"; // название таблицы в бд
    // названия столбцов
    public static final String Id = "_id";
    public static final String Name ="name";
    public static final String Email = "email";
    public static final String Phone = "phone";
    public static final String Image = "image";
    public static final String Location = "location";
    public static final String InstagramLink = "instagramLink";
    public static final String VkLink = "vkLink";
    public static final String TelegramLink = "telegramLink";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }
    

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE);

        db.execSQL("CREATE TABLE IF NOT EXISTS  `DBHelpers` (\n" +
                "\t`_id`\tINTEGER,\n" +
                "\t`name`\tTEXT,\n" +
                "\t`email`\tTEXT,\n" +
                "\t`phone`\tTEXT,\n" +
                "\t`image`\tTEXT,\n" +
                "\t`location`\tTEXT,\n" +
                "\t`instagramLink`\tTEXT,\n" +
                "\t`vkLink`\tTEXT,\n" +
                "\t`telegramLink`\tTEXT\n" +
                ");");
        


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
