package clientpackage;
import java.io.*;
import server1package.server1interface;
import server2package.server2interface;
import server3package.server3interface;
public class clientadmin 
{
	

	String rno;
	int No,No1;
	String date;
	String timeslot;
	boolean response;
	public void admin(String Id,server1interface serv1,server2interface serv2,server3interface serv3)throws IOException
	{
        String s1="DVL";
		String s2="KKL";
		String s3="WST";
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String campuss=Id.substring(0,3);
		System.out.println("Enter 1 to create a reccord ,enter 2 to delete a record ");
		No1=Integer.parseInt(br.readLine());
		switch(No1)
		{
			case 1:
	
			{
				System.out.println("Enter Room No,Date(dd-mm-yyyy)");
				rno=(br.readLine());
				date=br.readLine();
				System.out.println("Enter the slot as HR:MN-HR:MN");
				timeslot=br.readLine();
				if(campuss.equals(s1))
				{	
					response=serv1.createroom(rno,date,timeslot);
					if(response)
					{
						System.out.println("Room Creation Was Succesful");
					}
					else
					{
						System.out.println("Room Creation Was Unsuccesful");
					}
			
				}
				else if(campuss.equals(s2))
				{
    		
					response=serv2.createroom(rno,date,timeslot);
					if(response)
					{
						System.out.println("Room Creation Was Succesful");
					}
					else
					{
						System.out.println("Room Creation Was Unsuccesful");
					}
				}
				else if(campuss.equals(s3))
				{
					response=serv3.createroom(rno,date,timeslot);
					if(response)
					{
						System.out.println("Room Creation Was Succesful");
					}
					else
					{
						System.out.println("Room Creation Was Unsuccesful");
					}
				}
	
				break;
			}
			case 2:
			{
				System.out.println("Enter Room No,Date(dd-mm-yyyy)");
				rno=(br.readLine());
				date=br.readLine();
				System.out.println("Enter No of slots");
		  		No=Integer.parseInt(br.readLine());
				System.out.println("Enter the " + No +"slots one by one as HR:MN-HR:MN");
				timeslot=br.readLine(); 
				if(campuss.equals(s1))
				{	
					response=serv1.deleteroom(rno,date,timeslot);
					if(response)
					{
						System.out.println("Room Deletion Was Succesful");
					}
					else
					{
						System.out.println("Room Deletion Was Unsuccesful");
					}
				}
				else if(campuss.equals(s2))
				{
					response=serv2.deleteroom(rno,date,timeslot);
					if(response)
					{
						System.out.println("Room Deletion Was Succesful");
					}
					else
					{
						System.out.println("Room Deletion Was Unsuccesful");
					}
				}
				else if(campuss.equals(s3))
				{
					response=serv3.deleteroom(rno,date,timeslot);
					if(response)
					{
						System.out.println("Room Deletion Was Succesful");
					}
					else
					{
						System.out.println("Room Deletion Was Unsuccesful");
					}
				}
				break;
			}
		}

	}
}
