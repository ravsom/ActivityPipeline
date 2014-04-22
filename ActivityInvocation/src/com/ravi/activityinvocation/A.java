package com.ravi.activityinvocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class A extends ActionBarActivity {

	protected static final String TAG = "ActivityA";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		Button findViewById = (Button) findViewById(R.id.button1);

		findViewById.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Log.d(TAG, "Button clicked");
				startActivity(B.class, 1);
			}

		});

	}

	private void startActivity(Class<? extends Activity> cls, int requestCode) {
		Intent startBActivityIntent = new Intent(A.this, cls);
		startActivityForResult(startBActivityIntent, requestCode);
	}

	@Override
	protected void onActivityResult(int requestCode, int arg1, Intent intent) {

		Log.d(TAG, "arg0 : " + requestCode);
		Log.d(TAG, "arg1: " + arg1);
		Log.d(TAG, "intent: " + intent);

		Log.d(TAG, "intent component name: " + intent.getComponent());

		if (requestCode == 1) {
			Log.d(TAG, "B has finished --> Starting C");
			startActivity(C.class, 2);
		} else if (requestCode == 2) {
			Log.d(TAG, "C has finished --> ");
		}

		super.onActivityResult(requestCode, arg1, intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_a, container,
					false);
			return rootView;
		}
	}

}
