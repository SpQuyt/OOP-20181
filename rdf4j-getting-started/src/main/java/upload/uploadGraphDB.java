package upload;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;

import org.eclipse.rdf4j.RDF4JException;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.Update;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;

import gendata.Entity;
import gendata.Relation;
import model.*;

public class uploadGraphDB {
	public void uploadPvC(Person person, String rela, Country country) throws IOException {
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		
		try (RepositoryConnection con = repository.getConnection()) {
			   String queryString = 
					"PREFIX test: <http://test.com/ns#>\r\n" +
					"INSERT DATA\r\n" + 
					"{ "
					+ "GRAPH <http://test/OOP-20181> { "
					+ "test:" + person.getDinhDanh() + " " + "a" + " " + "test:Person .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:nhãn" + " \"" + person.getNhan() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:chức_vụ" + " \"" + person.getChucVu() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:mô_tả" + " \"" + person.getMoTa() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + person.getRandomTime() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + person.getLink() + "\" .\r\n"
					
					+ "test:" + country.getDinhDanh() + " " + "a" + " " + "test:Country .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:nhãn" + " \"" + country.getNhan() + "\" .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:mô_tả" + " \"" + country.getMota() + "\" .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + country.getRandomTime() + "\" .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + country.getLink() + "\" .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:thuộc_quốc_gia" + " \"" + country.getQuocGia() + "\" .\r\n"
					
					+ "test:" + person.getDinhDanh() + " " + "test:" + rela + " " + "test:" + country.getDinhDanh() + " .\r\n"
					+ "} "
					+ "}";
			   Update updateQuery = con.prepareUpdate(QueryLanguage.SPARQL, queryString);
			   updateQuery.execute();	
			   
			// Shutdown connection, repository and manager
			   con.close();
			   repository.shutDown();
			   repositoryManager.shutDown();
			}
				
	}
	
	public void uploadPvO(Person person, String rela, Organization organi) throws IOException {
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		
		try (RepositoryConnection con = repository.getConnection()) {
			   String queryString = 
					"PREFIX test: <http://test.com/ns#>\r\n" +
					"INSERT DATA\r\n" + 
					"{ "
					+ "GRAPH <http://test/OOP-20181> { "
					+ "test:" + person.getDinhDanh() + " " + "a" + " " + "test:Person .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:nhãn" + " \"" + person.getNhan() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:chức_vụ" + " \"" + person.getChucVu() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:mô_tả" + " \"" + person.getMoTa() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + person.getRandomTime() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + person.getLink() + "\" .\r\n"
					
					+ "test:" + organi.getDinhDanh() + " " + "a" + " " + "test:Organization.\r\n"
					+ "test:" + organi.getDinhDanh() + " " + "test:nhãn" + " \"" + organi.getNhan() + "\" .\r\n"
					+ "test:" + organi.getDinhDanh() + " " + "test:mô_tả" + " \"" + organi.getMota() + "\" .\r\n"
					+ "test:" + organi.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + organi.getRandomTime() + "\" .\r\n"
					+ "test:" + organi.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + organi.getLink() + "\" .\r\n"
					+ "test:" + organi.getDinhDanh() + " " + "test:trụ_sở_ở" + " \"" + organi.getTruso() + "\" .\r\n"
					
					+ "test:" + person.getDinhDanh() + " " + "test:" + rela + " " + "test:" + organi.getDinhDanh() + " .\r\n"
					+ "} "
					+ "}";
			   Update updateQuery = con.prepareUpdate(QueryLanguage.SPARQL, queryString);
			   updateQuery.execute();	
			   
			// Shutdown connection, repository and manager
			   con.close();
			   repository.shutDown();
			   repositoryManager.shutDown();
			}
				
	}
	
	public void uploadPvE(Person person, String rela, Event event) throws IOException {
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		
		try (RepositoryConnection con = repository.getConnection()) {
			   String queryString = 
					"PREFIX test: <http://test.com/ns#>\r\n" +
					"INSERT DATA\r\n" + 
					"{ "
					+ "GRAPH <http://test/OOP-20181> { "
					+ "test:" + person.getDinhDanh() + " " + "a" + " " + "test:Person .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:nhãn" + " \"" + person.getNhan() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:chức_vụ" + " \"" + person.getChucVu() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:mô_tả" + " \"" + person.getMoTa() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + person.getRandomTime() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + person.getLink() + "\" .\r\n"
					
					+ "test:" + event.getDinhDanh() + " " + "a" + " " + "test:Event .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:nhãn" + " \"" + event.getNhan() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:mô_tả" + " \"" + event.getMota() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + event.getRandomTime() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + event.getLink() + "\" .\r\n"
					
					+ "test:" + person.getDinhDanh() + " " + "test:" + rela + " " + "test:" + event.getDinhDanh() + " .\r\n"
					+ "} "
					+ "}";
			   Update updateQuery = con.prepareUpdate(QueryLanguage.SPARQL, queryString);
			   updateQuery.execute();	
			   
			// Shutdown connection, repository and manager
			   con.close();
			   repository.shutDown();
			   repositoryManager.shutDown();
			}
				
	}
	
