package com.example.augmentedshopping;

//import com.pro.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class DisplayIntent extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		ListView list = (ListView) findViewById(R.id.productdisp);
		
		
		
	}

}
