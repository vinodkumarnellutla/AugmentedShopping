package com.example.augmentedshopping;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;

public class Camera_ObjectDistanceActivity extends Activity{
	

	
		
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE); 

	        Preview mPreview = new Preview(this); 
	        DrawOnTop mDraw = new DrawOnTop(this); 

	        setContentView(mPreview); 
	        addContentView(mDraw, new LayoutParams 
		(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 

	      
	    }
	    class DrawOnTop extends View { 

	        public DrawOnTop(Context context) { 
	                super(context); 
	                // TODO Auto-generated constructor stub 
	        } 

	        protected void onDraw(Canvas canvas) { 
	                // TODO Auto-generated method stub 

	                Paint paint = new Paint(); 
	                paint.setStyle(Paint.Style.FILL); 
	                paint.setColor(Color.BLACK); 
	                canvas.drawText("Test Text", 10, 10, paint); 
	                

	                super.onDraw(canvas); 
	        } 

	} 

	//---------------------------------------------------------------------- 

	class Preview extends SurfaceView implements SurfaceHolder.Callback { 
	    SurfaceHolder mHolder; 
	    Camera mCamera; 

	    Preview(Context context) { 
	        super(context); 

	        // Install a SurfaceHolder.Callback so we get notified when the 
	        // underlying surface is created and destroyed. 
	        mHolder = getHolder(); 
	        mHolder.addCallback(this); 
	        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
	    } 

	    public void surfaceCreated(SurfaceHolder holder) { 
	        // The Surface has been created, acquire the camera and tell it where 
	        // to draw. 
	        mCamera = Camera.open(); 
	        /*try {
				mCamera.setPreviewDisplay(holder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/ 
	    } 

	    public void surfaceDestroyed(SurfaceHolder holder) { 
	        // Surface will be destroyed when we return, so stop the preview. 
	        // Because the CameraDevice object is not a shared resource, it's very 
	        // important to release it when the activity is paused. 
	        mCamera.stopPreview(); 
	        mCamera = null; 
	    }

		@Override
		 public void surfaceChanged(SurfaceHolder holder, int format, int 
				 w, int h) { 
				         // Now that the size is known, set up the camera parameters  and begin 
				         // the preview. 
				         Camera.Parameters parameters = mCamera.getParameters(); 
				         /*parameters.setPreviewSize(w, h); 
				         mCamera.setParameters(parameters); 
				         mCamera.setDisplayOrientation(90);*/
				         String currentversion = android.os.Build.VERSION.SDK;
				         int currentInt = android.os.Build.VERSION.SDK_INT;
				        
				         if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
				             if (currentInt != 7) {
				            	 mCamera.setDisplayOrientation(90);
				             } else {
				                
				            	 parameters.setRotation(90);

				                 /*
				                  * params.set("orientation", "portrait");
				                  * params.set("rotation",90);
				                  */
				            	 mCamera.setParameters(parameters);
				             }
				         }
				         if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				             // camera.setDisplayOrientation(0);
				             if (currentInt != 7) {
				            	 mCamera.setDisplayOrientation(0);
				             } else {
				            	 parameters.set("orientation", "landscape");
				            	 parameters.set("rotation", 90);
				            	 mCamera.setParameters(parameters);
				             }
				         }
				         try {
							mCamera.setPreviewDisplay(holder);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				         mCamera.startPreview(); 

		} 

	}
	}
	
	
	
	