	public void uploadPvL(Person person, String rela, Location location) throws IOException {
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		
		try (RepositoryConnection con = repository.getConnection()) {
			   String queryString = 
					"PREFIX test: <http://test.com/ns#>\r\n" +
					"INSERT DATA\r\n" + 
					"{ "
					+ "GRAPH <http://test/OOP-20181> { "
					+ "test:" + person.getDinhDanh() + " " + "a" + " " + "test:Person .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:nhãn" + " \"" + person.getNhan() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:chức_vụ" + " \"" + person.getChucVu() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:mô_tả" + " \"" + person.getMoTa() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + person.getRandomTime() + "\" .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + person.getLink() + "\" .\r\n"
					
					+ "test:" + location.getDinhDanh() + " " + "a" + " " + "test:Location .\r\n"
					+ "test:" + location.getDinhDanh() + " " + "test:nhãn" + " \"" + location.getNhan() + "\" .\r\n"
					+ "test:" + location.getDinhDanh() + " " + "test:mô_tả" + " \"" + location.getMota() + "\" .\r\n"
					+ "test:" + location.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + location.getRandomTime() + "\" .\r\n"
					+ "test:" + location.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + location.getLink() + "\" .\r\n"
					
					+ "test:" + person.getDinhDanh() + " " + "test:" + rela + " " + "test:" + location.getDinhDanh() + " .\r\n"
					+ "} "
					+ "}";
			   Update updateQuery = con.prepareUpdate(QueryLanguage.SPARQL, queryString);
			   updateQuery.execute();	
			   
			// Shutdown connection, repository and manager
			   con.close();
			   repository.shutDown();
			   repositoryManager.shutDown();
			}
				
	}
	
	public void uploadEvC(Event event, String rela, Country country) throws IOException {
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		
		try (RepositoryConnection con = repository.getConnection()) {
			   String queryString = 
					"PREFIX test: <http://test.com/ns#>\r\n" +
					"INSERT DATA\r\n" + 
					"{ "
					+ "GRAPH <http://test/OOP-20181> { "
					+ "test:" + event.getDinhDanh() + " " + "a" + " " + "test:Event .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:nhãn" + " \"" + event.getNhan() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:mô_tả" + " \"" + event.getMota() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + event.getRandomTime() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + event.getLink() + "\" .\r\n"
					
					+ "test:" + country.getDinhDanh() + " " + "a" + " " + "test:Country .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:nhãn" + " \"" + country.getNhan() + "\" .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:mô_tả" + " \"" + country.getMota() + "\" .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + country.getRandomTime() + "\" .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + country.getLink() + "\" .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:thuộc_quốc_gia" + " \"" + country.getQuocGia() + "\" .\r\n"
					
					+ "test:" + event.getDinhDanh() + " " + "test:" + rela + " " + "test:" + country.getDinhDanh() + " .\r\n"
					+ "} "
					+ "}";
			   Update updateQuery = con.prepareUpdate(QueryLanguage.SPARQL, queryString);
			   updateQuery.execute();	
			   
			// Shutdown connection, repository and manager
			   con.close();
			   repository.shutDown();
			   repositoryManager.shutDown();
			}
				
	}
	
	public void uploadEvL(Event event, String rela, Location location) throws IOException {
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		
		try (RepositoryConnection con = repository.getConnection()) {
			   String queryString = 
					"PREFIX test: <http://test.com/ns#>\r\n" +
					"INSERT DATA\r\n" + 
					"{ "
					+ "GRAPH <http://test/OOP-20181> { "
					+ "test:" + event.getDinhDanh() + " " + "a" + " " + "test:Event .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:nhãn" + " \"" + event.getNhan() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:mô_tả" + " \"" + event.getMota() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + event.getRandomTime() + "\" .\r\n"
					+ "test:" + event.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + event.getLink() + "\" .\r\n"
					
					+ "test:" + location.getDinhDanh() + " " + "a" + " " + "test:Location .\r\n"
					+ "test:" + location.getDinhDanh() + " " + "test:nhãn" + " \"" + location.getNhan() + "\" .\r\n"
					+ "test:" + location.getDinhDanh() + " " + "test:mô_tả" + " \"" + location.getMota() + "\" .\r\n"
					+ "test:" + location.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " \"" + location.getRandomTime() + "\" .\r\n"
					+ "test:" + location.getDinhDanh() + " " + "test:link_trích_rút" + " \"" + location.getLink() + "\" .\r\n"
					
					+ "test:" + event.getDinhDanh() + " " + "test:" + rela + " " + "test:" + location.getDinhDanh() + " .\r\n"
					+ "} "
					+ "}";
			   Update updateQuery = con.prepareUpdate(QueryLanguage.SPARQL, queryString);
			   updateQuery.execute();	
			   
			// Shutdown connection, repository and manager
			   con.close();
			   repository.shutDown();
			   repositoryManager.shutDown();
			}
				
	}
	
	public void initialize() throws RDFParseException, RDFHandlerException, IOException {		
		//Create empty files in entity folder
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
		
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		// Clear graph and all data
		try (RepositoryConnection con = repository.getConnection()) {
			   String queryString = 
					"clear graph <http://test/OOP-20181>";
			   Update updateQuery = con.prepareUpdate(QueryLanguage.SPARQL, queryString);
			   updateQuery.execute();	
			   
			// Shutdown connection, repository and manager
			   con.close();
			   repository.shutDown();
			   repositoryManager.shutDown();
			}
	}
}
