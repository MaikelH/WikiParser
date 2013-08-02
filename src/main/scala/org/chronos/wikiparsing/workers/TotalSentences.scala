package org.chronos.wikiparsing.workers

import akka.actor.Actor
import akka.event.Logging
import org.chronos.wikiparsing.messages.TaskMessage
import org.chronos.wikiparsing.utilities.Page

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 29-7-13
 * Time: 0:04
 */
class TotalSentences extends Actor {

  val log = Logging(context.system, this)

  def processMessage(page: Page) =  page.Text.split("\\.\\s[A-Z]").size

  def receive: Actor.Receive = {
    case TaskMessage(page) => processMessage(page)
    case _ =>
  }
}
