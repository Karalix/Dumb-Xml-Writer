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

import com.kodintek.dumbxmlwriter.Attribute;
import static org.junit.Assert.*;

/**
 *
 * @author Alix
 */
public class AttributeTest {
    
    public AttributeTest() {
    }
    
    
    @org.junit.Test
    public void testInitialization() {
        System.out.println("Constructor");
        Attribute instance = new Attribute("test", "test");
        assertNotNull(instance);
    }
    
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testInitializationWithNameNull() {
        System.out.println("Constructor with name null");
        Attribute instance = new Attribute(null, "test");
    }
    
    @org.junit.Test
    public void testInitializationWithValueNull() {
        System.out.println("Constructor with value null");
        Attribute instance = new Attribute("test", null);
        assertNotNull(instance);
    }

    /**
     * Test of getValue method, of class Attribute.
     */
    @org.junit.Test
    public void testGetValue() {
        System.out.println("getValue");
        Attribute instance = new Attribute("test", "test");
        String expResult = "test";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getValue method, of class Attribute. If null, it should return empty String
     */
    @org.junit.Test
    public void testGetValueWithNull() {
        System.out.println("getValue");
        Attribute instance = new Attribute("test", null);
        String expResult = "";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getValue method, of class Attribute. The result should not be trimmed;
     * 
     */
    @org.junit.Test
    public void testGetValueWithWhitespaces() {
        System.out.println("getValue");
        Attribute instance = new Attribute("test", "   test     ");
        String expResult = "   test     ";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Attribute.
     */
    @org.junit.Test
    public void testGetName() {
        System.out.println("getName");
        Attribute instance = new Attribute("test", "test");
        String expResult = "test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getName method, of class Attribute.
     */
    @org.junit.Test
    public void testGetNameWithWhitesapces() {
        System.out.println("getName");
        Attribute instance = new Attribute("   test     ", "test");
        String expResult = "test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Attribute.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        Attribute instance = new Attribute("test", "test");
        String expResult = "test=\"test\"";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of toString method, of class Attribute.
     */
    @org.junit.Test
    public void testToStringWithWhitespaces() {
        System.out.println("toString");
        Attribute instance = new Attribute("    test ", " test  ");
        String expResult = "test=\" test  \"";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
