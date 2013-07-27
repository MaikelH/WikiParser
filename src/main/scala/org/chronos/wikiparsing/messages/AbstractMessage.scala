package org.chronos.wikiparsing.messages

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 27-7-13
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
abstract class AbstractMessage
case class ExtractorMessage(message: String)
case class StartExtractor(file: String)