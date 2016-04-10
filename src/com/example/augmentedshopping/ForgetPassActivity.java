package com.example.augmentedshopping;

import org.json.JSONArray;
import org.json.JSONException;

import com.example.augmentedshopping.Connect_to_server;
import com.example.augmentedshopping.ForgetPassActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassActivity extends Activity {
	String[] questionAns;
	String userName;
	String question,userAns;
	String password;
	
	EditText QuestionEt,AnswerEt;
	Button ForgetBtn;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgetpassword);
		QuestionEt=(EditText) findViewById(R.id.editText1);
		AnswerEt=(EditText) findViewById(R.id.editText2);
		ForgetBtn=(Button) findViewById(R.id.button1);
		 Intent intent=new Intent();
		    intent=getIntent();
		    userName=intent.getStringExtra("userName");
		    System.out.println("::::::::::::::::"+userName);
		    String url=Connect_to_server.url+"Shopservlet?connect=forgetpassword&username="+userName;
		    System.out.println(url);
		    String response=Connect_to_server.getconnect(url);
		    System.out.println(response);
		    JSONArray array=null;
		    try {
				 array=new JSONArray(response);
				
				questionAns=new String[array.length()];
				
				for (int i = 0; i < array.length(); i++) {
					
					question=array.getJSONObject(i).getString("question");
					System.out.println(""+question);
					userAns=array.getJSONObject(i).getString("answer");
					System.out.println(""+userAns);
					password=array.getJSONObject(i).getString("password");
					System.out.println(""+password);
					
				}
				
				QuestionEt.setText(question);
				
				
				
				
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ForgetBtn.setOnClickListener(new OnClickListener() {
				
				//@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					if(AnswerEt.getText().toString().equals(""))
					{
						AnswerEt.setError("Please enter your answer");
					}else if(userAns.equals(AnswerEt.getText().toString()))
					{
					 Toast.makeText(ForgetPassActivity.this,"your password is"+password , Toast.LENGTH_LONG).show();
					}else
					{
						Toast.makeText(ForgetPassActivity.this, "ans does not match", Toast.LENGTH_LONG).show();
					}
					
				}
		    });
		    
		    
	}

}
