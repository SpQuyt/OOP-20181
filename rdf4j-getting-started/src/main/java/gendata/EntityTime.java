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

import model.Country;
import model.Organization;
import model.Person;
import model.Time;

public class EntityTime {
	
	ArrayList<Time>listTime= new ArrayList<>();
	
	public void gen1EntityTime() {
		Time time= new Time();
		time.setDate(time.getRandomTime());
		listTime.add(time);
	}
	
	public void genNEntityTime(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityTime();
			writeFileTime();
		}
	}
	public void showListTime() {
		for(Time t:listTime) {
			System.out.println(t);
		}
	}
	
	// ghi list country ra file
	public void writeFileTime() throws IOException {
		FileWriter out = new FileWriter("entity/Time/time.txt");
		for (Time c : listTime) {
			out.write(c.getDate()+ "\n");
		}
		out.close();
	}

	public Time get1EntityTimeFFile() throws IOException {
		Time country= new Time();
		String FileCountry = "entity/Time/time.txt";
		ArrayList<String> listCountry = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FileCountry), StandardCharsets.UTF_8);
		{

			listCountry = (ArrayList<String>) stream.collect(Collectors.toList());
			Random rd= new Random();
			String []ar=listCountry.get(rd.nextInt(listCountry.size())).split(",");	// lấy ngau nhiên  thuecj thẻ  count
			country.setDate(Date.valueOf(ar[0]));
			
		}
		return country;

	}
	
	public static void main(String[] args) throws IOException {
		EntityTime time= new EntityTime();
		System.out.println(time.get1EntityTimeFFile());
		
	}
	
}
