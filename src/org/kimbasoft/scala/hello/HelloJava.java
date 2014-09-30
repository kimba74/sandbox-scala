package org.kimbasoft.scala.hello;

/**
 * Missing documentation
 *
 * @since 1.0
 */
public class HelloJava {

    private String name;

    public HelloJava() {
        this("stranger");
    }

    public HelloJava(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hello " + name + ", this is the HelloJava class";
    }
}
