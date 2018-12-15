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
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;

import gendata.Entity;
import gendata.Relation;

public class uploadGraphDB {
	public void upload(Relation rl, Entity entity) throws IOException {
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		PrintWriter writer = new PrintWriter("test.ttl","UTF-8");
		String StringUpload = "test:Thu_tuong_Nguyen_Xuan_Phuc\r\n" + 
				"  a test:Person ;\r\n" + 
				"  test:name \"Thủ tướng Nguyễn Xuân Phúc\";\r\n" + 
				"  test:extract_time \"2018-12-01\";\r\n" + 
				"  test:extract_link \"dcs.vn\" ;\r\n" + 
				"  test:to_chuc test:APEC_2018 .\r\n" +
				"\r\n";
		writer.println(""
				+ "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\r\n" + 
				"@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\r\n" + 
				"@prefix owl: <http://www.w3.org/2002/07/owl#> .\r\n" + 
				"@prefix test: <http://test.com/ns#> .\r\n" + 
				"\r\n" + 
				"");
		writer.close();
		File file = new File("test.ttl");
		String baseURI = "http://exampleuri.com/examplepath";
		try (RepositoryConnection con = repository.getConnection()) {
			   con.add(file, baseURI, RDFFormat.TURTLE);
			   URL url = new URL("http://exampleuri.com/examplepath/remote.ttl");
			   con.add(url, url.toString(), RDFFormat.TURTLE);
			}
			catch (RDF4JException e) {
			   // handle exception. This catch-clause is
			   // optional since RDF4JException is an unchecked exception
			}
			catch (EOFException e) {
			   // handle io exception
			}
			catch (ConnectException e) {
				System.out.println("FILE UPLOADED.");
			}
			catch (UnknownHostException e) {
				System.out.println("FILE UPLOADED.");
			}
		// Shutdown connection, repository and manager
		//		repositoryConnection.close();
		//		repository.shutDown();
		//		repositoryManager.shutDown();
	}
	
	public static void main(String[] args) throws RDFParseException, RDFHandlerException, IOException {			
			
	}
}
