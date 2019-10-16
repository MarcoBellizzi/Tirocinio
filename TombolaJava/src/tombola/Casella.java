package tombola;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("casella")
public class Casella {
	@Param(0)
	private int riga;
	
	@Param(1)
	private int colonna;
	
	@Param(2)
	private int valore;
	
	public Casella() {}
	
	public Casella(int riga, int colonna, int valore) {
		this.riga = riga;
		this.colonna = colonna;
		this.valore = valore;
	}

	public int getRiga() {
		return riga;
	}

	public void setRiga(int riga) {
		this.riga = riga;
	}

	public int getColonna() {
		return colonna;
	}

	public void setColonna(int colonna) {
		this.colonna = colonna;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}
	
	
}
