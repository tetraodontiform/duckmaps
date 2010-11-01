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
 * @author <a href="http://github.com/derjan1982">Jan Ehrhardt</a>
 */
class EntrySpec extends UnitSpec {
  
  def "name may not be null"() {
    given:
    def entry = new Entry()
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["name"])
    "nullable" == entry.errors["name"]
  }
  
  def "name may not be blank"() {
    given:
    def entry = new Entry(name: "")
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["name"])
    "blank" == entry.errors["name"]
  }
  
  def "name can have 255 characters"() {
    given:
    def entry = new Entry(name: "")
    (1..255).each { entry.name += "a" }
    mockForConstraintsTests Entry, [entry]
    
    expect:
    true == entry.validate(["name"])
  }
  
  def "name may not have 256 characters"() {
    given:
    def entry = new Entry(name: "")
    (1..256).each { entry.name += "a" }
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["name"])
    "maxSize" == entry.errors["name"]
  }
  
  def "description can be blank"() {
    given:
    def entry = new Entry(description: "")
    mockForConstraintsTests Entry, [entry]
    
    expect:
    true == entry.validate(["description"])
  }
  
  def "dateCreated may not be null"() {
    given:
    def entry = new Entry()
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["dateCreated"])
    "nullable" == entry.errors["dateCreated"]
  }
  
  def "lastUpdated may not be null"() {
    given:
    def entry = new Entry()
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["lastUpdated"])
    "nullable" == entry.errors["lastUpdated"]
  }
  
  def "plan may not be null"() {
    given:
    def entry = new Entry()
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["plan"])
    "nullable" == entry.errors["plan"]
  }
  
  def "refId may not be null"() {
    given:
    def entry = new Entry()
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["refId"])
    "nullable" == entry.errors["refId"]
  }
  
  def "refId may not be blank"() {
    given:
    def entry = new Entry(refId: "")
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["refId"])
    "blank" == entry.errors["refId"]
  }
  
  def "refId can have 255 characters"() {
    given:
    def entry = new Entry(refId: "")
    (1..255).each { entry.refId += "a" }
    mockForConstraintsTests Entry, [entry]
    
    expect:
    true == entry.validate(["refId"])
  }
  
  def "refId may not have 256 characters"() {
    given:
    def entry = new Entry(refId: "")
    (1..256).each { entry.refId += "a" }
    mockForConstraintsTests Entry, [entry]
    
    expect:
    false == entry.validate(["refId"])
    "maxSize" == entry.errors["refId"]
  }
}
