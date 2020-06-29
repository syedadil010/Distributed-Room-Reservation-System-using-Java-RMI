package server2package;

import java.io.IOException;
import server2package.server2impl;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class threadlistner extends Thread
{
	public int count;
	private int c=0,d=0;
	String date ;
	public threadlistner (int c ,int d ,String date)
	{
		this.c=c;
		this.d=d;
		this.date=date;
		
	}
	public void run()
	{
         System.out.println("Inside the listerner of server 2");
		int c=0;
		DatagramSocket dSocket = null;
		try
		{      // int f=server2impl.roomcount;
			int f=1115;
				dSocket = new DatagramSocket(d);
				byte[] buffer4 = new byte[1000];
				buffer4=Integer.toString(f).getBytes();
				while(true)
				{
					
					DatagramPacket request2 = new DatagramPacket(buffer4, buffer4.length);
						DatagramPacket request4 = new DatagramPacket(buffer4, buffer4.length);
						dSocket.receive(request4);
						
						DatagramPacket reply = new DatagramPacket(request2.getData(),
						request2.getLength(), request4.getAddress(), request4.getPort());
						dSocket.send(reply);
					
				}
		}
		catch (SocketException e)
		{
				System.out.println("Socket: " + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("IO: " + e.getMessage());
			
		}
	
	    finally 
	   {
		if(dSocket != null) 
			dSocket.close();}
     	}
	
	}
	

