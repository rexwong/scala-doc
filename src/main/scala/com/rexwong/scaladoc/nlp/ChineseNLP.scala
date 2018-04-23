package com.rexwong.scaladoc.nlp

import com.hankcs.hanlp.tokenizer.NLPTokenizer

import scala.collection.JavaConversions._
import scala.io.Source

object ChineseNLP {
  def main(args: Array[String]): Unit = {
    val filename = "/Users/rexwong/Documents/comments.txt"
    for (line <- Source.fromFile(filename).getLines) {
      val ws = line.replaceAll("#",",").wordSplit()
      println(ws.mkString("^"))
    }
  }
  implicit class WordSplit(word: String) extends Serializable {

    def wordSplit(flag: Boolean = false): Seq[String] = {
      Option(word) match {
        case None => Seq.empty[String]
        case Some(s) => {
          val el = NLPTokenizer.segment(s.trim)
          val result = if (el.isEmpty) Seq.empty[String]
          else {
            el.map(x => {
              x.word.trim.replaceAll(" ", "")
            }).filterNot(_.isEmpty).distinct
          }
          flag match {
            case false => result
            case true => result.map(_.replaceAll(result.mkString("  "), ""))
          }
        }
      }
    }
  }
}
