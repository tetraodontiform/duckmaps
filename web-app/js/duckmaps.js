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
(function() {

  /**
   * A Canvas is used to draw on it. This Canvas is an abstraction layer
   * on top of SVGweb library, that provides some methods, that make drawing
   * simpler.
   */
  var Canvas = function() {
    var svgContainer = document.getElementById(arguments[0]);

    var svgRoot = document.createElementNS(svgns, "svg");
    svgRoot.onsvgload = function() {svgRoot = this;};
    svgRoot.setAttribute("width", arguments[1]);
    svgRoot.setAttribute("height", arguments[2]);

    svgweb.appendChild(svgRoot, svgContainer);
  };

  /**
   * The central type for instanciating a DuckMap in the browser.
   */
  var DuckMap = function() {
    var canvas = new Canvas(arguments[0], arguments[1], arguments[2]);
  };

  window["DuckMap"] = DuckMap;

})();
