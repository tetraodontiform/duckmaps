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

/**
 * @author <a href="http://github.com/derjan1982">Jan Ehrhardt</a>
 */
class User {
  
  String username
  
  String password
  
  boolean enabled
  
  boolean accountExpired
  
  boolean accountLocked
  
  boolean passwordExpired
  
  static constraints = {
    username blank: false, unique: true
    password blank: false
  }
  
  static mapping = { password column: '`password`' }
  
  Set<Role> getAuthorities() {
    UserRole.findAllByUser(this).collect { it.role } as Set
  }
}
