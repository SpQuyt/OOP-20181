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
	public void upload(Person person, String rela, Country country) throws IOException {
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
					+ "test:" + person.getDinhDanh() + " " + "test:nhãn" + " " + person.getNhan() + " .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:chức_vụ" + " " + person.getChucVu() + " .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:mô_tả" + " " + person.getMoTa() + " .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " " + person.getRandomTime() + " .\r\n"
					+ "test:" + person.getDinhDanh() + " " + "test:link_trích_rút" + " " + person.getLink() + " .\r\n"
					
					+ "test:" + country.getDinhDanh() + " " + "a" + " " + "test:Country .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:nhãn" + " " + country.getNhan() + " .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:mô_tả" + " " + country.getMota() + " .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:thời_gian_trích_rút" + " " + country.getRandomTime() + " .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:link_trích_rút" + " " + country.getLink() + " .\r\n"
					+ "test:" + country.getDinhDanh() + " " + "test:thuộc_quốc_gia" + " " + country.getQuocGia() + " .\r\n"
					
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
	
	public static void main(String[] args) throws RDFParseException, RDFHandlerException, IOException {			
		
	}
}
