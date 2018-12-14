package org.example.rdf4j_getting_started;

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

public class HelloRDF4J {

	public static void main(String[] args) throws RDFParseException, RDFHandlerException, IOException {
		
		RepositoryManager repositoryManager =
		        new RemoteRepositoryManager( "http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200" );
		repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set in configuration .ttl file
		Repository repository = repositoryManager.getRepository("02122018");
		
//		try (RepositoryConnection conn = repository.getConnection()) {
////			   String queryString = "PREFIX pub: <http://ontology.ontotext.com/taxonomy/> SELECT ?person ?occupation WHERE {" + 
////			   		"    ?x pub:occupation ?o ." + 
////			   		"    ?x a pub:Person ." + 
////			   		"    ?x pub:preferredLabel ?person ." + 
////			   		"    {\r\n" + 
////			   		"        ?o pub:hasValue ?value ." + 
////			   		"        ?value pub:preferredLabel ?occupation ." + 
////			   		"    } UNION {" + 
////			   		"        ?o pub:hasValue ?occupation ." + 
////			   		"        filter (isLiteral(?occupation)) ." + 
////			   		"     }" + 
////			   		"}";
////			   TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
////			   try (TupleQueryResult result1 = tupleQuery.evaluate()) {
////			      while (result1.hasNext()) {  // iterate over the result
////				   BindingSet bindingSet = result1.next();
////				   Value valueOfX = bindingSet.getValue("x");
////				   Value valueOfLabel = bindingSet.getValue("label");
////				   System.out.println(bindingSet);
////			   // do something interesting with the values here...
////			      }
////			   }
//			
//			}
		
//		PrintWriter writer = new PrintWriter("test.ttl","UTF-8");
//		writer.println(""
//				+ "@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\r\n" + 
//				"@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\r\n" + 
//				"@prefix owl: <http://www.w3.org/2002/07/owl#> .\r\n" + 
//				"@prefix test: <http://test.com/ns#> .\r\n" + 
//				"\r\n" + 
//				"test:Person rdf:type owl:Class .\r\n" + 
//				"test:name rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"tên\" .\r\n" + 
//				"test:description rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"mô tả\" .\r\n" + 
//				"test:extract_time rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"thời gian trích rút\" .\r\n" + 
//				"test:extract_link rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"nguồn gốc trích rút\" .\r\n" + 
//				"\r\n" + 
//				"test:Organization rdf:type owl:Class . \r\n" + 
//				"test:name rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Organization ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"tên\" .\r\n" + 
//				"test:description rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Organization ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"mô tả\" .\r\n" + 
//				"test:extract_time rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Organization ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"thời gian trích rút\" .\r\n" + 
//				"test:extract_link rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Organization ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"nguồn gốc trích rút\" .\r\n" + 
//				"\r\n" + 
//				"test:Event rdf:type owl:Class .\r\n" + 
//				"test:name rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Event ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"tên\" .\r\n" + 
//				"test:description rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Event ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"mô tả\" .\r\n" + 
//				"test:extract_time rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Event ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"thời gian trích rút\" .\r\n" + 
//				"test:extract_link rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Event ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"nguồn gốc trích rút\" .\r\n" + 
//				"\r\n" + 
//				"test:Time rdf:type owl:Class . \r\n" + 
//				"test:name rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Time ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"tên\" .\r\n" + 
//				"test:description rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Time ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"mô tả\" .\r\n" + 
//				"test:extract_time rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Time ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"thời gian trích rút\" .\r\n" + 
//				"test:extract_link rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Time ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"nguồn gốc trích rút\" .\r\n" + 
//				"\r\n" + 
//				"test:Country rdf:type owl:Class .\r\n" + 
//				"test:name rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Country ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"tên\" .\r\n" + 
//				"test:description rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Country ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"mô tả\" .\r\n" + 
//				"test:extract_time rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Country ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"thời gian trích rút\" .\r\n" + 
//				"test:extract_link rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Country ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"nguồn gốc trích rút\" .\r\n" + 
//				"\r\n" + 
//				"test:Location rdf:type owl:Class .\r\n" + 
//				"test:name rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Location ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"tên\" .\r\n" + 
//				"test:description rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Location ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"mô tả\" .\r\n" + 
//				"test:extract_time rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Location ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"thời gian trích rút\" .\r\n" + 
//				"test:extract_link rdf:type owl:DatatypeProperty ;\r\n" + 
//				"  rdfs:domain test:Location ;\r\n" + 
//				"  rdfs:range xsd:string ;\r\n" + 
//				"  rdfs:name \"nguồn gốc trích rút\" .\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"###########################################\r\n" + 
//				"########## quan hệ của các lớp\r\n" + 
//				"###########################################\r\n" + 
//				"\r\n" + 
//				"test:lam_viec_cho rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range test:Organization ;\r\n" + 
//				"  rdfs:name \"làm việc cho\" .\r\n" + 
//				"test:thuoc rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range test:Organization ;\r\n" + 
//				"  rdfs:name \"thuộc\" .\r\n" + 
//				"test:o_tai rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range test:Country,test:Location ;\r\n" + 
//				"  rdfs:name \"ở tại\" .\r\n" + 
//				"test:den_tham rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range test:Country,test:Location ;\r\n" + 
//				"  rdfs:name \"đến thăm\" .\r\n" + 
//				"test:di_du_lich rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range test:Country,test:Location ;\r\n" + 
//				"  rdfs:name \"đi du lịch\" .\r\n" + 
//				"test:roi_khoi rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range test:Country,test:Location ;\r\n" + 
//				"  rdfs:name \"rời khỏi\" .\r\n" + 
//				"test:tham_gia rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range test:Event ;\r\n" + 
//				"  rdfs:name \"tham gia\" .\r\n" + 
//				"test:to_chuc rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Person ;\r\n" + 
//				"  rdfs:range test:Event ;\r\n" + 
//				"  rdfs:name \"tổ chức\" .\r\n" + 
//				"\r\n" + 
//				"test:dien_ra_tai rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Event ;\r\n" + 
//				"  rdfs:range test:Country ;\r\n" + 
//				"  rdfs:name \"diễn ra tại\" .\r\n" + 
//				"test:dien_ra_tai rdf:type owl:ObjectProperty ;\r\n" + 
//				"  rdfs:domain test:Event ;\r\n" + 
//				"  rdfs:range test:Location ;\r\n" + 
//				"  rdfs:name \"diễn ra tại\" .");
//		writer.close();
//		File file = new File("E:/Eclipse Workspace/rdf4j-getting-started/test.ttl");
		File file = new File("D:/dataGraphDB/individuals.ttl");
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
}
