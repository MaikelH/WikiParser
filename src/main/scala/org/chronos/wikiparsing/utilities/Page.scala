package org.chronos.wikiparsing.utilities

import scala.xml.Elem

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 28-7-13
 * Time: 0:45
 * To change this template use File | Settings | File Templates.
 */
class Page(Content: Elem) {
  val Title = (Content \ "title").text
  val Text = (Content \ "text").text
}
