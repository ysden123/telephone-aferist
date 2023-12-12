/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import com.typesafe.scalalogging.StrictLogging

import java.text.SimpleDateFormat
import java.util.Date
import scala.util.Try

case class Telephone(number:String, date:Date)

object Telephone extends StrictLogging:
  private val dateFormat = SimpleDateFormat("yyyy/MM/dd")

  def fromString(text: String): Try[Telephone] =
    Try {
      if text == null || text.isEmpty || text.isBlank
        then
          logger.error("The text is null, empty or blank. text is |" + text + "|")
          throw new RuntimeException("Wrong phone number")
      val parts = text.split(",")
      if parts.length != 2
        then
          logger.error("Number of parts is not 2")
          throw new RuntimeException("Wrong phone number")
      try
        val date = dateFormat.parse(parts(1).stripLeading())
        Telephone(parts(0), date)
      catch
        case exception: Exception=>
          logger.error("Failed converting to date. text is: |" + text + "| - " + exception.getMessage, exception)
          throw exception
    }