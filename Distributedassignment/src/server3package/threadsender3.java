package server3package;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class threadsender3 extends Thread{
    private int c = 0;
    public int count,d;
    public boolean ready = false;
    
    public threadsender3(int c, int d)
    {
    	this.c=c;
    	this.d=d;
    }
   
	public void run()
	{   int e1;
	System.out.println(" Inside the sender of server 3");
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
		byte  d1[]=(reply.getData());
	    e1=ByteBuffer.wrap(d1).getInt();
	    count=e1;
	    ready =true;
		
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
