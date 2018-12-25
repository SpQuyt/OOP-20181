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
import model.Entity;

public class GenEntity {
	static ArrayList<model.Entity> listEntity = new ArrayList<>();
	
	public void showList() {
		for (model.Entity e: listEntity) {
			System.out.println(e);
		}
	}
	
	public void writeFile(String src) throws IOException {
		FileWriter out = new FileWriter(src);
		for (model.Entity c : listEntity) {
			out.write(c.getDinhDanh()+ "," + c.getNhan() + "," + c.getMota()  + "," + c.getLink()
					+ "," + c.getDate() + "\n");
		}
		out.close();
	}
	
	public model.Entity get1EntityFFile(String src) throws IOException {
		model.Entity entity= new Entity();
		String FileCountry = src;
		ArrayList<String> listCountry = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FileCountry), StandardCharsets.UTF_8);
		{

			listCountry = (ArrayList<String>) stream.collect(Collectors.toList());
			Random rd= new Random();
			String []ar=listCountry.get(rd.nextInt(listCountry.size())).split(",");	// lấy ngau nhiên  thuecj thẻ  count
			entity.setDinhDanh(ar[0]);
			entity.setNhan(ar[1]);
			entity.setMota(ar[2]);
			entity.setLink(ar[3]);
			entity.setDate(Date.valueOf(ar[4]));
		}
		return entity;

	}
	
	public int  get1Array(ArrayList<String> arr) {
		Random rd = new Random();
		
		int chiso = 0;
		if (arr.size() == 0)
			System.out.println("file rong");
		else {
			chiso = rd.nextInt(arr.size());
			
		}

		return chiso;
	}
}
