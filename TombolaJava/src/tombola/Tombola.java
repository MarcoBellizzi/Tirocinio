package tombola;

import java.util.Random;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class Tombola {

	public static void main(String[] args) {

		try {
			Handler handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));

			ASPMapper.getInstance().registerClass(Casella.class);

			InputProgram input = new ASPInputProgram();
			input.addFilesPath("src/tombola/codice");

			handler.addProgram(input);
			handler.addOption(new OptionDescriptor("-n 1000"));

			AnswerSets answer = (AnswerSets) handler.startSync();
			
			int count = 0;
			for(AnswerSet a : answer.getAnswersets()) {
				count++;
			}
			
			int random = new Random().nextInt(count);
			
			Cartella cartella = new Cartella();
			
			count = -1;
			for(AnswerSet a : answer.getAnswersets()){
				count++;
				if(count == random) {
					for(Object obj : a.getAtoms()) {
						if(obj instanceof Casella) {
							Casella c = (Casella) obj;
							cartella.set(c.getRiga(), c.getColonna(), c.getValore());
						}
					}					
				}
			}

			cartella.stampa();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
