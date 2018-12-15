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


public class Person {
	String dinhDanh,nhan,moTa,link,chucVu;
	Date date;
	
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String dinhDanh, String nhan, String moTa, String link, String chucVu, Date date) {
		super();
		this.dinhDanh = dinhDanh;
		this.nhan = nhan;
		this.moTa = moTa;
		this.link = link;
		this.chucVu = chucVu;
		this.date = date;
	}
	
	
	
	@Override
	public String toString() {
		return "Person [dinhDanh=" + dinhDanh + ", nhan=" + nhan + ", moTa=" + moTa + ", link=" + link + ", chucVu="
				+ chucVu + ", date=" + date + "]";
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
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

	//doc file ghi thuoc tinh ra mang
	public ArrayList<String> nhanPerson() throws IOException {
		String FilePerson = "entity/Person/nhan.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}
	public  ArrayList<String> motaPerson() throws IOException {
		String FilePerson = "entity/Person/mota.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}
	public  ArrayList<String> chucVuPerson() throws IOException {
		String FilePerson = "entity/Person/chucvu.txt";
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
	
	public  ArrayList<String> LinkPerson() throws IOException {
		String FilePerson = "entity/link.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}
}
