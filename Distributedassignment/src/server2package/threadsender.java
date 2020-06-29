package server2package;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class threadsender extends Thread{
    private int c = 0;
    public int count=0;
    
    public threadsender(int c)
    {
    	this.c=c;
    }
   
	public void run()
	{   int e1;
		DatagramSocket aSocket = null;
		try {
		aSocket = new DatagramSocket();
		byte [] m1 = Integer.toString(c).getBytes();
		InetAddress aHost = InetAddress.getByName("localhost");
		int serverPort = c;
		DatagramPacket request =new DatagramPacket(m1, Integer.toString(c).length(), aHost, serverPort);
		aSocket.send(request);
		byte[] buffer1 = new byte[1000];
		DatagramPacket reply = new DatagramPacket(buffer1, buffer1.length);
		aSocket.receive(reply);
		byte  d1[]=(reply.getData());
	   e1=ByteBuffer.wrap(d1).getInt();
	   count=e1;
		
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
