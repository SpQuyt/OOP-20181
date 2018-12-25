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

public class Organization extends Entity{
	String truso;
	
	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Organization(String dinhDanh, String nhan, String mota, String link, String truso, Date date) {
		super();
		this.dinhDanh = dinhDanh;
		this.nhan = nhan;
		this.mota = mota;
		this.link = link;
		this.truso = truso;
		this.date = date;
	}
	
	
	public String getTruso() {
		return truso;
	}
	public void setTruso(String truso) {
		this.truso = truso;
	}
	@Override
	public String toString() {
		return "Organization [dinhDanh=" + dinhDanh + ", nhan=" + nhan + ", mota=" + mota + ", link=" + link
				+ ", truso=" + truso + ", date=" + date + "]";
	}
	

	public  ArrayList<String> trusoOrgan() throws IOException {
		String FilePerson = "entity/Organization/truso.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}
	
}
