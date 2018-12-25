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

public class EntityPerson extends GenEntity{
	
	static ArrayList<Person> listEntity= new ArrayList<>();

	//sinh 1 thuc the person
	public void gen1EntityPersion() throws IOException {
		Person person = new Person();
		ArrayList<String> nhan = person.listNhan("entity/Person/nhan.txt");
		ArrayList<String> mota = person.listMota("entity/Person/mota.txt");
		ArrayList<String> chucvu = person.chucVuPerson();
		ArrayList<String> link = person.listLink();
		Date date = person.getRandomTime();
		
		int dd = 0;
		int index=get1Array(nhan);
		String nhanDon = nhan.get(index);
		for (int i = 0; i < listEntity.size(); i++) {
			if (listEntity.get(i).getNhan().equals(nhanDon))
				dd++;
		}

		String dinhdanh =nhanDon + String.format("%03d", dd);
		dinhdanh=dinhdanh.replaceAll("\\s", "");
		person.setDinhDanh(dinhdanh);
		person.setDate(date);
		person.setNhan(nhanDon);
		person.setMota(mota.get(index));
		person.setChucVu(chucvu.get(index));
		person.setLink(link.get(get1Array(link)));
		
		listEntity.add(person);
		
	}
	
	//sinh nhieu thuc the person
	public void genNEntityPerson(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityPersion();
			writeFile();
		}
	}
	
	//in tap thuc the person
	public void showList() {
		for(Person p:listEntity) {
			System.out.println(p);
		}
	}


	
	// ghi list country ra file
	public void writeFile() throws IOException {
		FileWriter out = new FileWriter("entity/Person/person.txt");
		for (Person c : listEntity) {
			out.write(c.getDinhDanh() + "," + c.getNhan() + "," + c.getMota() + "," + c.getChucVu() + "," + c.getLink()
					+ "," + c.getDate() + "\n");
		}
		out.close();
	}

	public Person get1EntityFFile() throws IOException {
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
			country.setMota(ar[2]);
			country.setChucVu(ar[3]);
			country.setLink(ar[4]);
			country.setDate(Date.valueOf(ar[5]));
		}
		return country;

	}

	public static void main(String[] args) throws IOException {
		EntityPerson ep= new EntityPerson();
		ep.genNEntityPerson(10);
		ep.showList();
		
		
	}
}
