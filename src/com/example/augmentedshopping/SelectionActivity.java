package com.example.augmentedshopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectionActivity extends Activity {
	Button Qrcode,selection;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_selection);
		super.onCreate(savedInstanceState);
		Qrcode=(Button) findViewById(R.id.qrmode);
		selection=(Button) findViewById(R.id.selectbtn);
		selection.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SelectionActivity.this,SelectShopAcivity.class);
				startActivity(intent);
				
			}
		});
		Qrcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SelectionActivity.this,ScanActivity.class);
				startActivity(intent);
				
				
			}
		});
	}

}
