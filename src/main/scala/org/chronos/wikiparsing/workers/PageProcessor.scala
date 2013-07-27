package org.chronos.wikiparsing.workers

import akka.actor.Actor
import akka.event.Logging
import org.chronos.wikiparsing.messages.Page

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 27-7-13
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
class PageProcessor extends Actor {
  val log = Logging(context.system, this)

  def receive: Actor.Receive = {
    case Page(content) => log.info("Received page.")
  }
}
