package klient.grafika;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
public class PanelSpodek extends Pane{
	private Label text,vysledek;
	protected void nastavVysledek(double d){
		this.vysledek.setText(String.valueOf(d));
	}
	private VBox pridameVBox(BorderNodeRozvrzeni layout) {		
	    VBox vbox = new VBox();									//	layout
	    vbox.setPadding(new Insets(layout.vyska/25,0,0,layout.sirka/5));				//	okraje layoutu ve 4 smerech (vrch,?,?,vlevo)
	    vbox.setSpacing(10);									//	mezery mezi komponenty
	    //vbox.setAlignment(Pos.BOTTOM_CENTER);
	    text = new Label("Vysledek");
	    text.setAlignment(Pos.BOTTOM_CENTER);
	    text.setPrefSize(400,30);
	    vysledek = new Label("?");
	    vysledek.setPrefSize(400,30);
	    vysledek.setAlignment(Pos.BOTTOM_CENTER);
	    vbox.getChildren().addAll(text,vysledek);
	    return vbox;	}
	protected PanelSpodek(BorderNodeRozvrzeni layout){
		//this.setPadding(new Insets(5, 5, 5, 5));						//	NUTNE jinak to nefunguje
		this.setStyle("-fx-background-color: #56eec6;");				//	nastavime barvu pozadi ve formatu HEX
		this.setPrefHeight((layout.vyska/5));
		VBox vbox = pridameVBox(layout);
		this.getChildren().add(vbox);	}
}
