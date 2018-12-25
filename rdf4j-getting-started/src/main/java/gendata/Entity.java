package gendata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Entity {
	public void genNEntity(int n) throws IOException {

		EntityPerson ep = new EntityPerson();
		EntityCountry ec = new EntityCountry();
		EntityLocation el = new EntityLocation();
		EntityOrganization eo = new EntityOrganization();
		EntityTime et = new EntityTime();
		EntityEvent ev = new EntityEvent();

		int k = n / 6;
		ep.genNEntityPerson(k);
		ec.genNEntityCountry(k);
		el.genNEntityLocation(k);
		eo.genNEntityOrganization(k);
		et.genNEntityTime(k);
		ev.genNEntityEvent(n - 5 * k);

		ep.showList();
		ec.showList();
		el.showList();
		eo.showList();
		et.showList();
		ev.showList();
	}

	public static void main(String[] args) throws IOException {

		Entity e = new Entity();
		e.genNEntity(30);
	}

}
