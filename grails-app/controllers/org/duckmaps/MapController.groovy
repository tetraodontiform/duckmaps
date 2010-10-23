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
class MapController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    [mapInstanceList: Map.list(params), mapInstanceTotal: Map.count()]
  }

  def create = {
    def mapInstance = new Map()
    mapInstance.properties = params
    return [mapInstance: mapInstance]
  }

  def save = {
    def mapInstance = new Map(params)
    if (mapInstance.save(flush: true)) {
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'map.label', default: 'Map'), mapInstance.id])}"
      redirect(action: "show", id: mapInstance.id)
    }
    else {
      render(view: "create", model: [mapInstance: mapInstance])
    }
  }

  def show = {
    def mapInstance = Map.get(params.id)
    if (!mapInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'map.label', default: 'Map'), params.id])}"
      redirect(action: "list")
    }
    else {
      [mapInstance: mapInstance]
    }
  }

  def edit = {
    def mapInstance = Map.get(params.id)
    if (!mapInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'map.label', default: 'Map'), params.id])}"
      redirect(action: "list")
    }
    else {
      return [mapInstance: mapInstance]
    }
  }

  def update = {
    def mapInstance = Map.get(params.id)
    if (mapInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (mapInstance.version > version) {
          mapInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'map.label', default: 'Map')] as Object[], "Another user has updated this Map while you were editing")
          render(view: "edit", model: [mapInstance: mapInstance])
            return
        }
      }
      mapInstance.properties = params
      if (!mapInstance.hasErrors() && mapInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'map.label', default: 'Map'), mapInstance.id])}"
        redirect(action: "show", id: mapInstance.id)
      }
      else {
        render(view: "edit", model: [mapInstance: mapInstance])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'map.label', default: 'Map'), params.id])}"
      redirect(action: "list")
    }
  }

  def delete = {
    def mapInstance = Map.get(params.id)
    if (mapInstance) {
      try {
        mapInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'map.label', default: 'Map'), params.id])}"
        redirect(action: "list")
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'map.label', default: 'Map'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'map.label', default: 'Map'), params.id])}"
      redirect(action: "list")
    }
  }
}
