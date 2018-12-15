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

public class Country {
	String dinhDanh,nhan,mota,link,quocGia;
	Date date;
	
	public Country(String dinhDanh, String nhan, String mota, String link, String quocGia, Date date) {
		super();
		this.dinhDanh = dinhDanh;
		this.nhan = nhan;
		this.mota = mota;
		this.link = link;
		this.quocGia = quocGia;
		this.date = date;
	}
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Country [dinhDanh=" + dinhDanh + ", nhan=" + nhan + ", mota=" + mota + ", link=" + link + ", quocGia="
				+ quocGia + ", date=" + date + "]";
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
	public String getQuocGia() {
		return quocGia;
	}
	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	//doc file ghi thuoc tinh ra mang
		public ArrayList<String> nhanCountry() throws IOException {
			String FilePerson = "/home/lvh/Desktop/entity/Country/nhan.txt";
			ArrayList<String> listPerson = new ArrayList<>();
			Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
			{

				listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
				return listPerson;
			}
		}
		public  ArrayList<String> motaCountry() throws IOException {
			String FilePerson = "/home/lvh/Desktop/entity/Country/mota.txt";
			ArrayList<String> listPerson = new ArrayList<>();
			Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
			{

				listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
				return listPerson;
			}
		}
		public  ArrayList<String> quocgiaCountry() throws IOException {
			String FilePerson = "/home/lvh/Desktop/entity/Country/quocgia.txt";
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
		
		public  ArrayList<String> LinkCountry() throws IOException {
			String FilePerson = "/home/lvh/Desktop/entity/link.txt";
			ArrayList<String> listPerson = new ArrayList<>();
			Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
			{

				listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
				return listPerson;
			}
		}
	
	
}
