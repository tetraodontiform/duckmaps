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
  
  def "created may not be null"() {
    given:
    def plan = new Plan()
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["created"])
    "nullable" == plan.errors["created"]
  }
  
  def "created may not be in the future"() {
    given:
    def plan = new Plan(created: new Date() + 1)
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["created"])
    "max" == plan.errors["created"]
  }
  
  def "created can be now"() {
    given:
    def plan = new Plan(created: new Date())
    mockForConstraintsTests Plan, [plan]
    
    expect:
    true == plan.validate(["created"])
  }
  
  def "created can be in the past"() {
    given:
    def plan = new Plan(created: new Date() - 1)
    mockForConstraintsTests Plan, [plan]
    
    expect:
    true == plan.validate(["created"])
  }
  
  def "updated may not be null"() {
    given:
    def plan = new Plan()
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["updated"])
    "nullable" == plan.errors["updated"]
  }
  
  def "updated may not be in the future"() {
    given:
    def plan = new Plan(updated: new Date() + 1)
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["updated"])
    "max" == plan.errors["updated"]
  }
  
  def "updated can be now"() {
    given:
    def plan = new Plan(updated: new Date())
    mockForConstraintsTests Plan, [plan]
    
    expect:
    true == plan.validate(["updated"])
  }
  
  def "updated can be in the past"() {
    given:
    def plan = new Plan(updated: new Date() - 1)
    mockForConstraintsTests Plan, [plan]
    
    expect:
    true == plan.validate(["updated"])
  }
  
  def "updated may not before created"() {
    given:
    def plan = new Plan(created: new Date(), updated: new Date() - 1)
    mockForConstraintsTests Plan, [plan]
    
    expect:
    false == plan.validate(["updated", "created"])
    1 == plan.errors.errorCount
    "early" == plan.errors["updated"]
  }
  
  def "updated may be at the same time as created"() {
    given:
    def now = new Date()
    def plan = new Plan(created: now, updated: now)
    mockForConstraintsTests Plan, [plan]
    
    expect:
    true == plan.validate(["updated", "created"])
  }
}
