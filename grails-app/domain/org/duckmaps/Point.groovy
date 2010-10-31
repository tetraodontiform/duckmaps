package org.duckmaps

class Point extends Tagged {
  
  int x
  
  int y
  
  Date dateCreated
  
  Date lastUpdated
  
  static belongsTo = [plan: Plan]
}
