package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Event {
	String dinhDanh,nhan,mota,link;
	Date date;
	
	
	
	public Event(String dinhDanh, String nhan, String mota, String link, Date date) {
		super();
		this.dinhDanh = dinhDanh;
		this.nhan = nhan;
		this.mota = mota;
		this.link = link;
		this.date = date;
	}
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "Event [dinhDanh=" + dinhDanh + ", nhan=" + nhan + ", mota=" + mota + ", link=" + link + ", date=" + date
				+ "]";
	}

	public String getDinhDanh() {
		return dinhDanh;
	}
	public void setDinhDanh(String dinhDanh) {
		this.dinhDanh = dinhDanh;
	}
	public String getNhan() {
		return nhan;
	}
	public void setNhan(String nhan) {
		this.nhan = nhan;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	//doc file ghi thuoc tinh ra mang
	public ArrayList<String> nhanEvent() throws IOException {
		String FilePerson = "/home/lvh/Desktop/entity/Event/nhan.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}
	public  ArrayList<String> motaEvent() throws IOException {
		String FilePerson = "/home/lvh/Desktop/entity/Event/mota.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}
	
	public Date getRandomTime() {
		Random rd = new Random();
		long millis = System.currentTimeMillis()-System.currentTimeMillis()/(rd.nextInt(9)+1);
		Date date = new Date(millis);
		return date;
	}
	
	public  ArrayList<String> LinkEvent() throws IOException {
		String FilePerson = "/home/lvh/Desktop/entity/link.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}
	
	
}
