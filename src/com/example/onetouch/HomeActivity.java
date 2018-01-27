package com.example.onetouch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity
{
	Integer btnchk=0;
	 SharedPreferences sp;
	 Button b1,b2;
	 MediaRecorder mr;
	 GpsTracker gps;
	 MediaPlayer mp;
	 String path="";
	 String fn="";
	 TextView tv;
	 int rc=0,smsrc=100;
	 Integer audiochk=0;
	 ArrayList<String> al=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		b1=(Button)findViewById(R.id.button2);
		b2=(Button)findViewById(R.id.button3);
		
		
		
			b1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					btnchk=2;
					gps=new GpsTracker(HomeActivity.this);
					
					if(gps.cangetlocation)
					{
						btnchk=2;
						double latitude=gps.getLatitude();
						double longitude=gps.getLongitude();
						LocationAddress locationAddress = new LocationAddress();
	                    locationAddress.getAddressFromLocation(latitude, longitude,
	                            getApplicationContext(), new GeocoderHandler());
						
					}
					else{
						gps.showsettingsalert();
					}
					
				}
					
				
			});
			b2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					gps=new GpsTracker(HomeActivity.this);
					
					if(gps.cangetlocation)
					{
						btnchk=1;
						double latitude=gps.getLatitude();
						double longitude=gps.getLongitude();
						LocationAddress locationAddress = new LocationAddress();
	                    locationAddress.getAddressFromLocation(latitude, longitude,
	                            getApplicationContext(), new GeocoderHandler());
						
					}
					else{
						gps.showsettingsalert();
					}
					
				}
				
			});
		
	}
class GeocoderHandler extends Handler {
    @Override
    public void handleMessage(Message message) {
        String myaddress;
        switch (message.what) {
            case 1:
                Bundle bundle = message.getData();
                myaddress = bundle.getString("address");
                break;
            default:
                myaddress = null;
        }
        Db_activity db1=new Db_activity(getApplicationContext());
		db1.open();
		al=db1.getResult();
		String msg = "Help Me Please. \n Name: "+al.get(0)+"\n Father Name: "+al.get(1)+"\n Mobile: "+al.get(2)+"\n My Location:- \n"+myaddress;
		String hp1,hp2,hp3,hpmobs;
		hp1 = al.get(3).toString();
		hp2 = al.get(4).toString();
		hp3 = al.get(5).toString();
		hpmobs=hp1+";"+hp2+";"+hp3;
		
		db1.close();
		
		/*Intent it=new Intent(android.content.Intent.ACTION_SENDTO);
		it.putExtra("address",hpmobs);
		it.putExtra("sms_body",msg);
		it.setType("vnd.android-dir/mms-sms");
		startActivityForResult(it,smsrc);*/
	
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.setData(Uri.parse("sms:" + hpmobs));
        smsIntent.putExtra("sms_body",msg);
        startActivityForResult(smsIntent,smsrc);
	}
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==rc && resultCode==RESULT_OK)
		{
			Toast.makeText(getApplicationContext(),"vedio saved",Toast.LENGTH_SHORT).show();
		}
		
		if(requestCode==smsrc)
		{
			 if(btnchk==1)
		        {
				 
		        Random r=new Random();
				int i=r.nextInt(100000 - 100) + 100;
				
				File mydirectory=new File(Environment.getExternalStorageDirectory(),"one touch");
				if(!mydirectory.exists())
				{
					mydirectory.mkdirs();
				}
				fn="ots"+i+".3gp";
				
				path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/one touch/"+fn;
				mr=new MediaRecorder();
				mr.setAudioSource(MediaRecorder.AudioSource.MIC);
				mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				mr.setOutputFile(path);
				try {
					mr.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mr.start();
				audiochk=1;
				Toast.makeText(getApplicationContext(),"recording started",Toast.LENGTH_SHORT).show();
		        }
		        if(btnchk==2)
		        {
		        	Intent d=new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
					startActivityForResult(d,rc);
		        	
		        }
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	
		Integer i=Integer.parseInt(item.getItemId()+"");
		
		
		if(R.id.item1==i)
		{
			Intent A=new Intent(HomeActivity.this,UpdateActivity.class);
			startActivity(A);
		}
		if(R.id.item2==i)
		{
			sp=getSharedPreferences(LoginActivity.myprefrences,Context.MODE_PRIVATE);
			Editor ed=sp.edit();
			ed.clear();
			ed.commit();
			Intent it=new Intent(HomeActivity.this,LoginActivity.class);
			startActivity(it);
			finish();
			
		}
		if(R.id.item3==i)
		{
			finish();
			
		}
		if(R.id.item4==i)
		{
			if(audiochk==1)
			{
				audiochk=0;
				mr.stop();
				mr.release();
				Toast.makeText(getApplicationContext(), "recording saved",Toast.LENGTH_SHORT).show();	
			}
		}
		
		
		return super.onOptionsItemSelected(item);
	}
}
