package tombola;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class Tombola {

	public static void main(String[] args) {

		try {
			ASPMapper.getInstance().registerClass(Casella.class);

			InputProgram input = new ASPInputProgram();
			input.addFilesPath("src/tombola/codice");

			Handler handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));
			handler.addProgram(input);
			
			Cartella cartella = new Cartella();

			AnswerSets answers = (AnswerSets) handler.startSync();
	
			for(AnswerSet a : answers.getAnswersets()){
				for(Object obj : a.getAtoms()) {
					if(obj instanceof Casella) {
						Casella c = (Casella) obj;
						cartella.set(c.getRiga(), c.getColonna(), c.getValore());
					}
				}
			}

			cartella.stampa();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
