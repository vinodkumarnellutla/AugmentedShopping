package com.example.augmentedshopping;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.StrictMode;


public class Connect_to_server {
	static String url = "http://153.91.195.211:8081/Shoppingserver/";
	static String response;

	public static String getconnect(String url) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	  
		InputStream is;
		StringBuilder sb = new StringBuilder("");
		try {
			URL urlstring = new URL(url.trim());
			HttpURLConnection connection = (HttpURLConnection) urlstring
					.openConnection();
			is = connection.getInputStream();
			int i = 0;

			while ((i = is.read()) != -1) {
				response = sb.append((char) i).toString();

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}

	
	
}
