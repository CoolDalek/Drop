package com.scalamandra.drop.models

import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2

class Bucket(val image: Texture, var x: Float, val y: Float) extends Disposable {
  
  override def dispose(): Unit = image.dispose()
  
}