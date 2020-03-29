from platforms.desktop.desktop_handler import DesktopHandler
from specializations.dlv2.desktop.dlv2_desktop_service import DLV2DesktopService
from languages.asp.asp_input_program import ASPInputProgram
from languages.asp.asp_mapper import ASPMapper
from prova.Marco import Marco

try:

    ASPMapper.get_instance().register_class(Marco)

    inputProgram = ASPInputProgram()
    inputProgram.add_files_path("../../lib/codice")
    
    inputProgram.add_program("marco(2).")
    inputProgram.add_object_input(Marco(3))
      
    handler = DesktopHandler(DLV2DesktopService("../../lib/dlv2"))
   
    handler.add_program(inputProgram)

    answer_sets = handler.start_sync()
    
    for answer_set in answer_sets.get_optimal_answer_sets():
        print(str(answer_set))
   
except Exception as e:
    print(str(e))
        