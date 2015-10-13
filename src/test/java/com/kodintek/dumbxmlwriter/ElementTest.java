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

import com.kodintek.dumbxmlwriter.InvalidChildException;
import com.kodintek.dumbxmlwriter.Element;
import com.kodintek.dumbxmlwriter.InvalidAttributeException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alix
 */
public class ElementTest {
    
    public ElementTest() {
    }

    /**
     * Test of Constructor with 1 param.
     */
    @Test
    public void testInitialization() {
        System.out.println("Constructor");
        Element e = new Element("test");
        assertNotNull(e);
    }
    
    /**
     * Test of Constructor with 1 param with null value
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitializationWithNull() {
        System.out.println("Constructor");
        Element e = new Element(null);
    }
    
    /**
     * Test of Constructor with 1 param with empty String
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitializationWithEmptyString() {
        System.out.println("Constructor");
        Element e = new Element("");
    }
    
    /**
     * Test of Constructor with 2 param.
     */
    @Test
    public void testInitializationTwoParam() {
        System.out.println("Constructor");
        Element e = new Element("test");
        Element e2 = new Element("test",e);
        assertNotNull(e2);
    }
    
    /**
     * Test of Constructor with 2 param with null parent
     */
    @Test
    public void testInitializationTwoParamWitNullParent() {
        System.out.println("Constructor");
        Element e2 = new Element("test",null);
        assertNotNull(e2);
    }
    
    /**
     * Test of Constructor with 2 param with null value
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitializationTwoParamWithNull() {
        System.out.println("Constructor");
        Element e = new Element("test");
        Element e2 = new Element(null, e);
    }
    
    /**
     * Test of Constructor with 2 param with empty String
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitializationTwoParamWithEmptyString() {
        System.out.println("Constructor");
        Element e = new Element("test");
        Element e2 = new Element("",e);
    }
    
    /**
     * Test of addAttribute method, of class Element.
     */
    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        String name = "test";
        String value = "test";
        Element instance = new Element("test");
        instance.addAttribute(name, value);
    }
    
    /**
     * Test of addAttribute method, of class Element with a null name
     */
    @Test(expected = InvalidAttributeException.class)
    public void testAddAttributeWithNullName() {
        System.out.println("addAttribute");
        String name = null;
        String value = "test";
        Element instance = new Element("test");
        instance.addAttribute(name, value);
    }
    
    /**
     * Test of addAttribute method, of class Element with an empty string for name
     */
    @Test(expected = InvalidAttributeException.class)
    public void testAddAttributeWithEmptyName() {
        System.out.println("addAttribute");
        String name = null;
        String value = "test";
        Element instance = new Element("test");
        instance.addAttribute(name, value);
    }
    
    /**
     * Test of addAttribute method, of class Element with adding a duplicate attribute
     */
    @Test(expected = InvalidAttributeException.class)
    public void testAddAttributeWithDuplicate() {
        System.out.println("addAttribute");
        String name = "test";
        String value = "test";
        Element instance = new Element("test");
        instance.addAttribute(name, value);
        instance.addAttribute(name, value);
    }

    /**
     * Test of addChild method, of class Element.
     */
    @Test
    public void testAddChild() {
        System.out.println("addChild");
        Element child = new Element("child");
        Element instance = new Element("parent");
        instance.addChild(child);
    }
    
    /**
     * Test of addChild method, of class Element with a null child.
     */
    @Test(expected = InvalidChildException.class)
    public void testAddChildWithNullValue() {
        System.out.println("addChild");
        Element child = null ;
        Element instance = new Element("parent");
        instance.addChild(child);
    }
    
    /**
     * Test of addChild method, of class Element when there is already a text child
     */
    @Test
    public void testAddChildWhenAlreadyText() {
        System.out.println("addChild");
        Element child = new Element("child");
        Element instance = new Element("parent");
        instance.addTextChild("test");
        instance.addChild(child);
    }

    /**
     * Test of addTextChild method, of class Element.
     */
    @Test
    public void testAddTextChild() {
        System.out.println("addTextChild");
        String text = "test";
        Element instance = new Element("test");
        instance.addTextChild(text);
    }
    
    /**
     * Test of addTextChild method, of class Element.
     */
    @Test
    public void testAddTextChildWhenAlreadyChild() {
        System.out.println("addTextChild");
        Element child = new Element("child");
        Element instance = new Element("parent");
        instance.addChild(child);
        instance.addTextChild("test");
    }
    
    /**
     * Test of addTextChild method, of class Element.
     */
    @Test(expected = InvalidChildException.class)
    public void testAddTextChildWhenAlreadyTextChild() {
        System.out.println("addTextChild");
        Element child = new Element("child");
        Element instance = new Element("parent");
        instance.addTextChild("test");
        instance.addTextChild("test");
    }

    /**
     * Test of compactize method, of class Element.
     */
    @Test
    public void testCompactize() {
        System.out.println("compactize");
        Element e = new Element("root") ;
        e.addAttribute("href", "/Converstaions/aaa");
        Element e2 = new Element("p", e);
        e2.addTextChild("Coucou le monde !");
        Element e3 = new Element("vide");
        e3.addAttribute("lol", "3");
        e.addChild(e3);
        assertFalse(e3.toString(true).contains("\n"));
    
    }

    /**
     * Test of deCompactize method, of class Element.
     */
    @Test
    public void testDeCompactize() {
        System.out.println("deCompactize");
        Element e = new Element("root") ;
        e.addAttribute("href", "/Converstaions/aaa");
        Element e2 = new Element("p", e);
        e2.addTextChild("Coucou le monde !");
        Element e3 = new Element("vide");
        e3.addAttribute("lol", "3");
        e.addChild(e3);
        assertTrue(e3.toString(false).contains("\n"));
    }

    /**
     * Test of toString method, of class Element.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Element e = new Element("root") ;
        e.addAttribute("href", "/Converstaions/aaa");
        Element e2 = new Element("p", e);
        e2.addTextChild("Coucou le monde !");
        Element e3 = new Element("vide");
        e3.addAttribute("lol", "3");
        e.addChild(e3);
        assertTrue(e3.toString().contains("\n"));
    }
    
}
