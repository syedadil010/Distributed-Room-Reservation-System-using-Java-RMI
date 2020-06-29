package server1package;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import server1package.server1impl;
import server2package.server2interface;
import server3package.server3interface;

public class classbookroom extends Thread {
	
String rno,date,timeslot,UID,campusName;
static String bookingid1;
server2interface si2;
server3interface si3;

	public classbookroom(String campusName,String rno,String date, String timeslot,String UID)
	{   this.campusName=campusName;
		this.rno=rno;
		this.date=date;
		this.timeslot=timeslot;
		this.UID=UID;
	}
		
	
	
public void run()
{
			int i=0;
			try
			{  
				if(server1impl.d.isEmpty())
				{
					server1impl.e.add("UID");
					server1impl.d.put(UID,server1impl.e);
				}
				Set<String> set=server1impl.d.keySet();
				Iterator it=set.iterator();
				while(it.hasNext())
				{
				
					String s=(String)it.next();		
					if(UID==s)
					{
						i++;
					}
				}
				if(i==0)
				{
					server1impl.e.add("UID");
					server1impl.d.put(UID,server1impl.e);
					i=0;
				}
				
				if(server1impl.d.get(UID).size()>3)
				{	System.out.println("You have reached the booking limit already");
					bookingid1="limitreached";
				}
				else
				{   
					si2=(server2interface)Naming.lookup("rmi://localhost:25012/tag2");
					si3=(server3interface)Naming.lookup("rmi://localhost:25013/tag3");
					if(campusName.equals(new String("DVL")))
					{
						bookingid1 = UUID.randomUUID().toString();
						server1impl.a.get(date).get(rno).put(timeslot,bookingid1);
						server1impl.roomcount--;
						System.out.println("booked");
					}
					else if(campusName==new String("KKL"))
					{
						bookingid1=si2.bookroom(campusName,rno,date,timeslot,UID);
					}
					else if(campusName==new String("WST"))
					{
						bookingid1=si3.bookroom(campusName,rno,date,timeslot,UID);
					}
				
				}
				
				server1impl.d.get(UID).add(bookingid1);
				
			}
			catch(NotBoundException e ) 
			{
				System.err.println("NotBoundException:"+e);
			}
		catch (MalformedURLException e)
			{
				System.err.println("MalformedURLException"+e);
			}
			catch(Exception e)
			{
				
			}
			
}		
}
