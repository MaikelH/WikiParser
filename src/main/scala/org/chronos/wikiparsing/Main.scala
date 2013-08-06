package org.chronos.wikiparsing

import akka.actor.{ActorSystem, Actor, Props}
import org.chronos.wikiparsing.workers.{MergeActor, TaskSeparator, PageExtractor}
import org.chronos.wikiparsing.messages.StartExtractor

object Main extends App {
  val system = ActorSystem("WikiParser")
  val actor = system.actorOf(Props[PageExtractor], "Extractor")
  val Separator = system.actorOf(Props[TaskSeparator], "Separator")
  val Merge = system.actorOf(Props[MergeActor], "Merge")
  actor ! StartExtractor("Y:\\enwiki-latest-pages-articles.xml")
}