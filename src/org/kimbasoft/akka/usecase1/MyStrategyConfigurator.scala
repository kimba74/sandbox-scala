package org.kimbasoft.akka.usecase1

import akka.actor.SupervisorStrategy.{Stop, Restart, Resume}
import akka.actor.{OneForOneStrategy, SupervisorStrategy, SupervisorStrategyConfigurator}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MyStrategyConfigurator extends SupervisorStrategyConfigurator {

  def create(): SupervisorStrategy = OneForOneStrategy() {
    case MyActor.ProcessingException => Resume
    case MyActor.GeneralException => Restart
    case _: MyActor.TestException => Stop
  }
}
