package com.scalamandra.drop.routing

import com.badlogic.gdx.{Game, Gdx, Screen}
import com.badlogic.gdx.utils.IntMap

import scala.collection.mutable

abstract class GameRouter extends Game with Router {

  private val screens = IntMap[() => Screen]()

  protected def addScreen[T <: Screen](route: Route[T])(factory: => T): Unit =
    screens.put(route.internal, () => factory)
    
  protected def addScreen[T <: Screen, R <: Navigator[T]](navigator: R)(factory: => T): Unit =
    addScreen(navigator.route)(factory)

  override def moveTo[T <: Screen](route: Route[T]): Unit =
    screens.get(route.internal) match {
      case null =>
        throw NoSuchScreen(route)
      case factory =>
        setScreen(factory())
    }

  override def setScreen(screen: Screen): Unit = {
    val current = getScreen
    if(current != null) {
      current.hide()
      current.dispose()
    }
    this.screen = screen
    if(screen != null) {
      screen.show()
      screen.resize(
        Gdx.graphics.getWidth,
        Gdx.graphics.getHeight,
      )
    }
  }

}