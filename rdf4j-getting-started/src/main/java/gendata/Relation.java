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
	String relaPerWTime[] = {"sinh_ra_vào_ngày", "mất_vào_ngày", "cưới_vào_ngày", "đỗ_đại_học_vào_ngày", "tốt_nghiệp_lúc" };

	String relaEventWCountry[] = { "được_tổ_chức_ở", "diễn_ra_tại" };
	String relaEventWLocation[] = { "được_tổ_chức_ở", "diễn_ra_tại" };
	String relaEventWTime[] = {"diễn_ra_vào_lúc","xảy_ra_vào_lúc", "được_tổ_chức_lúc"};
	String relaEventWOrgani[] = {"được_tổ_chức_bởi", "bị_huỷ_bởi"};
	String relaEventWPer[] = {"được_tổ_chức_bởi", "bị_huỷ_bởi"};

	
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
		int k = rd.nextInt(10) + 1;
		switch (k) {

		// quan he giua person va country
		case 1:
			person =ep.get1EntityFFile();
			country =ec.get1EntityFFile();
			rela=get1Relation(relaPerWCountry);
			count = count + 1;
			System.out.println(count + ": "+ person+" "+rela+" "+country);
			upload.uploadPvC(person, rela, country);
			
			break;

		// quan he giua person va origan
		case 2:
			
			person= ep.get1EntityFFile();
			organi= eo.get1EntityFFile();
			rela=get1Relation(relaPerWOrgani);
			count = count + 1;
			System.out.println(count + ": "+ person+ " "+rela+" "+organi);
			upload.uploadPvO(person, rela, organi);
			
			break;
		// quan he giua person va event
		case 3:

			person= ep.get1EntityFFile();
			event=ev.get1EntityFFile();
			rela=get1Relation(relaPerWEvent);
			count = count + 1;
			System.out.println(count + ": "+ person+ " "+rela+" "+event);
			upload.uploadPvE(person, rela, event);
			
			break;
		// quan he giua person va country
		case 4:

			person= ep.get1EntityFFile();
			location=el.get1EntityFFile();
			rela=get1Relation(relaPerLocation);
			count = count + 1;
			System.out.println(count + ": "+ person+" "+rela+" "+location);
			upload.uploadPvL(person, rela, location);
			
			break;

		// quan he giua event va country
		case 5:
			event= ev.get1EntityFFile();
			country=ec.get1EntityFFile();
			rela=get1Relation(relaEventWCountry);
			count = count + 1;
			System.out.println(count + ": "+ event+" "+rela+" "+country);
			upload.uploadEvC(event, rela, country);
			
			break;
		case 6:
			event=ev.get1EntityFFile();
			location =el.get1EntityFFile();
			rela= get1Relation(relaEventWLocation);
			count = count + 1;
			System.out.println(count + ": "+ event+" "+rela+" "+location);
			upload.uploadEvL(event, rela, location);
			
			break;
		case 7:
			person =ep.get1EntityFFile();
			time =et.get1EntityFFile();
			rela=get1Relation(relaPerWTime);
			count = count + 1;
			System.out.println(count + ": "+ person+" "+rela+" "+time);
			upload.uploadPvT(person, rela, time);
			
			break;
		case 8:
			event= ev.get1EntityFFile();
			time=et.get1EntityFFile();
			rela=get1Relation(relaEventWTime);
			count = count + 1;
			System.out.println(count + ": "+ event+" "+rela+" "+time);
			upload.uploadEvT(event, rela, time);
			
			break;
		case 9:
			event= ev.get1EntityFFile();
			person=ep.get1EntityFFile();
			rela=get1Relation(relaEventWPer);
			count = count + 1;
			System.out.println(count + ": "+ event+" "+rela+" "+person);
			upload.uploadEvP(event, rela, person);
			
			break;
		case 10:
			event= ev.get1EntityFFile();
			organi=eo.get1EntityFFile();
			rela=get1Relation(relaEventWOrgani);
			count = count + 1;
			System.out.println(count + ": "+ event+" "+rela+" "+organi);
			upload.uploadEvO(event, rela, organi);
			
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
