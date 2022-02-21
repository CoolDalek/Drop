package com.scalamandra.drop

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.utils.ScreenUtils
import com.scalamandra.drop.routing.Router
import com.scalamandra.drop.screens.GameScreen

class MainMenu(
                protected val batch: SpriteBatch,
                private val font: BitmapFont,
                private val router: Router,
              ) extends GameScreen with Drawable {
  
  protected val camera: OrthographicCamera = OrthographicCamera()
  camera.setToOrtho(false, 800, 480)

  override def show(): Unit = ()
  
  override def view(): Unit = draw {
    font.draw(batch, "Welcome to Drop", 100, 150)
    font.draw(batch, "Tap anywhere to begin", 100, 100)
  }

  override def update(delta: Float): Unit =
    if(Gdx.input.isTouched) {
      router.moveTo(GamePlay.route)
    }

}
