from languages.predicate import Predicate

class PickUp(Predicate):
    predicate_name = "pick-up"
    
    def __init__(self, block=None):
        Predicate.__init__(self, [("block")])
        self.block = block
        
    def get_block(self):
        return self.block
    
    def set_x(self, block):
        self.block = block
        
    def __str__(self):
        return "pick-up(block = " + self.block + ")"