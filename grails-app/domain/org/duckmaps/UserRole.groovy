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

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * @author <a href="http://github.com/derjan1982">Jan Ehrhardt</a>
 */
class UserRole implements Serializable {
  
  User user
  
  Role role
  
  boolean equals(other) {
    if (!(other instanceof UserRole)) {
      false
    }
    
    other.user?.id == user?.id && other.role?.id == role?.id
  }
  
  int hashCode() {
    def builder = new HashCodeBuilder()
    
    if(user) {
      builder.append(user.id)
    }
    
    if(role) {
      builder.append(role.id)
    }
    
    builder.toHashCode()
  }
  
  static UserRole get(long userId, long roleId) {
    find 'from UserRole where user.id=:userId and role.id=:roleId',
        [userId: userId, roleId: roleId]
  }
  
  static UserRole create(User user, Role role, boolean flush = false) {
    new UserRole(user: user, role: role).save(flush: flush, insert: true)
  }
  
  static boolean remove(User user, Role role, boolean flush = false) {
    UserRole instance = UserRole.findByUserAndRole(user, role)
    
    if(instance) {
      instance.delete(flush: flush)
    }
    else {
      false
    }
  }
  
  static void removeAll(User user) {
    executeUpdate 'DELETE FROM UserRole WHERE user=:user', [user: user]
  }
  
  static void removeAll(Role role) {
    executeUpdate 'DELETE FROM UserRole WHERE role=:role', [role: role]
  }
  
  static mapping = {
    id composite: ['role', 'user']
    version false
  }
}
