package com.rexwong.scaladoc

/**
  * Scala's Seq would be Java's List,
  * Scala's List would be Java's LinkedList.
  */
object SeqTest {
  case class RawDataRecord(videoId: String, text: String)
  def main(args: Array[String]): Unit = {

    val seq = Array("a","b").toSeq
    println(seq.mkString(" "))
    val text ="0,1";
    val data = text.split(",")
    println(data.length)
  }
}
