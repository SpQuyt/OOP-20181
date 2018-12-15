package model;

public class Relation {
	String entity1,entity2;
	String rela;
	
	public Relation(String entity1, String entity2, String rela) {
		super();
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.rela = rela;
	}

	public Relation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return " [" + entity1 + ",  "+ rela + ", " + entity2 + "]";
	}

	public String getEntity1() {
		return entity1;
	}

	public void setEntity1(String entity1) {
		this.entity1 = entity1;
	}

	public String getEntity2() {
		return entity2;
	}

	public void setEntity2(String entity2) {
		this.entity2 = entity2;
	}

	public String getRela() {
		return rela;
	}

	public void setRela(String rela) {
		this.rela = rela;
	}
	
	
	
	
}
