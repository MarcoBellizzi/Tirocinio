using it.unical.mat.embasp.languages;
using System;
using System.Collections.Generic;
using System.Text;

namespace BlocksWorld_SPD
{
    [Id("edge")]
    class PickUp
    {
        [Param(0)]
        private string block;

        public PickUp()
        {
            this.block = "";
        }

        public PickUp(string block)
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








