package com.example.child1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Person person = new Person("Alice", 30, "alice@example.com");

        {
            String name = person.getName();
            int age = person.getAge();
            String email = person.getEmail();
            System.out.println( name );
            System.out.println( age );
            System.out.println( email );

        }

        person.setName("Bob");
        person.setAge(40);
        person.setEmail("bob@example.com");


        {
            String name = person.getName();
            int age = person.getAge();
            String email = person.getEmail();
            System.out.println( name );
            System.out.println( age );
            System.out.println( email );

        }
    }
}
