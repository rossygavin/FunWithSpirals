object SpiralCalculator {

  def main(args: Array[String]): Unit = {

    try {

      val matrixDim = args(0).toInt

      //Checks to make sure the number is uneven
      if(matrixDim%2==0){
        println("number must be uneven")
        System.exit(1)
      }

      //This is the max number in the matrix
      val maxNumber = matrixDim * matrixDim

      val numberToFind = args(1).toInt

      //Making sure the number to find is in the possible range of numbers
      if(numberToFind < 1 || numberToFind > maxNumber){
        println("Please pick a number between 1 and "+maxNumber)
        System.exit(1)
      }

      //Creating the matrix
      val matrix = Array.ofDim[Int](matrixDim, matrixDim)

      //Finding the middle nuber in the indices lenght which will be the middle value
      val centrePoint = Math.round(matrixDim / 2)

      //Creating a map store the possible numbers in as keys and the value will be the indices which we need for manhattan distance.
      var numberLookup = Map[Int, (Int, Int)]()

      //Centre (starting point) in the matrix.
      val centreIndices = (centrePoint, centrePoint)

      //Creating a placeholder for the relative indices of the searched number
      var numberIndices = (0,0)

      //Creating a placeholder for the number of steps calculated
      var numberOfSteps = 0

      //Running the recursive function which populates the matrix and the map
      //I have kept the matrix in as its in the requirements, but I used the map for the 2nd step as I thought it was cleaner
      //and more efficient as for the Manhattan distance we need the indices of the centre point and the searched numbers point.
      //As we are moving anti clockwise from the centre we must move to the right.
      fillMatrix(centrePoint, centrePoint, 1, 1, 0, "right")

      //Getting searched numbers indices
      numberIndices = numberLookup.get(numberToFind).get

      //Calculating Manhattan distance taking the sets of indices as (a,b) (c,d) then calculating
      //abs((a-c))+abs((b-d))
      numberOfSteps = Math.abs((numberIndices._1 - centreIndices._1)) + Math.abs((numberIndices._2 - centreIndices._2))

      //Outputting result to console
      println("From "+numberToFind+" to centre (1) is "+numberOfSteps+" steps using the Manhattan distance.")

      def fillMatrix(indexOne: Int, indexTwo: Int, value: Int, stepsLength: Int, stepsCounter: Int, direction: String): Unit = {

        //Checking the current value is not greater than the max possible value.
        if (value <= maxNumber) {

          //Adding new value to matrix and map
          numberLookup += (value -> (indexOne, indexTwo))
          matrix(indexOne)(indexTwo) = value

          //If the steps in the direction we are going hasn't reached max we keep going until we hit the end of the current direction
          if (stepsCounter < stepsLength) {

            //Each time the value and steps counter are increased. One of the indexes are increased or decreased
            //depending on the direction we are moving in.
            if ("right".equals(direction)) {
              fillMatrix(indexOne, indexTwo + 1, value + 1, stepsLength, stepsCounter + 1, direction)
            }
            else if ("left".equals(direction)) {
              fillMatrix(indexOne, indexTwo - 1, value + 1, stepsLength, stepsCounter + 1, direction)
            }
            else if ("down".equals(direction)) {
              fillMatrix(indexOne + 1, indexTwo, value + 1, stepsLength, stepsCounter + 1, direction)
            }
            else {
              fillMatrix(indexOne - 1, indexTwo, value + 1, stepsLength, stepsCounter + 1, direction)
            }

          }
          else {

            //The scenario when we have hit the end of the current direction means we need to change direction and
            //also possibly need to increase the length of the direction on the matrix too.
            //The length only ever increases on down or up.
            //The indexes get changed also as like above, but the steps counter gets set back to one as
            //we are starting in a new direction.
            if ("right".equals(direction)) {
              fillMatrix(indexOne - 1, indexTwo, value + 1, stepsLength, 1, "up")
            }
            else if ("left".equals(direction)) {
              fillMatrix(indexOne + 1, indexTwo, value + 1, stepsLength, 1, "down")
            }
            else if ("down".equals(direction)) {
              fillMatrix(indexOne, indexTwo + 1, value + 1, stepsLength + 1, 1, "right")
            }
            else {
              fillMatrix(indexOne, indexTwo - 1, value + 1, stepsLength + 1, 1, "left")
            }

          }
        }

      }

    }
    //Here we are catching invalid number errors and also all other unknown errors
    catch{
      case numberFormatException: java.lang.NumberFormatException => {
        println("Invalid number format for number. Numbers must be Integers. "+numberFormatException)
      }
      case unknown: Throwable => {
        println("Got this unknown error: "+unknown)
      }
    }
  }
}