package com.example.onetouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassword extends Activity{
	
	EditText ed;
	TextView tv;
	Button b;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_password);
	
	ed=(EditText)findViewById(R.id.editText1);
	tv=(TextView)findViewById(R.id.textView1);
	b=(Button)findViewById(R.id.button1);
	
	b.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String s=ed.getText().toString();
			
			Db_activity db=new Db_activity(getApplicationContext());
			db.open();
			String uid=db.getid();
			String pwd=db.getpassword();
			db.close();
			
			if(uid.equals(s))
			{
				Toast.makeText(ForgetPassword.this,"Password is "+pwd, 1000).show();
			}
			else
			{
				Toast.makeText(ForgetPassword.this,"Userid not exists", 1000).show();
			}
		}
	});
	
}
}
