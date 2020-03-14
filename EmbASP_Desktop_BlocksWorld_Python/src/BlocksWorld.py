from platforms.desktop.desktop_handler import DesktopHandler
from specializations.solver_planning_domains.desktop.spd_desktop_service import SPDDesktopService
from languages.pddl.pddl_input_program import PDDLInputProgram
from languages.pddl.pddl_program_type import PDDLProgramType
from languages.pddl.pddl_mapper import PDDLMapper
from PickUp import PickUp
from PutDown import PutDown
from Stack import Stack
from Unstack import Unstack

handler  = DesktopHandler(SPDDesktopService())

input_domain = PDDLInputProgram(PDDLProgramType.DOMAIN)
input_domain.add_files_path("../domain.pddl")

input_problem= PDDLInputProgram(PDDLProgramType.PROBLEM)
input_problem.add_files_path("../p01.pddl")

handler.add_program(input_domain)
handler.add_program(input_problem)

PDDLMapper.get_instance().register_class(PickUp)
PDDLMapper.get_instance().register_class(PutDown)
PDDLMapper.get_instance().register_class(Stack)
PDDLMapper.get_instance().register_class(Unstack)

output = handler.start_sync()

for obj in output.get_actions_objects():
    if isinstance(obj, PickUp) | isinstance(obj, PutDown) | isinstance(obj, Stack) | isinstance(obj, Unstack) :
        print(obj)





