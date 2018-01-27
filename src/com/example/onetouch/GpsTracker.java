package com.example.onetouch;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;

public class GpsTracker extends Service implements LocationListener 
{
	final Context mcontext;
	Boolean isgpsenabled=false;
	Boolean isnetworkenabled=false;
	Boolean cangetlocation=false;
	Location location;
	double latitude;
	double longitude;
	static final long MIN_DISTANCE_CHANGE_FOR_UPDATES=10;
	static final long MIN_TIME_BW_UPDATES=1000*60*1;
	LocationManager locationmanager;
	
	
	
	

	public GpsTracker(Context applicationContext) {
		// TODO Auto-generated constructor stub
		this.mcontext=applicationContext;
		getlocation();
		
	}

	public Location getlocation() {
		// TODO Auto-generated method stub
		try{
			
			locationmanager=(LocationManager)mcontext.getSystemService(LOCATION_SERVICE);
			isgpsenabled=locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isnetworkenabled=locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			if(!isgpsenabled && !isnetworkenabled)
			{}
			else
			{
				this.cangetlocation=true;
				if(isnetworkenabled)
				{
				
					locationmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES,this);
					if(locationmanager!=null)
					{
						location=locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if(location!=null)
						{
							latitude=location.getLatitude();
							longitude=location.getLongitude();
							
						}
					}
				}
				if(isgpsenabled)
				{
					if(location==null)
					{
					locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES,this);
					if(locationmanager!=null)
					{
						location=locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
						if(location!=null)
						{
							latitude=location.getLatitude();
							longitude=location.getLongitude();
							
						}
					}
					}
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}

	

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double getLatitude()
	{
		if(location!=null)
		{
			latitude=location.getLatitude();
		}
		return latitude;
	}
	public double getLongitude()
	{
		if(location!=null)
		{
			longitude=location.getLongitude();
		}
		return longitude;
	}
	public boolean cangetlocation()
	{
		return this.cangetlocation;
	}

	public void showsettingsalert()
	{
		AlertDialog.Builder ad=new AlertDialog.Builder(mcontext);
		ad.setTitle("GPS settings");
		ad.setMessage("GPS not enabled do you want to go to settings menu ?");
		ad.setPositiveButton("settings", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				mcontext.startActivity(i);
				
			}
		});
		ad.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
			
				arg0.cancel();
			}
		});
		ad.show();
	}
	

}
