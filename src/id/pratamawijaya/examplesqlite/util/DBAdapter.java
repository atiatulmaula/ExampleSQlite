/*
 * Pratama Nur Wijaya (c) 2013 
 * 
 * Project       : ExampleSQLite
 * Filename      : DBAdapter.java
 * Creation Date : Jul 24, 2013 time : 12:56:23 PM
 *
 */

package id.pratamawijaya.examplesqlite.util;

import id.pratamawijaya.examplesqlite.entity.E_Buku;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper
{
	private static final String		DB_NAME				= "db_buku";
	private static final int		DB_VER				= 1;

	public static final String		TABLE_BUKU			= "tb_buku";
	public static final String		COL_BUKU_ID			= "_id";
	public static final String		COL_BUKU_JUDUL		= "judul";
	public static final String		COL_BUKU_PENERBIT	= "penerbit";

	private static DBAdapter		dbInstance			= null;
	private static SQLiteDatabase	db;

	private static final String		CREATE_BUKU			= "CREATE TABLE tb_buku(_id integer primary key autoincrement not null , judul text, penerbit text)";

	private DBAdapter(Context context)
	{
		super(context, DB_NAME, null, DB_VER);
	}

	/*
	 * Method Singleton dari class DBAdapter untuk instance class DBAdapter
	 */
	public static DBAdapter getInstance(Context context)
	{
		if (dbInstance == null)
		{
			dbInstance = new DBAdapter(context);
			db = dbInstance.getWritableDatabase();
		}
		return dbInstance;
	}

	@Override
	public synchronized void close()
	{
		super.close();
		if (dbInstance != null)
		{
			dbInstance.close();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// TODO Auto-generated method stub
		db.execSQL(CREATE_BUKU);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXIST " + TABLE_BUKU);
		onCreate(db);

	}

	/*
	 * Crud
	 */

	/*
	 * Baca semua buku
	 */
	public List<E_Buku> getAllBuku()
	{
		List<E_Buku> listBuku = new ArrayList<E_Buku>();
		Cursor cursor = db.query(TABLE_BUKU, new String[]
		{
				COL_BUKU_ID, COL_BUKU_JUDUL, COL_BUKU_PENERBIT
		}, null, null, null, null, null);

		/*
		 * Cek apakah ada data atau tidak
		 */
		if (cursor.getCount() >= 1)
		{
			cursor.moveToFirst();

			do
			{
				E_Buku buku = new E_Buku();
				buku.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_BUKU_ID)));
				buku.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(COL_BUKU_JUDUL)));
				buku.setPenerbit(cursor.getString(cursor.getColumnIndexOrThrow(COL_BUKU_PENERBIT)));
				listBuku.add(buku);

			} while (cursor.moveToNext());

		}

		return listBuku;
	}

	/*
	 * Insert Data
	 */
	public boolean insertData(E_Buku buku)
	{
		ContentValues values = new ContentValues();
		values.put(COL_BUKU_JUDUL, buku.getJudul());
		values.put(COL_BUKU_PENERBIT, buku.getPenerbit());

		long cek = db.insert(TABLE_BUKU, null, values);
		return (cek != -1) ? true : false;
	}
}
