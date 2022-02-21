package com.scalamandra.drop

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.scalamandra.drop.utils.{*, given}

trait Drawable {
  this: Screen =>
  
  protected def camera: OrthographicCamera
  protected def batch: SpriteBatch
  
  protected inline final def draw(batchAction: => Unit): Unit = {
    ScreenUtils.clear(0f, 0f, 0.2f, 1)
    camera.update()
    batch.setProjectionMatrix(camera.combined)
    batch.scoped {
      batchAction
    }
  }

}
