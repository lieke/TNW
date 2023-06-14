val scala3Version = "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Mastermind Puzzle",
    version := "0.1.0-SNAPSHOT",

    libraryDependencies += "com.fazecast" % "jSerialComm" % "2.7.0",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"


  )
