package server3package;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
public class server3rmireg {

	public static void main(String args[]) throws RemoteException
	{
		server3impl serv3=new server3impl();
		Registry re=java.rmi.registry.LocateRegistry.createRegistry(25013);
		re.rebind("tag3", serv3);
		System.out.println("server3 is binded");
	}
}
