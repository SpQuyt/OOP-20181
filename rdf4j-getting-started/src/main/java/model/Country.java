package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Country extends Entity {
	String quocGia;
	

	public ArrayList<String> quocgiaCountry() throws IOException {
		String FilePerson = "entity/Country/quocgia.txt";
		ArrayList<String> listPerson = new ArrayList<>();
		Stream<String> stream = Files.lines(Paths.get(FilePerson), StandardCharsets.UTF_8);
		{

			listPerson = (ArrayList<String>) stream.collect(Collectors.toList());
			return listPerson;
		}
	}


	public String getQuocGia() {
		return quocGia;
	}


	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}


	@Override
	public String toString() {
		return "Country [dinhDanh=" + dinhDanh + ", nhan=" + nhan + ", mota=" + mota + ", link=" + link + ", date="
				+ date + "]";
	}


	
	

}
