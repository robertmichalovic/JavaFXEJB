package server;
import javax.ejb.Remote;
@Remote
public interface ServerInterface {
	public double provedVypocet(double d1,double d2,String s);
}
