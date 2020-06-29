package server1package;
import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;
import server1package.classbookroom;

import java.rmi.*;
import java.rmi.server.*;
import java.sql.Timestamp;
import java.util.*;

import server2package.server2interface;
import server3package.server3interface;
import server1package.threadlistner;
import server1package.threadsender1;
public class server1impl extends UnicastRemoteObject implements server1interface 
{
	
	static HashMap<String,HashMap<String,String>> b=new HashMap<String,HashMap<String,String>>();
	static HashMap<String,String> c=new HashMap<String,String>();   
	static HashMap<String,HashMap<String,HashMap<String,String>>> a=new HashMap<String,HashMap<String,HashMap<String,String>>>();
	static HashMap<String,ArrayList<String>> d=new HashMap<String,ArrayList<String>>();
	static ArrayList<String> e=new ArrayList<String>();
	static String bookingid;
	double w1;
	int i=0,cou=0;
	String date;
	String rno;
	String timeslot;
	server2interface si2;
	server3interface si3;
	static int roomcount=0;
	

	public server1impl() throws  RemoteException
	{
		super();
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
	public boolean createroom(String rno,String date,String timeslot)throws RemoteException, FileNotFoundException
	{
		PrintWriter w=null;
		File file=new File("C:/Code Repository/Distributedassignment/src/server1package/createroomlogs.txt");
		try
		{
		w=new PrintWriter(file);
		}
		finally
		{
		w.close();
		}
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
		roomcount++;
		//w.close();
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
		
		
		classbookroom bk=new classbookroom(campusName,rno,date,timeslot,UID); 
		Thread t1=new Thread(bk);
		t1.start();
		t1.join();
		d.get(UID).add(classbookroom.bookingid1);
		if(classbookroom.bookingid1.equals("limitreached"))
		{
			return "limitreached";
		}
		else
		{
			return classbookroom.bookingid1;
		}
		/*
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		int i=0;
		try
		{  
			if(d.isEmpty())
			
			{
				e.add("UID");
				d.put(UID,e);
			}
			Set<String> set=d.keySet();
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
				e.add("UID");
				d.put(UID,e);
				i=0;
			}
			
			if(d.get(UID).size()>3)
			{	System.out.println("You have reached the booking limit already");
				return "";
			}
			else
			{
				si2=(server2interface)Naming.lookup("rmi://localhost:25012/tag2");
				si3=(server3interface)Naming.lookup("rmi://localhost:25013/tag3");
				if(campusName.equals(new String("DVL")))
				{
					bookingid = UUID.randomUUID().toString();
					a.get(date).get(rno).put(timeslot,bookingid);
					roomcount--;
					System.out.println("booked");
				}
				else if(campusName==new String("KKL"))
				{
					bookingid=si2.bookroom(campusName,rno,date,timeslot,UID);
				}
				else if(campusName==new String("WST"))
				{
					bookingid=si3.bookroom(campusName,rno,date,timeslot,UID);
				}
			}
			d.get(UID).add(bookingid);
			
		}
		catch(NotBoundException e ) 
		{
			System.err.println("NotBoundException:"+e);
		}
	catch (MalformedURLException e)
		{
			System.err.println("MalformedURLException"+e);
		}
		*/
	
	}		
	
	public int getAvailableTimeSlot ( String date)throws RemoteException, InterruptedException
	{ 
		
		try
		{
			si2=(server2interface)Naming.lookup("rmi://localhost:25012/tag2");
			si3=(server3interface)Naming.lookup("rmi://localhost:25013/tag3");
			si2.listener(2150,0,date);
			si3.listener(2160,0,date);
			
		}
		catch(NotBoundException e ) 
		{
			System.err.println(e);
		}
		catch (MalformedURLException e)
		{
			System.err.println(e);
		}
	
		String sc1,sc2;
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
		System.out.println("Server1: local count -" + roomcount);
		threadsender1 ts1=new threadsender1(cou,2150);
		threadsender1 ts2=new threadsender1(cou,2160);
		Thread t1=new Thread(ts1);
		Thread t2=new Thread(ts2);
		t1.start();
		t2.start();	
		//while (ts1.ready && ts2.ready) {
			//int total=ts1.count + ts2.count;}
		sc1=ts1.d1;
		sc2=ts2.d1;
		System.out.println("Server1: others count -" + sc1+"-"+sc2);
		t1.join();
		t2.join();
		sc1=ts1.d1;
		sc2=ts2.d1;
		System.out.println("Server1: others count -" + sc1+"-"+sc2);
		
		return roomcount;
		
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
public void cancelBooking (String bookingID)throws RemoteException
{
		 	int c=0;
			String s3=bookingID;
			Set<String> set= a.keySet();
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
				for(i=0;i<3;i++)
				{
					if(bookingID==d.get(s4).get(i))
					d.get(s4).remove(i);
					System.out.println("room deleted");
				}
							
			}
	  }
}