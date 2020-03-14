using it.unical.mat.embasp.languages;
using System;
using System.Collections.Generic;
using System.Text;

namespace BlocksWorld_SPD
{
    [Id("stack")]
    class Stack
    {
        [Param(0)]
        private string block1;

        [Param(1)]
        private string block2;

        public Stack()
        {
            this.block1 = "";
            this.block2 = "";
        }

        public Stack(string block1, string block2)
        {
            this.block1 = block1;
            this.block2 = block2;
        }

        public string getBlock1()
        {
            return this.block1;
        }

        public string getBlock2()
        {
            return this.block2;
        }

        public void setBlock1(string block1)
        {
            this.block1 = block1;
        }

        public void setBlock2(string block2)
        {
            this.block2 = block2;
        }

        public override string ToString()
        {
            return "stack(block1 = " + block1 + ", block2 = " + block2 + ")";
        }
    }
}








