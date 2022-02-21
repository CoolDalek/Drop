package com.scalamandra.drop.screens

import com.badlogic.gdx.Screen
import com.scalamandra.drop.utils.*
import com.badlogic.gdx.utils.Disposable

abstract class GameScreen extends Screen {
  private val disposables: GdxArray[Disposable] = GdxArray[Disposable]()

  protected def use[T <: Disposable](resource: T): T = {
    disposables.add(resource)
    resource
  }

  protected def useAll(resources: Disposable*): Unit =
    disposables.addAll(resources: _*)

  override def dispose(): Unit = disposables.foreach(_.dispose)

  override def render(delta: Float): Unit = {
    view()
    update(delta)
  }
  
  def view(): Unit
  
  def update(delta: Float): Unit

  override def resize(width: Int, height: Int): Unit = ()

  override def pause(): Unit = ()

  override def resume(): Unit = ()

  override def hide(): Unit = ()

}