package com.example.augmentedshopping;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import android.app.Activity;


public class CustomizedAdapter extends Activity {
	ArrayList<String> view, image;
	Context context;
	ImageButton imagebtn;
	Button viewbtn;
	ListView list;
	Toast toast;
	
	public CustomizedAdapter(Context context, int textViewResourceId,
			ArrayList<String> view, ArrayList<String> image) {
		
		super();
		this.view = view;
		System.out.println(view.size());
		this.image = image;
		this.context = context;
	}

	public int getCount() {
		return view.size();
	}

	public String getItem(int position) {
		// TODO Auto-generated method stub
		return view.get(position);
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.display_product, null);
		imagebtn=(ImageButton) v.findViewById(R.id.imageButton);
		 viewbtn=(Button) v.findViewById(R.id.viewbtn);
		 return v;
	}
}
