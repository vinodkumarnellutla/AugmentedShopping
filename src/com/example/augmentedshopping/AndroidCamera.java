package com.example.augmentedshopping;

import android.app.Activity;

import java.io.IOException;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;



	

	public class AndroidCamera extends Activity implements SurfaceHolder.Callback {

		Camera camera;
		SurfaceView surfaceView;
		SurfaceHolder surfaceHolder;
		boolean previewing = false;
		String url="";
		
		
		
     
		DrawOnTop mDraw;
		private static final int MENU_ITEM_1 = Menu.FIRST + 1;
		private static final int MENU_ITEM_2 = Menu.FIRST + 2;
		private static final int MENU_ITEM_3 = Menu.FIRST + 3;
		private static final int MENU_ITEM_4 = Menu.FIRST + 4;
		private static final int MENU_ITEM_5 = Menu.FIRST + 5;
		private static final int MENU_ITEM_6 = Menu.FIRST + 6;

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			
		
		
			System.out.println(url);
			
			menu.add(Menu.NONE, MENU_ITEM_1, Menu.NONE, "Start Camera").setIcon(
					R.drawable.ic_launcher);
			menu.add(Menu.NONE, MENU_ITEM_2, Menu.NONE, "Stop Camera").setIcon(
					R.drawable.ic_launcher);
			menu.add(Menu.NONE, MENU_ITEM_3, Menu.NONE, "Front Camera").setIcon(
					R.drawable.ic_launcher);
			menu.add(Menu.NONE, MENU_ITEM_4, Menu.NONE, "Back Camera").setIcon(
					R.drawable.ic_launcher);
			menu.add(Menu.NONE, MENU_ITEM_5, Menu.NONE, "Zoom In").setIcon(
					R.drawable.ic_launcher);
			menu.add(Menu.NONE, MENU_ITEM_6, Menu.NONE, "Zoom Out").setIcon(
					R.drawable.ic_launcher);

			return super.onCreateOptionsMenu(menu);
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()) {

			case MENU_ITEM_1:

				
				if (!previewing) {
					Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
				    int cameraCount = Camera.getNumberOfCameras();
				    for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
				        Camera.getCameraInfo( camIdx, cameraInfo );
				        if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT  ) {
				            try {
				            	camera = Camera.open(camIdx );
								if (camera != null) {
									try {
										camera.setPreviewDisplay(surfaceHolder);
										camera.setDisplayOrientation(90);
										camera.startPreview();
										previewing = true;
									} catch (IOException e) {
										
										e.printStackTrace();
									}
								}
				            }
				            
				            catch (Exception e) {
								
				            	e.printStackTrace();
							}
				            }
					
				}
				}
				break;
			case MENU_ITEM_2:

				
				if (camera != null && previewing) {
					camera.stopPreview();
					camera.release();
					camera = null;

					previewing = false;
				}
			
				break;
			case MENU_ITEM_3:

				
				if (camera != null && previewing) {
					camera.stopPreview();
					camera.release();
					camera = null;

					previewing = false;
				}

				if (!previewing) {
					Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
				    int cameraCount = Camera.getNumberOfCameras();
				    for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
				        Camera.getCameraInfo( camIdx, cameraInfo );
				        if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT  ) {
				            try {
				            	camera = Camera.open(camIdx );
								if (camera != null) {
									try {
										camera.setPreviewDisplay(surfaceHolder);
										camera.setDisplayOrientation(90);
										camera.startPreview();
										previewing = true;
									} catch (IOException e) {
										
										e.printStackTrace();
									}
								}
				            }
				            
				            catch (Exception e) {
								
				            	e.printStackTrace();
							}
				            }
					
				}
				}
				break;
			case MENU_ITEM_4:

				
				if (camera != null && previewing) {
					camera.stopPreview();
					camera.release();
					camera = null;

					previewing = false;
				}

				if (!previewing) {
					Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
				    int cameraCount = Camera.getNumberOfCameras();
				    for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
				        Camera.getCameraInfo( camIdx, cameraInfo );
				        if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK  ) {
				            try {
				            	camera = Camera.open(camIdx );
								if (camera != null) {
									try {
										camera.setPreviewDisplay(surfaceHolder);
										camera.setDisplayOrientation(90);
										camera.startPreview();
										previewing = true;
									} catch (IOException e) {
										
										e.printStackTrace();
									}
								}
				            }
				            
				            catch (Exception e) {
								
				            	e.printStackTrace();
							}
				            }
					
				}
				}
				break;
			case MENU_ITEM_5:
				mDraw.ZoomIn();
				break;
			case MENU_ITEM_6:
				mDraw.ZoomOut();
				break;

				
		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}

		/** Called when the activity is first created. */
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);
			mDraw = new DrawOnTop(this);

			addContentView(mDraw, new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));

			/*Button buttonStartCameraPreview = (Button) findViewById(R.id.startcamerapreview);
			Button buttonStopCameraPreview = (Button) findViewById(R.id.stopcamerapreview);
	*/
			
			getWindow().setFormat(PixelFormat.UNKNOWN);
			surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
			surfaceHolder = surfaceView.getHolder();
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

			/*buttonStartCameraPreview
					.setOnClickListener(new Button.OnClickListener() {

						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (!previewing) {
								camera = Camera.open();
								if (camera != null) {
									try {
										camera.setPreviewDisplay(surfaceHolder);
										camera.setDisplayOrientation(90);
										camera.startPreview();
										previewing = true;
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					});

			buttonStopCameraPreview
					.setOnClickListener(new Button.OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (camera != null && previewing) {
								camera.stopPreview();
								camera.release();
								camera = null;

								previewing = false;
							}
						}
					});
	*/
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
			// Now that the size is known, set up the camera parameters and begin
			// the preview.
			/*
			 * Camera.Parameters parameters = camera.getParameters();
			 * parameters.setPreviewSize(w, h); camera.setParameters(parameters);
			 * camera.setDisplayOrientation(90); try {
			 * camera.setPreviewDisplay(holder); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 * camera.startPreview();
			 */
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub

		}
		
		
		
		
		float x = 100, y = 300;
		int a1=250,b1=250;

		class DrawOnTop extends View {
			
			public DrawOnTop(Context context) {
				super(context);
				// TODO Auto-generated constructor stub
				
			}

			
			
			public void ZoomIn()
			{
				a1=a1+10;
				b1=b1+10;
				Bitmap b=Bitmap.createScaledBitmap(bitmap, a1, b1, true);
				
				tempCanvas.drawBitmap(b, x, y, null);
				
				
				
				
				invalidate();
			}
			
			public void ZoomOut()
			{
				a1=a1-10;
				b1=b1-10;
				Bitmap b=Bitmap.createScaledBitmap(bitmap, a1, b1, true);
				
				tempCanvas.drawBitmap(b, x, y, null);
				
				invalidate();
			}
			
			
			@Override
			public boolean onTouchEvent(MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {

				}
					break;

				case MotionEvent.ACTION_MOVE: {
					x = (int) event.getX();
					y = (int) event.getY();

					invalidate();
				}

					break;
				case MotionEvent.ACTION_UP:

					x = (int) event.getX();
					y = (int) event.getY();
					System.out.println(".................." + x + "......" + y); // x=
																					// 345
																					// y=530
					invalidate();
					break;
				}
				return true;
			}
			Canvas tempCanvas;
			Bitmap bitmap = null;
			
			protected void onDraw(Canvas canvas) {
				// TODO Auto-generated method stub

				Paint paint = new Paint();
				paint.setStyle(Paint.Style.FILL);
				paint.setColor(Color.RED);
				/*Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.shirt);
	*/
				try {
					url=SelectShopAcivity.fileurl1;
					
					
					bitmap = BitmapFactory.decodeStream(new URL(url).openConnection().getInputStream());
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+url);
					System.out.println("return value");
					Bitmap b=Bitmap.createScaledBitmap(bitmap, a1, b1, true);
					canvas.drawBitmap(b, x, y, null);
					tempCanvas=canvas;
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				// canvas.drawText("Test Text", 0, 0, paint);
				super.onDraw(tempCanvas);
			}

		}

	}

	

