/*
 * Copyright (c) 2023. StulSoft
 */

package com.stulsoft.swindler

import org.scalatest.flatspec.AnyFlatSpec

import scala.util.{Failure, Success}

class ProcessorTest extends AnyFlatSpec:
  "findCommonPrefixes" should "find common prefixes" in {
    Parser.parseFile("src/test/resources/data/list2.txt") match
      case Success(telephones) =>
        telephones.foreach(println)
        val commonPrefixes = Processor.findCommonPrefixes(telephones)
        println("commonPrefixes:")
        commonPrefixes.prefixes.foreach(println)
        assertResult(1)(commonPrefixes.prefixes.size)
        //        commonPrefixes.foreach(println)
        assertResult(true)(commonPrefixes.prefixes.toList.map(p => p.number).contains("+972 54-228-"))
      case Failure(exception) =>
        fail(exception.getMessage)
  }

  "findCommonPrefixes" should "find common prefixes 2" in {
    Parser.parseFile("src/test/resources/data/list3.txt") match
      case Success(telephones) =>
        val result = Processor.findCommonPrefixes(telephones)
        result.prefixes.foreach(println)
      case Failure(exception) =>
        fail(exception.getMessage)
  }

  "findCommonPrefixes" should "find common prefixes 4" in {
    Parser.parseFile("src/test/resources/data/list4.txt") match
      case Success(telephones) =>
        val result = Processor.findCommonPrefixes(telephones)
        result.prefixes.foreach(println)
      case Failure(exception) =>
        fail(exception.getMessage)
  }

  "findCommonPrefixes" should "find common prefixes 5" in {
    Parser.parseFile("src/test/resources/data/list5.txt") match
      case Success(telephones) =>
        val result = Processor.findCommonPrefixes(telephones)
        result.prefixes.foreach(println)
      case Failure(exception) =>
        fail(exception.getMessage)
  }

  "findCommonPrefixes" should "find common prefixes 6" in {
    Parser.parseFile("src/test/resources/data/list6.txt") match
      case Success(telephones) =>
        val result = Processor.findCommonPrefixes(telephones)
        result.prefixes.foreach(println)
      case Failure(exception) =>
        fail(exception.getMessage)
  }

  "findCommonPrefixes" should "find common prefixes 6-2" in {
    Parser.parseFile("src/test/resources/data/list6.txt") match
      case Success(telephones) =>
        val result = Processor.findCommonPrefixes(telephones)
        println(s"Total number of telephones = ${result.total}")
        println(s"Prefixes:")
        result.prefixes.foreach(println)
      case Failure(exception) =>
        fail(exception.getMessage)
  }