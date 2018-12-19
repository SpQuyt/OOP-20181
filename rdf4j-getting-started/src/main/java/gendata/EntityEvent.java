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
import model.Event;
import model.Location;
import model.Person;

public class EntityEvent {

	static ArrayList<Event> listEvent = new ArrayList<>();

	// sinh 1 thuc the person
	public void gen1EntityEvent() throws IOException {
		Event event = new Event();
		ArrayList<String> nhan = event.nhanEvent();
		ArrayList<String> mota = event.motaEvent();
		ArrayList<String> link = event.LinkEvent();
		Date date = event.getRandomTime();

		int dd = 0;
		String nhanDon = get1Array(nhan);
		for (int i = 0; i < listEvent.size(); i++) {
			if (listEvent.get(i).getNhan().equals(nhanDon))
				dd++;
		}

		String dinhdanh = "" + nhanDon + dd;
		event.setDinhDanh(dinhdanh);
		event.setDate(date);
		event.setNhan(nhanDon);
		event.setMota(get1Array(mota));
		event.setLink(get1Array(link));

		listEvent.add(event);

	}

	// sinh nhieu thuc the person
	public void genNEntityEvent(int n) throws IOException {
		for (int i = 0; i < n; i++) {
			gen1EntityEvent();
			writeFileEvent();
		}
	}

	// in tap thuc the person
	public void showListEvent() {
		for (Event p : listEvent) {
			System.out.println(p);
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
			property = arr.get(chiso);
		}

		return property;
	}

	public void writeFileEvent() throws IOException {
		FileWriter out = new FileWriter("entity/Event/event.txt");
		for (Event c : listEvent) {
			out.write(c.getDinhDanh() + "," + c.getNhan() + "," + c.getMota() + "," + c.getLink() + "," + c.getDate()
					+ "\n");
		}
		out.close();
	}

	public Event get1EntityEventFFile() throws IOException {
		Event country = new Event();
		String FileCountry = "entity/Event/event.txt";
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
		EntityEvent ep = new EntityEvent();
		System.out.println(ep.get1EntityEventFFile());
	}
}
