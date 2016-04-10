package com.example.augmentedshopping;

public class Userform {
	private String email;
	private String name;

	private String password;
	
	private String answer;
	private String questionId;
	private String gender;
	private String address;
	private String contact;
	
	
	public String getemail() {
		System.out.println("email");
		return email;
		
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getpassword(){
		System.out.println("password");
		return password;
	}
	public void setpassword(String password)
	{
		this.password=password;
	}
	public String getname()
	{
		System.out.println(" name");
		return name;
		
	}
	public void setname(String name){
		this.name=name;
	}
	public String getanswer(){
		System.out.println("answer");
		return answer;
	}
	public void setanswer(String answer){
		this.answer=answer;
	}
	public String getaddress() {
		System.out.println("address");
		return address;
		
	}
	public void setaddress(String address) {
		this.address = address;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getgender() {
		return gender;
	}
	public void setgender(String gender) {
		this.gender = gender;
	}
	public boolean isValideQuestionId() {
		if(questionId.equalsIgnoreCase("") && questionId.length()==0)
		{
			return true;
		}
		
		return false;
	}
	public boolean isValidegender() {
		if(gender.equalsIgnoreCase("")&&gender.length()==0)
		{
			return true;
		}
		
		return false;
	}
	public boolean isValideSecurityAnswer() {
		if(answer.equalsIgnoreCase("")||answer.length()==0)
		{
			return true;
		}
		
		return false;
	}
	public boolean isValidename() {
		if(name.equalsIgnoreCase("")&&name.length()==0)
		{
			return true;
		}
		
		return false;
	}
	public boolean isValidePassword() {
		System.out.println(""+password);
		if(password.equalsIgnoreCase("")&&password.length()==0)
		{
			return true;
		}
		
		return false;
	}
public boolean isValideemail() {
		
		System.out.println(""+email);
		if(email.equalsIgnoreCase("")&&email.length()==0)
		{
			return true;
		}
		
		return false;
	}
public boolean isValideaddress() {
	boolean flage=true;
	if(address.equalsIgnoreCase("")&&address.length()==0||address==null)
	{
		return flage;
	}
	
	return false;
}

}
