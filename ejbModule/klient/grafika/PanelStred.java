package klient.grafika;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class PanelStred extends Pane{
	@SuppressWarnings("rawtypes")
	private ChoiceBox cb;
	private Button tlac;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private VBox pridameVBox(BorderNodeRozvrzeni layout) {		
	    VBox vbox = new VBox();									//	layout
	    vbox.setPadding(new Insets(layout.vyska/20,0,0,layout.sirka/5));				//	okraje layoutu ve 4 smerech (vrch,?,?,vlevo)
	    vbox.setSpacing(50);									//	mezery mezi komponenty
	    //vbox.setAlignment(Pos.BASELINE_CENTER);
	    vbox.setAlignment(Pos.BOTTOM_CENTER);
	    //vbox.setAlignment(Pos.CENTER);
	    //vbox.setAlignment(Pos.TOP_CENTER);
	    Label text = new Label("Vyberte si matematickou operaci");
	    cb = new ChoiceBox(FXCollections.observableArrayList("+ sèítaní", "- odeèítání", "* násobení","/ dìlení"));
	    cb.setPrefSize(200,30);
	    //cb.setValue("+ sèítaní");
	    cb.setTooltip(new Tooltip("Vyberte si matematickou operaci"));
	    cb.addEventHandler(ActionEvent.ACTION, new AktivaceButtonu(this,layout));
	    /*	-	anonymni trida registrujici zmenu nastaveni ChoiceBoxu  - nefunguje pokud je nastaveno napr. "+ sèítaní" a zvolim "+ sèítaní" 
	    cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {  System.out.println();	}    
	    });*/
	    tlac = new Button("Vypoèti");
	    tlac.setDisable(true);
	    tlac.setTooltip(new Tooltip("Po vybrani matematického vypoctu stisknete pro vysledek"));
	    tlac.setPrefSize(400, 100);
	    tlac.setOnAction(new UdalostTlacitko(layout));			//	prichytneme udalost1 - klik
	    vbox.getChildren().addAll(text,cb,tlac);
	    return vbox;	}
	protected PanelStred(BorderNodeRozvrzeni layout){	//	konstruktor
		this.setPadding(new Insets(5, 5, 5, 5));						//	NUTNE jinak to nefunguje
		this.setStyle("-fx-background-color: #85bbe1;");				//	nastavime barvu pozadi ve formatu HEX
		VBox vbox = pridameVBox(layout);
		this.getChildren().add(vbox);	}
	private class AktivaceButtonu implements EventHandler<ActionEvent>{
		private PanelStred temp;
		private BorderNodeRozvrzeni layout;
		protected AktivaceButtonu(PanelStred panelStred,BorderNodeRozvrzeni layout) {		//	konstruktor
			this.temp=panelStred;
			this.layout=layout;	}
		public void handle(ActionEvent arg0) {
			//	kontrola na vstupy .... nutne aby nešlo odesilat nesmysli
			String textField1 = layout.vrch.ziskejVstup1().getText();
			String textField2 = layout.vrch.ziskejVstup2().getText();
			if(textField1.length()>0 & textField2.length()>0)	{
				try {
					try {
						@SuppressWarnings("unused")
						double cislo = Double.valueOf(textField1).doubleValue();	}
					catch(NumberFormatException e) {	
						JOptionPane.showMessageDialog(null,"Nejedna se o èíslo v levem textfieldu ", "Pozor", JOptionPane.ERROR_MESSAGE);	}
					try {
						@SuppressWarnings("unused")
						double cislo = Double.valueOf(textField2).doubleValue();	}
					catch(NumberFormatException e) {	
						JOptionPane.showMessageDialog(null,"Nejedna se o èíslo v pravem textfieldu ", "Pozor", JOptionPane.ERROR_MESSAGE);	}	
					this.temp.tlac.setDisable(false);	}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Nepodarilo se zobrazit button, definujte èisla ", "Pozor", JOptionPane.ERROR_MESSAGE);	}	}	}
	}
	private class UdalostTlacitko implements EventHandler<ActionEvent> {
		private BorderNodeRozvrzeni layout;
		public UdalostTlacitko(BorderNodeRozvrzeni layout) {
			this.layout=layout;	}
		private double ziskejDouble(String text){
			double cislo=0;
			try {
				cislo = Double.valueOf(text).doubleValue();	}
			catch(NumberFormatException e) {	
				JOptionPane.showMessageDialog(null,"Nejedna se o èíslo", "Pozor", JOptionPane.ERROR_MESSAGE);	}
			return cislo;	}
		public void handle(ActionEvent arg0) {
			double cislo1 = ziskejDouble(layout.vrch.ziskejVstup1().getText());
			double cislo2 = ziskejDouble(layout.vrch.ziskejVstup2().getText());
			String text= layout.stred.cb.getValue().toString();
			System.out.println("Zde jsou vstupy :"+cislo1+"\t"+cislo2+"\t"+text);
			double vysledek = layout.frame.getKlientEJB().getBean().provedVypocet(cislo1, cislo2, text);
			System.out.println("Info : "+vysledek);
			layout.spodek.nastavVysledek(vysledek);	}
	}
}
