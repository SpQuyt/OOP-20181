package model;

import java.io.IOException;


public class Event extends Entity {
	
	public static void main(String[] args) throws IOException {
		Event e= new Event();
		for(String s:e.listMota("entity/Event/mota.txt")){
				System.out.println(s.toString());}
	}
	

	@Override
	public String toString() {
		return "Event [dinhDanh=" + dinhDanh + ", nhan=" + nhan + ", mota=" + mota + ", link=" + link + ", date=" + date
				+ "]";
	}
	
}
