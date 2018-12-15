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

import model.Organization;
import model.Person;

public class EntityOrganization {
	
	static ArrayList<Organization> listOrganization= new ArrayList<>();

	//sinh 1 thuc the person
	public void gen1EntityOrganization() throws IOException {
		Organization organ= new Organization();
		ArrayList<String> nhan = organ.nhanOrgan();
		ArrayList<String> mota = organ.motaORgan();
		ArrayList<String> truso = organ.trusoOrgan();
		ArrayList<String> link = organ.LinkOrgan();
		Date date = organ.getRandomTime();
		
		int dd=0;
		String nhanDon=get1Array(nhan);
		for(int i=0;i<listOrganization.size();i++) {
			if(listOrganization.get(i).getNhan().equals(nhanDon))
				dd++;
		}
		
		String dinhdanh=""+nhanDon+dd;
		organ.setDinhDanh(dinhdanh);
		organ.setDate(date);
		organ.setNhan(nhanDon);
		organ.setMota(get1Array(mota));
		organ.setTruso(get1Array(truso));
		organ.setLink(get1Array(link));
		
		listOrganization.add(organ);
		
	}
	
	//sinh nhieu thuc the person
	public void genNEntityOrganization(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityOrganization();
		}
	}
	
	//in tap thuc the person
	public void showListOrganization() {
		for(Organization p:listOrganization) {
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
			property=arr.get(chiso);
		}
		
		return property;
	}

	public static void main(String[] args) throws IOException {
		EntityOrganization ep= new EntityOrganization();
		Person person = new Person();
		ArrayList<String> nhan = person.nhanPerson();
		ep.genNEntityOrganization(30);
		
		
		
		
	}
}
