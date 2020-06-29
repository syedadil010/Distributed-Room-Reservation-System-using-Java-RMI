package server1package;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class threadsender1 extends Thread{
    private int c = 0;
    public int count=0,d;
    public boolean ready = false;
    public String d1;
    
    public threadsender1(int c,int d)
    {
    	this.c=c;
    	this.d=d;
    }
   
	public void run()
	{
		System.out.println("Inside the sender of server 1");
		int e1;
		DatagramSocket aSocket = null;
		try {
		aSocket = new DatagramSocket();
		byte [] m1 = Integer.toString(c).getBytes();
		InetAddress aHost = InetAddress.getByName("localhost");
		int serverPort = d;
		DatagramPacket request =new DatagramPacket(m1, Integer.toString(c).length(), aHost, serverPort);
		aSocket.send(request);
		byte[] buffer1 = new byte[1000];
		DatagramPacket reply = new DatagramPacket(buffer1, buffer1.length);
		aSocket.receive(reply);
		String str=new String( reply.getData(),reply.getOffset(),reply.getLength());
		//String s5=reply.getData();
		System.out.println(str);
		d1=str;
	   //e1=ByteBuffer.wrap(d1).getInt();
	   //count=e1;
	   ready = true;

		}
		catch (SocketException e)
		{	System.out.println("Socket: " + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("IO: " + e.getMessage());
		}
		finally 
		{
				if(aSocket != null)
				aSocket.close();
		}
	}
}
