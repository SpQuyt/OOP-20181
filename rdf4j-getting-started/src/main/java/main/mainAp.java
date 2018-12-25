package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Scanner;

import gendata.Entity;
import gendata.Relation;
import querydata.queryGraphDB;
import upload.uploadGraphDB;

public class mainAp {
	public static void main(String[] args) throws IOException {
		Gen();
		Query();
		
	}
	
	public static void Query() throws UnknownHostException {
		System.out.println("\n---------Truy Vấn-------");
		queryGraphDB query = new queryGraphDB();
		query.queryMenu();
	}
	
	public static void Gen() throws IOException {
		uploadGraphDB upload = new uploadGraphDB();
		upload.initialize();
		
		Relation rl= new Relation();
		Scanner sc= new Scanner(System.in);
		int nBEntity;
		int nBRelation;
		
		Entity entity= new Entity();
		do {
			System.out.println("Sinh thực thể, hãy nhập số lượng (n>=20):");
			nBEntity= sc.nextInt();	
		}while(nBEntity < 20);
		
		do {
			System.out.println("Sinh quan hệ, nhập số lượng (m>=n):");
			nBRelation= sc.nextInt();
		}while(nBRelation < nBEntity);
		
		
		System.out.println("\n---------Thực Thể-------");
		entity.genNEntity(nBEntity);
		System.out.println("\n---------Quan Hệ-------");
		rl.genNRelationfull(nBRelation);
			
	}
}
