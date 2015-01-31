/*package com.sharon.mtbriderapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDatabase
{
	private static int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "a2990850_MTBRide";

//	private static final String DATABASE_TABLE_PLAYERS = "players";
	private static final String DATABASE_TABLE_MTBRIDERAPPLOGINSIGNIN = "MTBRiderAppLoginSignIn";
//	private static final String DATABASE_TABLE_FIXTURES = "fixtures";

	//Player table columns
//	private static final String PLAYER_ROWID = "_id";
//	private static final String PLAYER_NAME = "name";
//	private static final String PLAYER_ROLE = "role";
//	private static final String PLAYER_BIRTHYEAR = "birth_year";
//	private static final String PLAYER_BIRTHMONTH = "birth_month";
//	private static final String PLAYER_BIRTHDAY = "birth_day";
//	private static final String PLAYER_NUMBER = "number";
//	private static final String PLAYER_AGE = "age";
//	private static final String PLAYER_IMAGE = "image";
	
	private static final String NEW_ROWID = "_id";
	private static final String NEW_EMAIL = "email";
	private static final String NEW_USER = "user";
	private static final String NEW_PASS = "pass";
	//private static final String NEW_IMAGE = "image";
	
//	private static final String FIXTURE_ROWID = "_id";
//	private static final String FIXTURE_NUM = "num";
//	private static final String FIXTURE_DATE = "date";
//	private static final String FIXTURE_HOME = "home";
//	private static final String FIXTURE_AWAY = "away";
//	private static final String FIXTURE_HOMESCORED = "home_scored";
//	private static final String FIXTURE_AWAYSCORED = "away_scored";
	

	private DbHelper myHelper;
	private final Context myContext;
	private SQLiteDatabase myDatabase;

	private static class DbHelper extends SQLiteOpenHelper
	{

		private List<Login> userList;
//private List<Fixture> fixturesList;

		public DbHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub

		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// TODO Auto-generated method stub

			WebServerConnection webSrv = new WebServerConnection();
			userList = webSrv.getAllUsers();
//			fixturesList = webSrv.getAllFixtures();
//			playersList = new ArrayList<Player>();

//			db.execSQL("CREATE TABLE " + DATABASE_TABLE_PLAYERS + " (" + PLAYER_ROWID
//					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + PLAYER_NAME + " TEXT NOT NULL, " + PLAYER_ROLE
//					+ " TEXT NOT NULL, " + PLAYER_BIRTHYEAR + " INTEGER, " + PLAYER_BIRTHMONTH + " INTEGER, "
//					+ PLAYER_BIRTHDAY + " INTEGER, " + PLAYER_NUMBER + " INTEGER, "
//					+ PLAYER_AGE + " INTEGER, " + PLAYER_IMAGE + " INTEGER)");
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_MTBRIDERAPPLOGINSIGNIN + " (" + NEW_ROWID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NEW_EMAIL
			+ " TEXT NOT NULL, " + NEW_USER + " TEXT NOT NULL, " + NEW_PASS + " TEXT NOT NULL)");
			
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_FIXTURES + " (" + FIXTURE_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIXTURE_NUM + " INTEGER, " + FIXTURE_DATE
					+ " TEXT NOT NULL, " + FIXTURE_HOME + " TEXT NOT NULL, " + FIXTURE_AWAY + " TEXT NOT NULL, " 
					+ FIXTURE_HOMESCORED + " INTEGER, " + FIXTURE_AWAYSCORED + " INTEGER)");
			
//
//			
//
//			ContentValues cvPlayer = new ContentValues();
			
			ContentValues cvNew = new ContentValues();
			
//			
//			//22 players
//			Calendar calendar = Calendar.getInstance();
//			int currYear = calendar.get(Calendar.YEAR);
//			
//			GregorianCalendar birthDate;
//			
//			birthDate = new GregorianCalendar(1990, 10, 26);	
//			playersList.add(new Player("בוריס קליימן", "שוער", birthDate, 1, 
//					currYear - birthDate.get(GregorianCalendar.YEAR), R.drawable.boris_kleyman));
//			
//
//			for(int i = 0; i < playersList.size(); i++)
//			{
//				cvPlayer.put(PLAYER_NAME, playersList.get(i).getName());
//				cvPlayer.put(PLAYER_ROLE, playersList.get(i).getRole());
//				cvPlayer.put(PLAYER_BIRTHYEAR, playersList.get(i).getBirthday().get(GregorianCalendar.YEAR));
//				cvPlayer.put(PLAYER_BIRTHMONTH, playersList.get(i).getBirthday().get(GregorianCalendar.MONTH));
//				cvPlayer.put(PLAYER_BIRTHDAY, playersList.get(i).getBirthday().get(GregorianCalendar.DAY_OF_MONTH));
//				cvPlayer.put(PLAYER_NUMBER, playersList.get(i).getNumber());
//				cvPlayer.put(PLAYER_AGE, playersList.get(i).getAge());
//				cvPlayer.put(PLAYER_IMAGE, playersList.get(i).getImage());
//				db.insert(DATABASE_TABLE_PLAYERS, null, cvPlayer);
//			}
			
			for(int i = 0; i < userList.size(); i++)
			{
				cvNew.put(NEW_EMAIL, userList.get(i).getEmail());
				cvNew.put(NEW_USER, userList.get(i).getUser());
				cvNew.put(NEW_PASS, userList.get(i).getPass());
				db.insert(DATABASE_TABLE_MTBRIDERAPPLOGINSIGNIN, null, cvNew);
			}
			
			
			ContentValues cvFixture = new ContentValues();
			for(int i = 0; i < fixturesList.size(); i++)
			{
				cvFixture.put(FIXTURE_NUM, fixturesList.get(i).getNum());
				cvFixture.put(FIXTURE_DATE, fixturesList.get(i).getDate());
				cvFixture.put(FIXTURE_HOME, fixturesList.get(i).getHome());
				cvFixture.put(FIXTURE_AWAY, fixturesList.get(i).getAway());
				cvFixture.put(FIXTURE_HOMESCORED, fixturesList.get(i).getHome_scored());
				cvFixture.put(FIXTURE_AWAYSCORED, fixturesList.get(i).getAway_scored());
				db.insert(DATABASE_TABLE_FIXTURES, null, cvFixture);
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MTBRIDERAPPLOGINSIGNIN);
	//		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FIXTURES);
			onCreate(db);
		}
		
		public void update(SQLiteDatabase db)
		{
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MTBRIDERAPPLOGINSIGNIN);
//			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FIXTURES);
			onCreate(db);
		}



	}

	public SqliteDatabase(Context c)
	{
		myContext = c;
	}

	public SqliteDatabase open()
	{
		myHelper = new DbHelper(myContext);
		myDatabase = myHelper.getWritableDatabase();
		return this;
	}
	
	public void update()
	{
		myHelper.update(myDatabase);
	}

	public void close()
	{
		myHelper.close();
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
	
	public List<Login> getNews()
	{
		// TODO Auto-generated method stub

		List<Login> newsList = new ArrayList<Login>();

		String[] columns = new String[] { NEW_ROWID, NEW_EMAIL, NEW_USER, NEW_PASS };
		Cursor c = myDatabase.query(DATABASE_TABLE_MTBRIDERAPPLOGINSIGNIN, columns, null, null, null, null, null);

		int iDesc = c.getColumnIndex(NEW_EMAIL);
		int iDate = c.getColumnIndex(NEW_USER);
		int iTime = c.getColumnIndex(NEW_PASS);
	//	int iImage = c.getColumnIndex(NEW_IMAGE);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			Login n = new Login(c.getString(iDesc), c.getString(iDate), c.getString(iTime));
			newsList.add(n);
		}

		return newsList;
	}
	
	
	public List<Fixture> getFixtures()
	{
		// TODO Auto-generated method stub

		List<Fixture> fixturesList = new ArrayList<Fixture>();

		String[] columns = new String[] { FIXTURE_ROWID, FIXTURE_NUM, FIXTURE_DATE, FIXTURE_HOME, FIXTURE_AWAY, 
				FIXTURE_HOMESCORED, FIXTURE_AWAYSCORED };
		Cursor c = myDatabase.query(DATABASE_TABLE_FIXTURES, columns, null, null, null, null, null);

		int iNum = c.getColumnIndex(FIXTURE_NUM);
		int iDate = c.getColumnIndex(FIXTURE_DATE);
		int iHome = c.getColumnIndex(FIXTURE_HOME);
		int iAway = c.getColumnIndex(FIXTURE_AWAY);
		int iHomeScored = c.getColumnIndex(FIXTURE_HOMESCORED);
		int iAwayScored = c.getColumnIndex(FIXTURE_AWAYSCORED);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			Fixture fixture = new Fixture(c.getInt(iNum), c.getString(iDate), c.getString(iHome), c.getString(iAway), 
					c.getInt(iHomeScored), c.getInt(iAwayScored));
			fixturesList.add(fixture);
		}

		return fixturesList;
	}

}


*/