package org.chronos.wikiparsing.workers

import akka.actor.Actor
import org.chronos.wikiparsing.messages.TaskMessage
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

  def findWordAmount(page: Page) = {
    val n = page.Text.split("\\w+").size
  }

  def receive = {
    case TaskMessage(page) => findWordAmount(page)
    case _ =>
  }
}
