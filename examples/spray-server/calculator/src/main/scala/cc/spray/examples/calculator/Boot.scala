package cc.spray
package examples.calculator

import util.Spray
import akka.actor.Props

class Boot {
  val mainModule = new CalculatorService {
    // bake your module cake here
  }
  
  val httpService = Spray.system.actorOf(
    props = Props(new HttpService(mainModule.calculatorService)),
    name = "my-service"
  )
  val rootService = Spray.system.actorOf(
    props = Props(new RootService(httpService)),
    name = "spray-root-service" // must match the name in the config so the ConnectorServlet can find the actor
  )
}