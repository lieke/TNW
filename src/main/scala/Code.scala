package main.scala

import scala.io.StdIn.readLine

class Code(val codeLength: Int, val code: List[Int]) {

  def crackTheCode(ringLight: RingLight): Unit = {
    var guess: List[Int] = List()

    while (code != guess) {
      println("What is your next guess? ")
      val nextGuessString = readLine()
      val numbersPattern = s"""([0-9]{$codeLength})""".r
      nextGuessString match {
        case numbersPattern(_) =>
          guess = convertInputStringToGuessList(nextGuessString)
          val exactlyRightFilteredOut = filterOutExactlyRightNumbers(code, guess)
          val amountOfGreenLights = code.size - exactlyRightFilteredOut.size
          val amountOfOrangeLights = checkContainsNumbers(exactlyRightFilteredOut)
          ringLight.showTheFeedbackOnRingLight(amountOfGreenLights, amountOfOrangeLights)
          println(s"Green lights is $amountOfGreenLights - Orange lights is $amountOfOrangeLights")
        case "stop" =>
          throw new Exception("You have chickened out of trying to crack the code!")
        case _ => println(s"Please provide a $codeLength digit code")
      }
    }
  }

  def convertInputStringToGuessList(inputString: String): List[Int] = {
    inputString.toCharArray.map(_.asDigit).toList
  }

  def filterOutExactlyRightNumbers(code: List[Int], guess: List[Int]): List[(Int, Int)] = {
    val zippedList = guess.zip(code)
    zippedList.filter(x => x._2 != x._1)
  }

  def checkContainsNumbers(zippedList: List[(Int, Int)]): Int = {
    val unzippedList = zippedList.unzip
    val guess = unzippedList._1
    val code = unzippedList._2
    guess.filter(x => code.contains(x)).size
  }
}
