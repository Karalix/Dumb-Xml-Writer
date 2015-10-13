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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * Class Element
 * 
 * Represents a XML element, typically made of 
 * - a name
 * - a list of attributes
 * - a text value
 * or
 * - XML elements as children
 * 
 * @author Alix Ducros
 */
public class Element {
    /**
     * The name of the element.
     */
    private String elementName ;
    /**
     * The list of attributes.
     */
    private Map<String, Attribute> attributes ;
    /**
     * The list of children.
     */
    private List<Element> children ;
    /**
     * The parent of the current element.
     */
    private Element parent ;
    /**
     * The depth of the current element : (0 if it is root element, 1, if child of root, etc).
     */
    private int depth ;
    /**
     * Text value of the element.
     */
    private String text ;
    /**
     * Separator to use to indent according to depth.
     */
    private String tabs = "    ";
    /**
     * Character to use at the end of an XML node
     */
    private char returns = '\n';

    /**
     * Initializes the element with no parent
     * 
     * The parent of the element is then set to null
     * 
     * @throws IllegalArgumentException if the name of the element is null or empty
     * 
     * @param elementName the name of the element
     */
    public Element(String elementName) {
        if(elementName == null || elementName.trim().length() == 0) {
            throw new IllegalArgumentException("element must have a non-empty name.");
        }
        this.elementName = elementName.trim();
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
    }

    /**
     * Initializes the element with a parent
     * 
     * @throws IllegalArgumentException if the name of the element is null or empty
     * 
     * @param elementName the name of the element
     * @param parent the parent of the created element
     */
    public Element(String elementName, Element parent) {
        if(elementName == null || elementName.trim().length() == 0) {
            throw new IllegalArgumentException("element must have a non-empty name.");
        }
        this.elementName = elementName.trim();
        this.parent = parent;
        this.attributes = new HashMap<>();
        this.children = new ArrayList<>();
        
        if(parent != null) {
            this.depth = parent.getDepth() + 1 ;
            parent.addChild(this);
        }
    }

    /**
     * Returns the depth of the element
     * 
     * @return depth
     */
    private int getDepth() {
        return depth ;
    }
    
    /**
     * Adds an attribute to the element.
     * 
     * @throws InvalidAttributeException if the attribute name is null or empty, or if there is already an attribute with the same name
     * 
     * @param name the name of the attribute
     * @param value the value of the attribute
     */
    public void addAttribute(String name, String value) {
        if(name == null || name.trim().length()==0) {
            throw new InvalidAttributeException("the name cannot have null or empty value.");
        }
        if(attributes.containsKey(name)) {
            throw new InvalidAttributeException("the element already has an attribute with this name : "+name);
        }
        this.attributes.put(name, new Attribute(name, value));
    }
    
    /**
     * Adds a child element
     * 
     * @param child the child to add to the element
     * @throws InvalidChildException if the element already contains a text value or if the given child element is null.
     */
    public void addChild(Element child) {
        if(child == null) {
            throw new InvalidChildException("the child cannot have null value.");
        }
        this.children.add(child);
        child.setParent(this);
    }
    
    /**
     * Adds a text value to the element
     * 
     * @param text the text to add to the element
     * @throws InvalidChildException if the element already contains XML child elements or if it already has some text value
     */
    public void addTextChild(String text) {
        if(this.text != null) {
            throw new InvalidChildException("the element has already a text child.");
        }
        this.text = text.trim() ;
    }

    /**
     * Sets the parent og the element
     * @param parent the new parent of the element
     */
    private void setParent(Element parent) {
        this.parent = parent ;
        this.depth = parent.getDepth()+1;
    }

    /**
     * Returns a String formatted accordingly to the XML syntax
     * 
     * The returned String will be nicely indented
     * 
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<this.depth ; i++) {
            sb.append(this.tabs);
        }
        sb.append('<');
        sb.append(this.elementName);
        for(Entry<String, Attribute> e :attributes.entrySet()) {
            sb.append(' ');
            sb.append(e.getValue().toString());
        }
        //If there is no text value nor children, the element is made of only one block
        if(this.text == null && children.isEmpty()) {
            sb.append("/>");
            sb.append(this.returns);
            return sb.toString();
        }
        sb.append('>');
        sb.append(this.returns);
        
        //Add the text value
        for(int i = 0 ; i<this.depth+1 ; i++) {
            sb.append(this.tabs);
        }
        sb.append(this.text);
        sb.append(this.returns);
        //Add the XML children
        for(Element e : this.children) {
            sb.append(e.toString());
        }
        
        for(int i = 0 ; i<this.depth ; i++) {
            sb.append(this.tabs);
        }
        sb.append("</");
        sb.append(this.elementName);
        sb.append('>');
        sb.append(this.returns);
        
        return sb.toString();
    }
    
    /**
     * Returns a String formatted accordingly to the XML syntax
     * 
     * If compacted is set to true, the returned String will be compacted. The result is not perfect.
     * 
     * @param compacted if true, the return String will be compacted
     * @return 
     */
    public String toString(boolean compacted){
        
        String returnValue = toString() ;
        if(compacted) {
            returnValue = returnValue.replaceAll(">[ \n\t]*<", "><");
            returnValue = returnValue.replaceAll("\n", "");
        }
        return returnValue ;
    }
    
    
}
