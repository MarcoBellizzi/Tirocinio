from languages.predicate import Predicate

class PutDown(Predicate):
    predicate_name = "pick-up"
    
    def __init__(self, block=None):
        Predicate.__init__(self, [("block")])
        self.block = block
        
    def get_block(self):
        return self.block
    
    def set_block(self, block):
        self.block = block
        
    def __str__(self):
        return "pick-up(block = " + self.block + ")"