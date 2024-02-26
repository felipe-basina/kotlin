package vanilla.rabbitmq

import com.rabbitmq.client.ConnectionFactory
import java.io.IOException
import java.util.concurrent.TimeoutException

const val QUEUE_NAME = "hello"
const val SERVER_NAME = "localhost"

/**
 * Run from docker:
 * docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
 *
 * Go to url:
 * http://localhost:15672/
 *
 * Credentials:
 * guest/guest
 */

fun main() {

    val factory = ConnectionFactory()
    factory.host = SERVER_NAME
    try {
        factory.newConnection().use { conn ->
            conn.createChannel().use { channel ->
                channel.queueDeclare(QUEUE_NAME, false, false, false, null)
                val message = "Hello World " + System.currentTimeMillis()
                channel.basicPublish("", QUEUE_NAME, null, message.toByteArray())
                println(String.format(" [x] Sent '%s'", message))
            }
        }
    } catch (e: IOException) {
        throw RuntimeException(e)
    } catch (e: TimeoutException) {
        throw RuntimeException(e)
    }

}