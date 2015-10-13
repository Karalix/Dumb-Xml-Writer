/*
 * Copyright (C) 2015 Alix Ducros
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kodintek.dumbxmlwriter;

import java.util.Map;

/**
 * Class Attribute
 * 
 * represents a XML attribute 'name="value"'
 * 
 * @author Alix Ducros
 */
class Attribute{
    private final String value;
    private final String name;

    /**
     * Initializes the Attribute
     * 
     * @throws IllegalArgumentException if the name is null or empty
     * 
     * if value is null, it then replaced by an empty String
     * 
     * @param name the name of the attribute
     * @param value the value of the attribute
     */
    Attribute(String name, String value) {
        if(name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("'name' parameter cannot be null nor empty");
        }
        this.name = name.trim() ;
        
        if(value == null) {
            this.value = "" ;
        } else {
            this.value = value ;
        }
    }
    
    /**
     * Returns the value of the attribute
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Return the name of the attribute
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a String formatted accordingly to the XML syntax
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name.trim());
        sb.append("=\"");
        sb.append(value);
        sb.append('"');
        return sb.toString() ;
    }
}
