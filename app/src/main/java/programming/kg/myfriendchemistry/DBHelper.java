package programming.kg.myfriendchemistry;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by MJ.KIM on 2017-05-07.
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final String ROOT_DIR = "/data/data/programming.kg.myfriendchemistry/databases/";  //로컬db 저장
    private static final String DATABASE_NAME = "TextFile.db"; //로컬db명
    private static final int SCHEMA_VERSION = 1; //로컬db 버전
    public DBHelper(Context context)    {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        setDB(context); // setDB에 context 부여
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * assets에 있는 db파일을 애플리케이션 내부에 있는 db폴더로 복사한다.
     * @param ctx
     */
    public static void setDB(Context ctx) {
        File folder = new File(ROOT_DIR);
        if(folder.exists()) {
        }
        else {
            folder.mkdirs();
        }
        AssetManager assetManager = ctx.getResources().getAssets(); //ctx가 없으면 assets폴더를 찾지 못한다.
        File outfile = new File(ROOT_DIR+"TextFile.db");
        InputStream is = null;
        FileOutputStream fo = null;
        long filesize = 0;
        try {
            is = assetManager.open("TextFile.db", AssetManager.ACCESS_BUFFER);
            filesize = is.available();
            if (outfile.length() <= 0) {//파일이 없을 때만 복사한다.
                byte[] tempdata = new byte[(int) filesize];
                is.read(tempdata);
                is.close();
                outfile.createNewFile();
                fo = new FileOutputStream(outfile);
                fo.write(tempdata);
                fo.close();
            }
            else {}
        } catch (IOException e) {}
    }


}
