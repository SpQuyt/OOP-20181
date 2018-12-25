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
import model.Entity;
import model.Event;
import model.Location;
import model.Person;

public class EntityEvent extends GenEntity {

	

	// sinh 1 thuc the person
	public void gen1EntityEvent() throws IOException {
		Event event = new Event();
		ArrayList<String> nhan = event.listNhan("entity/Event/nhan.txt");
		ArrayList<String> mota = event.listMota("entity/Event/mota.txt");
		ArrayList<String> link = event.listLink();
		Date date = event.getRandomTime();

		int dd = 0;
		int index=get1Array(nhan);
		String nhanDon = nhan.get(index);
		for (int i = 0; i < listEntity.size(); i++) {
			if (listEntity.get(i).getNhan().equals(nhanDon))
				dd++;
		}

		String dinhdanh =nhanDon + String.format("%03d", dd);
		dinhdanh=dinhdanh.replaceAll("\\s", "");
		event.setDinhDanh(dinhdanh);
		event.setDate(date);
		event.setNhan(nhanDon);
		event.setMota(mota.get(index));
		event.setLink(link.get(get1Array(link)));

		listEntity.add(event);

	}

	// sinh nhieu thuc the person
	public void genNEntityEvent(int n) throws IOException {
		for (int i = 0; i < n; i++) {
			gen1EntityEvent();
			writeFile("entity/Event/event.txt");
		}
	}
	public model.Event get1EntityFFile() throws IOException {
		model.Event entity= new Event();
		String FileCountry = "entity/Event/event.txt";
		ArrayList<String> listCountry = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FileCountry), StandardCharsets.UTF_8);
		{

			listCountry = (ArrayList<String>) stream.collect(Collectors.toList());
			Random rd= new Random();
			String []ar=listCountry.get(rd.nextInt(listCountry.size())).split(",");	// lấy ngau nhiên  thuecj thẻ  count
			entity.setDinhDanh(ar[0]);
			entity.setNhan(ar[1]);
			entity.setMota(ar[2]);
			entity.setLink(ar[3]);
			entity.setDate(Date.valueOf(ar[4]));
		}
		return entity;

	}

	public static void main(String[] args) throws IOException {
		EntityEvent ep = new EntityEvent();
		System.out.println("run...");
		ep.genNEntityEvent(10);
		ep.showList();
	}
}
