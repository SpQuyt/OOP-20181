package connect;

import java.io.IOException;
import java.net.Inet4Address;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;

public class connectGraphDB {

	public static void main(String[] args) throws RDFParseException, RDFHandlerException, IOException {
		
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");

		try (RepositoryConnection con = repository.getConnection()) {
			   System.out.println("DATABASE CONNECTED!");
			}
			
		
		 //Shutdown connection, repository and manager
//		repositoryConnection.close();
//		repository.shutDown();
//		repositoryManager.shutDown();
	}
}
