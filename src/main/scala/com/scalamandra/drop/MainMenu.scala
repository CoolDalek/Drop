package com.scalamandra.drop

import com.badlogic.gdx.{Gdx, Input, Screen}
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.utils.ScreenUtils
import com.scalamandra.drop.routing.Router

class MainMenu(
                protected val batch: SpriteBatch,
                private val font: BitmapFont,
                private val router: Router,
              ) extends Screen with Drawable {
  protected val camera: OrthographicCamera = OrthographicCamera()
  camera.setToOrtho(false, 800, 480)

  override def show(): Unit = ()

  override def render(delta: Float): Unit = {
    draw {
      font.draw(batch, "Welcome to Drop", 100, 150)
      font.draw(batch, "Tap anywhere to begin", 100, 100)
    }
    if(Gdx.input.isTouched) {
      router.moveTo(GamePlay.route)
    }
  }

  override def resize(width: Int, height: Int): Unit = ()

  override def pause(): Unit = ()

  override def resume(): Unit = ()

  override def hide(): Unit = ()

  override def dispose(): Unit = ()

}
object MainMenu