import org.scalatest._
import flatspec._
import main.scala.Code
import matchers._

import java.awt.Color

class CodeTest extends AnyFlatSpec with should.Matchers {

  "The input string" should "be converted to the guess array" in {
    val code = new Code(3, List(0, 0, 0))
    val guess = code.convertInputStringToGuessList("777")
    guess should be (List(7,7,7))
  }

  "The right code should " should "give all green lights" in {
    val code = new Code(3, List(0, 0, 0))
    val guess = List(0, 0, 0)
    val exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    val amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    val amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (3)
    amountOfOrangeLights should be (0)
  }

  "A single correctly placed number" should "give a single green light" in {
    val code = new Code(3, List(0, 0, 0))
    val guess = List(1, 1, 0)
    val exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    val amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    val amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (1)
    amountOfOrangeLights should be (0)
  }

  "A single correctly placed number and a correct number in wrong place" should "give a single green light and a single orange light" in {
    val code = new Code(3, List(0, 3, 0))
    val guess = List(0, 2, 3)
    val exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    val amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    val amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (1)
    amountOfOrangeLights should be (1)
  }

  "A all of the numbers in the wrong place" should "give three orange lights" in {
    val code = new Code(3, List(0, 1, 2))
    val guess = List(1, 2, 0)
    val exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    val amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    val amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (0)
    amountOfOrangeLights should be (3)
  }

  "For the code 348 the amount of orange and green lights" should "be right" in { // bug found during the TNW conference
    val code = new Code(3, List(3,4,8))
    var guess = List(8,3,3)
    var exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    var amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    var amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (0)
    amountOfOrangeLights should be (2)

    guess = List(3,3,8)
    exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (2)
    amountOfOrangeLights should be (0)

    guess = List(4,3,8)
    exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (1)
    amountOfOrangeLights should be (2)

    guess = List(0,3,3)
    exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (0)
    amountOfOrangeLights should be (1)
  }

  "For the code 054 the amount of orange and green lights" should "be right" in { // bug found during the TNW conference
    val code = new Code(3, List(0,5,4))
    var guess = List(5,0,0)
    var exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    var amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    var amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (0)
    amountOfOrangeLights should be (2)

    guess = List(5,4,5)
    exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (0)
    amountOfOrangeLights should be (2)
  }

  "For the code 788 the amount of orange and green lights" should "be right" in { // bug found during the TNW conference
    val code = new Code(3, List(7,8,8))
    var guess = List(8,7,6)
    var exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    var amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    var amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (0)
    amountOfOrangeLights should be (2)

    guess = List(7,6,8)
    exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (2)
    amountOfOrangeLights should be (0)
  }

  "For the code 404 the amount of orange and green lights" should "be right" in { // bug found during the TNW conference
    val code = new Code(3, List(4,0,4))
    var guess = List(1,4,7)
    var exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    var amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    var amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (0)
    amountOfOrangeLights should be (1)

    guess = List(7,4,0)
    exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (0)
    amountOfOrangeLights should be (2)

    guess = List(4,0,7)
    exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (2)
    amountOfOrangeLights should be (0)

    guess = List(4,4,0)
    exactlyRightFilteredOut = code.filterOutExactlyRightNumbers(code.code, guess)
    amountOfGreenLights = guess.size - exactlyRightFilteredOut.size
    amountOfOrangeLights = code.checkContainsNumbers(exactlyRightFilteredOut)
    amountOfGreenLights should be (1)
    amountOfOrangeLights should be (2)
  }

}

