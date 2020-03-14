using it.unical.mat.embasp.@base;
using it.unical.mat.embasp.languages.pddl;
using it.unical.mat.embasp.platforms.desktop;
using it.unical.mat.embasp.specializations.solver_planning_domains.desktop;
using System;
using System.IO;

namespace BlocksWorld_SPD
{
    class Program
    {
        static void Main(string[] args)
        {
            Handler handler = new DesktopHandler(new SPDDesktopService());

            InputProgram inputDomain = new PDDLInputProgram(PDDLProgramType.DOMAIN);
            inputDomain.AddFilesPath(".." + Path.DirectorySeparatorChar + ".." + Path.DirectorySeparatorChar 
                + ".." + Path.DirectorySeparatorChar + "domain.pddl");

            InputProgram inputProblem = new PDDLInputProgram(PDDLProgramType.PROBLEM);
            inputProblem.AddFilesPath(".." + Path.DirectorySeparatorChar + ".." + Path.DirectorySeparatorChar
                + ".." + Path.DirectorySeparatorChar + "p01.pddl");

            handler.AddProgram(inputDomain);
            handler.AddProgram(inputProblem);

            try
            {
                PDDLMapper.Instance.RegisterClass(typeof(PickUp));
                PDDLMapper.Instance.RegisterClass(typeof(PutDown));
                PDDLMapper.Instance.RegisterClass(typeof(Stack));
                PDDLMapper.Instance.RegisterClass(typeof(Unstack));

                Output output = handler.StartSync();

                if (!(typeof(Plan).IsInstanceOfType(output)))
                {
                    return;
                }

                Plan plan = (Plan)output;

                foreach(object obj in plan.ActionsObjects)
                {
                    if (typeof(PickUp).IsInstanceOfType(obj) || typeof(PutDown).IsInstanceOfType(obj) 
                        || typeof(Stack).IsInstanceOfType(obj) || typeof(Unstack).IsInstanceOfType(obj)) {

                        Console.WriteLine(obj.ToString());
                    }
                }

            }
            catch (Exception e)
            { 
                Console.WriteLine(e.Message);
            }
        }
    }
}
