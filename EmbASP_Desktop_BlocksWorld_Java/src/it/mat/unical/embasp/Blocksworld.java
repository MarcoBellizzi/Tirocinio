package it.mat.unical.embasp;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.pddl.PDDLInputProgram;
import it.unical.mat.embasp.languages.pddl.PDDLMapper;
import it.unical.mat.embasp.languages.pddl.PDDLProgramType;
import it.unical.mat.embasp.languages.pddl.Plan;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.solver_planning_domains.desktop.SPDDesktopService;

public class Blocksworld {
	
	private static String domainFileName = "domain.pddl";
	private static String problemFileName = "p01.pddl";;

	public static void main(String[] args) {
		Handler handler = new DesktopHandler(new SPDDesktopService());
		
		final InputProgram inputProgramDomain = new PDDLInputProgram(PDDLProgramType.DOMAIN);
		inputProgramDomain.addFilesPath(domainFileName);

		final InputProgram inputProgramProblem = new PDDLInputProgram(PDDLProgramType.PROBLEM);
		inputProgramProblem.addFilesPath(problemFileName);

		handler.addProgram(inputProgramDomain);
		handler.addProgram(inputProgramProblem);

		try {

			PDDLMapper.getInstance().registerClass(PickUp.class);
			PDDLMapper.getInstance().registerClass(PutDown.class);
			PDDLMapper.getInstance().registerClass(Stack.class);
			PDDLMapper.getInstance().registerClass(Unstack.class);

			Output output = handler.startSync();
			
			if (!(output instanceof Plan))
				return; 
			
			Plan plan = (Plan) output;
			
			for (final Object obj : plan.getActionsObjects())
				if (obj instanceof PickUp || obj instanceof Stack
						 || obj instanceof Unstack || obj instanceof PutDown) 
					System.out.println(obj.toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

