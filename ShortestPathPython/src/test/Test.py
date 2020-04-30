from platforms.desktop.desktop_handler import DesktopHandler
from specializations.dlv2.desktop.dlv2_desktop_service import DLV2DesktopService
#from specializations.dlv.desktop.dlv_desktop_service import DLVDesktopService
from languages.asp.asp_input_program import ASPInputProgram

handler = DesktopHandler(DLV2DesktopService("../../executable/dlv2"))
input_program = ASPInputProgram()
input_program.add_files_path("./code1.txt")
#input_program.add_files_path("./code2.txt")
input_program.add_program("test(2).")
handler.add_program(input_program)
output = handler.start_sync()
for answer_set in output.get_answer_sets() :
    print(answer_set)