/*package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SqliteDatabase
{
	private static int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "a2990850_MTBRide";
	private static final String DATABASE_TABLE_NAME = "Users";

	private DbHelper myHelper;
	private final Context myContext;
	private SQLiteDatabase myDatabase;
	private SQLiteStatement insertUsers;
	
	private static final String INSERT_USERS = "insert into " + DATABASE_TABLE_NAME
			   + " (email,username,password) values (?,?,?)";

	private static class DbHelper extends SQLiteOpenHelper
	{

		private List<Login> userList;

		public DbHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{

			db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_NAME +
					" (email TEXT ,username TEXT ,password TEXT )");	
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
			onCreate(db);
		}
	}

	public SqliteDatabase(Context c)
	{
		myContext = c;
		myHelper = new DbHelper(myContext);
		myDatabase = myHelper.getWritableDatabase();
		insertUsers = myDatabase.compileStatement(INSERT_USERS);
	}

	public void close()
	{
		myDatabase.close();
		myHelper.close();
	}
	
	public long insertUsersToTable(String email , String username , String password){
		insertUsers.bindString(1, email);
		insertUsers.bindString(2, username);
		insertUsers.bindString(3, password);
		return insertUsers.executeInsert();
	}
	
	public void deleteUsersTable(){
		myDatabase.delete(DATABASE_TABLE_NAME, null, null);
	}
	
	public void deleteUser(String username){
		myDatabase.delete(DATABASE_TABLE_NAME, "username = ?", new String[] { username });
		
	}
	
	public List<Login> selectAllUsers(){
		
		List<Login> users = new ArrayList<Login>();
		
		String[] columns = new String[] { "id" , "email" , "username" , "password" };
		
		Cursor c = myDatabase.query(DATABASE_TABLE_NAME, columns, null, null, null, null, null);
		
		if(c.moveToFirst()){
			do {
				Login user = new Login(c.getString(1),c.getString(2),c.getString(3));
				users.add(user);
				
			} while (c.moveToNext());
		}
		
		if(c != null && !(c.isClosed())){
			c.close();
		}
		
		return users;
		
	}
	
public Login selectUser(String username){
		
		List<Login> users = new ArrayList<Login>();
		
		String[] columns = new String[] { "id" , "email" , "username" , "password" };
		
		String[] args = {username};
		
		Cursor c = myDatabase.query(DATABASE_TABLE_NAME, columns, "username = ?", args, null, null, null);
		
		if(c.moveToFirst()){
			do {
				Login user = new Login(c.getString(1),c.getString(2),c.getString(3));
				users.add(user);
				
			} while (c.moveToNext());
		}
		
		if(c != null && !(c.isClosed())){
			c.close();
		}
		
		if(!users.isEmpty()){
			return users.get(0);
		}
		
		return null;
		
	}
	
	

	//	public List<Player> getPlayers()
	//	{
	//		// TODO Auto-generated method stub
	//
	//		List<Player> playersList = new ArrayList<Player>();
	//
	//		String[] columns = new String[] { PLAYER_ROWID, PLAYER_NAME, PLAYER_ROLE, PLAYER_BIRTHYEAR, 
	//				PLAYER_BIRTHMONTH, PLAYER_BIRTHDAY, PLAYER_NUMBER, PLAYER_AGE, PLAYER_IMAGE };
	//		Cursor c = myDatabase.query(DATABASE_TABLE_PLAYERS, columns, null, null, null, null, null);
	//
	//		int iName = c.getColumnIndex(PLAYER_NAME);
	//		int iRole = c.getColumnIndex(PLAYER_ROLE);
	//		int iBirthYear = c.getColumnIndex(PLAYER_BIRTHYEAR);
	//		int iBirthMonth = c.getColumnIndex(PLAYER_BIRTHMONTH);
	//		int iBirthDay = c.getColumnIndex(PLAYER_BIRTHDAY);
	//		int iNumber = c.getColumnIndex(PLAYER_NUMBER);
	//		int iAge = c.getColumnIndex(PLAYER_AGE);
	//		int iImage = c.getColumnIndex(PLAYER_IMAGE);
	//
	//		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
	//		{
	//			Player p = new Player(c.getString(iName), c.getString(iRole), 
	//					new GregorianCalendar(iBirthYear, iBirthMonth, iBirthDay), c.getInt(iNumber),
	//					c.getInt(iAge), c.getInt(iImage));
	//			playersList.add(p);
	//		}
	//
	//		return playersList;
	//	}
	

}


*/
