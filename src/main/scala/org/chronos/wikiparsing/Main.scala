package org.chronos.wikiparsing

import akka.actor.{ActorSystem, Actor, Props}
import org.chronos.wikiparsing.workers.PageExtractor
import org.chronos.wikiparsing.messages.StartExtractor

object Main extends App {
  val system = ActorSystem("WikiParser")
  val actor = system.actorOf(Props[PageExtractor], "Extractor")
  actor ! StartExtractor("Y:\\enwiki-latest-pages-articles.xml")
}