package com.sharon.mtbriderapp;

public class Login
{
	private String email, user, pass;

	public Login(String email, String user, String pass)
	{
		super();
		this.email = email;
		this.user = user;
		this.pass = pass;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPass()
	{
		return pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}
	
}
