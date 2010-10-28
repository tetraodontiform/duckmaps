/**
 * This file is part of the DuckMaps software for making building plans
 * available on the Internet.
 * 
 * Copyright (C) 2010  Jan Ehrhardt
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.duckmaps

import grails.plugin.spock.UnitSpec

/**
 * @author <a href="http://www.ducktools.org">Jan Ehrhardt</a>
 */
class TagSpec extends UnitSpec {
  
  def "key may not be null"() {
    given:
    def tag = new Tag()
    mockForConstraintsTests Tag, [tag]
    
    expect:
    false == tag.validate(["key"])
    "nullable" == tag.errors["key"]
  }
  
  def "key may not be blank"() {
    given:
    def tag = new Tag(key: "")
    mockForConstraintsTests Tag, [tag]
    
    expect:
    false == tag.validate(["key"])
    "blank" == tag.errors["key"]
  }
  
  def "key can have 50 characters"() {
    given:
    def tag = new Tag(key: "")
    (1..50).each { tag.key += "a" }
    mockForConstraintsTests Tag, [tag]
    
    expect:
    true == tag.validate(["key"])
  }
  
  def "key may not have 51 characters"() {
    given:
    def tag = new Tag(key: "")
    (1..51).each { tag.key += "a" }
    mockForConstraintsTests Tag, [tag]
    
    expect:
    false == tag.validate(["key"])
    "maxSize" == tag.errors["key"]
  }
  
  def "value may not be null"() {
    given:
    def tag = new Tag()
    mockForConstraintsTests Tag, [tag]
    
    expect:
    false == tag.validate(["value"])
    "nullable" == tag.errors["value"]
  }
  
  def "value may not be blank"() {
    given:
    def tag = new Tag(value: "")
    mockForConstraintsTests Tag, [tag]
    
    expect:
    false == tag.validate(["value"])
    "blank" == tag.errors["value"]
  }
  
  def "value can have 50 characters"() {
    given:
    def tag = new Tag(value: "")
    (1..50).each { tag.value += "a" }
    mockForConstraintsTests Tag, [tag]
    
    expect:
    true == tag.validate(["value"])
  }
  
  def "value may not have 51 characters"() {
    given:
    def tag = new Tag(value: "")
    (1..51).each { tag.value += "a" }
    mockForConstraintsTests Tag, [tag]
    
    expect:
    false == tag.validate(["value"])
    "maxSize" == tag.errors["value"]
  }
  
  def "dateCreated may not be null"() {
    given:
    def plan = new Plan()
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["dateCreated"])
    "nullable" == plan.errors["dateCreated"]
  }
  
  def "lastUpdated may not be null"() {
    given:
    def plan = new Plan()
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["lastUpdated"])
    "nullable" == plan.errors["lastUpdated"]
  }
}
