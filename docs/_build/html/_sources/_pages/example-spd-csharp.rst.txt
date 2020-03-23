.. _pages-example-spd:

.. raw:: html

    <style> .strong {color:red; font-weight: bold;} </style>

.. role:: strong

==============
C# SPD example
==============

Getting started
===============

The framework id released as VisualStudio project, therefore it can be easily added and used in any VisualStudio solution.

Using EmbASP
============

In the following, we describe an the actual usage of the framework by means of a running example;
as a use case, we will develop a simple Desktop application to solve the blocks-world problem.

.. image:: ../_image/blocks-world.png
   :height: 200 px
   :width: 500 px
   :align: center

We will make use of the annotation-guided mapping, in order to retrieve the actions constituting a PDDL plan via C# objects.

To this purpose, the following classes are intended to represent possible actions that a blocks-world solution plan can feature:

.. code-block:: csharp

  [Id("pick-up")]
    class PickUp
    {
    	[Param(0)]
    	private string block;
    	
        [...]
    }

.. code-block:: csharp

  [Id("put-down")]
    class PutDown
    {
    	[Param(0)]
    	private string block;
    	
    	[...]
    }

.. code-block:: csharp

  [Id("stack")]
    class Stack
    {
    	[Param(0)]
    	private string block1;
    	
    	[Param(1)]
    	private string block2;
    	
    	[...]
    }

.. code-block:: csharp

  [Id("unstack")]
    class Unstack
    {
    	[Param(0)]
    	private string block1;
    	
    	[Param(1)]
    	private string block2;
    	
    	[...]
    }
            

At this point, supposing that we are given two files defining the blocks-world domain and a problem instance, we can start deploying our application:

.. code-block:: csharp

  class Program
  {
      static void Main(string[] args)
      {
          Handler handler = new DesktopHandler(new SPDDesktopService());
          InputProgram inputDomain = new PDDLInputProgram(PDDLProgramType.DOMAIN);
          inputDomain.AddFilesPath("domain.pddl");

          InputProgram inputProblem = new PDDLInputProgram(PDDLProgramType.PROBLEM);
          inputProblem.AddFilesPath("p01.pddl");

          handler.AddProgram(inputDomain);
          handler.AddProgram(inputProblem);

          try
          {
              PDDLMapper.Instance.RegisterClass(typeof(PickUp));
              PDDLMapper.Instance.RegisterClass(typeof(PutDown));
              PDDLMapper.Instance.RegisterClass(typeof(Stack));
              PDDLMapper.Instance.RegisterClass(typeof(Unstack));

              Plan plan = (Plan)handler.StartSync();

              foreach(object obj in plan.ActionsObjects)
              {
                  // Manage objects as needed
              }

          }
          catch (Exception e)
          { 
              // Handle Exception
          }
      }
  }


The class contains an :strong:`Handler` instance as field, that is initialized with a :strong:`DesktopHandler` using the required parameter :strong:`SPDDesktopService`.

Then it's set-up the input to the solver; since PDDL requires separate definitions for domain and problem, two :strong:`PDDLInputProgram` are created and then given to the handler.

The next lines inform the :strong:`PDDLMapper` about what classes are intended to map the output actions.

Finally the solver is invoked, and the output is retrieved.

The output actions can be managed accordingly to the user's desiderata. 

For further information, contact *embasp@mat.unical.it* or visit our `Website <https://www.mat.unical.it/calimeri/projects/embasp/>`_
