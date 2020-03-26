# Android example

## Getting started

In order to use the framework in your applications you have to import it as module on Android Studio
* Import the framework module:
  * Download the framework last released module.
  * In the project view, right-click on your project New > Module.
  * Select Import .JAR/.AAR Package.
  * Select the directory in which the module has been downloaded.
* Set the dependency:
  * In the Android Studio menu: File > Project Structure .
  * Select your project module (by default called app).
  * In the Dependencies Tab add as Module Dependency the previously imported framework.

## Using EmbASP

In the following, we describe an the actual usage of the framework by means of a running example;
as a use case, we will develop a simple Android application for solving Sudoku puzzles.

The framework features a annotation-guided mapping, offered by the `ASPMapper` component, for two-way translations between strings recognizable by ASP solvers and objects in the programming language at hand, directly employable within applications. 
By means of this feature, the ASP-based aspects can be separated from the Java coding: the programmer doesn't even necessarily need to be aware of ASP.

Let us think of a user that designed (or has been given) a proper logic program P to solve a sudoku puzzle and has also an initial schema.
We assume that the initial schema is well-formed i.e. the complete schema solution exists and is unique.
A possible program P is embedded in the complete example, that, coupled with a set of facts F representing the given initial schema, allows to obtain the only admissible solution.

By means of the annotation-guided mapping, the initial schema can be expressed in forms of Java objects.
To this extent, we define the class `Cell`, aimed at representing the single cell of the sudoku schema, as follows:

```java
@Id("cell")
public class Cell {
    
   @Param(0)
   private int row;
        
   @Param(1)
   private int column;
        
   @Param(2)
   private int value;
        
   [...]
        
}
```

It is worth noticing how the class has been annotated by two custom annotations, defined according to the following syntax:

* `@Id(string_name)` : the target must be a class, and defines the predicate name the class is mapped to;
* `@Param(integer_position)` : the target must be a field of a class annotated via @Id, and defines the term (and its position) in the ASP atom the field is mapped to.

Thanks to these annotations the `ASPMapper` class will be able to map `Cell` objects into strings properly recognizable from the ASP solver as logic facts of the form `cell(Row,Column,Value)`.
At this point, we can create an [Android Activity Component](https://developer.android.com/reference/android/app/Activity.html) , and start deploying our sudoku application: 

```java
public class MainActivity extends AppCompatActivity {
	
   [...]
    
   private Handler handler;
    
   @Override
   protected void onCreate(Bundle bundle) {
      handler = new AndroidHandler(getApplicationContext(), DLVAndroidService.class);
      [...]
   }
        
   public void onClick(final View view){
      startReasoning();
      [...]
   }
    
   public void startReasoning() {
      InputProgram inputProgram = new InputProgram();
      for (int i = 0; i < 9; i++){
        for (int j = 0; j < 9; j++)
          try {
            if(sudokuMatrix[i][j]!=0) {
              inputProgram.addObjectInput(new Cell(i, j, sudokuMatrix[i][j]));
            }
          } catch (Exception e) {	
            // Handle Exception 
          }
      }
      handler.addProgram(inputProgram);
    
      String sudokuEncoding = getEncodingFromResources();
      handler.addProgram(new InputProgram(sudokuEncoding));
    
      Callback callback = new MyCallback();
      handler.startAsync(callback);
   }
}
```

The class contains an `Handler` instance as field, that is initialized when the Activity is created as an `AndroidHandler`.
Required parameters include the Android Context (an Android utility, needed to start an Android Service Component) and the type of `AndroidService` to use, in our case a `DLVAndroidService`.

In addiction, in order to represent an initial sudoku schema, the class features a matrix of integers as another field where position (i,j) contains the value of cell (i,j) in the initial schema; cells initially empty are represented by positions containing zero.

The method startReasoning is in charge of actually managing the reasoning: in our case, it is invoked in response to a click event that is generated when the user asks for the solution.
It is firstly created an `InputProgram` object that is filled with `Cell` objects representing the initial schema, which is then provided to the handler;
then it is provided with the sudoku encoding. It could be loaded, for instance, by means of an utility function that retrieves it from the Android Resources folder, which, within Android applications, is typically meant for containing images, sounds, files and resources in general.

At this point, the reasoning process can start; since for Android we provide only the asynchronous execution mode, a callback object is in charge of fetching the output when the ASP system has done.

Finally, once the computation is over, from within the callback function the output can be retrieved directly in form of Java objects.
For instance, in our case an inner class MyCallback implements the interface `Callback`:

```java
private class MyCallback implements Callback {

   @Override
   public void callback(Output o) {
      if(!(o instanceof AnswerSets))
        return;
      AnswerSets answerSets=(AnswerSets)o;
      if(answerSets.getAnswersets().isEmpty())
        return;
      AnswerSet as = answerSets.getAnswersets().get(0);
      try {
        for(Object obj:as.getAtoms()) {
          Cell cell = (Cell) obj;
          sudokuMatrix[cell.getRow()][cell.getColumn()] = cell.getValue();
        }
      } catch (Exception e) {
        // Handle Exception
      }
      displaySolution();
   }
}
```

&nbsp;

For further information, contact *embasp@mat.unical.it* or visit our [Website](https://www.mat.unical.it/calimeri/projects/embasp/).