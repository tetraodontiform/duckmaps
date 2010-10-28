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
class PlanSpec extends UnitSpec {
  
  def "name may not be null"() {
    given:
    def plan = new Plan()
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate()
    "nullable" == plan.errors["name"]
  }
  
  def "name may not be blank"() {
    given:
    def plan = new Plan(name: "")
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["name"])
    "blank" == plan.errors["name"]
  }
  
  def "name can have 255 characters"() {
    given:
    def plan = new Plan(name: "")
    (1..255).each { plan.name += "a" }
    mockForConstraintsTests Plan, [plan]
    
    expect:
    true == plan.validate(["name"])
  }
  
  def "name may not have 256 characters"() {
    given:
    def plan = new Plan(name: "")
    (1..256).each { plan.name += "a" }
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["name"])
    "maxSize" == plan.errors["name"]
  }
}
