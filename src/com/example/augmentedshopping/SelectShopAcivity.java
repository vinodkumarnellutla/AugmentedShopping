package com.example.augmentedshopping;

import org.json.JSONArray;

import android.R.string;
import android.app.Activity;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class SelectShopAcivity extends Activity {
	Spinner categorysp, brandsp, subcatsp, productsp;
	Button gobtn;
	ImageButton image;
	Button onlinebtn;
	String catid = null, subcatid, brandid;
	private String[] cat_name, cat_id, subcat_id, subcat_name, brand_id,
			brand_name, product_id, image_url, product_name,view_url;
	static int pos, pos1, position2;
	static String fileurl, fileurl1;

	int selctdos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		System.out.println("onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectiveshop);
		System.out.println("Content View");
		categorysp = (Spinner) findViewById(R.id.categorysp);
		brandsp = (Spinner) findViewById(R.id.brandsp);
		subcatsp = (Spinner) findViewById(R.id.subcatsp);
		//productsp = (Spinner) findViewById(R.id.productsp);
		gobtn = (Button) findViewById(R.id.searchbtn);
		image = (ImageButton) findViewById(R.id.imageButton1);
		onlinebtn = (Button) findViewById(R.id.viewbtn);
		System.out.println("****Category***");
		String caturl = Connect_to_server.url + "Shopservlet?connect=category";
		System.out.println(caturl + "***************************");
		String res = Connect_to_server.getconnect(caturl);
		System.out.println(res);
		JSONArray array = null;

		try {
			array = new JSONArray(res);
			cat_id = new String[array.length() + 1];

			cat_name = new String[array.length() + 1];

			cat_id[0] = "999";
			cat_name[0] = "Select";
			for (int i = 0; i < array.length(); i++) {
				System.out.println(array.getJSONObject(i));
				cat_id[i + 1] = array.getJSONObject(i).getString("catid");
				cat_name[i + 1] = array.getJSONObject(i).getString("catna");

			}
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
					SelectShopAcivity.this,
					android.R.layout.simple_spinner_dropdown_item, cat_name);
			categorysp.setAdapter(adapter1);
			categorysp.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					catid = cat_id[position];
					pos = position;
					System.out
							.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC:::"
									+ catid);
					System.out
							.println("::::::::::::::::::::::::::::::::::::::::: "
									+ pos);
					String sub_caturl = Connect_to_server.url
							+ "Shopservlet?connect=subcategory&catid=" + pos;

					System.out.println(sub_caturl
							+ "***************************");
					String res1 = Connect_to_server.getconnect(sub_caturl);
					System.out.println(res1);
					JSONArray array1 = null;
					try {
						array1 = new JSONArray(res1);
						subcat_id = new String[array1.length() + 1];

						subcat_name = new String[array1.length() + 1];

						subcat_id[0] = "999";
						subcat_name[0] = "Select";
						for (int i = 0; i < array1.length(); i++) {
							System.out.println(array1.getJSONObject(i));
							subcat_id[i + 1] = array1.getJSONObject(i)
									.getString("subcatid");
							subcat_name[i + 1] = array1.getJSONObject(i)
									.getString("subcatna");

						}
						ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
								SelectShopAcivity.this,
								android.R.layout.simple_spinner_dropdown_item,
								subcat_name);
						subcatsp.setAdapter(adapter2);
						subcatsp.setOnItemSelectedListener(new OnItemSelectedListener() {

							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int position, long arg3) {
								// TODO Auto-generated method stub
								subcatid = subcat_id[position];

								System.out
										.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"
												+ catid);
								pos1 = position;

							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub

							}
						});

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		System.out.println("OutSide::::" + pos);
		String brandurl = Connect_to_server.url + "Shopservlet?connect=brandid";

		System.out.println(brandurl + "***************************");

		String res2 = Connect_to_server.getconnect(brandurl);
		System.out.println(res2);

		JSONArray array2 = null;
		try {
			array2 = new JSONArray(res2);
			brand_id = new String[array2.length() + 1];

			brand_name = new String[array2.length() + 1];

			brand_id[0] = "999";
			brand_name[0] = "Select";
			for (int i = 0; i < array2.length(); i++) {
				System.out.println(array2.getJSONObject(i));
				brand_id[i + 1] = array2.getJSONObject(i).getString("brandid");
				brand_name[i + 1] = array2.getJSONObject(i)
						.getString("brandna");

			}
			ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
					SelectShopAcivity.this,
					android.R.layout.simple_spinner_dropdown_item, brand_name);
			brandsp.setAdapter(adapter3);
			brandsp.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					brandid = brand_id[position];
					position2 = position;

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		gobtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String searchurl = Connect_to_server.url
						+ "Shopservlet?connect=select&subcatid=" + pos1
						+ "&brandid=" + position2;
				System.out.println(searchurl + "***************************");
				String res2 = Connect_to_server.getconnect(searchurl);
				System.out.println(res2);
				JSONArray array2 = null;
				try {
					array2 = new JSONArray(res2);
					product_id = new String[array2.length()];

					image_url = new String[array2.length()];
					product_name = new String[array2.length()];
					view_url=new String[array2.length()];

					for (int i = 0; i < array2.length(); i++) {
						System.out.println(array2.getJSONObject(i));
						product_id[i] = array2.getJSONObject(i).getString(
								"productid");
						image_url[i] = array2.getJSONObject(i).getString(
								"imageurl");
						product_name[i] = array2.getJSONObject(i).getString(
								"productname");
						view_url[i]=array2.getJSONObject(i).getString("viewurl").trim();
						selctdos=i;
						System.out.println("printing");
						fileurl = image_url[i];
						 fileurl1 = Connect_to_server.url
								+ "images/" + fileurl;
						ExifInterface exifMedia = null;
						try {
							/*
							 * String exifOrint =
							 * exifMedia.getAttribute(ExifInterface
							 * .TAG_ORIENTATION); int exifOrientation =
							 * Integer.parseInt(exifOrint);
							 * System.out.println("Orientation Tag is:"
							 * +exifOrientation);
							 * 
							 * BitmapFactory.Options mOptions=new
							 * BitmapFactory.Options(); mOptions.inSampleSize=2;
							 * Bitmap imgBitmap =
							 * BitmapFactory.decodeFile(fileurl,mOptions);
							 * //Runtime.getRuntime().gc();
							 */

							URL newurl = new URL(fileurl1);
							Bitmap bitmap = BitmapFactory.decodeStream(newurl
									.openConnection().getInputStream());
							// bitmap = getResizedBitmapImage(imgBitmap, 200,
							// 200, exifOrientation);
							image.setImageBitmap(bitmap);

							if (newurl != null) {
								Toast.makeText(SelectShopAcivity.this,
										"image found", Toast.LENGTH_LONG)
										.show();
							} else {
								Toast.makeText(SelectShopAcivity.this,
										"image doesnot exsist",
										Toast.LENGTH_LONG).show();
							}
							image.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									Intent intent = new Intent(
											SelectShopAcivity.this,
											AndroidCamera.class);
									intent.putExtra("url", fileurl1);
									System.out.println(fileurl1);

									startActivity(intent);
									
								}
							});
							
							
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}

			}
		});
		onlinebtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW, 
					     Uri.parse(view_url[selctdos]));
					startActivity(intent);
		 
				
			}
		});
	}

	/*
	 * protected Bitmap getResizedBitmapImage(Bitmap imgBitmap, int i, int j,
	 * int exifOrientation) { // TODO Auto-generated method stub return null; }
	 */

}
