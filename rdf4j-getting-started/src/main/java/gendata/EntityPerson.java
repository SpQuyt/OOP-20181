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

import model.Organization;
import model.Person;

public class EntityPerson {
	
	static ArrayList<Person> listPerson= new ArrayList<>();

	//sinh 1 thuc the person
	public void gen1EntityPersion() throws IOException {
		Person person = new Person();
		ArrayList<String> nhan = person.nhanPerson();
		ArrayList<String> mota = person.motaPerson();
		ArrayList<String> chucvu = person.chucVuPerson();
		ArrayList<String> link = person.LinkPerson();
		Date date = person.getRandomTime();
		
		int dd = 0;
		int index=get1Array(nhan);
		String nhanDon = nhan.get(index);
		for (int i = 0; i < listPerson.size(); i++) {
			if (listPerson.get(i).getNhan().equals(nhanDon))
				dd++;
		}

		String dinhdanh =nhanDon + String.format("%03d", dd);
		dinhdanh=dinhdanh.replaceAll("\\s", "");
		person.setDinhDanh(dinhdanh);
		person.setDate(date);
		person.setNhan(nhanDon);
		person.setMoTa(mota.get(index));
		person.setChucVu(chucvu.get(index));
		person.setLink(link.get(get1Array(link)));
		
		listPerson.add(person);
		
	}
	
	//sinh nhieu thuc the person
	public void genNEntityPerson(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityPersion();
			writeFilePerson();
		}
	}
	
	//in tap thuc the person
	public void showListPerson() {
		for(Person p:listPerson) {
			System.out.println(p);
		}
	}

	// lay ngau nhieu 1 phan tu tu mang
	public int get1Array(ArrayList<String> arr) {
		Random rd = new Random();
		
		int chiso = 0;
		if (arr.size() == 0)
			System.out.println("file rong");
		else {
			chiso = rd.nextInt(arr.size());
			
		}
		
		return chiso;
	}
	
	// ghi list country ra file
	public void writeFilePerson() throws IOException {
		FileWriter out = new FileWriter("entity/Person/person.txt");
		for (Person c : listPerson) {
			out.write(c.getDinhDanh() + "," + c.getNhan() + "," + c.getMoTa() + "," + c.getChucVu() + "," + c.getLink()
					+ "," + c.getDate() + "\n");
		}
		out.close();
	}

	public Person get1EntityPersonFFile() throws IOException {
		Person country= new Person();
		String FileCountry = "entity/Person/person.txt";
		ArrayList<String> listCountry = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FileCountry), StandardCharsets.UTF_8);
		{

			listCountry = (ArrayList<String>) stream.collect(Collectors.toList());
			Random rd= new Random();
			String []ar=listCountry.get(rd.nextInt(listCountry.size())).split(",");	// lấy ngau nhiên  thuecj thẻ  count
			country.setDinhDanh(ar[0]);
			country.setNhan(ar[1]);
			country.setMoTa(ar[2]);
			country.setChucVu(ar[3]);
			country.setLink(ar[4]);
			country.setDate(Date.valueOf(ar[5]));
		}
		return country;

	}

	public static void main(String[] args) throws IOException {
		EntityPerson ep= new EntityPerson();
		ep.genNEntityPerson(10);
		ep.showListPerson();
		
		
	}
}