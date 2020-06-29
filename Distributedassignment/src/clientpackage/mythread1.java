package clientpackage;

public class mythread1
{

	public static void main(String[] args) 
	{
		clientobject obj=new clientobject();
		Thread t1=new Thread(obj);
		t1.start();
	
	}
}
