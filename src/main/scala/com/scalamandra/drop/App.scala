package com.scalamandra.drop

import com.badlogic.gdx.*
import com.badlogic.gdx.audio.*
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}
import com.badlogic.gdx.math.{Rectangle, Vector2, Vector3}
import com.badlogic.gdx.utils.ScreenUtils
import com.scalamandra.drop.routing.*

class App extends GameRouter {
  private lazy val batch: SpriteBatch = SpriteBatch()
  private lazy val font = BitmapFont()

  override def create(): Unit = {
    addScreen(GamePlay) {
      GamePlay(batch)
    }
    val menu = MainMenu(batch, font, this)
    setScreen(menu)
  }

  override def dispose(): Unit = {
    if (screen != null) screen.dispose()
    batch.dispose()
    font.dispose()
  }
  
}