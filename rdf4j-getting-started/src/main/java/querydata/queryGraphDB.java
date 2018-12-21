package querydata;

import java.io.Console;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.Array;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.GraphQueryResult;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.query.Update;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;

public class queryGraphDB {
	public void queryMenu() throws UnknownHostException {
		// Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager = new RemoteRepositoryManager(
				"http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200");
		repositoryManager.initialize();
		Repository repository = repositoryManager.getRepository("02122018");

		while (true) {
			System.out.println("" + "0. Bao nhiêu thực thể thuộc lớp Person?\n"
					+ "1. Sự kiện Apple ra mắt IphoneX diễn ra ở đâu?\n" + "2. Ai tổ chức sự kiện Hội Nghị APEC 2018?\n"
					+ "3. Thông tin mô tả của Mark ZuckenBerg?\n"
					+ "4. Những thực thể được trích rút vào ngày 2012-11-07?");

			System.out.print("Nhập số thứ tự câu truy vấn ban muốn chọn: ");
			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt();

			Object[] queryString = {
					// bao nhieu thuc the thuoc lop Person?
					"prefix test:<http://test.com/ns#>\r\n" + "select distinct ?object ?name {\r\n"
							+ "    ?object a test:Person .\r\n" + "    ?object test:nhãn ?name . \r\n" + "}",

					// su kien Apple ra mắt IphoneX dien ra o dau?
					"prefix test:<http://test.com/ns#>\r\n" + "select ?locationName  {\r\n"
							+ "    ?sukien test:nhãn \"Apple ra mắt IphoneX\"  . \r\n"
							+ "    ?sukien test:diễn_ra_tại ?location .\r\n"
							+ "    ?location test:nhãn ?locationName .\r\n" + "}",

					// ai to chuc su kien Hội Nghị APEC 2018?
					"prefix test:<http://test.com/ns#>\r\n" + "select ?personName{\r\n"
							+ "    ?person test:tổ_chức ?sukien .\r\n" + "    ?person test:nhãn ?personName .\r\n"
							+ "    ?sukien test:nhãn \"Hội Nghị APEC 2018\" .\r\n" + "}\r\n" + "",

					// thông tin mô tả của Mark ZuckenBerg?
					"prefix test:<http://test.com/ns#>\r\n" + "select distinct ?thongtin{\r\n"
							+ "    ?person test:nhãn \"Mark ZuckenBerg\" .\r\n"
							+ "    ?person test:mô_tả ?thongtin .\r\n" + "}",

					// những thực thể được trích rút vào ngày 2012-11-07
					"prefix test:<http://test.com/ns#>\r\n" + "select distinct ?object{\r\n"
							+ "    ?object test:thời_gian_trích_rút \"2012-11-07\" .\r\n" + "}", };

			Date dateNow = new Date();
			long timeMilliNow = dateNow.getTime();

			try (RepositoryConnection con = repository.getConnection()) {
				TupleQuery tupleQuery = con.prepareTupleQuery(queryString[num].toString());
				try (TupleQueryResult result1 = tupleQuery.evaluate()) {
					BindingSet bindingSet = result1.next();
					Set<String> listBinding = bindingSet.getBindingNames();
					Object[] list = listBinding.toArray();

					while (result1.hasNext()) { // iterate over the result
						BindingSet bindingSet1 = result1.next();
						for (int i = 0; i < list.length; i++) {
							if (i == list.length - 1) {
								System.out.print(bindingSet1.getValue(list[i].toString()));
							} else {
								System.out.print(bindingSet1.getValue(list[i].toString()) + "---------------------");
							}
						}
						System.out.println("");
//							   String[] result = bindingSet.getValue("name").toString().split("");
//							   String extra = "^^<http://www.w3.org/2001/XMLSchema#string>";
//							   for (int i = 0; i < result.length-extra.length(); i++) {
//								   System.out.print(result[i]);
//							   }
					}
				}
				Date dateAfter = new Date();
				long timeMilliAfter = dateAfter.getTime();
				System.out.println("\n====>Query took " + (timeMilliAfter - timeMilliNow) / 1000.0 + "s.\n\n");
			} finally {
				repository.shutDown();
				repositoryManager.shutDown();
//						sc.close();
			}
		}
	}

	public static void main(String[] args) throws RDFParseException, RDFHandlerException, IOException {
		// Remember to turn on GraphDB local server first!
		RepositoryManager repositoryManager = new RemoteRepositoryManager(
				"http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200");
		repositoryManager.initialize();
		Repository repository = repositoryManager.getRepository("02122018");

		while (true) {
			System.out.println("" + "0. Bao nhiêu thực thể thuộc lớp Person?\n"
					+ "1. Sự kiện Apple ra mắt IphoneX diễn ra ở đâu?\n" 
					+ "2. Ai tổ chức sự kiện Hội Nghị APEC 2018?\n"
					+ "3. Thông tin mô tả của Mark ZuckenBerg?\n"
					+ "4. Những thực thể được trích rút vào ngày 2012-11-07?\n"
					+ "5. Những thực thể được trích rút từ nguồn p.com?\n"
					+ "6. Danh sách <= 100 triples đầu tiên của database?\n"
					+ "7. Liệt kê <= 300 thực thể thuộc lớp Event?\n"
					+ "8. Liệt kê các thực thể có nhãn Hội Nghị APEC 2018 với nhãn sắp xếp giảm dần?\n"
					+ "");

			System.out.print("Nhập số thứ tự câu truy vấn ban muốn chọn: ");
			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt();

			Object[] queryString = {
					// bao nhieu thuc the thuoc lop Person?
					"prefix test:<http://test.com/ns#>\r\n" + "select distinct ?object ?name {\r\n"
							+ "    ?object a test:Person .\r\n" + "    ?object test:nhãn ?name . \r\n" + "}",

					// su kien Apple ra mắt IphoneX dien ra o dau?
					"prefix test:<http://test.com/ns#>\r\n" + "select ?locationName  {\r\n"
							+ "    ?sukien test:nhãn \"Apple ra mắt IphoneX\"  . \r\n"
							+ "    ?sukien test:diễn_ra_tại ?location .\r\n"
							+ "    ?location test:nhãn ?locationName .\r\n" + "}",

					// ai to chuc su kien Hội Nghị APEC 2018?
					"prefix test:<http://test.com/ns#>\r\n" + "select ?personName{\r\n"
							+ "    ?person test:tổ_chức ?sukien .\r\n" + "    ?person test:nhãn ?personName .\r\n"
							+ "    ?sukien test:nhãn \"Hội Nghị APEC 2018\" .\r\n" + "}\r\n" + "",

					// thông tin mô tả của Mark ZuckenBerg?
					"prefix test:<http://test.com/ns#>\r\n" + "select distinct ?thongtin{\r\n"
							+ "    ?person test:nhãn \"Mark ZuckenBerg\" .\r\n"
							+ "    ?person test:mô_tả ?thongtin .\r\n" + "}",

					// những thực thể được trích rút vào ngày 2012-11-07
					"prefix test:<http://test.com/ns#>\r\n" + "select ?object{\r\n"
							+ "    ?object test:thời_gian_trích_rút \"2012-11-07\" .\r\n" + "}", 
							
					//các thực thể được trích rút từ link p.com
					"PREFIX test: <http://test.com/ns#>\r\n" + 
					"select distinct ?object {\r\n" + 
					"	?object test:link_trích_rút \"p.com\" .\r\n" + 
					"}"		,
					
					//liệt kê 100 triples trong database
					"select * where { \r\n" + 
					"	?s ?p ?o .\r\n" + 
					"} limit 100 ",
					
					//300 thực thể thuộc lớp Event
					"PREFIX test: <http://test.com/ns#>\r\n" + 
					"select ?object {\r\n" + 
					"	?object a test:Event .\r\n" + 
					"} limit 300 ",
					
					//liệt kê các thực thể có nhãn Hội Nghị APEC 2018 với nhãn sắp xếp giảm dần
					"PREFIX test: <http://test.com/ns#>\r\n" + 
					"select ?object {\r\n" + 
					"    ?object test:nhãn \"Hội Nghị APEC 2018\" .\r\n" + 
					"} order by desc(?object)",
					
					
			};

			Date dateNow = new Date();
			long timeMilliNow = dateNow.getTime();

			try (RepositoryConnection con = repository.getConnection()) {
				// use the num to pick the queryString
				TupleQuery tupleQuery = con.prepareTupleQuery(queryString[num].toString());
				try (TupleQueryResult result1 = tupleQuery.evaluate()) {

					// if this node is NOT NULL
					if (result1.hasNext()) {

						// print the first node
						BindingSet bindingSet = result1.next();
						Set<String> listBinding = bindingSet.getBindingNames();
						Object[] list = listBinding.toArray();
						for (int i = 0; i < list.length; i++) {
							if (i == list.length - 1) {
								System.out.print(bindingSet.getValue(list[i].toString()));
							} else {
								System.out.print(bindingSet.getValue(list[i].toString()) + "---------------------");
							}
						}
						System.out.println("");

						// then print all other ones
						while (result1.hasNext()) {
							BindingSet bindingSet1 = result1.next();
							for (int i = 0; i < list.length; i++) {
								if (i == list.length - 1) {
									System.out.print(bindingSet1.getValue(list[i].toString()));
								} else {
									System.out
											.print(bindingSet1.getValue(list[i].toString()) + "---------------------");
								}
							}
							System.out.println("");
//											   String[] result = bindingSet.getValue("name").toString().split("");
//											   String extra = "^^<http://www.w3.org/2001/XMLSchema#string>";
//											   for (int i = 0; i < result.length-extra.length(); i++) {
//												   System.out.print(result[i]);
//											   }
						}
					}

					// if this node is NULL
					else {
						System.out.println("====>Không có giá trị để truy vấn.");
					}
				}
				Date dateAfter = new Date();
				long timeMilliAfter = dateAfter.getTime();
				System.out.println("\n====>Query took " + (timeMilliAfter - timeMilliNow) / 1000.0 + "s.\n\n");
			} finally {
				repository.shutDown();
				repositoryManager.shutDown();
			}
		}
	}
}
