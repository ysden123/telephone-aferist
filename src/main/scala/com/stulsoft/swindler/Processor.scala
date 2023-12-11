/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import com.typesafe.scalalogging.StrictLogging

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class Combination(prefix: String, numbers: mutable.Set[String])

object Processor extends StrictLogging:
/*  def findCommonPrefixes(telephones: List[Telephone]): Result =
    val analyzedCombinations2 = mutable.HashMap[String, Combination]()

    val analyzedCombinations = mutable.HashMap[String, String]()
    val sortedTelephones = telephones.sortBy(tel => tel.number)
    sortedTelephones.foreach(tel => logger.debug(tel.toString))
    val prefixes = mutable.SortedMap[String, Prefix]()
    for (i1 <- sortedTelephones.indices) {
      val number1 = sortedTelephones(i1).number
      logger.debug("number1: {}", number1)
      for (i2 <- i1 + 1 until sortedTelephones.indices.length) {
        val number2 = sortedTelephones(i2).number
        logger.debug("number2: {}", number2)
        var prefix = ""
        var continue = true
        for (j <- number1.indices if j < number2.length && continue) {
          if number1(j) == number2(j)
          then
            prefix += number1(j)
          else
            continue = false
        }
        logger.debug("number1: {}, number2: {}, prefix: {}", number1, number2, prefix)
        if prefix.nonEmpty then
          val combination = analyzedCombinations2.get(prefix) match
            case None =>
              val aComb = Combination(prefix, mutable.HashSet[String]())
              analyzedCombinations2.addOne(prefix -> aComb)
              aComb
            case Some(comb) =>
              comb
          if !combination.numbers.contains(number2) then
            combination.numbers.addOne(number2)
            val previous = prefixes.getOrElseUpdate(prefix, Prefix(prefix, 1))
            prefixes(prefix) = Prefix(prefix, previous.count + 1)
        /*          if !analyzedCombinations.exists((pref,num) => pref == prefix && num == number2)
                  then
                    logger.debug("Not found => prefix: {}, number2: {}", prefix, number2)
                    analyzedCombinations.addOne(prefix -> number2)
                    val previous = prefixes.getOrElseUpdate(prefix, Prefix(prefix, 1))
                    prefixes(prefix) = Prefix(prefix, previous.count + 1)
                  else
                    logger.debug("Found => prefix: {}, number2: {}", prefix, number2)
        */
      }
      logger.debug("-----------------------")
    }
    logger.debug("analyzedCombinations:")
    analyzedCombinations.foreach(comb => logger.debug(comb.toString()))
    Result(telephones.length, prefixes.values)
  end findCommonPrefixes
*/
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
    uniquePrefixes.addOne(lastPrefix)
    
    logger.debug("uniquePrefixes:")
    uniquePrefixes.foreach(prefix=>logger.debug(prefix.toString))

    Result(telephones.length, uniquePrefixes.toList)
  end findCommonPrefixes
end Processor
