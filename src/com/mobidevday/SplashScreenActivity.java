package com.mobidevday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashScreenActivity extends Activity {
	private Thread splashThread;  
	public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.splashscreen);
		  splashThread = new Thread()
		   {
			@Override
			public void run() {
				super.run();
			int i = 0;
		       try {
		    	   synchronized(this){

					wait(3000);
					}
					}
					catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							finish();
						   Intent intent = new Intent();
						   intent.setClassName("com.mobidevday","com.mobidevday.Startup");
						   startActivity(intent);
						   stop();
					
			}   
		   };
		   splashThread.start();
	
	   }
	   @Override
	    public boolean onTouchEvent(MotionEvent evt)
	    {
	        if(evt.getAction() == MotionEvent.ACTION_DOWN)
	        {
	            synchronized(splashThread){
	               splashThread.notifyAll();
	            }
	        }
	        return true;
	    }    

	   
}
