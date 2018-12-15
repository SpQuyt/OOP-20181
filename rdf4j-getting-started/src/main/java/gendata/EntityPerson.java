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
		
		int dd=0;
		String nhanDon=get1Array(nhan);
		for(int i=0;i<listPerson.size();i++) {
			if(listPerson.get(i).getNhan().equals(nhanDon))
				dd++;
		}
		
		String dinhdanh=""+nhanDon+dd;
		person.setDinhDanh(dinhdanh);
		person.setDate(date);
		person.setNhan(nhanDon);
		person.setMoTa(get1Array(mota));
		person.setChucVu(get1Array(chucvu));
		person.setLink(get1Array(link));
		
		listPerson.add(person);
		
	}
	
	//sinh nhieu thuc the person
	public void genNEntityPerson(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityPersion();
		}
	}
	
	//in tap thuc the person
	public void showListPerson() {
		for(Person p:listPerson) {
			System.out.println(p);
		}
	}

	// doc file ghi ra mang
	public static ArrayList<String> readFilePerson() throws IOException {
		String FilePerson = "/home/lvh/Desktop/entity/person";
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
			property=arr.get(chiso);
		}
		
		return property;
	}

	public static void main(String[] args) throws IOException {
		EntityPerson ep= new EntityPerson();
		Person person = new Person();
		ArrayList<String> nhan = person.nhanPerson();
		ep.genNEntityPerson(300);
		
		ep.showListPerson();
		
		
	}
}
