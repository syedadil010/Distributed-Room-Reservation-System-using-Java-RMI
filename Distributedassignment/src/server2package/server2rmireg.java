package server2package;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
public class server2rmireg {

	public static void main(String args[]) throws RemoteException
	{
		server2impl serv2=new server2impl();
		Registry re=java.rmi.registry.LocateRegistry.createRegistry(25012);
		re.rebind("tag2", serv2);
		System.out.println("server2 is binded");
	}
}
