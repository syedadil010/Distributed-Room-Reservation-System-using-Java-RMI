package clientpackage;
import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import server1package.server1interface;
import server2package.server2interface;
import server3package.server3interface;



import java.lang.*;

public class homepageclass
{
	
	 void homepagemethod() throws Exception 
	 {
		String ID;
		char a;
		int i;
		clientstudent s;
		clientadmin ad;	
		server1interface si1;
		server2interface si2;
		server3interface si3;
		si1=(server1interface)Naming.lookup("rmi://localhost:25011/tag1");
		si2=(server2interface)Naming.lookup("rmi://localhost:25012/tag2");
		si3=(server3interface)Naming.lookup("rmi://localhost:25013/tag3");
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {	System.out.println("Enter 1 to use room reservation application \t Enter 2 to exit");
			i=Integer.parseInt(br.readLine());

			switch(i)
			{    
				case 1:
				{    
					System.out.println("Enter the User ID");
		
					ID=br.readLine();
					if(ID.charAt(3)=='S')
					{
						s=new clientstudent();
						s.student(ID,si1,si2,si3);
					}

					else if(ID.charAt(3)=='A')
					{
						ad=new clientadmin();
						ad.admin(ID,si1,si2,si3);
					}
					else
					{
						System.out.println("Enter a valid User ID");
					}
					break;
				}
				case 2:
				{
					System.exit(0);
					break;
				}
			}
        }
	}
	
}