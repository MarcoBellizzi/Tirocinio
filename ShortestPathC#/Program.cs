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
               
                Handler handler = new DesktopHandler(new DLV2DesktopService("C:/Users/Marco/source/repos/ShortestPath/dlv2.win.x64_2"));

                ASPMapper.Instance.RegisterClass(typeof(Edge));
                ASPMapper.Instance.RegisterClass(typeof(From));
                ASPMapper.Instance.RegisterClass(typeof(To));
                ASPMapper.Instance.RegisterClass(typeof(Path));

                InputProgram input = new ASPInputProgram();

                from = 0;
                to = 7;

                input.AddProgram("from(" + from + "). to(" + to + ").");

                input.AddFilesPath("C:/Users/Marco/source/repos/ShortestPath/codiceASP.txt");

                foreach (Edge edge in getEdges())
                {
                    input.AddObjectInput(edge);
                }

                handler.AddProgram(input);

                AnswerSets answerSets = (AnswerSets)handler.StartSync();

                foreach (AnswerSet answerSet in answerSets.Answersets)
                {
                    List<Couple> path = new List<Couple>();
                    int sum = 0;

                    foreach(object obj in answerSet.Atoms)
                    {
                        if(typeof(Path).IsInstanceOfType(obj))
                        {
                            Path p = (Path)obj;
                            path.Add(new Couple(p.getFrom(), p.getTo()));
                            sum += p.getWeight();
                        }
                    }

                    sortedPath = new List<int>();
                    sortedPath.Add(from);
                    join(from, path, sortedPath);
                    print(sortedPath, sum);
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

       private static void join(int from, List<Couple> path, List<int> sortedPath)
       {
            foreach(Couple couple in path)
            {
                if(couple.getX() == from)
                {
                    sortedPath.Add(couple.getY());
                    if(couple.getY() == to)
                    {
                        return;
                    }
                    join(couple.getY(), path, sortedPath);
                    return;
                }
            }
       }
        
        private static void print(List<int> path, int sum)
        {
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
