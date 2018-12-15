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
		}
	}

	// in tap thuc the person
	public void showListCountry() {
		for (Country p : listCountry) {
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
			property = arr.get(chiso);
		}

		return property;
	}

	public static void main(String[] args) throws IOException {
		EntityCountry ep = new EntityCountry();
		Country c = new Country();

	}
}
