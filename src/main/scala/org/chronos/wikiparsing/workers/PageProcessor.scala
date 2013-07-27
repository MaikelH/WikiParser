package org.chronos.wikiparsing.workers

import akka.actor.Actor
import akka.event.Logging
import org.chronos.wikiparsing.messages.PageMessage
import com.google.protobuf.AbstractMessage
import org.chronos.wikiparsing.utilities.Page
import scala.xml.XML

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 27-7-13
 * Time: 22:00
 */
class PageProcessor extends Actor {
  val log = Logging(context.system, this)

  def receive: Actor.Receive = {
    case PageMessage(content) => processPage(content)
    case x: AbstractMessage => log.warning("Received unknown message: " + x.toString)
  }

  def processPage(content: String) = {
    try {
      val page = new Page(XML.loadString(content))
      log.info("Processed: " + page.Title)
    }
    catch  {
      case e: Exception => log.error(e, "Error occured")
    }
  }
}
