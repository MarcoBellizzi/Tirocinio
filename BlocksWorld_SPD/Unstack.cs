using it.unical.mat.embasp.languages;
using System;
using System.Collections.Generic;
using System.Text;

namespace BlocksWorld_SPD
{
    [Id("stack")]
    class Unstack
    {
        [Param(0)]
        private string block1;

        [Param(1)]
        private string block2;

        public Unstack()
        {
            this.block1 = "";
            this.block2 = "";
        }

        public Unstack(string block1, string block2)
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

        public void setBlock2(String block2)
        {
            this.block2 = block2;
        }

        public override String ToString()
        {
            return "stack(block1 = " + block1 + ", block2 = " + block2 + ")";
        }
    }
}








