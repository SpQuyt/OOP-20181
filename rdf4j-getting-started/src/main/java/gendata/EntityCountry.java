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
import model.Person;

public class EntityCountry extends GenEntity{

	static ArrayList<Country> listCountry = new ArrayList<>();
	
	// sinh 1 thuc the person
	public void gen1EntityCountry() throws IOException {
		int index=0;
		Country country = new Country();
		ArrayList<String> nhan = country.listNhan("entity/Country/nhan.txt");
		ArrayList<String> mota = country.listMota("entity/Country/mota.txt");
		ArrayList<String> quocgia = country.quocgiaCountry();
		ArrayList<String> link = country.listLink();
		Date date = country.getRandomTime();

		int dd = 0;
		index=get1Array(nhan);
		String nhanDon = nhan.get(index);
		for (int i = 0; i < listCountry.size(); i++) {
			if (listCountry.get(i).getNhan().equals(nhanDon))
				dd++;
		}

		String dinhdanh = nhanDon +String.format("%03d", dd);
		dinhdanh=dinhdanh.replaceAll("\\s", "");
		country.setDinhDanh(dinhdanh);
		country.setDate(date);
		country.setNhan(nhanDon);
		country.setMota(mota.get(index));
		country.setQuocGia(quocgia.get(index));
		country.setLink(link.get(get1Array(link)));

		listCountry.add(country);

	}

	// sinh nhieu thuc the person
	public void genNEntityCountry(int n) throws IOException {
		for (int i = 0; i < n; i++) {
			gen1EntityCountry();
			writeFile();
		}
	}

	// in tap thuc the person
	public void showList() {
		for (Country p : listCountry) {
			System.out.println(p);
		}
	}

	// ghi list country ra file
	public void writeFile() throws IOException {
		FileWriter out = new FileWriter("entity/Country/country.txt");
		for (Country c : listCountry) {
			out.write(c.getDinhDanh() + "," + c.getNhan() + "," + c.getMota() + "," + c.getQuocGia() + "," + c.getLink()
					+ "," + c.getDate() + "\n");
		}
		out.close();
	}

	public Country get1EntityFFile() throws IOException {
		Country country= new Country();
		String FileCountry = "entity/Country/country.txt";
		ArrayList<String> listCountry = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FileCountry), StandardCharsets.UTF_8);
		{

			listCountry = (ArrayList<String>) stream.collect(Collectors.toList());
			Random rd= new Random();
			String []ar=listCountry.get(rd.nextInt(listCountry.size())).split(",");	// lấy ngau nhiên  thuecj thẻ  count
			country.setDinhDanh(ar[0]);
			country.setNhan(ar[1]);
			country.setMota(ar[2]);
			country.setQuocGia(ar[3]);
			country.setLink(ar[4]);
			country.setDate(Date.valueOf(ar[5]));
		}
		return country;

	}
	public static void main(String[] args) throws IOException {
		EntityCountry ec= new EntityCountry();
		ec.genNEntityCountry(10);
		ec.showList();
	}

}
