package server;
import javax.ejb.Stateless;
@Stateless(name="EJBServer", mappedName="ejb/ServerEJB01")
public class ServerEJB implements ServerInterface{
	public double provedVypocet(double d1, double d2, String s) {
		double vysledek=5;
		System.out.println("Vstupy :"+d1+"\t"+d2+"\t"+s);
		if(s.equals("+ s��tan�")) 		vysledek = d1+d2;
		if(s.equals("- ode��t�n�")) 	vysledek = d1-d2;
		if(s.equals("* n�soben�")) 		vysledek = d1*d2;
		if(s.equals("/ d�len�")) 		vysledek = d1/d2;
		System.out.println("Vypis na serveru : "+vysledek);
		return vysledek;	}
}
