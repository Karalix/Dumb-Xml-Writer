# Dumb-Xml-Writer
A simple tiny library to quickly obtain XML elements as String


```java
Element e = new Element("root") ;
e.addAttribute("href", "/Converstaions/aaa");
Element e2 = new Element("p", e);
e2.addTextChild("Coucou le monde !");
Element e3 = new Element("vide");
e3.addAttribute("lol", "3");
e.addChild(e3);
e.addTextChild("Yo mec.");
e.toString()
```

will give

```xml
<root href="/Converstaions/aaa">
    Yo mec.
    <p>
        Coucou le monde !
    </p>
    <vide lol="3"/>
</root>
```

or instead if using `e.toString(true)`

```xml
<root href="/Converstaions/aaa">    Yo mec.    <p>        Coucou le monde !    </p><vide lol="3"/></root>
```

This simple library is to enable me to generate *good enough* XML in a simple way.


Any comments welcome.
