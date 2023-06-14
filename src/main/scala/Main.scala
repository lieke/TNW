import main.scala.Code
import main.scala.RingLight

import java.io.BufferedWriter
import scala.io.StdIn.readLine
import java.nio.file.{Files, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets


object Main extends App {

  val codeLength = 3
  val code = new Code(codeLength, generateNewRandomCode(codeLength))
  //val code = new Code(3, List(2,2,2))

  var ringLight: RingLight = new RingLight("/dev/tty.usbserial-14140")
  val name = applicationStartupAndGetName()
  readLine()
  val processStart = System.currentTimeMillis()

  try {
    code.crackTheCode(ringLight)
    val amountOfTime = calculateHowLongItTookToFindTheCode(processStart)
    println()
    println(s"Well done!! You took $amountOfTime seconds to crack the code")
    writeNewScoreToLogFile(name, amountOfTime)
  } catch {
    case e: Exception => println(e.getMessage)
  }

  ringLight.shutRingLightDown()

  def applicationStartupAndGetName(): String = {
    println("Crack the code as quickly as possible!!\n Please enter your name\n")
    val name = readLine()
    println(s"Welcome $name \n Press the return key to start\n")
    name
  }

  def calculateHowLongItTookToFindTheCode(startTime: Long): Float = {
    val processEnd = System.currentTimeMillis()
    ((processEnd - startTime).toFloat / 1000)
  }

  def generateNewRandomCode(codeLength: Int): List[Int] = {
    val rand = new scala.util.Random
    List.fill(codeLength)(rand.nextInt(10))
  }

  def writeNewScoreToLogFile(name: String, amountOfTime: Float): Unit = {
    val writer: BufferedWriter = Files.newBufferedWriter(Paths.get("logtimes.csv"), StandardCharsets.UTF_8, StandardOpenOption.APPEND)
    val newScore: String = amountOfTime + "," + name
    writer.write(newScore)
    writer.newLine()
    writer.close()
  }
}