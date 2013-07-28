package org.chronos.wikiparsing.workers

import akka.actor.{Props, Actor}
import akka.event.Logging
import org.chronos.wikiparsing.messages.{TaskMessage, PageMessage}
import com.google.protobuf.AbstractMessage
import org.chronos.wikiparsing.utilities.Page
import scala.xml.XML
import akka.routing.SmallestMailboxRouter

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 27-7-13
 * Time: 22:00
 */
class PageProcessor extends Actor {
  val log = Logging(context.system, this)
  val router = context.actorOf(Props[TaskSeparator].withRouter(SmallestMailboxRouter(5)), "TaskSeparator")

  def receive: Actor.Receive = {
    case PageMessage(content) => processPage(content)
    case x: AbstractMessage => log.warning("Received unknown message: " + x.toString)
  }

  def processPage(content: String) = {
    try {
      val page = new Page(XML.loadString(content))
      router ! TaskMessage(page)
    }
    catch  {
      case e: Exception => log.error(e, "Error occured")
    }
  }
}
