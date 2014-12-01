package com.IDSoft.melodyapp;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class PlayActivity extends Activity {
	
	private static final String LOG_TAG = "PlaySong";
    private MediaPlayer   mPlayer = null;
    
 // references to our images
    private Integer[] mThumbIds = {
            R.drawable.ic_play_default3, R.drawable.ic_play_default3,
            R.drawable.ic_play_default3, R.drawable.ic_play_default3,
            R.drawable.ic_play_default3, R.drawable.ic_play_default3,
            R.drawable.ic_play_default, R.drawable.ic_play_default,
            R.drawable.ic_play_default, R.drawable.ic_play_default,
            R.drawable.ic_play_default, R.drawable.ic_play_default,
            R.drawable.ic_play_default, R.drawable.ic_play_default,
            R.drawable.ic_play_default, R.drawable.ic_play_default,
            R.drawable.ic_play_default, R.drawable.ic_play_default,
            R.drawable.ic_play_default, R.drawable.ic_play_default,
            R.drawable.ic_play_default, R.drawable.ic_play_default,
            R.drawable.ic_play_default, R.drawable.ic_play_default
    };

    private void onPlay(boolean start, String mFile) {
        if (start) {
            startPlaying(mFile);
        }
    }

    private void startPlaying(String mFile) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFile);
            mPlayer.prepare();
            mPlayer.start();
            
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }
    
//    private void stopPlaying() {
//        mPlayer.release();
//        mPlayer = null;
//    }

    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		Toast.makeText(PlayActivity.this, getString(R.string.play_tip), Toast.LENGTH_SHORT).show();
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new MusicAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	    	boolean mStartPlaying = true;
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	String mFileName;
	        	String File = "/MelodyApp/" + position + ".mp3";
	        	mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
	        	mFileName += File;
//	        	stopPlaying();
	        	onPlay(mStartPlaying, mFileName);
//                if (mStartPlaying) {
//                    setText("Stop playing");
//                } else {
//                    setText("Start playing");
//                }
	        }
	    });
	}
	
	@Override
    public void onPause() {
        super.onPause();

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
	
	public class MusicAdapter extends BaseAdapter {
	    private Context mContext;

	    public MusicAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        return mThumbIds.length;
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    // create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(325, 325));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(0, 0, 0, 0);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(mThumbIds[position]);
	        return imageView;
	    }

	}

}
