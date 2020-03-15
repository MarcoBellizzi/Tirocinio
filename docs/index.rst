.. TirocinioProva documentation master file, created by
   sphinx-quickstart on Tue Mar 10 16:45:37 2020.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

.. _index:

.. image:: _image/EmbASP_Logo.png
   :height: 500 px
   :width: 500 px
   :align: center

======
EmbASP
======

Presentation
============

A framework for the integration (embedding) of **Logic Programming** in external systems for generic applications.
It helps developers at designing and implementing complex reasoning tasks by means of solvers on different platforms.

The framework can be implemented in a object-oriented programming language of choice, easing and guiding the generation of suitable libraries for the use of specific solvers on selected platforms.
We currently provide 3 implementations (in `Java <https://www.java.com>`_ , in `Python <https://www.python.org>`_ and in `C# <https://docs.microsoft.com/dotnet/csharp/language-reference/>`_ ) 
and ready-made libraries for the embedding of the ASP (Answer Set Programming) solvers `DLV <http://www.dlvsystem.com/dlv>`_ , `DLV2 <https://www.mat.unical.it/DLV2>`_ , `clingo <https://potassco.org/clingo>`_ , `DLVHEX <http://www.kr.tuwien.ac.at/research/systems/dlvhex/>`_ and the PDDL (Planning Domain Definition Language) cloud solver `Solver.Planning.Domains <http://solver.planning.domains>`_ (SPD) on the Desktop platform and of DLV and SPD on the Mobile (Android™) one (available only for the Java version).

However, the framework has been designed to be easily extensible and adaptable to different solvers and platforms.
It is worth to notice that solvers are invoked in different modes; for instance, SPD is invoked via a remote connection, while for the other, binaries are effectively embedded and natively executed.


Documentation
=============

.. toctree::
   :caption: Documentation
   :hidden:

   pages/implementation

:doc:`pages/implementation`
   Implementation description

.. toctree::
   :caption: Examples
   :hidden:

   pages/example-spd
   pages/example-android

:doc:`pages/example-android`
   Android example

:doc:`pages/example-spd`
   SPD example

Download
========

You can find the current development version on `GitHub <https://github.com/DeMaCS-UNICAL/EmbASP-Java>`_ 

Core Team
=========

* Francesco Calimeri
* Davide Fuscà
* Stefano Germano
* Simona Perri
* Jessica Zangari

Contacts
========

For further information, contact *embasp@mat.unical.it* or visit our `Website <https://www.mat.unical.it/calimeri/projects/embasp/>`_


