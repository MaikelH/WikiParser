package org.chronos.wikiparsing.workers

import akka.actor.Actor
import org.chronos.wikiparsing.messages.{StartExtractor}
import scala.xml.pull.{EvElemEnd, EvText, EvElemStart, XMLEventReader}
import scala.io.Source

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 27-7-13
 * Time: 21:34
 * To change this template use File | Settings | File Templates.
 */
class PageExtractor extends Actor {
  def receive = {
    case StartExtractor(file) => processFile(file)
  }

  def processFile(file: String) = {
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
        case EvElemEnd(_, "page") => builder.append("</page>")
        case EvElemEnd(_, "text") | EvElemEnd(_, "title") => currentNode = ""
        case _ =>
      }
    }
  }
}
