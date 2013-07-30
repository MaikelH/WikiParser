package org.chronos.wikiparsing.messages

import scala.xml.Elem
import org.chronos.wikiparsing.utilities.Page

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 27-7-13
 */
abstract class AbstractMessage
case class ExtractorMessage(message: String)
case class StartExtractor(file: String)
case class PageMessage(content: String)
case class TaskMessage(content: Page)
case class TotalSentenceCountMessage(title: String, Count: Int)
case class TotalWordCountMessage(title: String, Count: Int)
case class TotalSyllableCountMessage(title: String, Count: Int)