package programming.kg.myfriendchemistry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Query{
	private DBHelper m_db = null;

	public Query(Context context) {
		m_db = new DBHelper(context);
	}



	/**
	 * 객관식 데이터 조회
	 * @return
	 */
	public Cursor searchQMC(int no) {
		SQLiteDatabase db = m_db.getReadableDatabase();
		Cursor cursor = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT NO,QUES,A1,A2,A3,A4,A5,ANS FROM QMC ");
			if (no  > 0) {
				sql.append(" WHERE NO = ").append(no).append(" ");
			}

			Log.d("Query", "[searchQMC]" + sql.toString());
			cursor = db.rawQuery(sql.toString(), null);
			if (cursor.isClosed() == true || cursor.getCount() == 0) cursor = null;
		}
		catch (SQLException e) {
			Log.e("Query",e.getMessage());
		}
		catch (IllegalStateException e) {
			Log.e("Query",e.getMessage());
			cursor = null;
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return cursor;
	}

	/**
	 * 객관식 데이터 갯수
	 * @return
	 */
	public int countQMC() {
		SQLiteDatabase db = m_db.getReadableDatabase();
		Cursor cursor = null;
		int count = 0;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) ");
			sql.append("FROM QMC ");

			Log.d("Query", sql.toString());
			cursor = db.rawQuery(sql.toString(), null);
			if (cursor.isClosed() == false ) {
				cursor.moveToFirst();
				count = cursor.getInt(0);
			}
		}
		catch (SQLException e) {
			Log.e("Query", e.getMessage());
		}
		catch (IllegalStateException e) {
			Log.e("Query", e.getMessage());
			cursor = null;
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return count;
	}

	/**
	 *주관식 데이터 조회
	 * @return
	 */
	public Cursor searchQSQ(int no) {
		SQLiteDatabase db = m_db.getReadableDatabase();
		Cursor cursor = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM QSQ ");
			if (no  > 0) {
				sql.append(" WHERE NO = ").append(no).append(" ");
			}

			Log.d("Query", "[searchQSQ]" + sql.toString());
			cursor = db.rawQuery(sql.toString(), null);
			if (cursor.isClosed() == true || cursor.getCount() == 0) cursor = null;
		}
		catch (SQLException e) {
			Log.e("Query",e.getMessage());
		}
		catch (IllegalStateException e) {
			Log.e("Query",e.getMessage());
			cursor = null;
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return cursor;
	}

	/**
	 * 주관식 데이터 갯수
	 * @return
	 */
	public int countQSQ() {
		SQLiteDatabase db = m_db.getReadableDatabase();
		Cursor cursor = null;
		int count = 0;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) ");
			sql.append("FROM QSQ ");

			Log.d("Query", sql.toString());
			cursor = db.rawQuery(sql.toString(), null);
			if (cursor.isClosed() == false ) {
				cursor.moveToFirst();
				count = cursor.getInt(0);
			}
		}
		catch (SQLException e) {
			Log.e("Query", e.getMessage());
		}
		catch (IllegalStateException e) {
			Log.e("Query", e.getMessage());
			cursor = null;
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return count;
	}

	/**
	 * 오답목록에 있는 객관식 데이터 조회
	 * @return
	 */
	public Cursor searchWQMC(int no) {
		SQLiteDatabase db = m_db.getReadableDatabase();
		Cursor cursor = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT NO,QUES,A1,A2,A3,A4,A5,ANS FROM WrongQMC ");
			if (no  > 0) {
				sql.append(" WHERE NO = ").append(no).append(" ");
			}

			Log.d("Query", "[searchWQMC]" + sql.toString());
			cursor = db.rawQuery(sql.toString(), null);
			if (cursor.isClosed() == true || cursor.getCount() == 0) cursor = null;
		}
		catch (SQLException e) {
			Log.e("Query",e.getMessage());
		}
		catch (IllegalStateException e) {
			Log.e("Query",e.getMessage());
			cursor = null;
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return cursor;
	}


	/**
	 * 오답목록에 있는 객관식 데이터 개수
	 * @return
	 */
	public int countWQMC() {
		SQLiteDatabase db = m_db.getReadableDatabase();
		Cursor cursor = null;
		int count = 0;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) ");
			sql.append("FROM WrongQMC ");

			Log.d("Query", sql.toString());
			cursor = db.rawQuery(sql.toString(), null);
			if (cursor.isClosed() == false ) {
				cursor.moveToFirst();
				count = cursor.getInt(0);
			}
		}
		catch (SQLException e) {
			Log.e("Query", e.getMessage());
		}
		catch (IllegalStateException e) {
			Log.e("Query", e.getMessage());
			cursor = null;
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return count;
	}

	/**
	 * 오답목록에 있는 주관식 데이터 조회
	 * @return
	 */
	public Cursor searchWQSQ(int no) {
		SQLiteDatabase db = m_db.getReadableDatabase();
		Cursor cursor = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT NO,QUES,ANS FROM WrongQSQ ");
			if (no  > 0) {
				sql.append(" WHERE NO = ").append(no).append(" ");
			}

			Log.d("Query", "[searchWQSQ]" + sql.toString());
			cursor = db.rawQuery(sql.toString(), null);
			if (cursor.isClosed() == true || cursor.getCount() == 0) cursor = null;
		}
		catch (SQLException e) {
			Log.e("Query",e.getMessage());
		}
		catch (IllegalStateException e) {
			Log.e("Query",e.getMessage());
			cursor = null;
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return cursor;
	}

	/**
	 * 오답목록에 있는 주관식 데이터 개수
	 * @return
	 */
	public int countWQSQ() {
		SQLiteDatabase db = m_db.getReadableDatabase();
		Cursor cursor = null;
		int count = 0;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) ");
			sql.append("FROM WrongQSQ ");

			Log.d("Query", sql.toString());
			cursor = db.rawQuery(sql.toString(), null);
			if (cursor.isClosed() == false ) {
				cursor.moveToFirst();
				count = cursor.getInt(0);
			}
		}
		catch (SQLException e) {
			Log.e("Query", e.getMessage());
		}
		catch (IllegalStateException e) {
			Log.e("Query", e.getMessage());
			cursor = null;
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
		return count;
	}

	/**
	 * 오답목록에 있던 객관식 데이터 지정 삭제
	 * @return
	 */
	public void Delete_WQMC(int no) {
		SQLiteDatabase db = m_db.getWritableDatabase();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM WrongQMC WHERE NO = ").append(no);
			db.execSQL(sql.toString());
		}
		catch (SQLException e) {
			Log.e("Query", e.getMessage());
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	/**
	 * 오답목록에 있던 주관식 데이터 지정 삭제
	 * @return
	 */
	public void Delete_WQSQ(int no) {
		SQLiteDatabase db = m_db.getWritableDatabase();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE FROM WrongQSQ WHERE NO = ").append(no);
			db.execSQL(sql.toString());
		}
		catch (SQLException e) {
			Log.e("Query", e.getMessage());
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	/**
	 *오답목록에 객관식 데이터 추가
	 * @return
	 */
	public void InsertQMC(int no, String ques, String a1, String a2, String a3, String a4, String a5, String ans){
		if (no <= 0 || TextUtils.isEmpty(ques)) return;

		SQLiteDatabase db = m_db.getWritableDatabase();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO WrongQMC(NO,QUES,A1,A2,A3,A4,A5,ANS) VALUES(").append(no).append(",'").append(ques).append("','").append(a1).append("','");
			sql.append(a2).append("','").append(a3).append("','").append(a4).append("','").append(a5).append("','").append(ans).append("')");
			db.execSQL(sql.toString());
		}
		catch (SQLException e) {
			Log.e("Query", e.getMessage());
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	/**
	 *오답목록에 주관식 데이터 추가
	 * @return
	 */
	public void InsertQSQ(int no, String ques, String ans){
		if (no <= 0 || TextUtils.isEmpty(ques)) return;

		SQLiteDatabase db = m_db.getWritableDatabase();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO WrongQSQ(NO,QUES,ANS) VALUES(").append(no).append(",'").append(ques).append("','").append(ans).append("')");
			db.execSQL(sql.toString());
		}
		catch (SQLException e) {
			Log.e("Query", e.getMessage());
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}
}