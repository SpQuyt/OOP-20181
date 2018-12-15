package model;

import java.sql.Date;
import java.util.Random;

public class Time {
	Date date;
	
	public Date getRandomTime() {
		Random rd = new Random();
		long millis = System.currentTimeMillis()-System.currentTimeMillis()/(rd.nextInt(10)+1);
		Date date = new Date(millis);
		return date;
	}
	

	public Time() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return  ""+ date ;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static void main(String[] args) {
		Time t = new Time();
		for(int i=0;i<80;i++) {
		t.getRandomTime();}
	}

}
