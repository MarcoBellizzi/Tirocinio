from platforms.desktop.desktop_handler import DesktopHandler
from specializations.dlv.desktop.dlv_desktop_service import DLVDesktopService
#from specializations.dlv2.desktop.dlv2_desktop_service import DLV2DesktopService
from languages.asp.asp_mapper import ASPMapper
from languages.asp.asp_input_program import ASPInputProgram
from predicates.Path import Path
from predicates.Edge import Edge
from predicates.Source import Source
from predicates.Destination import Destination

def getEdges():
    edges = []
    
    edges.append(Edge(0,1,1))
    edges.append(Edge(0,2,4))
    edges.append(Edge(1,2,2))
    edges.append(Edge(1,3,4))
    edges.append(Edge(1,4,1))
    edges.append(Edge(2,4,4))
    edges.append(Edge(3,5,6))
    edges.append(Edge(3,6,1))
    edges.append(Edge(4,3,1))
    edges.append(Edge(6,4,5))
    edges.append(Edge(6,5,9))
    edges.append(Edge(6,7,1))
    edges.append(Edge(7,5,2))
    
    return edges

def join(source, path, sortedPath):
    for p in path :
        if(int(p.get_source()) == int(source)) :
            sortedPath.append(p.get_destination())
            if(int(p.get_destination()) == destination) :
                return
            join(p.get_destination(), path, sortedPath)
            return

def show(path, sum_):
    first = True
    print("path = ", end='')
    for n in path :
        if not first :
            print(" - ", end='')
        else :
            first = False
        print(n, end='')
    print("\nsum = " + str(sum_))
    

try:

    handler = DesktopHandler(DLVDesktopService("../lib/dlv"))
    #handler = DesktopHandler(DLV2DesktopService("../lib/dlv2"))

    ASPMapper.get_instance().register_class(Edge)
    ASPMapper.get_instance().register_class(Path)
    ASPMapper.get_instance().register_class(Source)
    ASPMapper.get_instance().register_class(Destination)

    inputProgram = ASPInputProgram()

    source = 0
    destination = 7

    inputProgram.add_files_path("../encoding/rulesDlv")
    #inputProgram.add_files_path("../encoding/rulesDlv2")

    inputProgram.add_program("source(" + str(source) + "). destination(" + str(destination) + ").")
    inputProgram.add_objects_input(getEdges())

    handler.add_program(inputProgram)

    answerSets = handler.start_sync()

    for answerSet in answerSets.get_answer_sets() :
    
        path = []
        sum_ = 0
    
        for obj in answerSet.get_atoms():
        
            if isinstance(obj, Path):
            
                path.append(obj)
                sum_ += int(obj.get_weight())


        sortedPath = []
        sortedPath.append(source)
    
        join(source, path, sortedPath)
        show(sortedPath, sum_)

except Exception as e:
    print(str(e))
    
    
    
    
    
    
    
    
    
    
    
    
    