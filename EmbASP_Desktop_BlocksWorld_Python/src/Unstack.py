from languages.predicate import Predicate

class Unstack(Predicate):
    predicate_name = "stack"
    
    def __init__(self, block1=None, block2=None):
        Predicate.__init__(self, [("block1"),("block2")])
        self.block1 = block1
        self.block2 = block2
        
    def get_block1(self):
        return self.block1
    
    def get_block2(self):
        return self.block2
    
    def set_block1(self, block1):
        self.block1 = block1
        
    def set_block2(self, block2):
        self.block2 = block2
        
    def __str__(self):
        return "stack(block1 = " + self.block1 + ", block2 = " + self.block2 + ")"