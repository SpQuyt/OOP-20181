package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import gendata.Entity;
import gendata.Relation;

public class mainAp {
	public static void main(String[] args) throws IOException {
		Gen();
		
	}
	public static void Gen() throws IOException {
		Relation rl= new Relation();
		Entity entity= new Entity();
		System.out.println("sinh thuc the, nhap so luong:");
		Scanner sc= new Scanner(System.in);
		int nBEntity= sc.nextInt();
		
		System.out.println("sinh quan he, nhap so luong:");
		int nBRelation= sc.nextInt();
		System.out.println("\n---------thucthe-------");
		entity.genNEntity(nBEntity);
		System.out.println("\n---------quanhe-------");
		rl.genNRelationfull(nBRelation);
	}
}
