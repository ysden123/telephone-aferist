/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import java.text.SimpleDateFormat
import java.util.Date
import scala.util.Try

case class Telephone(number:String, date:Date)

object Telephone:
  private val dateFormat = SimpleDateFormat("yyyy/MM/dd")

  def fromString(text: String): Try[Telephone] =
    Try {
      if text == null || text.isEmpty || text.isBlank then throw new RuntimeException("Wrong phone number")
      val parts = text.split(",")
      if parts.length != 2 then throw new RuntimeException("Wrong phone number")
      val date = dateFormat.parse(parts(1).stripLeading())
      Telephone(parts(0), date)
    }