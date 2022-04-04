import org.apache.log4j.{Level, Logger}
import org.eclipse.paho.client.mqttv3.{MqttClient, MqttMessage}
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

object Main {
  def main(args: Array[String]) = {
    implicit val log: Logger = Logger.getLogger(getClass)
    if (!Logger.getRootLogger.getAllAppenders.hasMoreElements) {
      Logger.getRootLogger.setLevel(Level.WARN)
    }

    val topic = "point"

    var client: MqttClient = null
    try {
      val persistence = new MemoryPersistence()
      client = new MqttClient("tcp://mqtt:1883", MqttClient.generateClientId(), persistence)

      client.connect()

      val msgtopic = client.getTopic(topic)
      var x = 50
      var y = 50
      val computerGuess = scala.util.Random


      while (true) {
        if (computerGuess.nextInt() % 2 == 0) x += 1
        else x -= 1

        if (computerGuess.nextInt() % 2 == 0) y -= 1
        else y += 1

        log.warn(s"""{\"x\":$x,\"y\":$y}""")
        val msgContent = s"""{\"x\":$x,\"y\":$y}"""
        val message = new MqttMessage(msgContent.getBytes("utf-8"))
        msgtopic.publish(message)
        log.warn(s"Published data. topic: ${msgtopic.getName()}; Message: $message")
        Thread.sleep(400)
      }
    }
  }
}
