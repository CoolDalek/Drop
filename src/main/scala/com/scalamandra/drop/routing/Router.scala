package com.scalamandra.drop.routing

import com.badlogic.gdx.{Gdx, Screen}

trait Router {

  def moveTo[T <: Screen](route: Route[T]): Unit

}