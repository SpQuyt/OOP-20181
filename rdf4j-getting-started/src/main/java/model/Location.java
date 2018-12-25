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

public class Location extends Entity {
	
	@Override
	public String toString() {
		return "Location [dinhDanh=" + dinhDanh + ", nhan=" + nhan + ", mota=" + mota + ", link=" + link + ", date="
				+ date + "]";
	}
	
	
}