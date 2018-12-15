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
	
	public void genNEntityTime(int n) {
		for(int i=0;i<n;i++) {
			gen1EntityTime();
		}
	}
	public void showListTime() {
		for(Time t:listTime) {
			System.out.println(t);
		}
	}
	
	public static void main(String[] args) {
		EntityTime time= new EntityTime();
		time.genNEntityTime(400);
		time.showListTime();
		
	}
}
