package tombola;

public class Cartella {
	private int[][] caselle;

	public Cartella() {
		caselle = new int[3][9];
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<9; j++) {
				caselle[i][j] = 0;
			}
		}
	}
	
	public void set(int riga, int colonna, int valore) {
		caselle[riga-1][colonna-1] = valore;
	}

	public void stampa() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<9; j++) {
				if(caselle[i][j] == 0) {
					System.out.print("   | ");
				}
				else {
					System.out.print(caselle[i][j] + " | ");					
				}
			}
			System.out.println();
		}
	}
}