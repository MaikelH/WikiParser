package org.chronos.wikiparsing.workers

import akka.actor.Actor
import org.chronos.wikiparsing.messages.{TotalSentenceCountMessage, TotalSyllableCountMessage, TotalWordCountMessage}
import akka.event.Logging

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 6-8-13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
class MergeActor extends Actor {
  val log = Logging(context.system, this)

  def receive: Actor.Receive = {
    case TotalSyllableCountMessage(title, count) => log.debug("Received: " + title + " with syllable count: " + count)
    case TotalWordCountMessage(title, count) => log.debug("Received: " + title + " with wordcount: " + count)
    case TotalSentenceCountMessage(title, count) => log.debug("Received: " + title + " with sentencecount: " + count)
    case _ =>
  }
}
