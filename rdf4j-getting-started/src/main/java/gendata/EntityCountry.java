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

public class EntityCountry {

	static ArrayList<Country> listCountry = new ArrayList<>();

	// sinh 1 thuc the person
	public void gen1EntityCountry() throws IOException {
		Country country = new Country();
		ArrayList<String> nhan = country.nhanCountry();
		ArrayList<String> mota = country.motaCountry();
		ArrayList<String> quocgia = country.quocgiaCountry();
		ArrayList<String> link = country.LinkCountry();
		Date date = country.getRandomTime();

		int dd = 0;
		String nhanDon = get1Array(nhan);
		for (int i = 0; i < listCountry.size(); i++) {
			if (listCountry.get(i).getNhan().equals(nhanDon))
				dd++;
		}

		String dinhdanh = "" + nhanDon + dd;
		country.setDinhDanh(dinhdanh);
		country.setDate(date);
		country.setNhan(nhanDon);
		country.setMota(get1Array(mota));
		country.setQuocGia(get1Array(quocgia));
		country.setLink(get1Array(link));

		listCountry.add(country);

	}

	// sinh nhieu thuc the person
	public void genNEntityCountry(int n) throws IOException {
		for (int i = 0; i < n; i++) {
			gen1EntityCountry();
			writeFileCountry();
		}
	}

	// in tap thuc the person
	public void showListCountry() {
		for (Country p : listCountry) {
			System.out.println(p);
		}
	}

	// ghi list country ra file
	public void writeFileCountry() throws IOException {
		FileWriter out = new FileWriter("entity/Country/country.txt");
		for (Country c : listCountry) {
			out.write(c.getDinhDanh() + "," + c.getNhan() + "," + c.getMota() + "," + c.getQuocGia() + "," + c.getLink()
					+ "," + c.getDate() + "\n");
		}
		out.close();
	}

	public Country get1EntityCountryFFile() throws IOException {
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

	public static void main(String[] args) throws IOException {
		EntityCountry ep = new EntityCountry();
		ep.genNEntityCountry(30);
		ep.showListCountry();
		

	}
}
