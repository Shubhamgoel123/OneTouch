package com.example.onetouch;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Db_activity {

	final static String Key_Userid="user_id";
	final static String Key_Userpassword="user_password";
	final static String Key_Username="user_name";
	final static String Key_Fathername="father_name";
	final static String Key_Mothername="mother_name";
	final static String Key_City="user_city";
	final static String Key_State="user_state";
	final static String Key_Address="user_address";
	final static String Key_Pincode="user_pincode";
	final static String Key_Mobile="mobile_no";
	final static String Key_Helpline1="helplineno_1";
	final static String Key_Helpline2="helplineno_2";
	final static String Key_Helpline3="helplineno_3";

	
	
	final static String Database_Name="onetouchdb03";
	final static String Database_Table="usertable03";
	final static int Database_Version=1;
	
	Dbhelper ourhelper;
	
	final Context ourcontext;
	SQLiteDatabase ourdatabase;
	
	
	private static class Dbhelper extends SQLiteOpenHelper
	{
		public Dbhelper(Context context)
		{
			super(context,Database_Name,null,Database_Version);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+ Database_Table + "(" + Key_Userid + " TEXT PRIMARY KEY," + Key_Userpassword + " TEXT NOT NULL," + Key_Username + " TEXT NOT NULL," + Key_Fathername + " TEXT NOT NULL," + Key_Mothername + " TEXT NOT NULL," + Key_City + " TEXT," + Key_State + " TEXT," + Key_Address + " TEXT," + Key_Pincode + " TEXT NOT NULL," + Key_Mobile + " TEXT NOT NULL," + Key_Helpline1 + " TEXT NOT NULL," + Key_Helpline2 + " TEXT," + Key_Helpline3 + " TEXT);");
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + Database_Table);
			onCreate(db);
		}
	}
	
	public Db_activity(Context c)
	{
		ourcontext=c;
	}
	
	public Db_activity open()
	{
		ourhelper=new Dbhelper(ourcontext);
		ourdatabase=ourhelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		ourhelper.close();
	}

	public void entry(String id, String password, String name,
			String fathername, String mothername, String city, String state,
			String address, String pincode, String mobile, String help1,
			String help2, String help3) {
		// TODO Auto-generated method stub
		
		ContentValues cv=new ContentValues();
		cv.put(Key_Userid,id);
		cv.put(Key_Userpassword,password);
		cv.put(Key_Username,name);
		cv.put(Key_Fathername,fathername);
		cv.put(Key_Mothername,mothername);
		cv.put(Key_City,city);
		cv.put(Key_State,state);
		cv.put(Key_Address,address);
		cv.put(Key_Pincode,pincode);
		cv.put(Key_Mobile,mobile);
		cv.put(Key_Helpline1,help1);
		cv.put(Key_Helpline2,help2);
		cv.put(Key_Helpline3,help3);
		
		
		ourdatabase.insert(Database_Table, null,cv);
		
	}

	public ArrayList<String> getResult() {
		// TODO Auto-generated method stub
		String[] columns=new String[]{Key_Userid,Key_Userpassword,Key_Username,Key_Fathername,Key_Mothername,Key_City,Key_State,Key_Address,Key_Pincode,Key_Mobile,Key_Helpline1,Key_Helpline2,Key_Helpline3}; 
		 Cursor c=ourdatabase.query(Database_Table,columns,null,null,null,null,null,null);
		
		 
		 ArrayList<String> a=new ArrayList<String>();
		 Integer id=c.getColumnIndex(Key_Userid);
		 Integer ipassword=c.getColumnIndex(Key_Userpassword);
		 Integer iname=c.getColumnIndex(Key_Username);
		 Integer ifather=c.getColumnIndex(Key_Fathername);
		 Integer imother=c.getColumnIndex(Key_Mothername);
		 Integer icity=c.getColumnIndex(Key_City);
		 Integer istate=c.getColumnIndex(Key_State);
		 Integer iaddress=c.getColumnIndex(Key_Address);
		 Integer ipincode=c.getColumnIndex(Key_Pincode);
		 Integer imobile=c.getColumnIndex(Key_Mobile);
		 Integer ihelp1=c.getColumnIndex(Key_Helpline1);
		 Integer ihelp2=c.getColumnIndex(Key_Helpline2);
		 Integer ihelp3=c.getColumnIndex(Key_Helpline3);
		 
		 for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		 {
			 a.add(c.getString(iname));
			 a.add(c.getString(ifather)); 
			 a.add(c.getString(imobile));
			 a.add(c.getString(ihelp1));
			 a.add(c.getString(ihelp2)); 
			 a.add(c.getString(ihelp3));
		 }
		 return a;
	}

	public String[] fetchdata() {
		// TODO Auto-generated method stub
		String[] columns=new String[]{Key_Userid,Key_Userpassword,Key_Username,Key_Fathername,Key_Mothername,Key_City,Key_State,Key_Address,Key_Pincode,Key_Mobile,Key_Helpline1,Key_Helpline2,Key_Helpline3}; 
		 Cursor c=ourdatabase.query(Database_Table,columns,null,null,null,null,null,null);
		
			String results[]=new String[]{"","","","","","","","","","","","","","","","","","","","","","","",""};
		 
		 Integer id=c.getColumnIndex(Key_Userid);
		 Integer ipassword=c.getColumnIndex(Key_Userpassword);
		 Integer iname=c.getColumnIndex(Key_Username);
		 Integer ifather=c.getColumnIndex(Key_Fathername);
		 Integer imother=c.getColumnIndex(Key_Mothername);
		 Integer icity=c.getColumnIndex(Key_City);
		 Integer istate=c.getColumnIndex(Key_State);
		 Integer iaddress=c.getColumnIndex(Key_Address);
		 Integer ipincode=c.getColumnIndex(Key_Pincode);
		 Integer imobile=c.getColumnIndex(Key_Mobile);
		 Integer ihelp1=c.getColumnIndex(Key_Helpline1);
		 Integer ihelp2=c.getColumnIndex(Key_Helpline2);
		 Integer ihelp3=c.getColumnIndex(Key_Helpline3);
		 
		 
		 if(c!=null)
		 {
			 	c.moveToFirst();
			 	
			 	results[0]=c.getString(id);
			 	results[1]=c.getString(ipassword);
			 	results[2]=c.getString(iname);
			 	results[3]=c.getString(ifather);
			 	results[4]=c.getString(imother);
			 	results[5]=c.getString(icity);
			 	results[6]=c.getString(istate);
			 	results[7]=c.getString(iaddress);
			 	results[8]=c.getString(ipincode);
			 	results[9]=c.getString(imobile);
			 	results[10]=c.getString(ihelp1);
			 	results[11]=c.getString(ihelp2);
			 	results[12]=c.getString(ihelp3);
		 }
		 
		 return results;
		
		
	}

	public void update(String id, String password, String name,
			String fathername, String mothername, String city, String state,
			String address, String pincode, String mobile, String help1,
			String help2, String help3) {
		// TODO Auto-generated method stub
		
		ContentValues cv=new ContentValues();
		cv.put(Key_Userid,id);
		cv.put(Key_Userpassword,password);
		cv.put(Key_Username,name);
		cv.put(Key_Fathername,fathername);
		cv.put(Key_Mothername,mothername);
		cv.put(Key_City,city);
		cv.put(Key_State,state);
		cv.put(Key_Address,address);
		cv.put(Key_Pincode,pincode);
		cv.put(Key_Mobile,mobile);
		cv.put(Key_Helpline1,help1);
		cv.put(Key_Helpline2,help2);
		cv.put(Key_Helpline3,help3);
		
		
		ourdatabase.update(Database_Table,cv,null,null);

	}

	public Integer getCount() {
		// TODO Auto-generated method stub
		
		String[] columns=new String[]{Key_Userid,Key_Userpassword,Key_Username,Key_Fathername,Key_Mothername,Key_City,Key_State,Key_Address,Key_Pincode,Key_Mobile,Key_Helpline1,Key_Helpline2,Key_Helpline3}; 
		 Cursor c=ourdatabase.query(Database_Table,columns,null,null,null,null,null,null);
		
		 Integer i=c.getCount();
		
		
		return i;
	}

	public Boolean check(String id, String password) {
		// TODO Auto-generated method stub
		
		String[] columns=new String[]{Key_Userid,Key_Userpassword,Key_Username,Key_Fathername,Key_Mothername,Key_City,Key_State,Key_Address,Key_Pincode,Key_Mobile,Key_Helpline1,Key_Helpline2,Key_Helpline3}; 
		 Cursor c=ourdatabase.query(Database_Table,columns,Key_Userid+"="+id,null,null,null,null,null);
		
		 if(c!=null)
		 {
			 c.moveToFirst();
			 String s=c.getString(1);
			 
			 if(s.equals(password))
				 return true;
			 else
				 return false;
		 }
		 else
		 {
			 return false;
		 }
		
	}

	public String getid() {
		String[] columns=new String[]{Key_Userid,Key_Userpassword,Key_Username,Key_Fathername,Key_Mothername,Key_City,Key_State,Key_Address,Key_Pincode,Key_Mobile,Key_Helpline1,Key_Helpline2,Key_Helpline3}; 
		 Cursor c=ourdatabase.query(Database_Table,columns,null,null,null,null,null,null);
		
		 c.moveToFirst();
		 return c.getString(0);
	}

	public String getpassword() {
		String[] columns=new String[]{Key_Userid,Key_Userpassword,Key_Username,Key_Fathername,Key_Mothername,Key_City,Key_State,Key_Address,Key_Pincode,Key_Mobile,Key_Helpline1,Key_Helpline2,Key_Helpline3}; 
		 Cursor c=ourdatabase.query(Database_Table,columns,null,null,null,null,null,null);
		
		 c.moveToFirst();
		 return c.getString(1);
	}
	 }
