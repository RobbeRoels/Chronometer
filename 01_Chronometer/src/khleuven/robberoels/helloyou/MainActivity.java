package khleuven.robberoels.helloyou;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
	private static final String STATE_TIME = "TIME";
	private static final String STATE = "STATE";
	boolean running;
	int counter = 0;
	OnClickListener startListener,stopListener, pauseListener;
	
	
	Handler handler = new Handler();
	Runnable timedTask = new Runnable(){
	    @Override
	    public void run() {
	    	tick();
	    }
	};
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListeners();
        ((Button) findViewById(R.id.start)).setOnClickListener(startListener);
        ((Button) findViewById(R.id.stop)).setOnClickListener(stopListener);
        ((Button) findViewById(R.id.pause)).setOnClickListener(pauseListener);
        ((TextView) findViewById(R.id.tvCounter)).setText(counter+ "");
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void tick(){
    	if(running){
    		counter++;
    		((TextView) findViewById(R.id.tvCounter)).setText(counter+ "");
    		handler.postDelayed(timedTask, 1000);
    	}
    }
    
    public void initListeners(){
    	startListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!running){
					running = true;
					handler.postDelayed(timedTask, 1000);
				}
				
			}
		};
    	stopListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				running = false;
				counter = 0;
				((TextView) findViewById(R.id.tvCounter)).setText(counter+ "");
			}
		};
    	pauseListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				running = false;
			}
		};
    	
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { 
    	// Save the user's current game state 
    	savedInstanceState.putInt(STATE_TIME , counter);
    	savedInstanceState.putBoolean(STATE, running);
    	// Always call the superclass so it can save the view hierarchy state 
    	super.onSaveInstanceState(savedInstanceState);
    }
    
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) { 
    	// Always call the superclass so it can restore the view hierarchy 
    	// super.onRestoreInstanceState(savedInstanceState); 
    	// Restore state members from saved instance 
    	counter = savedInstanceState.getInt(STATE_TIME);
    	running = savedInstanceState.getBoolean(STATE);
		((TextView) findViewById(R.id.tvCounter)).setText(counter+ "");
		handler.postDelayed(timedTask, 1000);
    }
    
}
