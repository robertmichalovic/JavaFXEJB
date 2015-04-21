package klient.grafika;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
public class PanelVrch extends Pane {
	private TextField vstup1Vlevo,vstup2Vpravo;
	protected TextField ziskejVstup1() { return this.vstup1Vlevo;	} 	//	getter 1
	protected TextField ziskejVstup2() { return this.vstup2Vpravo;	}	//	getter 2
	private javafx.scene.layout.VBox pridameVBoxLevy(BorderNodeRozvrzeni layout){
		javafx.scene.layout.VBox panel = new javafx.scene.layout.VBox();
		panel.setPadding(new Insets(5, 5, 5, 5));			//	okraje layoutu ve 4 smerech (vrch,?,?,vlevo)
		Label text = new Label("Èíslo 1:");
		text.setAlignment(Pos.CENTER);
	    vstup1Vlevo = new TextField();
	    vstup1Vlevo.setId("VlevoTextField");					//	nutne kvuli getSource - vymazani textfieldu v pripade vyjimky
	    vstup1Vlevo.setStyle("-fx-font-size: 12pt;");					//	velikost pisma
	    vstup1Vlevo.setTooltip(new Tooltip("Vložte èíslo-používejte desetinou tecku[.]"));
	    vstup1Vlevo.setOnKeyReleased(new KontrolaTextFieldu());
	    vstup1Vlevo.setPrefSize(250, 30);						//	preferovana velikost tlacitka
	    panel.setSpacing(10);									//	mezera mezi komponenty
	    panel.getChildren().addAll(text,vstup1Vlevo);
		return panel;	}
	private javafx.scene.layout.VBox pridameVBoxPravy(BorderNodeRozvrzeni layout){
		javafx.scene.layout.VBox panel = new javafx.scene.layout.VBox();
		panel.setPadding(new Insets(5, 5, 5, 5));			//	okraje layoutu ve 4 smerech (vrch,?,?,vlevo)
		Label text = new Label("Èíslo 2:");
		text.setAlignment(Pos.CENTER);
	    vstup2Vpravo = new TextField();
	    vstup2Vpravo.setId("VpravoTextField");					//	nutne kvuli getSource - vymazani textfieldu v pripade vyjimky
	    vstup2Vpravo.setStyle("-fx-font-size: 12pt;");					//	velikost pisma
	    vstup2Vpravo.setTooltip(new Tooltip("Vložte èíslo-používejte desetinou tecku[.]"));
	    vstup2Vpravo.setOnKeyReleased(new KontrolaTextFieldu());
	    vstup2Vpravo.setPrefSize(250, 30);						//	preferovana velikost tlacitka
	    panel.setSpacing(10);
	    panel.getChildren().addAll(text,vstup2Vpravo);
		return panel;	}
	private HBox pridameHBox(BorderNodeRozvrzeni layout) {		
	    HBox hbox = new HBox();									//	layout
	    hbox.setPadding(new Insets(10, 0, 0, 50));			//	okraje layoutu ve 4 smerech (vrch,?,?,vlevo)
	    hbox.setSpacing(80);									//	mezery mezi komponenty
	    hbox.getChildren().addAll(pridameVBoxLevy(layout),pridameVBoxPravy(layout));	//	pridabe objekty tlacitek do layoutu
	    return hbox;	}
	public PanelVrch(BorderNodeRozvrzeni layout){
		this.setPadding(new Insets(5, 5, 5, 5));								//	NUTNE jinak to nefunguje
		this.setStyle("-fx-background-color: #778675;");						//	nastavime barvu pozadi ve formatu HEX
		this.setPrefHeight((layout.vyska/5));									//	nastavime velikost panelu na vysku
		HBox hbox = pridameHBox(layout);
		this.getChildren().add(hbox);	}
	private class KontrolaTextFieldu implements EventHandler<KeyEvent>{
		public void handle(KeyEvent event) {
			TextField textfield = (TextField) event.getSource();
			String temp = textfield.getText();
			//System.out.println("Info :"+temp+"\tvelikost stringu :"+temp.length());
			if(temp.length()>0)	{
				try {
					@SuppressWarnings("unused")
					double cislo = Double.valueOf(temp).doubleValue();	}
				catch(NumberFormatException e) {	
					JOptionPane.showMessageDialog(null,"Nejedna se o èíslo", "Pozor", JOptionPane.ERROR_MESSAGE);
					if("VlevoTextField".equals(textfield.getId())) 		vstup1Vlevo.setText("");
					else 	vstup2Vpravo.setText("");	}	}	}
	}
}
//	***************  NEVHODNE KODY ***********************
	/*private class KontrolaVstupZnaku implements EventHandler<KeyEvent> {
		public void handle(KeyEvent event) {
			String carka = ",";
			String znak = event.getCharacter(); 					// do promenne kod ulozim string ale uklada se pouze stisk cisla
			System.out.println("znak :"+znak);
			if(!znak.equals(carka)) {				//	nerovna-li se carka s nactenym znakem proved
				try {
					@SuppressWarnings("unused") 
					double cislo = Double.valueOf(znak).doubleValue();
					nastav = false;	}					//	provedeli se v poradku blok try nenastavi se vyjimka nastav na true
				catch(NumberFormatException e) {	
					JOptionPane.showMessageDialog(null,"Nejedna se o èíslo", "Pozor", JOptionPane.ERROR_MESSAGE);
					nastav = true;	}	}	}
	}
	private class VymazTextFieldu implements EventHandler<KeyEvent>{
		public void handle(KeyEvent event) {
			if(nastav){													//	je-li nastav na true provedeme smazani 
				TextField textfield = (TextField) event.getSource();	//	nutne kvuli vymazu a identifikaci zdroje
				nastav = false;
				if("VlevoTextField".equals(textfield.getId())) 		vstup1Vlevo.setText("");
				else 	vstup2Vpravo.setText("");		}	}
	}*/
/*
private class KontrolaNaCislo1 implements EventHandler<KeyEvent> {
public void handle(KeyEvent event) {
	String carka = ",";
	String znak = event.getCharacter(); 					// do promenne kod ulozim string ale uklada se pouze stisk cisla
	TextField textfield = (TextField) event.getSource();	//	nutne kvuli vymazu
	System.out.println("Info :"+textfield+"\tznak :"+znak);
	if(!znak.equals(carka)) {				//	nerovna-li se carka s nactenym znakem proved
		try {
			@SuppressWarnings("unused")
			double cislo = Double.valueOf(znak).doubleValue();	}
		catch(NumberFormatException e) {	
			JOptionPane.showMessageDialog(null,"Nejedna se o èíslo", "Pozor", JOptionPane.ERROR_MESSAGE);
			if("VlevoTextField".equals(textfield.getId())) 	{
				System.out.println("Info1 :"+znak);
				vstup1Vlevo.setText("");	}
			else {
				System.out.println("Info2 :"+znak);
				vstup2Vpravo.setText("");	}	}	}	}
}*/