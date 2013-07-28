package org.chronos.wikiparsing.workers

import akka.actor.{Props, Actor}
import akka.event.Logging
import org.chronos.wikiparsing.utilities.Page
import org.chronos.wikiparsing.messages.TaskMessage
import akka.routing.SmallestMailboxRouter

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 28-7-13
 * Time: 13:28
 */
class TaskSeparator extends Actor {
  val TotalWords = context.actorOf(Props[TotalWords].withRouter(SmallestMailboxRouter(5)), "TotalWords")
  val TotalSyllables = context.actorOf(Props[TotalSyllables].withRouter(SmallestMailboxRouter(5)), "TotalSyllables")
  val TotalSentences = context.actorOf(Props[TotalSentences].withRouter(SmallestMailboxRouter(5)), "TotalSentences")
  val log = Logging(context.system, this)

  def receive: Actor.Receive = {
    case TaskMessage(page) => {
      log.debug("Received: " + page.Title)
      TotalWords ! TaskMessage(page)
      TotalSyllables ! TaskMessage(page)
      TotalSentences ! TaskMessage(page)
    }
    case _ =>  log.info("Unknown message." )
  }
}
