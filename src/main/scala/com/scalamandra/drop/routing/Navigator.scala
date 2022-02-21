package com.scalamandra.drop.routing

import com.badlogic.gdx.Screen

trait Navigator[T <: Screen] {
  
  final val route = Route[T]

}