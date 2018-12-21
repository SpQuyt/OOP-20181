package gendata;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Event;
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
		
		int dd = 0;
		int index=get1Array(nhan);
		String nhanDon = nhan.get(index);
		for (int i = 0; i < listLocation.size(); i++) {
			if (listLocation.get(i).getNhan().equals(nhanDon))
				dd++;
		}

		String dinhdanh =nhanDon + String.format("%03d", dd);
		dinhdanh=dinhdanh.replaceAll("\\s", "");
		location.setDinhDanh(dinhdanh);
		location.setDate(date);
		location.setNhan(nhanDon);
		location.setMota(mota.get(index));
		location.setLink(link.get(get1Array(link)));
		
		listLocation.add(location);
		
	}
	
	//sinh nhieu thuc the person
	public void genNEntityLocation(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityLocation();
			writeFileLocation();
		}
	}
	
	//in tap thuc the person
	public void showListLocation() {
		for(Location p:listLocation) {
			System.out.println(p);
		}
	}

	// lay ngau nhieu 1 phan tu tu mang
	public int get1Array(ArrayList<String> arr) {
		Random rd = new Random();
		
		int chiso=0;
		if (arr.size() == 0)
			System.out.println("file rong");
		else {
			chiso = rd.nextInt(arr.size());
			
		}
		
		return chiso;
	}
	
	public void writeFileLocation() throws IOException {
		FileWriter out = new FileWriter("entity/Location/location.txt");
		for (Location c : listLocation) {
			out.write(c.getDinhDanh() + "," + c.getNhan() + "," + c.getMota() + "," + c.getLink() + "," + c.getDate()
					+ "\n");
		}
		out.close();
	}

	public Location get1EntityLocationFFile() throws IOException {
		Location country = new Location();
		String FileCountry = "entity/Location/location.txt";
		ArrayList<String> listCountry = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FileCountry), StandardCharsets.UTF_8);
		{

			listCountry = (ArrayList<String>) stream.collect(Collectors.toList());
			Random rd = new Random();
			String[] ar = listCountry.get(rd.nextInt(listCountry.size())).split(","); // lấy ngau nhiên thuecj thẻ count
			country.setDinhDanh(ar[0]);
			country.setNhan(ar[1]);
			country.setMota(ar[2]);
			country.setLink(ar[3]);
			country.setDate(Date.valueOf(ar[4]));
		}
		return country;

	}

	public static void main(String[] args) throws IOException {
		EntityLocation ep= new EntityLocation();
		ep.genNEntityLocation(10);
		ep.showListLocation();
	
		
	}
}