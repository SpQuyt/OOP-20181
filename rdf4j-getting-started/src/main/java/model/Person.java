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


public class Person extends Entity {
	String chucVu;
	
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String dinhDanh, String nhan, String moTa, String link, String chucVu, Date date) {
		super();
		this.dinhDanh = dinhDanh;
		this.nhan = nhan;
		this.mota = moTa;
		this.link = link;
		this.chucVu = chucVu;
		this.date = date;
	}
	
	
	
	public String getChucVu() {
		return chucVu;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	@Override
	public String toString() {
		return "Person [dinhDanh=" + dinhDanh + ", nhan=" + nhan + ", moTa=" + mota + ", link=" + link + ", chucVu="
				+ chucVu + ", date=" + date + "]";
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
	
}
