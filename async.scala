package ninja.walex

import scala.async.Async
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ScalaAsyncBasicApp extends App {
  val num1 = Future(10)
  val num2 = Future(20)

  val forOutput = for {
    no1 <- num1
    no2 <- num2
  } yield no1 + no2

  Thread.sleep(1000)

  println(s"For comprehension addition output: ${forOutput.value}")

  val futureOutput = num1.map { no1 =>
    num2.map { no2 =>
      no2 + no1
    }
  }
  Thread.sleep(1000)
  println(s"Scala future addition output: ${forOutput.value}")

  val asyncOut = Async.async {
    Async.await(num1) + Async.await(num2)
  }
  Thread.sleep(1000)
  println(s"Scala Async API addition output: ${asyncOut.value}")
}
