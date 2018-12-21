package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import gendata.Entity;
import gendata.Relation;
import querydata.queryGraphDB;
import upload.uploadGraphDB;

public class mainAp {
	public static void main(String[] args) throws IOException {
		Gen();
		
	}
	public static void Gen() throws IOException {
		uploadGraphDB upload = new uploadGraphDB();
		upload.initialize();
		
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
		
		System.out.println("\n---------truyvan-------");
		queryGraphDB query = new queryGraphDB();
		query.queryMenu();
	}
}
