package com.example.onetouch;

import com.example.onetouch.RegisterActivity;
import com.example.onetouch.SplashActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {

	ProgressBar pb;
	Thread t;
	Integer i=0;
	Handler h=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		
			t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(i<100)
				{
					i=i+1;
					h.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							pb.setProgress(i);
						}
					});
					
					try {
						t.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				Db_activity db=new Db_activity(getApplicationContext());
				db.open();
				Integer i=db.getCount();
				db.close();
				
				if(i>0)
				{
					Intent in=new Intent(SplashActivity.this,LoginActivity.class);
					startActivity(in);
					finish();
				}
				else
				{
					Intent in=new Intent(SplashActivity.this,RegisterActivity.class);
					startActivity(in);
					finish();
				}
			}
			});
			t.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

}
