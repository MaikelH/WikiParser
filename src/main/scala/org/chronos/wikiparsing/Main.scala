package org.chronos.wikiparsing

import akka.actor.{ActorSystem, Actor, Props}
import org.chronos.wikiparsing.workers.{TaskSeparator, PageExtractor}
import org.chronos.wikiparsing.messages.StartExtractor

object Main extends App {
  val system = ActorSystem("WikiParser")
  val actor = system.actorOf(Props[PageExtractor], "Extractor")
  val Separator = system.actorOf(Props[TaskSeparator], "Separator")
  actor ! StartExtractor("Y:\\enwiki-latest-pages-articles.xml")
}