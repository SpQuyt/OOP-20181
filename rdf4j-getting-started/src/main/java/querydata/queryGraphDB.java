package querydata;

import java.io.IOException;
import java.net.Inet4Address;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;

public class queryGraphDB {
public static void main(String[] args) throws RDFParseException, RDFHandlerException, IOException {
		
		//Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
		try (RepositoryConnection con = repository.getConnection()) {
			   String queryString = "PREFIX test: <http://test.com/ns#> \r\n" + 
			   		"select ?sukien ?name {\r\n" + 
			   		"    ?sukien test:dien_ra_tai ?datnuoc .\r\n" + 
			   		"	?sukien test:name ?name .\r\n" + 
			   		"    ?datnuoc test:name \"Mỹ\" .\r\n" + 
			   		"}";
			   TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
			   try (TupleQueryResult result1 = tupleQuery.evaluate()) {
//			      while (result1.hasNext()) {  // iterate over the result
				   BindingSet bindingSet = result1.next();
				   String[] result = bindingSet.getValue("name").toString().split("");
				   String extra = "^^<http://www.w3.org/2001/XMLSchema#string>";
				   for (int i = 0; i < result.length-extra.length(); i++) {
					   System.out.print(result[i]);
				   }
//			      }
			   }		
			}
		
		// Shutdown connection, repository and manager
//		repositoryConnection.close();
		repository.shutDown();
		repositoryManager.shutDown();
	}
}