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
import model.Event;
import model.Location;
import model.Organization;
import model.Person;
import model.Time;
import upload.uploadGraphDB;

public class Relation {
	 public int count = 0;
	 ArrayList<model.Relation> listRelationFull = new ArrayList<>();

	 static Person person= new Person();
	 static Country country= new Country();
	 static Event event= new Event();
	 static Location location= new Location();
	 static Organization organi= new Organization();
	 static Time time= new Time();
	 static String rela;
	 
	 

	String relaPerWCountry[] = { "sống_ở", "đến", "ở_tại", "đến_thăm", "đi_du_lịch_ở", "rời_khỏi" };
	String relaPerWOrgani[] = { "làm_việc_ở", "thuộc", "đến", "tham_gia", "bỏ_việc_ở", "rời_khỏi" };
	String relaPerLocation[] = { "ở_lại", "làm_việc_ở", "thuộc", "đến", "đến_thăm", "tham_dự_tại",
			"đi_du_lịch", "rời_khỏi" };
	String relaPerWEvent[] = { "làm_việc_ở", "thuộc", "đến", "tham_gia", "rời_khỏi", "tổ_chức" };

	String relaEventWCountry[] = { "được_tổ_chức_ở", "diễn_ra_tại" };
	String relaEventWLocation[] = { "được_tổ_chức_ở", "diễn_ra_tại" };

	
	public  void genNRelationfull(int n) throws IOException {
		for(int i=0;i<n;i++){
			gen1RDRelationFull();
		}
	}

	// sinh 1 thuc the person
	public  void gen1RDRelationFull() throws IOException {
		
		EntityCountry ec= new EntityCountry();
		EntityEvent ev= new EntityEvent();
		EntityLocation el= new EntityLocation();
		EntityOrganization eo= new EntityOrganization();
		EntityPerson ep= new EntityPerson();
		EntityTime et= new EntityTime();
		
		uploadGraphDB upload = new uploadGraphDB();
		
		Random rd = new Random();
		int k = rd.nextInt(6) + 1;
		switch (k) {

		// quan he giua person va country
		case 1:
			person =ep.get1EntityPersonFFile();
			country =ec.get1EntityCountryFFile();
			rela=get1Relation(relaPerWCountry);
			count = count + 1;
			System.out.println(count + ": "+ person+" "+rela+" "+country);
			upload.uploadPvC(person, rela, country);
			
			break;

		// quan he giua person va origan
		case 2:
			
			person= ep.get1EntityPersonFFile();
			organi= eo.get1EntityOrganiFFile();
			rela=get1Relation(relaPerWOrgani);
			count = count + 1;
			System.out.println(count + ": "+ person+ " "+rela+" "+organi);
			upload.uploadPvO(person, rela, organi);
			
			break;
		// quan he giua person va event
		case 3:

			person= ep.get1EntityPersonFFile();
			event=ev.get1EntityEventFFile();
			rela=get1Relation(relaPerWEvent);
			count = count + 1;
			System.out.println(count + ": "+ person+ " "+rela+" "+event);
			upload.uploadPvE(person, rela, event);
			
			break;
		// quan he giua person va country
		case 4:

			person= ep.get1EntityPersonFFile();
			location=el.get1EntityLocationFFile();
			rela=get1Relation(relaPerLocation);
			count = count + 1;
			System.out.println(count + ": "+ person+" "+rela+" "+location);
			upload.uploadPvL(person, rela, location);
			
			break;

		// quan he giua event va country
		case 5:
			event= ev.get1EntityEventFFile();
			country=ec.get1EntityCountryFFile();
			rela=get1Relation(relaEventWCountry);
			count = count + 1;
			System.out.println(count + ": "+ event+" "+rela+" "+country);
			upload.uploadEvC(event, rela, country);
			
			break;
		case 6:
			event=ev.get1EntityEventFFile();
			location =el.get1EntityLocationFFile();
			rela= get1Relation(relaEventWLocation);
			count = count + 1;
			System.out.println(count + ": "+ event+" "+rela+" "+location);
			upload.uploadEvL(event, rela, location);
			
			break;

		}
	}

	// lay ngau nhien 1 quan he tuw mang
	public String get1Relation(String[] arr) {
		int k = arr.length;
		Random rd = new Random();
		String rela = arr[rd.nextInt(k)];
		return rela;
	}


	public static void main(String[] args) throws IOException {
		Relation r= new Relation();
		r.genNRelationfull(10);
	}
}
