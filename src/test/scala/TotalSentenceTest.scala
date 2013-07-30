import org.chronos.wikiparsing.utilities.Page
import org.chronos.wikiparsing.workers.TotalSentences
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import akka.testkit.TestActorRef
import akka.actor.ActorSystem
import org.scalatest.matchers.ShouldMatchers

/**
 * Created with IntelliJ IDEA.
 * User: Maikel
 * Date: 30-7-13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
class TotalSentenceTest extends FunSuite with BeforeAndAfter with ShouldMatchers{

  var actor: TotalSentences = _

  before {
    implicit val System = ActorSystem("WikiParser")
    val actorRef = TestActorRef[TotalSentences]
    actor = actorRef.underlyingActor
  }

  test("One sentence") {
    val page = new Page("Test", " Hallo dit is een test.")
    actor.processMessage(page) should equal(1)
  }

  test("Two sentences") {
    val page = new Page("Test", " Hallo dit is een test. En nog een zin.")
    actor.processMessage(page) should equal(2)
  }

  test("Two sentences with abbreviation") {
    val page = new Page("Test", "Hallo dit is een test. En nog Mr. een zin.")
    actor.processMessage(page) should equal(2)
  }

  test("Two sentences with abbreviation at end") {
    val page = new Page("Test", "Hallo dit is een test. En nog Mr. een zin. PS.")
    actor.processMessage(page) should equal(3)
  }
}
