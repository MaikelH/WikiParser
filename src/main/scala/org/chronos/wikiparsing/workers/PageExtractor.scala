package org.chronos.wikiparsing.workers

import akka.actor.{Props, Actor}
import org.chronos.wikiparsing.messages.{PageMessage, StartExtractor}
import scala.xml.pull.{EvElemEnd, EvText, EvElemStart, XMLEventReader}
import scala.io.Source
import akka.event.Logging
import akka.routing.SmallestMailboxRouter

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 27-7-13
 * Time: 21:34
 */
class PageExtractor extends Actor {

  val log = Logging(context.system, this)
  val router = context.actorOf(Props[PageProcessor].withRouter(SmallestMailboxRouter(5)), "PageProcessorRouter")

  override def preStart() ={
    log.debug("Starting PageExtractor Actor.")
  }

  def receive = {
    case StartExtractor(file) => processFile(file)
  }

  def processFile(file: String) = {

    log.info("Received file: " + file )
    val source = Source.fromFile(file)

    val reader = new XMLEventReader(source)

    val builder = new StringBuilder()
    var currentNode = ""

    for(event <- reader)
    {
      event match {
        case EvElemStart(_, "page", _, _) => {
          currentNode = "page"
          builder.append("<page>")
        }
        case EvElemStart(_, "text", _, _) => currentNode = "text"
        case EvElemStart(_, "title", _, _) => currentNode = "title"
        case EvText(text) => if(currentNode.equals("text")) {
          builder.append("<text>")
          builder.append(text)
          builder.append("</text>")
        }
        else if(currentNode.equals("title")) {
          builder.append("<title>")
          builder.append(text)
          builder.append("</title>")
        }
        case EvElemEnd(_, "page") => {
          builder.append("</page>")
          router ! PageMessage(builder.mkString)
          // Use setLength here instead of new so that backing buffer stays intact.
          builder.setLength(0)
        }
        case EvElemEnd(_, "text") | EvElemEnd(_, "title") => currentNode = ""
        case _ =>
      }
    }
  }
}
