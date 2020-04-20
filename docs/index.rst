.. _index:

.. image:: _image/EmbASP_Logo.png
   :height: 300 px
   :width: 500 px
   :align: center
   :class: classe
   :name: nome

======
EmbASP
======

A framework for the integration (embedding) of **Logic Programming** in external systems for generic applications.
It helps developers at designing and implementing complex reasoning tasks by means of solvers on different platforms.

The framework can be implemented in a object-oriented programming language of choice, easing and guiding the generation of suitable libraries for the use of specific solvers on selected platforms.
We currently provide 3 implementations (in `Java <https://www.java.com>`_ , in `Python <https://www.python.org>`_ and in `C# <https://docs.microsoft.com/en-us/dotnet/csharp/>`_ ) 
and ready-made libraries for the embedding of the ASP (Answer Set Programming) solvers `DLV <http://www.dlvsystem.com/dlv>`_ , `DLV2 <https://www.mat.unical.it/DLV2>`_ , `clingo <https://potassco.org/clingo>`_ , `DLVHEX <http://www.kr.tuwien.ac.at/research/systems/dlvhex/>`_ and the PDDL (Planning Domain Definition Language) cloud solver `Solver.Planning.Domains <http://solver.planning.domains>`_ (SPD) on the Desktop platform and of DLV and SPD on the Mobile (Android™) one (available only for the Java version).

However, the framework has been designed to be easily extensible and adaptable to different solvers and platforms.
It is worth to notice that solvers are invoked in different modes; for instance, SPD is invoked via a remote connection, while for the other, binaries are effectively embedded and natively executed.


Documentation
=============

.. toctree::
   :caption: Documentation
   :hidden:

   documentation/java-implementation.rst
   documentation/python-implementation.rst
   documentation/csharp-implementation.rst

Implementations
---------------

*  :doc:`documentation/java-implementation`
*  :doc:`documentation/python-implementation`
*  :doc:`documentation/csharp-implementation`

.. toctree::
   :caption: Examples
   :hidden:

   examples/shortest-path-asp-java.rst
   examples/blocks-world-spd-java.rst
   examples/blocks-world-spd-python.rst
   examples/blocks-world-spd-csharp.rst
   examples/sudoku-android.rst

Desktop ASP exemples
--------------------

* :doc:`examples/shortest-path-asp-java`

Desktop SPD examples
--------------------

*  :doc:`examples/blocks-world-spd-java`
*  :doc:`examples/blocks-world-spd-python`
*  :doc:`examples/blocks-world-spd-csharp`

Android example
---------------

*  :doc:`examples/sudoku-android`

Contacts
========

For further information, contact `embasp@mat.unical.it <embasp@mat.unical.it>`_ or visit our `website <https://www.mat.unical.it/calimeri/projects/embasp/>`_.


