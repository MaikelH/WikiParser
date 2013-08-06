package org.chronos.wikiparsing.workers

import akka.actor.Actor
import org.chronos.wikiparsing.messages.{TotalSyllableCountMessage, TaskMessage}
import org.chronos.wikiparsing.utilities.Page
import com.redis.RedisClientPool

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 29-7-13
 * Time: 0:03

 */
class TotalSyllables extends Actor{
  val clients = new RedisClientPool("192.168.1.37", 6379)
  val mergeActor = context.actorSelection("/user/Merge")
  def receive: Actor.Receive = {
    case TaskMessage(page) => mergeActor ! TotalSyllableCountMessage(page.Title, processPage(page))
    case _ =>
  }

  def processPage(page: Page): Int = {
    val words = page.Text.split("\\W+").map(x => x.toLowerCase)

    val sum = words.map(x => (x, {
      clients.withClient {
        client => client.get(x)
      }})).flatMap(x => x._2).map(x => x.toInt).sum

    sum
  }
}
