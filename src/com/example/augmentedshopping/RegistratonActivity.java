package com.example.augmentedshopping;

import java.net.ResponseCache;

import org.json.JSONArray;

import com.example.augmentedshopping.Connect_to_server;
import com.example.augmentedshopping.RegistratonActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class RegistratonActivity extends Activity {
	Button submitbtn;
	EditText email, password, name, address, answer;
	RadioButton male, female;
	Spinner securitysp;
	private String[] q_name, q_id;
	String qid;
	String gender;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		submitbtn = (Button) findViewById(R.id.submitBtn);
		email = (EditText) findViewById(R.id.EmailEt);
		password = (EditText) findViewById(R.id.passwordEt);
		name = (EditText) findViewById(R.id.NameEt);
		address = (EditText) findViewById(R.id.AddressEt);
		answer = (EditText) findViewById(R.id.AnswerEt);
		male = (RadioButton) findViewById(R.id.MaleRBtn);
		female = (RadioButton) findViewById(R.id.FemaleRBtn);
		securitysp = (Spinner) findViewById(R.id.secsp);
		String qurl = Connect_to_server.url + "Shopservlet?connect=question";
		System.out.println(qurl + "***************************");
		String qres1 = Connect_to_server.getconnect(qurl);

		System.out
				.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
						+ qres1);
		JSONArray qarray = null;
		try {
			qarray = new JSONArray(qres1);
			q_id = new String[qarray.length() + 1];

			q_name = new String[qarray.length() + 1];

			q_id[0] = "999";
			q_name[0] = "Select";
			for (int i = 0; i < qarray.length(); i++) {
				System.out.println(qarray.getJSONObject(i));
				q_id[i + 1] = qarray.getJSONObject(i).getString("qid");
				q_name[i + 1] = qarray.getJSONObject(i).getString("qna");

			}
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
					RegistratonActivity.this,
					android.R.layout.simple_spinner_dropdown_item, q_name);
			securitysp.setAdapter(adapter1);
			securitysp.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					qid = q_id[position];
					System.out
							.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"
									+ qid);

				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		submitbtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (male.isChecked()) {
					gender = "male";

				} else {
					gender = "female";
				}

				String emailid = email.getText().toString().trim();
				String nameid = name.getText().toString().trim();
				String pwdid = password.getText().toString().trim();
				String ansid = answer.getText().toString().trim();
				String addressid = address.getText().toString().trim();

				Userform user = new Userform();
				user.setemail(emailid);
				user.setpassword(pwdid);
				user.setname(nameid);
				user.setanswer(ansid);
				user.setaddress(addressid);
				user.setgender(gender);
				if (user.isValideemail()) {
					System.out.println("id1");
					email.setError("please fill");
				} else if (user.isValidePassword())

				{
					System.out.println("id2");
					password.setError("please fill");
				} else if (user.isValidename())

				{
					System.out.println("id3");
					name.setError("please fill");
				} else if (user.isValidegender())

				{
					System.out.println("id4");
					name.setError("please fill");
				} else if (user.isValideSecurityAnswer())

				{
					System.out.println("id5");
					answer.setError("please fill");
				} else if (address.getText().toString().trim().length() == 0)

				{
					System.out.println("id5");
					address.setError("please fill");
				} else if (qid.equalsIgnoreCase("999"))

				{
					Toast.makeText(RegistratonActivity.this, "select question",
							3000).show();
				}

				else {
					String uri = "Shopservlet?connect=registration&username="
							+ user.getname() + "&password="
							+ user.getpassword() + "&address="
							+ user.getaddress() + "&email=" + user.getemail()
							+ "&answer=" + user.getanswer() + "&gender="
							+ user.getgender() + "&q_id=" + qid;
					String url = Connect_to_server.url + uri;
					System.out.println(url);
					String response = Connect_to_server.getconnect(url);
					System.out.println(response);
					if(response.trim().equals("success"))
					{
						Toast.makeText(RegistratonActivity.this,"registration done go back to login page" ,5000).show();
					}

				}
				

			}
		});
	}

}
