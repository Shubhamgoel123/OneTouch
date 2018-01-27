package com.example.onetouch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends Activity{
	
	
	Button b;
	EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11,ed12,ed13;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		
		b=(Button)findViewById(R.id.Button1);
		ed1=(EditText)findViewById(R.id.EditText1);
		ed2=(EditText)findViewById(R.id.EditText2);
		ed3=(EditText)findViewById(R.id.EditText3);
		ed4=(EditText)findViewById(R.id.EditText4);
		ed10=(EditText)findViewById(R.id.EditText10);
		ed11=(EditText)findViewById(R.id.EditText11);
		ed12=(EditText)findViewById(R.id.EditText12);
		ed13=(EditText)findViewById(R.id.EditText13);
		
		Db_activity db=new Db_activity(getApplicationContext());
		db.open();
		
		String s[]=new String[]{"","","","","","","","","","","","","","","","","","","","","","","",""};
		s=db.fetchdata();
		db.close();
		
		ed1.setText(s[0]);
		ed2.setText(s[1]);
		ed3.setText(s[2]);
		ed4.setText(s[3]);
		ed10.setText(s[9]);
		ed11.setText(s[10]);
		ed12.setText(s[11]);
		ed13.setText(s[12]);
		
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
String id,password,name,fathername,mothername,city,state,address,pincode,mobile,help1,help2,help3;
				
				id=ed1.getText().toString();
				password=ed2.getText().toString();
				name=ed3.getText().toString();
				fathername=ed4.getText().toString();
				mobile=ed10.getText().toString();
				help1=ed11.getText().toString();
				help2=ed12.getText().toString();
				help3=ed13.getText().toString();
				
				Integer flag=0;
				String s="";
				if(password.equals("") || name.equals("") || fathername.equals("") || mobile.equals("") || help1.equals(""))
				{
					flag=1;
					s=s+"**** Marked field Should not be blank\n";
				}
				
				if(id.length()<8)
				{
					flag=1;
					s=s+"**** User id should be of minimum 8 characters\n";
				}
				
				if(password.length()<6)
				{
					flag=1;
					s=s+"**** Password should of minimum 6 characters\n";
				}
				
				if(mobile.length()!=10 || help1.length()!=10)
				{
					flag=1;
					s=s+"**** Contact No. shpuld be of 10 digits\n";
				}
				
				Toast.makeText(UpdateActivity.this,s, Toast.LENGTH_SHORT).show();
				
				if(flag==0)
				{
					Db_activity db=new Db_activity(getApplicationContext());
					db.open();
					db.update(id,password,name,fathername,"m","c","s","a","p",mobile,help1,help2,help3);
					db.close();
					Toast.makeText(UpdateActivity.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
				}		
			}
		});
	}

}
