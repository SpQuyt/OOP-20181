package gendata;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Location;
import model.Person;

public class EntityLocation {
	
	static ArrayList<Location> listLocation= new ArrayList<>();

	//sinh 1 thuc the person
	public void gen1EntityLocation() throws IOException {
		Location location= new Location();
		ArrayList<String> nhan = location.nhanLocation();
		ArrayList<String> mota = location.motaLocation();
		ArrayList<String> link =location.LinkLocation();
		Date date = location.getRandomTime();
		
		int dd=0;
		String nhanDon=get1Array(nhan);
		for(int i=0;i<listLocation.size();i++) {
			if(listLocation.get(i).getNhan().equals(nhanDon))
				dd++;
		}
		
		String dinhdanh=""+nhanDon+dd;
		location.setDinhDanh(dinhdanh);
		location.setDate(date);
		location.setNhan(nhanDon);
		location.setMota(get1Array(mota));
		location.setLink(get1Array(link));
		
		listLocation.add(location);
		
	}
	
	//sinh nhieu thuc the person
	public void genNEntityLocation(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityLocation();
		}
	}
	
	//in tap thuc the person
	public void showListLocation() {
		for(Location p:listLocation) {
			System.out.println(p);
		}
	}

	// doc file ghi ra mang
	public static ArrayList<String> readFilePerson() throws IOException {
		String FilePerson = "entity/person";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}

	// lay ngau nhieu 1 phan tu tu mang
	public String get1Array(ArrayList<String> arr) {
		Random rd = new Random();
		String property = null;
		int chiso;
		if (arr.size() == 0)
			System.out.println("file rong");
		else {
			chiso = rd.nextInt(arr.size());
			property=arr.get(chiso);
		}
		
		return property;
	}

	public static void main(String[] args) throws IOException {
		EntityLocation ep= new EntityLocation();
		Person person = new Person();
		ArrayList<String> nhan = person.nhanPerson();
	
		
	}
}
