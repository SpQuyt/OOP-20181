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

public class Relation {

	 ArrayList<model.Relation> listRelation = new ArrayList<>();

	 ArrayList<String> nhanPerson = new ArrayList<>();
	 ArrayList<String> nhanCountry = new ArrayList<>();
	 ArrayList<String> nhanEvent = new ArrayList<>();
	 ArrayList<String> nhanLocation = new ArrayList<>();
	 ArrayList<String> nhanOrganization = new ArrayList<>();

	 String relaPerWCountry[] = { "song", "den", "ow tai", "den tham", "di di lich", "roi khoi" };
	 String relaPerWOrgani[] = { "lam viec", "thuoc", "den", "thamgia", "bo viec", "doi khoi" };
	 String relaPerLocation[] = { " o lai", "lam viec", "thuoc", "den", "thamgia", " den tham", " tham du tai",
			"di du lich", "roi kkhoi" };
	 String relaPerWEvent[] = { "lam viec", "thuoc", "den", "thamgia", "doi kh", "to chuc" };

	 String relaEventWCountry[] = { "to chuc", "dien ra tai" };
	 String relaEventWLocation[] = { "to chuc", "dien ra tai" };

	public  void updateArrEntity() throws IOException {
		nhanPerson = nhan("Person");
		nhanOrganization = nhan("Organization");
		nhanCountry = nhan("Country");
		nhanEvent = nhan("Event");
		nhanLocation = nhan("Location");
	}
	
	public  void genNRelation(int n) throws IOException {
		for(int i=0;i<n;i++){
			gen1RDRelation();
		}
	}

	// sinh 1 thuc the person
	public  void gen1RDRelation() throws IOException {
		updateArrEntity();
		Random rd = new Random();
		int k = rd.nextInt(6) + 1;
		switch (k) {

		// quan he giua person va country
		case 1:
			gen1Rela(nhanPerson, nhanCountry, relaPerWCountry);
			break;

		// quan he giua person va country
		case 2:
			gen1Rela(nhanPerson, nhanOrganization, relaPerWOrgani);
			break;
		// quan he giua person va country
		case 3:
			gen1Rela(nhanPerson, nhanEvent, relaPerWEvent);
			break;
		// quan he giua person va country
		case 4:
			gen1Rela(nhanPerson, nhanLocation, relaEventWLocation);
			break;

		// quan he giua person va country
		case 5:
			gen1Rela(nhanEvent, nhanCountry, relaEventWCountry);
			break;
		case 6:
			gen1Rela(nhanEvent, nhanLocation, relaEventWLocation);
			break;

		}
	}

	public  void showListRelation() {
		for (model.Relation r : listRelation) {
			System.out.println(r);
		}
	}

	// lay ngau nhieu 1 phan tu tu mang
	public  String get1Array(ArrayList<String> arr) {
		Random rd = new Random();
		String name = null;
		int chiso;
		if (arr.size() == 0)
			System.out.println("file rong");
		else {
			chiso = rd.nextInt(arr.size());
			name = arr.get(chiso);
		}

		return name;
	}

	// lay ngau nhien 1 quan he tuw mang
	public String get1Relation(String[] arr) {
		int k = arr.length;
		Random rd = new Random();
		String rela = arr[rd.nextInt(k)];
		return rela;
	}

	// sinh quan he hona chinh
	public  model.Relation gen1Rela(ArrayList<String> al1, ArrayList<String> al2, String[] arrRela) {

		model.Relation relation = new model.Relation();

		relation.setEntity1(get1Array(al1));
		relation.setEntity2(get1Array(al2));
		relation.setRela(get1Relation(arrRela));

		listRelation.add(relation);

		return relation;
	}

	// doc file ghi thuoc tinh ra mang
	public  ArrayList<String> nhan(String name) throws IOException {
		String FilePerson = "/home/lvh/Desktop/entity/" + name + "/nhan.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}

	public  void main(String[] args) throws IOException {

		updateArrEntity();
//		for (String s : nhanPerson) {
//			System.out.println(s);
//		}
//		for (String s : nhanCountry) {
//			System.out.println(s);
//		}
//		for (String s : nhanEvent) {
//			System.out.println(s);
//		}
//		for (String s : nhanLocation) {
//			System.out.println(s);
//		}
//		for (String s : nhanOrganization) {
//			System.out.println(s);
//		}
		genNRelation(100);
		showListRelation();
	}
}
