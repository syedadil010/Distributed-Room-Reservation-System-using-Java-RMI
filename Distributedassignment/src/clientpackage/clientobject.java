package clientpackage;
public class clientobject implements Runnable
{
	public void run() 
	{  
		
		try
		{
			homepageclass s1=new homepageclass();
			s1.homepagemethod();
		}
	    catch(Exception e)  
		{
		
	    }
	}
	

}
