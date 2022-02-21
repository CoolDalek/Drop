package com.scalamandra.drop.models

import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture

class Drop(val image: Texture, val sound: Sound) extends Disposable {
  
  def playSound(): Unit = sound.play()
  
  override def dispose(): Unit = {
    image.dispose()
    sound.dispose()
  }
  
}