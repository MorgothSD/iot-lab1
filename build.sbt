name := "iot-lab1"

version := "0.1"

scalaVersion := "2.11.12"

val sparkVersion = "2.3.0"
resolvers += Resolver.JCenterRepository

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.logging.log4j" % "log4j-api" % "2.17.2",
  "org.apache.logging.log4j" % "log4j-core" % "2.17.2",
  "org.eclipse.paho" % "org.eclipse.paho.client.mqttv3" % "1.2.5"
)