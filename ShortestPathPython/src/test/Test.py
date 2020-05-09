from platforms.desktop.desktop_handler import DesktopHandler
from specializations.dlv2.desktop.dlv2_desktop_service import DLV2DesktopService
#from specializations.dlv.desktop.dlv_desktop_service import DLVDesktopService
from languages.asp.asp_input_program import ASPInputProgram

handler = DesktopHandler(DLV2DesktopService("../../executable/dlv2"))
input_program = ASPInputProgram()
input_program.add_files_path("./code1.txt")
#input_program.add_files_path("./code2.txt")
input_program.add_program("ciao.")
handler.add_program(input_program)
output = handler.start_sync()
print(output.get_output())
print(output.get_errors())