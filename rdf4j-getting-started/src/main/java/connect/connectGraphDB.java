package connect;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;

public class connectGraphDB {
	public RepositoryManager repositoryManager;
	public Repository repository;
	public RepositoryConnection con;
	
	public void getConnected() throws UnknownHostException {
		// Remember to turn on GraphDB local server first!
		this.repositoryManager = new RemoteRepositoryManager(
				"http://" + Inet4Address.getLocalHost().getHostAddress() + ":7200");
		this.repositoryManager.initialize();

		// Get the repository from repository manager, note the repository id set inconnec
		// configuration .ttl file
		this.repository = repositoryManager.getRepository("02122018");
		this.con = this.repository.getConnection();
		System.out.println("DB CONNECTED");
	}
	public void getDisconnected() throws UnknownHostException {
		this.con.close();
		this.repository.shutDown();
		this.repositoryManager.shutDown();
		System.out.println("DB DISCONNECTED");
	}
	
	public static void main(String[] args) throws UnknownHostException {
		connectGraphDB connect = new connectGraphDB();
		connect.getConnected();
		connect.getDisconnected();
		
	}
}
