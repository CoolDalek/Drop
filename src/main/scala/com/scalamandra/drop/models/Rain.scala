package com.scalamandra.drop.models

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import com.scalamandra.drop.utils.*

class Rain(val music: Music) extends Disposable {

  var lastDropTime: Long = 0
  val drops: GdxArray[Vector2] = GdxArray[Vector2]()

  def addDrop(drop: Vector2): Unit = drops.add(drop)

  def playMusic(): Unit = music.play()

  override def dispose(): Unit = music.dispose()

}