package test;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.DLV2AnswerSets;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class Test {

	public static void main(String[] args) {
		Handler handler = new DesktopHandler(new DLV2DesktopService("executable/dlv2"));
		InputProgram input = new ASPInputProgram();
		input.addFilesPath("src/test/code1.txt");
		//input.addFilesPath("src/test/code2.txt");
		input.addProgram("test(3).");
		handler.addProgram(input);
		DLV2AnswerSets answerSets = (DLV2AnswerSets) handler.startSync();
		for(AnswerSet answerSet : answerSets.getAnswersets()) {
			System.out.println(answerSet);
		}
		System.out.println(answerSets.getErrors());
	}

}
