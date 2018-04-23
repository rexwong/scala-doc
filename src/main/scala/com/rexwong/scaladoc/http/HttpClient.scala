package com.rexwong.scaladoc.http

import com.alibaba.fastjson.{JSON, JSONObject}
import scalaj.http.{Http, HttpOptions}

/**
  * 抓取youku电影[唐人街探案2]接口查询豆瓣的评论
  *
  * @author rexwong
  */
object HttpClient {

  def main(args: Array[String]): Unit = {
    val result = Http("http://p.comments.youku.com/ycp/comment/pc/getDbComment")
      .params(Map("app" -> "100-DDwODVkv",
        "page" -> "1",
        "videoId" -> "883924481",
        "showId" -> "322277",
        "sign" -> "bc7fe10a462227b9e0158a0984fdf870",
        "time" -> "1524448799"))
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString

    val json = JSON.parseObject(result.body)

    if (json.getString("message").equals("success")) {
      val pageResule = json.getJSONObject("data").getJSONObject("con").getJSONObject("pageResult")
      val data = pageResule.getJSONArray("data")

      val dataIter = data.iterator()
      while (dataIter.hasNext){
        val content = dataIter.next().asInstanceOf[JSONObject].get("content");
        println(content)
      }
    }
  }

}
