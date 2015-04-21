package klient;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import klient.grafika.BorderNodeRozvrzeni;
public class KlientFX extends Application {								//	Objekt okna - tvz.frame
	private KlientEJB klientEJB;
	public KlientEJB getKlientEJB() { return klientEJB; }
	public void init(){
		klientEJB = new KlientEJB();
	}
	public void start(Stage frame) {
		frame.setTitle("Program pro kalkulacu pres Java EE EJB");		//	Titulek okna
		frame.centerOnScreen();											//	prichytneme na stred
		BorderNodeRozvrzeni rozvrzeni = new BorderNodeRozvrzeni(this);
		Scene scene = new Scene(rozvrzeni);
		frame.setScene(scene);
		frame.show();	}
	public static void main(String[] args) {
		Application.launch(args);	}
}

