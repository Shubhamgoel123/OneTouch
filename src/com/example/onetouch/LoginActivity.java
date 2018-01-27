package com.example.onetouch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	static String myprefrences="";
	String myuid;
	SharedPreferences sp;
	EditText ed1,ed2;
	Button b;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	    sp=getSharedPreferences(myprefrences,Context.MODE_PRIVATE);
		
		if(sp.contains(myuid))
		{
			Intent i=new Intent(LoginActivity.this,HomeActivity.class);
			startActivity(i);
			finish();
		}
	
		ed1=(EditText)findViewById(R.id.EditText1);
		ed2=(EditText)findViewById(R.id.EditText2);
		b=(Button)findViewById(R.id.Button1);
		tv=(TextView)findViewById(R.id.textView1);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String id=ed1.getText().toString();
				String password=ed2.getText().toString();
				
				Db_activity db=new Db_activity(getApplicationContext());
				db.open();
				String uid=db.getid();
				String pwd=db.getpassword();
				db.close();
				
				if(!id.equals(uid))
				{
					Toast.makeText(LoginActivity.this,"User Id is Incorrect",Toast.LENGTH_SHORT).show();
				}
				else if(!pwd.equals(password))
				{
					Toast.makeText(LoginActivity.this,"Password is Incorrect",Toast.LENGTH_SHORT).show();
				}
				else
				{
					Editor ed=sp.edit();
					ed.putString(myuid,uid);
					ed.commit();
					
					Intent p=new Intent(LoginActivity.this,HomeActivity.class);
					startActivity(p);
					finish();
				}
			}
		});
		
		tv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(LoginActivity.this,ForgetPassword.class);
				startActivity(i);
				
			}
		});
		
	}
	
	
}
