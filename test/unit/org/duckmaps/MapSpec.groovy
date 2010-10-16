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

import grails.plugin.spock.*

/**
 * @author <a href="http://www.ducktools.org">Jan Ehrhardt</a>
 */
class MapSpec extends UnitSpec {
  
  def "validate a map with an empty name"() {
    
    given: "a map with a blank name is mocked for constraints test"
    def map = new Map(name: "")
    mockForConstraintsTests Map, [map]
    
    when: "the map is validated"
    map.validate()
    
    then: "the error for name is blank"
    "blank" == map.errors["name"]
  }
  
  def "validate a map with null as name"() {
    
    given:
    mockForConstraintsTests Map
    def map = new Map()
    
    when:
    map.validate()
    
    then:
    "nullable" == map.errors["name"]
  }
  
  def "validate a map, whose name has 1 characters"() {
    
    given:
    mockForConstraintsTests Map
    def map = new Map(name: "a")
    
    when:
    map.validate()
    
    then:
    null == map.errors["name"]
  }
  
  def "validate a map, whose name has 256 characters"() {
    
    given:
    mockForConstraintsTests Map
    def name = ""
    (1..256).each { name += "a" }
    def map = new Map(name: name)
    
    when:
    map.validate()
    
    then:
    "maxSize" == map.errors["name"]
  }
  
  def "validate a map, whose name has 255 characters"() {
    
    given:
    mockForConstraintsTests Map
    def name = ""
    (1..255).each { name += "a" }
    def map = new Map(name: name)
    
    when:
    map.validate()
    
    then:
    null == map.errors["name"]
  }
  
  def "validate a map with null as created date"() {
    
    given:
    mockForConstraintsTests Map
    def map = new Map()
    
    when:
    map.validate()
    
    then:
    "nullable" == map.errors["created"]
  }
}
