package vanilla.rabbitmq

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import java.nio.charset.Charset

fun main() {

    val connectionFactory = ConnectionFactory()
    connectionFactory.host = SERVER_NAME

    val connection = connectionFactory.newConnection()
    val channel = connection.createChannel()

    channel.queueDeclare(QUEUE_NAME, false, false, false, null)

    println(" [*] Waiting for messages. To exit press CTRL+C")

    val deliverCallback = DeliverCallback { _: String?, delivery: Delivery ->
        val message = String(delivery.body, Charset.forName("UTF-8"))
        println(" [x] Received '$message'")
    }
    channel.basicConsume(QUEUE_NAME, true, deliverCallback) { consumerTag: String? -> }

}