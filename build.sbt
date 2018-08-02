val akkaHttpVersion = "10.1.3"
val akkaVersion = "2.5.13"

name := "tutorialScala"

version := "1.0"

scalaVersion := "2.12.6"



libraryDependencies ++= Seq(
  // logging
  "org.slf4j" % "slf4j-api" % "1.7.12"
  , "ch.qos.logback" % "logback-classic" % "1.1.3"

  // akka
  , "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  , "com.typesafe.akka" %% "akka-stream" % akkaVersion

  // testing
  , "org.scalatest" %% "scalatest" % "3.0.4" % "test"
  , "org.mockito" % "mockito-core" % "1.9.5" % "test"
  , "junit" % "junit" % "4.10" % "test"

  //for parser
  ,"org.codehaus.jackson" % "jackson-core-asl" % "1.6.1"
)
