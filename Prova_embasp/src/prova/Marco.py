from languages.predicate import Predicate

class Marco(Predicate):
    predicate_name = "marco"
    
    def __init__(self, x=None):
        Predicate.__init__(self, [("x")])
        self.x = x
        
    def get_x(self):
        return self.x
    
    def set_x(self, x):
        self.x = x