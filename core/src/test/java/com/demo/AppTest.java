package com.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static org.jooq.impl.DSL.*;
import static com.demo.codegen.Tables.*;
import org.jooq.*;
import org.jooq.impl.*;

/**
 * Unit test for simple App. https://www.jooq.org/javadoc/dev/org.jooq/org/jooq/impl/DSL.html
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void jooqSelectDemo()
    {
        System.out.println(
            select(ACCOUNT.NAME)
            .from(ACCOUNT)
            );

        assertTrue( true );
    }

    @Test
    public void jooqUpdateDemo()
    {
        System.out.println(
            update(ACCOUNT)
            .set(ACCOUNT.NAME, "bbb")
            .set(ACCOUNT.PASSWORD, "ccc")
            .where(ACCOUNT.ID.eq("aaa"))
            );
        assertTrue( true );
    }
    
    @Test
    public void jooqInsertDemo()
    {
        System.out.println(
            insertInto(ACCOUNT, ACCOUNT.NAME,ACCOUNT.PASSWORD)
            .values("asd","asd")
            .values("bas","bas")
            );

        assertTrue( true );
    }
    
    @Test
    public void jooqDeleteDemo()
    {
        System.out.println(
            deleteFrom(ACCOUNT)
            .where(ACCOUNT.ID.eq("aaa"))
            );

        assertTrue( true );
    }
}
