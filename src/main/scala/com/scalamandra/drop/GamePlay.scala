package com.scalamandra.drop

import com.badlogic.gdx.{Audio, Gdx, Input, Screen}
import com.badlogic.gdx.audio.{Music, Sound}
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.{OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.{MathUtils, Rectangle, Vector3, Vector2}
import com.badlogic.gdx.utils.{ScreenUtils, TimeUtils}
import com.scalamandra.drop.GamePlay.*
import com.scalamandra.drop.models.*
import com.scalamandra.drop.routing.Navigator
import com.scalamandra.drop.screens.GameScreen
import com.scalamandra.drop.utils.*

class GamePlay(protected val batch: SpriteBatch) extends GameScreen with Drawable {

  private inline def texture(name: String) = Texture(Files.internal(name))
  private inline def audio[T](name: String)(get: Audio => FileHandle => T): T =
    get(Gdx.audio)(Files.internal(name))
  private inline def sound(name: String): Sound = audio(name)(_.newSound)
  private inline def music(name: String): Music = audio(name)(_.newMusic)

  private val bucket = use {
    Bucket(
      image = texture("bucket.png"),
      x = viewportWidth / 2 - halfOfTexture,
      y = 20f,
    )
  }
  private val drop = use {
    Drop(
      image = texture("droplet.png"),
      sound = sound("drop.wav"),
    )
  }
  private val rain = use {
    val audio = music("rain.mp3")
    audio.setLooping(true)
    Rain(audio)
  }

  protected val camera: OrthographicCamera = OrthographicCamera()
  camera.setToOrtho(false, viewportWidth, viewportHeight)

  private val cursorPosition = Vector3()

  private def spawnRaindrop(): Unit = {
    val raindrop = Vector2(
      MathUtils.random(leftCorner, rightCorner),
      dropSpawn,
    )
    rain.addDrop(raindrop)
    rain.lastDropTime = TimeUtils.nanoTime()
  }

  override def show(): Unit = {
    rain.playMusic()
    spawnRaindrop()
  }

  override def view(): Unit = draw {
    batch.draw(bucket.image, bucket.x, bucket.y)
    rain.drops.foreach { raindrop =>
      batch.draw(drop.image, raindrop.x, raindrop.y)
    }
  }

  override def update(delta: Float): Unit = {
    moveByMouse()
    moveByKey(delta, Input.Keys.LEFT, Direction.Left)
    moveByKey(delta, Input.Keys.RIGHT, Direction.Right)
    
    if(bucket.x < leftCorner) bucket.x = leftCorner
    if(bucket.x > rightCorner) bucket.x = rightCorner
    
    rain.drops.foreachRemove { (raindrop, remove) =>
      raindrop.y -= speed * delta
      if(raindrop.y + textureSize < bottom) remove()
      val overlaps = raindrop.x < bucket.x + textureSize && raindrop.x + textureSize > bucket.x && raindrop.y < bucket.y + textureSize && raindrop.y + textureSize > bucket.y
      if(overlaps) {
        drop.playSound()
        remove()
      }
    }
    
    if(TimeUtils.nanoTime() - rain.lastDropTime > timeBeforeDrop) spawnRaindrop()
  }

  private inline def getInput(getter: Input => Int): Float = getter(Gdx.input).toFloat

  private def moveByMouse(): Unit =
    if(Gdx.input.isTouched()) {
      val x = getInput(_.getX)
      val y = getInput(_.getY)
      cursorPosition.set(x, y, 0f)
      camera.unproject(cursorPosition)
      bucket.x = cursorPosition.x - halfOfTexture
    }

  private inline def moveByKey(delta: Float, onKey: Int, inline direction: Direction): Unit =
    if(Gdx.input.isKeyPressed(onKey)) {
      val step = speed * delta
      inline direction match {
        case Direction.Left =>
          bucket.x -= step
        case Direction.Right =>
          bucket.x += step
      }
    }
  
}
object GamePlay extends Navigator[GamePlay] {
  private enum Direction {
    case Left extends Direction
    case Right extends Direction
  }

  private inline val viewportWidth = 800f
  private inline val viewportHeight = 400f
  private inline val textureSize = 64f
  private inline val halfOfTexture = textureSize / 2
  private inline val rightCorner = viewportWidth - textureSize
  private inline val leftCorner = 0f
  private inline val bottom = 0f
  private inline val dropSpawn = 480f
  private inline val timeBeforeDrop = 1000000000
  private inline val speed = 200
}