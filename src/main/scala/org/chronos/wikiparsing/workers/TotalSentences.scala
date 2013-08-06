package org.chronos.wikiparsing.workers

import akka.actor.Actor
import akka.event.Logging
import org.chronos.wikiparsing.messages.{TotalSentenceCountMessage, TaskMessage}
import org.chronos.wikiparsing.utilities.Page

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 29-7-13
 * Time: 0:04
 */
class TotalSentences extends Actor {

  val log = Logging(context.system, this)
  val mergeActor = context.actorSelection("/user/Merge")

  def processMessage(page: Page) =  page.Text.split("\\.\\s[A-Z]").size

  def receive: Actor.Receive = {
    case TaskMessage(page) => mergeActor ! TotalSentenceCountMessage(page.Title, processMessage(page))
    case _ =>
  }
}
