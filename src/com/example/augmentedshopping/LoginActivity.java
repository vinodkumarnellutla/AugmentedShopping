package com.example.augmentedshopping;




import android.os.Bundle;
import com.example.augmentedshopping.Connect_to_server;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	Button submitbtn;
	EditText nameEt;
	EditText passwordEt;
	TextView forgetpassTv,notregisterTv;
	String uname;
	String password;
	static String Userid;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		 nameEt=(EditText) findViewById(R.id.editText2);
		 passwordEt=(EditText) findViewById(R.id.editText1);
	     submitbtn=(Button) findViewById(R.id.button1);
	     forgetpassTv=(TextView) findViewById(R.id.forgetid);
	     notregisterTv=(TextView) findViewById(R.id.registerid);
	     uname=nameEt.getText().toString();
	     password=passwordEt.getText().toString();
	     
	     submitbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
               Userform user=new Userform();
               user.setemail(nameEt.getText().toString().trim());
               user.setpassword(passwordEt.getText().toString().trim());
               if (user.isValideemail()) {
					System.out.println("id1");
					nameEt.setError("please fill");
				} else if (user.isValidePassword()) {
					System.out.println("id2");
					passwordEt.setError("please fill");
				}
				else{
					String url=Connect_to_server.url+"Shopservlet?connect=login&username="+user.getemail()+"&password="+user.getpassword();
					System.out.println(""+url);
					Toast.makeText(LoginActivity.this, url, 5000).show();
					String response = Connect_to_server.getconnect(url);
					System.out.println(response);
					if (response.trim().equalsIgnoreCase("success")) {
						
						UserInfo.userid = nameEt.getText().toString();
						Toast.makeText(LoginActivity.this,
								"validation done!!!!", Toast.LENGTH_LONG)
								.show();
						Intent intent = new Intent(LoginActivity.this,
								SelectionActivity.class);

						intent.putExtra("UserName_info",nameEt.getText()
								.toString());
						startActivity(intent);
						Userid = nameEt.getText().toString();
					} else {
						Toast.makeText(LoginActivity.this,
								"invalid credentials !!!", Toast.LENGTH_LONG)
								.show();
					}
					
							
				}
				
				
			}
		});
	     
	     
	     notregisterTv.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LoginActivity.this,RegistratonActivity.class);
				startActivity(intent);
				
			}
		});
	    
		forgetpassTv.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(nameEt.getText().toString().trim().length()==0)
				{
					Toast.makeText(LoginActivity.this,
							"enter email to know the password", Toast.LENGTH_LONG)
							.show();
				
				}
				else{
					Intent intent=new Intent(LoginActivity.this,ForgetPassActivity.class);
					intent.putExtra("userName", nameEt.getText().toString().trim());
					
					startActivity(intent);
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
