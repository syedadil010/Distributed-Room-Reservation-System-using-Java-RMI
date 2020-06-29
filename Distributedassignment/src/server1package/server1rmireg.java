package server1package;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
public class server1rmireg
{

	public static void main(String args[]) throws RemoteException
	{
		server1impl serv1=new server1impl();
		Registry re=java.rmi.registry.LocateRegistry.createRegistry(25011);
		re.rebind("tag1", serv1);
		System.out.println("server1 is binded");
	}
}
 