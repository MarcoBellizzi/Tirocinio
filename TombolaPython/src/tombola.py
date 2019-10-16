from languages.predicate import Predicate
from languages.asp.asp_mapper import ASPMapper
from platforms.desktop.desktop_handler import DesktopHandler
from specializations.dlv2.desktop.dlv2_desktop_service import DLV2DesktopService
from languages.asp.asp_input_program import ASPInputProgram

class Cartella:
    def __init__(self):
        self.caselle = ["  "] * 3
        for i in range(3):
            self.caselle[i] = ["  "] * 9
        
    def set(self, riga, colonna, valore):
        self.caselle[(int(riga))-1][(int(colonna))-1] = valore
        
    def stampa(self):
        for riga in range(3):
            for colonna in range(9):
                print(self.caselle[riga][colonna], end=' | ')
            print("")
            
class Casella(Predicate):
    predicate_name = "casella"
    
    def __init__(self, riga=None, colonna=None, valore=None):
        Predicate.__init__(self, [("riga"),("colonna"),("valore")])
        self.riga = riga
        self.colonna = colonna
        self.valore = valore
        
    def get_riga(self):
        return self.riga
    
    def get_colonna(self):
        return self.colonna
    
    def get_valore(self):
        return self.valore
    
    def set_riga(self, riga):
        self.riga = riga
        
    def set_colonna(self, colonna):
        self.colonna = colonna
        
    def set_valore(self, valore):
        self.valore = valore
                

ASPMapper.get_instance().register_class(Casella)

inputProgram = ASPInputProgram()
inputProgram.add_files_path("./codice")

handler = DesktopHandler(DLV2DesktopService("../lib/dlv2"))
handler.add_program(inputProgram)

out = handler.start_sync()

cartella = Cartella()

for ans in out.get_answer_sets():
    for obj in ans.get_atoms():
        if isinstance(obj, Casella):
            cartella.set(obj.get_riga(), obj.get_colonna(), obj.get_valore())
        
cartella.stampa()




