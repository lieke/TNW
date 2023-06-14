package main.scala

import com.fazecast.jSerialComm._

object RingLight {//extends App {

  val light: RingLight = new RingLight("/dev/tty.usbserial-14140")
  Thread.sleep(1500)
  for (x <- 1 to 3) {
      light.showTheFeedbackOnRingLight(1,1)
    }
  light.shutRingLightDown()
}

class RingLight(val portName: String) {

  var port: SerialPort = null
  try {
    port = SerialPort.getCommPort(portName)
    port.openPort()
  } catch {
    case e: SerialPortInvalidPortException => println("Ringlight port could not be found")
  }

  def showTheFeedbackOnRingLight(amountOfGreenLights: Int, amountOfOrangeLights: Int) {
    if (port != null) {
      var bytes = Array[Byte]()
      bytes = Array[Byte](amountOfGreenLights.toByte, amountOfOrangeLights.toByte)
      port.writeBytes(bytes, 2)
      Thread.sleep(3000)

    }
  }

  def shutRingLightDown(): Unit = {
    if (port != null)  {
      port.closePort()
    }
  }
}