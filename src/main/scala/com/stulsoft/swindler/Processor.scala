/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import com.typesafe.scalalogging.StrictLogging

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class Combination(prefix: String, numbers: mutable.Set[String])

object Processor extends StrictLogging:
  def findCommonPrefixes(telephones: List[Telephone]): Result =
    val sortedTelephones = telephones.sortBy(tel => tel.number)

    logger.debug("Sorted telephones:")
    sortedTelephones.foreach(tel => logger.debug(tel.toString))

    val prefixes = mutable.SortedMap[String, Prefix]()
    for (i1 <- sortedTelephones.indices) {
      val number1 = sortedTelephones(i1).number
      for (i2 <- i1 + 1 until sortedTelephones.indices.length) {
        val number2 = sortedTelephones(i2).number
        var prefix = ""
        var continue = true
        for (j <- number1.indices if j < number2.length && continue) {
          if number1(j) == number2(j)
          then
            prefix += number1(j)
          else
            continue = false
        }
        if prefix.nonEmpty then
          val previous = prefixes.getOrElse(prefix, Prefix(prefix, 1))
          prefixes(prefix) = Prefix(prefix, previous.count + 1)
      }
    }

    logger.debug("prefixes:")
    prefixes.values.foreach(prefix => logger.debug("{}", prefix))

    val uniquePrefixes = ListBuffer[Prefix]()
    val prefixesAsList = prefixes.values.toList
    var lastPrefix: Prefix = null
    for (i <- 0 until prefixesAsList.size - 1) {
      if i == 0 then lastPrefix = prefixesAsList.head
      if prefixesAsList(i + 1).number.startsWith(lastPrefix.number)
        then
          lastPrefix = prefixesAsList(i + 1)
      else
        uniquePrefixes.addOne(lastPrefix)
        lastPrefix = prefixesAsList(i + 1)
    }
    if lastPrefix != null then uniquePrefixes.addOne(lastPrefix)
    
    logger.debug("uniquePrefixes:")
    uniquePrefixes.foreach(prefix=>logger.debug(prefix.toString))

    Result(telephones.length, uniquePrefixes.toList)
  end findCommonPrefixes
end Processor
