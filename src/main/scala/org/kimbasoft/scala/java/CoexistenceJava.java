package org.kimbasoft.scala.java;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class CoexistenceJava {

    public static void main(String[] args) {
        HelloWorldJava java = new HelloWorldJava();
        HelloWorldScala scala = new HelloWorldScala();
        
        /* Without parameter */
        System.out.println("-- Without Parameter ---------------");
        System.out.println(java.helloWorld());
        //Using Scala functions with default arguments from Java doesn't seem to be trivial to do.
        // For now I set the "default" value manually.
        System.out.println(scala.helloWorld("world"));

        /* With parameter */
        System.out.println("-- With Parameter ------------------");
        System.out.println(java.helloWorld("John Doe"));
        System.out.println(scala.helloWorld("John Doe"));
    }
}
