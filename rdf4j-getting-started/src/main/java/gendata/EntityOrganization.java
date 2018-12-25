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
import model.Organization;
import model.Person;

public class EntityOrganization extends GenEntity {
	
	static ArrayList<Organization> listEntity= new ArrayList<>();

	//sinh 1 thuc the person
	public void gen1EntityOrganization() throws IOException {
		Organization organ= new Organization();
		ArrayList<String> nhan = organ.listNhan("entity/Organization/nhan.txt");
		ArrayList<String> mota = organ.listMota("entity/Organization/mota.txt");
		ArrayList<String> truso = organ.trusoOrgan();
		ArrayList<String> link = organ.listLink();
		Date date = organ.getRandomTime();
		
		int dd = 0;
		int index=get1Array(nhan);
		String nhanDon = nhan.get(index);
		for (int i = 0; i < listEntity.size(); i++) {
			if (listEntity.get(i).getNhan().equals(nhanDon))
				dd++;
		}

		String dinhdanh =nhanDon + String.format("%03d", dd);
		dinhdanh=dinhdanh.replaceAll("\\s", "");
		organ.setDinhDanh(dinhdanh);
		organ.setDate(date);
		organ.setNhan(nhanDon);
		organ.setMota(mota.get(index));
		organ.setTruso(truso.get(index));
		organ.setLink(link.get(get1Array(link)));
		
		listEntity.add(organ);
		
	}
	
	//sinh nhieu thuc the person
	public void genNEntityOrganization(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityOrganization();
			writeFile();
		}
	}
	
	//in tap thuc the person
	public void showList() {
		for(Organization p:listEntity) {
			System.out.println(p);
		}
	}



	// ghi list country ra file
		public void writeFile() throws IOException {
			FileWriter out = new FileWriter("entity/Organization/organization.txt");
			for (Organization c : listEntity) {
				out.write(c.getDinhDanh() + "," + c.getNhan() + "," + c.getMota() + "," + c.getTruso() + "," + c.getLink()
						+ "," + c.getDate() + "\n");
			}
			out.close();
		}

		public Organization get1EntityFFile() throws IOException {
			Organization country= new Organization();
			String FileCountry = "entity/Organization/organization.txt";
			ArrayList<String> listCountry = new ArrayList<>();
			Stream<String> stream = Files.lines(Paths.get(FileCountry), StandardCharsets.UTF_8);
			{

				listCountry = (ArrayList<String>) stream.collect(Collectors.toList());
				Random rd= new Random();
				String []ar=listCountry.get(rd.nextInt(listCountry.size())).split(",");	// lấy ngau nhiên  thuecj thẻ  count
				country.setDinhDanh(ar[0]);
				country.setNhan(ar[1]);
				country.setMota(ar[2]);
				country.setTruso(ar[3]);
				country.setLink(ar[4]);
				country.setDate(Date.valueOf(ar[5]));
			}
			return country;

		}

	public static void main(String[] args) throws IOException {
		EntityOrganization ep= new EntityOrganization();
		ep.genNEntityOrganization(10);
		ep.showList();
		
		
		
		
	}
}
