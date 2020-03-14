using it.unical.mat.embasp.languages;
using System;
using System.Collections.Generic;
using System.Text;

namespace BlocksWorld_SPD
{
    [Id("pick-up")]
    class PutDown
    {
        [Param(0)]
        private string block;

        public PutDown()
        {
            this.block = "";
        }

        public PutDown(string block)
        {
            this.block = block;
        }

        public string getBlock()
        {
            return this.block;
        }

        public void setBlock(string block)
        {
            this.block = block;
        }

        public override string ToString()
        {
            return "pick-up(block = " + block + ")";
        }
    }
}








