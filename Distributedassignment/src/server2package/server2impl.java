package server2package;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import server1package.server1interface;

import server2package.server2interface;
import server2package.threadlistner;
import server2package.threadsender2;
import server3package.server3interface;
public class server2impl extends UnicastRemoteObject implements server2interface {
	
	HashMap<String,HashMap<String,String>> b=new HashMap<String,HashMap<String,String>>();
	HashMap<String,String> c=new HashMap<String,String>();   
	HashMap<String,HashMap<String,HashMap<String,String>>> a=new HashMap<String,HashMap<String,HashMap<String,String>>>();
	HashMap<String,ArrayList<String>> d=new HashMap<String,ArrayList<String>>();
	ArrayList<String> e=new ArrayList<String>();
	String bookingid;
	double w1;
	int i=0,cou=0;
	String date;
	String rno;
	String timeslot;
	server1interface si1;
	server3interface si3;
	static int  roomcount;
	

	public server2impl() throws  RemoteException
	{
		super();
	}
	public int localcount(String date)
	{
		int lcount=0;
		String s3="Available";

		Set<String> set= a.get(date).keySet();
		Iterator it=set.iterator();
		while(it.hasNext())
		{
			String s =(String)it.next();
			Set<String> set1 =	a.get(date).get(s).keySet();
			Iterator it1 =set1.iterator();
			while(it1.hasNext())
			{
				String s1=(String)it1.next();
				if(s3.equals(a.get(date).get(s).get(s1)));
				lcount++;
			}
	
		}
		return lcount;
	}
	public void listener(int a,int b,String date) throws RemoteException
	{
		threadlistner tl1=new threadlistner(cou,a,date);
		//threadlistner tl2=new threadlistner(cou,b);
		Thread t3=new Thread(tl1);
		//Thread t4=new Thread(tl2);
		t3.start();
		//t4.start();
	}
	
	public boolean createroom(String rno,String date,String timeslot)throws RemoteException
	{
		Set<String> setr=b.keySet();
		Set<String> sett=c.keySet();
		Iterator ir=setr.iterator();
		Iterator it=sett.iterator();
		while(ir.hasNext())
		{	
			String s=(String)ir.next();
			if(s.equals(rno))
				{
					while(it.hasNext())
					{
		                String s1=(String)it.next();
						if(s1.equals(timeslot))
						{
							System.out.println("Timeslot Already Exists");
							return false;
						}
					}
				}
		}
			
				c.put(timeslot,"Available");
				b.put(rno,c);
				a.put(date,b);		
				System.out.println(c);
		roomcount++;
		return true;
	}	
	public boolean deleteroom(String rno, String date, String timeslot)throws RemoteException
	{
		
		a.get(date).get(rno).remove(timeslot);
		roomcount--;
		return true;
	}
	public String bookroom(String campusName,String rno,String date,String timeslot,String UID)throws RemoteException, InterruptedException
	{
		//System.out.println("in bookromm");
	//	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		int i=0;
		try
		{  
			if(d.isEmpty())
			{  
				e.add("UID");
				d.put(UID,e);	
				System.out.println("in 1st if");
			}
			Set<String> set=d.keySet();
			Iterator it=set.iterator();
			while(it.hasNext())
			{
			
				String s=(String)it.next();
			
				if(UID==s)
				{
					i++;
					System.out.println("in 2st if");
				}
			}
			if(i==0)
			{   e.add("UID");
				d.put(UID,e);
				i=0;
				System.out.println("in 3rd if");
			}
			
			if(d.get(UID).size()>3)
			{	System.out.println("You have reached the booking limit already");
				return "limit reached";
			}
			else
			{
				System.out.println("in else part");
				si1=(server1interface)Naming.lookup("rmi://localhost:25011/tag1");
				si3=(server3interface)Naming.lookup("rmi://localhost:25013/tag3");
				if(campusName.equals(new String("KKL")))
				{
					bookingid=UUID.randomUUID().toString();
					System.out.println(bookingid);
					a.get(date).get(rno).put(timeslot,(bookingid));
					roomcount--;
					System.out.println("booked");
				}
			else if(campusName.equals(new String("DVL")))
				{
					bookingid=si1.bookroom(campusName,rno,date,timeslot,UID);
				}
				else if(campusName.equals(new String("WST")))
				{
					bookingid=si3.bookroom(campusName,rno,date,timeslot,UID);
				}
			}
			d.get(UID).add((bookingid));
	}
	
	catch(NotBoundException e ) 
		{
			
		}
	catch (MalformedURLException e)
		{
		}
		System.out.println("before return");
	return bookingid;
	}
		
		
	
	public int getAvailableTimeSlot ( String date)throws RemoteException, InterruptedException
	{	try
	{
		si1=(server1interface)Naming.lookup("rmi://localhost:25011/tag1");
		si3=(server3interface)Naming.lookup("rmi://localhost:25013/tag3");
		si1.listener(2190,0,date);
		si3.listener(2220,0,date);
}
	catch(NotBoundException e ) 
	{
		System.err.println(e);
		
	}
catch (MalformedURLException e)
	{
	System.err.println(e);
	}
		boolean v1=true;
		boolean v2=true;
		int sc1=0,sc2=0,sc3=0;
		String s3="Available";
		cou=0;
		
	/*	Set<String> set= a.get(date).keySet();
		Iterator it=set.iterator();
		while(it.hasNext())
		{
		String s =(String)it.next();
		Set<String> set1 =	a.get(date).get(s).keySet();
		Iterator it1 =set1.iterator();
		while(it1.hasNext())
		{
			String s1=(String)it1.next();
			if(s3.equals(a.get(date).get(s).get(s1)));
			cou++;
		}
		}*/
		threadsender2 ts1=new threadsender2(cou,2190);
		threadsender2 ts2=new threadsender2(cou,2220);
		Thread t1=new Thread(ts1);
		Thread t2=new Thread(ts2);
		
		t1.start();
		t2.start();
		while (ts1.ready && ts2.ready) {
			cou+=ts1.count + ts2.count;
		}
		t1.join();
		t2.join();

		
		sc1=ts1.count;
		sc2=ts2.count;
		
      return cou;
		
}
	  public void cancelBooking (String bookingID)throws RemoteException
	  {
		  int c=0;
			String s3=bookingID;
			Set<String> set=a.keySet();
			Iterator it=set.iterator();
			while(it.hasNext())
			{
			String s =(String)it.next();
			Set<String> set1 =	a.get(s).keySet();
			Iterator it1 =set1.iterator();
			while(it1.hasNext())
			{
				String s1=(String)it1.next();
				Set<String> set2=a.get(s).get(s1).keySet();
				Iterator it2=set2.iterator();
				while(it2.hasNext())
				{
					String s2=(String)it2.next();
					if(s3.equals(a.get(s).get(s1).get(s2)));
					{	
						a.get(s).get(s1).remove(s2);
						System.out.println("Booking cancellation was successful");
						roomcount++;
					}
						
				}
			}
			}
			Set<String> set3= d.keySet();
			Iterator it3=set3.iterator();
			while(it3.hasNext())
			{
				String s4=(String)it3.next();
				try
				{
				for(i=0;i<3;i++)
				{
					if((bookingID)==d.get(s4).get(i))
					d.get(s4).remove(i);	
				}
				}
				catch(IndexOutOfBoundsException e)
				{
				}
				
			}
	  }
}
