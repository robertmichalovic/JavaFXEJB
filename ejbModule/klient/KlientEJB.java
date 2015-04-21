package klient;
import java.util.Properties;
import server.ServerInterface;
import javax.naming.*;
public class KlientEJB {
	private ServerInterface bean;
	private Context data;
	public ServerInterface getBean() {	return bean;	}					//	getter
	public void setBean(ServerInterface bean) {	this.bean = bean;	}		//	setter
	private Context pripravData() throws NamingException{
		Properties properties = new Properties();
		properties.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
		return new InitialContext(properties);	}
	protected KlientEJB(){					//	konstruktor
		try {
			data = this.pripravData();	} 
		catch (NamingException e) {
			System.out.println("Nepodaøilo se pripravit data");
			e.printStackTrace();	}
		String lookupName = "ejb/ServerEJB01";
		try {
			setBean((ServerInterface) data.lookup(lookupName));	} 
		catch (NamingException e) {
			System.out.println("Nepodaøilo se nacist EJB");
			e.printStackTrace();	}	}
	
}
