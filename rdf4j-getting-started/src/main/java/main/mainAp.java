package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import gendata.Entity;
import gendata.Relation;

public class mainAp {
	public static void main(String[] args) throws IOException {
		Gen();
		
	}
	public static void Gen() throws IOException {
		PrintWriter writer = new PrintWriter("entity/Country/country.txt");
		writer.print("");
		writer.close();
		PrintWriter writer1 = new PrintWriter("entity/Location/location.txt");
		writer1.print("");
		writer1.close();
		PrintWriter writer2 = new PrintWriter("entity/Person/person.txt");
		writer2.print("");
		writer2.close();
		PrintWriter writer3 = new PrintWriter("entity/Organization/Organization.txt");
		writer3.print("");
		writer3.close();
		PrintWriter writer4 = new PrintWriter("entity/Event/event.txt");
		writer4.print("");
		writer4.close();
		
		Relation rl= new Relation();
		Entity entity= new Entity();
		System.out.println("sinh thuc the, nhap so luong (n>=20):");
		Scanner sc= new Scanner(System.in);
		int nBEntity= sc.nextInt();
		
		System.out.println("sinh quan he, nhap so luong (m>=n):");
		int nBRelation= sc.nextInt();
		System.out.println("\n---------thucthe-------");
		entity.genNEntity(nBEntity);
		System.out.println("\n---------quanhe-------");
		rl.genNRelationfull(nBRelation);
	}
}
