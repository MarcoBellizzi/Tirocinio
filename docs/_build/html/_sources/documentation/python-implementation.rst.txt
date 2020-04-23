=====================
Python implementation
=====================

The following figure provides some details about classes and interfaces of the implementation.

.. raw:: html

   <style>
   * {box-sizing: border-box;}

   .img-magnifier-container {
     position:relative;
   }

   .img-magnifier-glass {
     position: absolute;
     border: 3px solid #000;
     border-radius: 50%;
     cursor: none;
     /*Set the size of the magnifier glass:*/
     width: 170px;
     height: 170px;
   }
   </style>

   <script>
   function magnify(imgID, zoom) {
     var img, glass, w, h, bw;
     img = document.getElementById(imgID);
     /*create magnifier glass:*/
     glass = document.createElement("DIV");
     glass.setAttribute("class", "img-magnifier-glass");
     /*insert magnifier glass:*/
     img.parentElement.insertBefore(glass, img);
     /*set background properties for the magnifier glass:*/
     glass.style.backgroundImage = "url('" + img.src + "')";
     glass.style.backgroundRepeat = "no-repeat";
     glass.style.backgroundSize = (img.width * zoom) + "px " + (img.height * zoom) + "px";
     bw = 3;
     w = glass.offsetWidth / 2;
     h = glass.offsetHeight / 2;
     /*execute a function when someone moves the magnifier glass over the image:*/
     glass.addEventListener("mousemove", moveMagnifier);
     img.addEventListener("mousemove", moveMagnifier);
     /*and also for touch screens:*/
     glass.addEventListener("touchmove", moveMagnifier);
     img.addEventListener("touchmove", moveMagnifier);
     function moveMagnifier(e) {
       var pos, x, y;
       /*prevent any other actions that may occur when moving over the image*/
       e.preventDefault();
       /*get the cursor's x and y positions:*/
       pos = getCursorPos(e);
       x = pos.x;
       y = pos.y;
       /*prevent the magnifier glass from being positioned outside the image:*/
       if (x > img.width - (w / zoom)) {x = img.width - (w / zoom);}
       if (x < w / zoom) {x = w / zoom;}
       if (y > img.height - (h / zoom)) {y = img.height - (h / zoom);}
       if (y < h / zoom) {y = h / zoom;}
       /*set the position of the magnifier glass:*/
       glass.style.left = (x - w) + "px";
       glass.style.top = (y - h) + "px";
       /*display what the magnifier glass "sees":*/
       glass.style.backgroundPosition = "-" + ((x * zoom) - w + bw) + "px -" + ((y * zoom) - h + bw) + "px";
     }
     function getCursorPos(e) {
       var a, x = 0, y = 0;
       e = e || window.event;
       /*get the x and y positions of the image:*/
       a = img.getBoundingClientRect();
       /*calculate the cursor's x and y coordinates, relative to the image:*/
       x = e.pageX - a.left;
       y = e.pageY - a.top;
       /*consider any page scrolling:*/
       x = x - window.pageXOffset;
       y = y - window.pageYOffset;
       return {x : x, y : y};
     }
   }
   </script>

   <div class="img-magnifier-container">
     <img id="myimage" src="../_static/class_diagram_python_v6.png" width="1000" height="500">
   </div>

   <script>
   /* Initiate Magnify Function
   with the id of the image, and the strength of the magnifier glass:*/
   magnify("myimage", 2.5);
   </script>

Base module implementation
==========================

Each component in the :guilabel:`Base` module has been implemented by means of generic class or interface that will specialize in the following packages.

In particular, the :code:`Handler` class collects :code:`InputProgram` and :code:`OptionDescriptor` objects communicated by the user.

For what the asynchronous mode is concerned, the class :code:`Service` depends from the interface :code:`Callback`, since once the reasoning service has terminated, the result of the computation is returned back via a class :code:`Callback`.

Platforms module implementation
===============================

In order to support a new platform, the :code:`Handler` and :code:`Service` components must be adapted.

For the desktop platform we developed a :code:`DesktopHandler` and a :code:`DesktopService`, which generalizes the usage of a solver on the desktop platform, allowing both synchronous and asynchronous execution modes.

Languages module implementation
===============================

This module includes specific classes for the management of input and output to ASP and PDDL solvers.

The :code:`Mapper` component of the :guilabel:`Languages` module is implemented via a :code:`Mapper` class, that allows to translate input and output into Python objects.
Such translations are guided by `ANTLR4 <https://www.antlr.org/>`_ library and :code:`Predicate` abstract class, also present in the module.

To make possible translate facts into strings and vice versa, the classes that want to represent a predicate, must extend the abstract class :code:`Predicate`, and must be implemented by including the following code:

* *predicateName="string_name"* : must be entered as a class field and must contain the predicate name (in the ASP case) or the action name (in the PDDL case) to map;
* *[("class_field_name_1", int), ("class_field_name_2"), ...]* : Is a list that must be passed to super in the constructor, and must contain so many tuples how many are the class field, containing the field name, sorted by the position of the terms they represent, and optionally the keyword int if the field represents an integer.

Thanks to the structure of the :code:`Predicate` class, this information is passed to the :code:`Mapper` class, to correctly perform the translation mechanism.

If the classes intended for the translation are not constructed correctly in this way, an exception is raised.

In addition to the :code:`Mapper`, this module features two sub-modules which are more strictly related to ASP and PDDL.

Specialization module implementation
====================================

The classes :code:`DLVAnswerSets`, :code:`DLV2AnswerSets`, :code:`ClingoAnswerSets`, :code:`DLVHEXAnswerSets` and :code:`SPDPlan` implement specific extensions of the :code:`AnswerSets` or :code:`Plan` classes, in charge of manipulating the output of the respective solvers.

Moreover, this module can contain classes extending :code:`OptionDescriptor` to implement specific options of the solver at hand. 

|

For further information, contact `embasp@mat.unical.it <embasp@mat.unical.it>`_ or visit our `website <https://www.mat.unical.it/calimeri/projects/embasp/>`_.
