<<<<<<< HEAD
package com.IDSoft.melodyapp;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaRecorder;
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

public class EditActivity extends Activity{
	
	private static final String LOG_TAG = "RecordSong";
    private MediaRecorder mRecorder = null;
    
 // references to our images
    private Integer[] mThumbIds = {
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default,
            R.drawable.ic_edit_default, R.drawable.ic_edit_default
    };
    
    private Integer[] mThumbIds2 = {
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2,
            R.drawable.ic_edit_default2, R.drawable.ic_edit_default2
    };

    private void onRecord(boolean start, String mFile) {
        if (start) {
            startRecording(mFile);
        } else {
            stopRecording();
        }
    }

    private void startRecording(String mFile) {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFile);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        Toast.makeText(EditActivity.this, getString(R.string.edit_tip), Toast.LENGTH_SHORT).show();
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new MusicAdapter(this));
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	    	boolean mStartRecording = true;
	    	//----------------
	    	
	    	//----------------
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	String mFileName;
	        	String File = "/MelodyApp/" + position + ".mp3";
	        	mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
	        	mFileName += File;
	        	onRecord(mStartRecording, mFileName);
//	        	if (mStartRecording) {
//	        		mThumbIds[position] = R.drawable.ic_edit_hover;
//	        	} else {
//	        		mThumbIds[position] = R.drawable.ic_edit_default;
//	        	}
	        	mStartRecording = !mStartRecording;
	        }
	    });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
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
=======
package com.IDSoft.melodyapp;

import android.app.Activity;
import android.os.Bundle;

public class EditActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
	}

}
>>>>>>> da279aa370d87a8cc37eaeaeb6d712cce08c7e69
