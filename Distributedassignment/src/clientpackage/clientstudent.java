package clientpackage;

import java.io.*;
	
import java.lang.*;
import server1package.server1interface;
import server2package.server2interface;
import server3package.server3interface;

public class clientstudent 
{	
		
		public void student(String Id,server1interface serv1,server2interface serv2,server3interface serv3)throws IOException, InterruptedException
		{
			String rno;
			String date;
			int No1,No2;
			String campusname;
			String timeslot;
			String bookingid;
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter 1 to bookroom,Enter 2 to get available timeslot,Enter 3 to cancelbooking");
 
			String campuss=Id.substring(0,3);
			No1=Integer.parseInt(br.readLine());
        	switch(No1)
        	{
        	case 1:
        	{
        		System.out.println("Enter campus name,date,rno,timeslot");
        		campusname=br.readLine();
        		date=br.readLine();
        		rno=br.readLine();
 
        		timeslot=br.readLine();
        		if(campuss.equals(new String("DVL")))
        		{	
        			bookingid=serv1.bookroom(campusname, rno, date, timeslot,Id);  
        			if(bookingid.equals("limitreached"))
        			{
        				System.out.println("you have reached the limit");
        			}
        			else
        			{
        			System.out.println("your booking id is "+bookingid);}
        		}
        		else if(campuss.equals(new String("KKL")))
        		{
        			bookingid=serv2.bookroom(campusname, rno, date,timeslot,Id);
        			System.out.println("your booking id is "+bookingid);
        		}
        		else if(campuss.equals(new String("WST")))
        		{
        			bookingid=serv3.bookroom(campusname, rno, date, timeslot,Id);
        			System.out.println("your booking id is "+bookingid);
        	
        		}
        		break;
        	}
        	case 2:
        	{
        		System.out.println("enter the date");
        		date=br.readLine();
        		if(campuss.equals(new String("DVL")))
        		{	
        			No2=serv1.getAvailableTimeSlot(date);
        			System.out.println(No2);
        		}
        		else if(campuss.equals(new String("KKL")))
        		{
        			No2=serv2.getAvailableTimeSlot(date);
        			System.out.println(No2);
        		}
        		else if(campuss.equals(new String("WST")))
        		{
        			No2=serv3.getAvailableTimeSlot(date);
        			System.out.println(No2);
        		}
        	
        		break;
        	}
        	case 3:
        	{
        		System.out.println("enter the bookind id");
        		bookingid=br.readLine();
        		if(campuss.equals(new String("DVL")))
        		{	
        			serv1.cancelBooking(bookingid);		
        		}
        		
        		else if(campuss.equals(new String("KKL")))
        		{

        			serv2.cancelBooking(bookingid);	
        		}
        		else if(campuss.equals(new String("WST")))
        		{
        			serv3.cancelBooking(bookingid);	
        		}
        		break;
        	}
        }
		}
	}
/*class studentThread
{
	public static void main(String args[])
	{
			
	}
}*/