package org.chronos.wikiparsing.workers

import akka.actor.Actor
import akka.event.Logging
import org.chronos.wikiparsing.utilities.Page
import org.chronos.wikiparsing.messages.TaskMessage

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 28-7-13
 * Time: 13:28
 */
class TaskSeparator extends Actor {

  val log = Logging(context.system, this)

  def receive: Actor.Receive = {
    case TaskMessage(page) => log.info("Received: " + page.Title)
    case _ =>  log.info("Unknown message." )
  }
}
