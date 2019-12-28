using it.unical.mat.embasp.@base;
using it.unical.mat.embasp.languages.asp;
using it.unical.mat.embasp.platforms.desktop;
using it.unical.mat.embasp.specializations.dlv2.desktop;
using ShortestPath.predicati;
using System;
using System.Collections.Generic;

namespace ShortestPath
{
    class Program
    {
        private static int from, to;
        private static List<int> sortedPath;

        public static void Main(string[] args)
        {
            try
            {
                Handler handler = new DesktopHandler(new DLV2DesktopService("C:/Users/Marco/Desktop/ShortestPathC#/dlv2.win.x64_2"));

                ASPMapper.Instance.RegisterClass(typeof(Edge));
                ASPMapper.Instance.RegisterClass(typeof(Path));
                ASPMapper.Instance.RegisterClass(typeof(From));
                ASPMapper.Instance.RegisterClass(typeof(To));

                InputProgram input = new ASPInputProgram();

                from = 0;   // source node
                to = 7;     // destination node

                input.AddProgram("from(" + from + "). to(" + to + ").");

                input.AddFilesPath("C:/Users/Marco/Desktop/ShortestPathC#/encoding.txt");

                foreach (Edge edge in getEdges())
                {
                    input.AddObjectInput(edge);
                }

                handler.AddProgram(input);

                AnswerSets answerSets = (AnswerSets)handler.StartSync();

                foreach (AnswerSet answerSet in answerSets.GetOptimalAnswerSets())
                {

                    List<Path> path = new List<Path>();    //  edges in the shortest path (unsorted)
                    int sum = 0;                           //  total weight of the path

                    foreach (object obj in answerSet.Atoms)
                    {
                        if (typeof(Path).IsInstanceOfType(obj))
                        {
                            path.Add((Path)obj);
                            sum += ((Path)obj).getWeight();
                        }
                    }

                    sortedPath = new List<int>();     // edges in the shorted path (sorted)
                    sortedPath.Add(from);

                    join(from, path, sortedPath);     // sorts the edges
                    print(sortedPath, sum);           // show the result
                }
            
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Source);
            }
        }

        private static List<Edge> getEdges()
        {
            List<Edge> edges = new List<Edge>();

            edges.Add(new Edge(0, 1, 1));
            edges.Add(new Edge(0, 2, 4));
            edges.Add(new Edge(1, 2, 2));
            edges.Add(new Edge(1, 3, 4));
            edges.Add(new Edge(1, 4, 1));
            edges.Add(new Edge(2, 4, 4));
            edges.Add(new Edge(3, 5, 6));
            edges.Add(new Edge(3, 6, 1));
            edges.Add(new Edge(4, 3, 1));
            edges.Add(new Edge(6, 4, 5));
            edges.Add(new Edge(6, 5, 9));
            edges.Add(new Edge(6, 7, 1));
            edges.Add(new Edge(7, 5, 2));

            return edges;
        }

       private static void join(int from, List<Path> path, List<int> sortedPath)
       {
            foreach(Path p in path)
            {
                if(p.getFrom() == from)
                {
                    sortedPath.Add(p.getTo());
                    if(p.getTo() == to)
                    {
                        return;
                    }
                    join(p.getTo(), path, sortedPath);
                    return;
                }
            }
       }
        
        private static void print(List<int> path, int sum)
        {
            Console.Write("path = ");
            bool first = true;
            foreach(int n in path)
            {
                if(!first)
                {
                    Console.Write(" - ");
                }
                else
                {
                    first = false;
                }
                Console.Write(n);
            }
            Console.WriteLine("\nsum = " + sum);
        }

    }
}
