package com.example.augmentedshopping;

import com.example.augmentedshopping.AndroidCamera;
import com.example.augmentedshopping.ScanActivity;
import com.example.augmentedshopping.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScanActivity extends Activity{
	TextView tvStatus;
	TextView tvResult;
	static String url;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	tvStatus = (TextView) findViewById(R.id.tvStatus);
	tvResult = (TextView) findViewById(R.id.tvResult);
	
	
	Button scanBtn = (Button) findViewById(R.id.btnScan);

	//in some trigger function e.g. button press within your code you should add:
	scanBtn.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			try {
				Log.d("~~~~~~~~~~~~", "before intent");
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
				startActivityForResult(intent, 0);
				Log.d("~~~~~~~~~~~~", "after intent");
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();

			}

		}
	});

}
//In the same activity you’ll need the following to retrieve the results:
public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	Log.d("~~~~~~~~~~~~", "before if");
	if (requestCode == 0) {
		Log.d("~~~~~~~~~~~~", "after if");
		if (resultCode == RESULT_OK) {
			tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
			tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
			
			if(intent.getStringExtra("SCAN_RESULT").equals(tvResult.getText().toString().trim()))
			{
				System.out.println("********************************8"+tvResult.getText().toString().trim());
				url=tvResult.getText().toString().trim();
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
				String imageurl="http://182.18.171.243:9090/shirt.png";
				Intent i=new Intent(ScanActivity.this, AndroidCamera1.class);
				i.putExtra("url", imageurl);
				startActivity(i);
			}
		} else if (resultCode == RESULT_CANCELED) {
			tvStatus.setText("Press a button to start a scan.");
			tvResult.setText("Scan cancelled.");
		}
		else
		{
			Toast.makeText(ScanActivity.this, "asdasdas", 5000).show();
		}
	}
	else
	{
		Toast.makeText(ScanActivity.this, "xyz", 5000).show();
	}
}


}
