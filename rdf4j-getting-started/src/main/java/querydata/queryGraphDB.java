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
import org.eclipse.rdf4j.query.BooleanQuery;
import org.eclipse.rdf4j.query.BooleanQueryResultHandler;
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

		Scanner sc = new Scanner(System.in);
		// Choose level
		int levelList[] = { 0, 1, 2, 3, 4, 5 };
		Object[] graphList = { "    graph <http://test/OOP-20181> {\r\n",
				"    graph <http://test/OOP-20181-lvl1> {\r\n", "    graph <http://test/OOP-20181-lvl2> {\r\n",
				"    graph <http://test/OOP-20181-lvl3> {\r\n", "    graph <http://test/OOP-20181-lvl4> {\r\n",
				"    graph <http://test/OOP-20181-lvl5> {\r\n" };
		System.out.print("1. Level 1 (100 entities - 200 statements)\n"
				+ "2. Level 2 (5000 entities - 7000 statements)\n" + "3. Level 3 (60000 entities - 80000 statements)\n"
				+ "4. Level 4 (500 entities - 1000 statements)\n" + "5. Level 5 (2000 entities - 4000 statements)\n"
				+ "Nhập level của cơ sở dữ liệu: ");
		int level = sc.nextInt();
		String graph = "";
		switch (level) {
		case 0:
			graph = (String) graphList[0];
			break;
		case 1:
			graph = (String) graphList[1];
			break;
		case 2:
			graph = (String) graphList[2];
			break;
		case 3:
			graph = (String) graphList[3];
			break;
		case 4:
			graph = (String) graphList[4];
			break;
		case 5:
			graph = (String) graphList[5];
			break;
		}

		while (true) {
			System.out.println("\n" + "0. Bao nhiêu thực thể thuộc lớp Person?\n"
					+ "1. Sự kiện Cốc trà đá vì cộng đồng diễn ra ở đâu?\n"
					+ "2. Ai tổ chức sự kiện Hội Nghị APEC 2018?\n" + "3. Thông tin mô tả của Lady Gaga?\n"
					+ "4. Những thực thể được trích rút vào ngày 2012-11-07?\n"
					+ "5. Những thực thể được trích rút từ nguồn p.com?\n"
					+ "6. Danh sách <= 100 triples đầu tiên của database?\n"
					+ "7. Liệt kê <= 300 thực thể thuộc lớp Event?\n"
					+ "8. Liệt kê các thực thể có nhãn Hội Nghị APEC 2018 với nhãn sắp xếp giảm dần?\n"
					+ "9. Ai tốt nghiệp vào ngày 1994-06-28?\n"
					+ "10. Thông tin mô tả sự kiện Hoa hậu hoàn vũ thế giới 2013?\n"
					+ "11. Bao nhiêu thực thể được trích rút vào ngày 2009-03-07?\n"
					+ "12. Bao nhiêu thực thể được trích rút từ nguồn d.com?\n"
					+ "13. Có bao nhiêu định danh có nhãn Hội Nghị APEC 2018?\n"
					+ "14. Bao nhiêu thưc thể có nhãn Lady Gaga và đến thăm Edinburgh?\n"
					+ "15. In ra định danh, tên và mô tả của các thực thể lớp Person trong Database?\n"
					+ "16. In ra định danh, tên và mô tả của các thực thể lớp Event trong Database\n"
					+ "17. In ra tên những người sinh ra hoặc tốt nghiệp vào ngày 1994-06-28?\n"
					+ "18. Bao nhiêu sự kiện diễn ra hoặc được tổ chức ở Edinburgh?\n"
					+ "19. Thực thể lớp Country nhiều hơn thực thể lớp Location không?\n"
					+ "20. Thực thể lớp Person ít hơn thực thể lớp Event không?\n");

			System.out.print("Nhập số thứ tự câu truy vấn ban muốn chọn: ");
			int num = sc.nextInt();

			Object[] queryString = {
					// bao nhieu thuc the thuoc lop Person?
					"PREFIX test: <http://test.com/ns#>\r\n" + "select (count(?object) as ?count) {\r\n" + graph
							+ "        ?object a test:Person .\r\n" + "    }\r\n" + "}",

					// su kien Cốc trà đá vì cộng đồng dien ra o dau?
					"prefix test:<http://test.com/ns#>\r\n" + "select ?locationName  {\r\n" + graph
							+ "    ?sukien test:nhãn \"Cốc trà đá vì cộng đồng\"  . \r\n"
							+ "    ?sukien test:diễn_ra_tại ?location .\r\n"
							+ "    ?location test:nhãn ?locationName .\r\n" + "}\r\n" + "}",

					// ai to chuc su kien Hội Nghị APEC 2018?
					"prefix test:<http://test.com/ns#>\r\n" + "select ?personName{\r\n" + graph
							+ "    ?person test:tổ_chức ?sukien .\r\n" + "    ?person test:nhãn ?personName .\r\n"
							+ "    ?sukien test:nhãn \"Hội Nghị APEC 2018\" .\r\n" + "}\r\n" + "}",

					// thông tin mô tả của Lady Gaga?
					"prefix test:<http://test.com/ns#>\r\n" + "select distinct ?thongtin{\r\n" + graph
							+ "    ?person test:nhãn \"Lady Gaga\" .\r\n" + "    ?person test:mô_tả ?thongtin .\r\n"
							+ "}\r\n" + "}",

					// những thực thể được trích rút vào ngày 2012-11-07
					"prefix test:<http://test.com/ns#>\r\n" + "select ?object{\r\n" + graph
							+ "    ?object test:thời_gian_trích_rút \"2012-11-07\" .\r\n" + "}\r\n" + "}",

					// các thực thể được trích rút từ link p.com
					"PREFIX test: <http://test.com/ns#>\r\n" + "select distinct ?object {\r\n" + graph
							+ "	?object test:link_trích_rút \"p.com\" .\r\n" + "}\r\n" + "}",

					// liệt kê 100 triples trong database
					"select * where { \r\n" + graph + "	?s ?p ?o .\r\n" + "}\r\n " + "}\r\n " + "limit 100 ",

					// 300 thực thể thuộc lớp Event
					"PREFIX test: <http://test.com/ns#>\r\n" + "select ?object {\r\n" + graph
							+ "	?object a test:Event .\r\n" + "}\r\n" + "}\r\n limit 300 ",

					// liệt kê các thực thể có nhãn Hội Nghị APEC 2018 với nhãn sắp xếp giảm dần
					"PREFIX test: <http://test.com/ns#>\r\n" + "select ?object {\r\n" + graph
							+ "    ?object test:nhãn \"Hội Nghị APEC 2018\" .\r\n" + "}\r\n" + "}\r\n"
							+ " order by desc(?object)",

					// ai tốt nghiệp lúc 1994-06-28
					"PREFIX test: <http://test.com/ns#>\r\n" + "select ?object {\r\n" + graph
							+ "        ?object test:tốt_nghiệp_lúc test:1994-06-28 .\r\n" + "    }\r\n" + "}",

					// mô tả sự kiện hoa hậu hoàn vũ thế giới năm 2013
					"PREFIX test: <http://test.com/ns#>\r\n" + "select distinct ?mota{\r\n" + graph
							+ "        ?object test:nhãn \"Chung kết hoa hậu hoàn vũ thế giới năm 2013\" .\r\n"
							+ "        ?object test:mô_tả ?mota .\r\n" + "    }\r\n" + "}",

					// bao nhiêu thực thể trích rút vào ngày 2009-03-07
					"PREFIX test: <http://test.com/ns#>\r\n" + "select (count(?object) as ?count){\r\n" + graph
							+ "        ?object test:thời_gian_trích_rút \"2009-03-07\" .\r\n" + "    }\r\n" + "} ",

					// bao nhiêu thực thể trích rút từ nguồn d.com
					"PREFIX test: <http://test.com/ns#>\r\n" + "select (count(?object) as ?count){\r\n" + graph
							+ "        ?object test:link_trích_rút \"d.com\" .\r\n" + "    }\r\n" + "} ",

					// Bao nhiêu định danh có nhãn Hội Nghị APEC 2018
					"PREFIX test: <http://test.com/ns#>\r\n" + "select (count(?dinhdanh) as ?count){\r\n" + graph
							+ "        ?dinhdanh test:nhãn \"Hội Nghị APEC 2018\" .\r\n" + "    }\r\n" + "} ",

					// Bao nhiêu thưc thể có nhãn Lady Gaga và đến thăm Edinburgh
					"PREFIX test: <http://test.com/ns#>\r\n" + "select (count(?object) as ?count) where { \r\n" + graph
							+ "		?object test:nhãn \"Lady Gaga\" .\r\n"
							+ "        ?place test:nhãn \"Edinburgh\" .\r\n"
							+ "        ?object test:đến_thăm ?place .\r\n" + "    }\r\n" + "} ",

					// In ra định danh, tên và mô tả của các thực thể lớp Person trong Database
					"PREFIX test: <http://test.com/ns#>\r\n" + "select ?object ?name ?mota {\r\n" + graph
							+ "        ?object a test:Person .\r\n" + "        ?object test:nhãn ?name .\r\n"
							+ "        ?object test:mô_tả ?mota .\r\n" + "    }\r\n" + "}",

					// In ra định danh, tên và mô tả của các thực thể lớp Event trong Database
					"PREFIX test: <http://test.com/ns#>\r\n" + "select ?object ?name ?mota {\r\n" + graph
							+ "        ?object a test:Event .\r\n" + "        ?object test:nhãn ?name .\r\n"
							+ "        ?object test:mô_tả ?mota .\r\n" + "    }\r\n" + "}",

					// In ra tên những người sinh ra hoặc tốt nghiệp vào ngày 1994-06-28?
					"PREFIX test: <http://test.com/ns#>\r\n" + "select distinct ?objectName {\r\n" + graph
							+ "        {?object test:sinh_ra_vào_ngày test:1994-06-28 .\r\n"
							+ "        ?object test:nhãn ?objectName}\r\n" + "        union\r\n"
							+ "        {?object test:tốt_nghiệp_lúc test:1994-06-28 .\r\n"
							+ "         ?object test:nhãn ?objectName}\r\n" + "    }\r\n" + "}",

					// Bao nhiêu sự kiện diễn ra hoặc được tổ chức ở Edinburgh?
					"PREFIX test: <http://test.com/ns#>\r\n" + "select distinct (count(?objectName) as ?count) {\r\n"
							+ graph + "        {?object test:diễn_ra_tại ?place .\r\n"
							+ "            ?place test:nhãn \"Edinburgh\" .\r\n"
							+ "        ?object test:nhãn ?objectName}\r\n" + "        union\r\n"
							+ "        {?object test:được_tổ_chức_ở ?place .\r\n"
							+ "            ?place test:nhãn \"Edinburgh\" .\r\n"
							+ "         ?object test:nhãn ?objectName}\r\n" + "    }\r\n" + "}",

					// Thực thể lớp Country nhiều hơn thực thể lớp Location không?
					"PREFIX test: <http://test.com/ns#>\r\n" + "ask {\r\n"
							+ graph + "        {\r\n"
							+ "            select (count(?country) as ?count1){\r\n"
							+ "           ?person a test:Country .\r\n" + "        }\r\n" + "        }\r\n"
							+ "        {\r\n" + "            select (count(?location) as ?count2){\r\n"
							+ "           ?event a test:Location .\r\n" + "        }\r\n" + "        }\r\n"
							+ "        \r\n" + "    }filter(?count1 > ?count2) .\r\n" + "}\r\n" + "",

					// Thực thể lớp Person ít hơn thực thể lớp Event không?
					"PREFIX test: <http://test.com/ns#>\r\n" + "ask {\r\n" + graph + "        {\r\n"
							+ "            select (count(?person) as ?count1){\r\n"
							+ "           ?person a test:Person .\r\n" + "        }\r\n" + "        }\r\n"
							+ "        {\r\n" + "            select (count(?event) as ?count2){\r\n"
							+ "           ?event a test:Event .\r\n" + "        }\r\n" + "        }\r\n"
							+ "        \r\n" + "    }filter(?count1 < ?count2) .\r\n" + "}\r\n" + "",

			};

			Date dateNow = new Date();
			long timeMilliNow = dateNow.getTime();

			try (RepositoryConnection con = repository.getConnection()) {
				// use the num to pick the queryString
				TupleQuery tupleQuery = con.prepareTupleQuery(queryString[num].toString());
				BooleanQuery booleanQuery = con.prepareBooleanQuery(queryString[num].toString());

				// if users pick number 19 or 20 (ASKing queries)
				if (num == 19 || num == 20) {
					System.out.println(booleanQuery.evaluate());
				}

				else {
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
										System.out.print(
												bindingSet1.getValue(list[i].toString()) + "---------------------");
									}
								}
								System.out.println("");
							}
						}

						// if this node is NULL
						else {
							System.out.println("====>Không có giá trị để truy vấn.");
						}
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

	public static void main(String[] args) throws RDFParseException, RDFHandlerException, IOException {

	}
}
