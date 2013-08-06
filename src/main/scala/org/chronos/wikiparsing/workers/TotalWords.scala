package org.chronos.wikiparsing.workers

import akka.actor.Actor
import org.chronos.wikiparsing.messages.{TotalWordCountMessage, TaskMessage}
import org.chronos.wikiparsing.utilities.Page
import akka.event.Logging

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 28-7-13
 * Time: 21:48
 */
class TotalWords extends Actor{
  val log = Logging(context.system, this)
  val mergeActor = context.actorSelection("/user/Merge")

  def findWordAmount(page: Page) = page.Text.split("\\W+").size

  def receive = {
    case TaskMessage(page) => mergeActor ! TotalWordCountMessage(page.Title, findWordAmount(page))
    case _ =>
  }
}
