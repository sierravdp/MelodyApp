package com.IDSoft.melodyapp;

import java.io.File;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Toast.makeText(MainActivity.this, getString(R.string.greeting), Toast.LENGTH_SHORT).show();
		
		createDirIfNotExists("MelodyApp");
		
		TabHost tabHost = getTabHost();
        
        // Tab for Play
        TabSpec playspec = tabHost.newTabSpec("Play");
        // setting Title and Icon for the Tab
        playspec.setIndicator(getString(R.string.play), getResources().getDrawable(R.drawable.icon_play_tab));
        Intent playIntent = new Intent(this, PlayActivity.class);
        playspec.setContent(playIntent);
         
        // Tab for Edit
        TabSpec editspec = tabHost.newTabSpec("Edit");        
        editspec.setIndicator(getString(R.string.edit), getResources().getDrawable(R.drawable.icon_edit_tab));
        Intent editIntent = new Intent(this, EditActivity.class);
        editspec.setContent(editIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(playspec); // Adding photos tab
        tabHost.addTab(editspec); // Adding songs tab
	}
	
	public static boolean createDirIfNotExists(String path) {
	    boolean ret = true;

	    File file = new File(Environment.getExternalStorageDirectory(), path);
	    if (!file.exists()) {
	        if (!file.mkdirs()) {
	            Log.e("TravellerLog :: ", "Problem creating Image folder");
	            ret = false;
	        }
	    }
	    return ret;
	}

}
