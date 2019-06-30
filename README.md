# FunWithSpirals

This as an sbt project.

To run this in intelliJ go to file -> new -> from existing sources then select the sbt file then click ok.

The main class where all the logic is contained is SpiralCalculator.scala.

You may first need to first run 'sbt package' in the terminal in this project to produce the jar file to run the application.

The application jar file path is /target/scala-2.11/nitrotechnicaltestrossgavin_2.11-0.1.jar .

The application takes 2 commands, the first being the length of the N value in the NxN matrix and
the second being the number you want to find the distance to the centre from.

To run the program on the command line use this command:
'scala /path/to/test/target/scala-2.11/nitrotechnicaltestrossgavin_2.11-0.1.jar 607 368078'

This project produces a 2d array (matrix) with given dimensions consisting of uneven numbers.
It then populates this matrix by moving counter-clockwise from the centre point to the outside.
The centre square always starts with 1.
A user can then find the manhattan distance between a number inputted and the centre point of the matrix.
A hashmap is used to store the indices of every number in the matrix so the number is the key and the
indices are the value. We can then quickly retrieve the indices of the searched number and we have the centre point indices.
Then we can get the manhattan distance taking the first set of indices to be (a,b) and the second set to be (c,d).
We then find (a-c)+(b-d) and get the absolute values to get the distance between the two points abs(a-c)+abs(b-d).
