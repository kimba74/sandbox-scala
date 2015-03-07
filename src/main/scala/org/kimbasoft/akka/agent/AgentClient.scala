package org.kimbasoft.akka.agent

import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import akka.agent.Agent

import scala.language.postfixOps

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object AgentClient {

  def main(args: Array[String]) {
    val agent = Agent(5)
    println(s"Agent with initial value = ${agent.get()}")

    agent send 7
    Thread.sleep(10)
    println(s"Agent with new value = ${agent.get()}")

    agent send (_ * 2)
    Thread.sleep(10)
    println(s"Agent with modified value = ${agent.get()}")

    val future1 = agent alter 3
    val a1Value = Await.result(future1, Timeout(5 seconds).duration)
    println(s"Agent with another new value = $a1Value")

    val future2 = agent alter (_ * 3)
    val a2Value = Await.result(future2, Timeout(5 seconds).duration)
    println(s"Agent with another modified value = $a2Value")
  }
}
