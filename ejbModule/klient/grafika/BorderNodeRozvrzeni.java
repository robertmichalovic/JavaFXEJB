//	barva FX pozadi  - http://www.colorhexa.com/
package klient.grafika;
import javafx.scene.layout.BorderPane;
import klient.KlientFX;
public class BorderNodeRozvrzeni extends BorderPane {			//	objekt rozvrzeni - layout
	protected int vyska=600,sirka=700;
	protected PanelVrch vrch;
	protected PanelStred stred;
	protected PanelSpodek spodek;
	protected KlientFX frame;
	public BorderNodeRozvrzeni(KlientFX frame){								//	konstruktor
		this.frame=frame;
		vrch = new PanelVrch(this);
		stred = new PanelStred(this);
		spodek = new PanelSpodek(this);
		
		this.setPrefSize(sirka,vyska);
		this.setTop(vrch);
		this.setCenter(stred);
		this.setBottom(spodek);	}
}
