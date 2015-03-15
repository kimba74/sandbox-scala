package org.kimbasoft.akka.scheduler

import akka.actor.ActorSystem
import org.kimbasoft.akka.scheduler.WorkerActor.Messages.Tick

import scala.concurrent.duration._
import scala.language.postfixOps

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SchedulerClient {

  def main(args: Array[String]) {

    val sys = ActorSystem("SchedulerSystem")

    val worker = sys.actorOf(WorkerActor.props, "worker")

    import sys.dispatcher

    /* Scheduling Actor 'worker' being invoked after 2 seconds with message Tick */
    sys.scheduler.scheduleOnce(2 seconds, worker, Tick("tick"))

    /* Scheduling function call being invoked after 2 seconds with message Tick */
    sys.scheduler.scheduleOnce(2 seconds) {
      worker ! Tick("tock")
    }

    /* Scheduling reoccurring call to Actor 'worker' with message Tick */
    val schedule = sys.scheduler.schedule(0 second, 1 second, worker, Tick("tick-tock"))

    /* Sleep for 10sec then cancel reoccurring task */
    Thread.sleep(10000)
    schedule.cancel()
  }
}
