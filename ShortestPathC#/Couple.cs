using System;
using System.Collections.Generic;
using System.Text;

namespace ShortestPath
{
    class Couple
    {
        private int x, y;
        public Couple(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}
