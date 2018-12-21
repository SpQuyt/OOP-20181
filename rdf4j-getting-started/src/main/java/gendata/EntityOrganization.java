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
		
		int dd = 0;
		int index=get1Array(nhan);
		String nhanDon = nhan.get(index);
		for (int i = 0; i < listOrganization.size(); i++) {
			if (listOrganization.get(i).getNhan().equals(nhanDon))
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
		
		listOrganization.add(organ);
		
	}
	
	//sinh nhieu thuc the person
	public void genNEntityOrganization(int n) throws IOException {
		for(int i=0;i<n;i++) {
			gen1EntityOrganization();
			writeFileOrganization();
		}
	}
	
	//in tap thuc the person
	public void showListOrganization() {
		for(Organization p:listOrganization) {
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
		public void writeFileOrganization() throws IOException {
			FileWriter out = new FileWriter("entity/Organization/organization.txt");
			for (Organization c : listOrganization) {
				out.write(c.getDinhDanh() + "," + c.getNhan() + "," + c.getMota() + "," + c.getTruso() + "," + c.getLink()
						+ "," + c.getDate() + "\n");
			}
			out.close();
		}

		public Organization get1EntityOrganiFFile() throws IOException {
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
		ep.showListOrganization();
		
		
		
		
	}
}