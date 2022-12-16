/*
 * This file is generated by jOOQ.
 */
package com.demo.codegen;


import com.demo.codegen.tables.Account;
import com.demo.codegen.tables.FtpServer;
import com.demo.codegen.tables.FtpServerAccount;
import com.demo.codegen.tables.Station;
import com.demo.codegen.tables.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = -998915092;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.account</code>.
     */
    public final Account ACCOUNT = com.demo.codegen.tables.Account.ACCOUNT;

    /**
     * The table <code>public.ftp_server</code>.
     */
    public final FtpServer FTP_SERVER = com.demo.codegen.tables.FtpServer.FTP_SERVER;

    /**
     * The table <code>public.ftp_server_account</code>.
     */
    public final FtpServerAccount FTP_SERVER_ACCOUNT = com.demo.codegen.tables.FtpServerAccount.FTP_SERVER_ACCOUNT;

    /**
     * The table <code>public.station</code>.
     */
    public final Station STATION = com.demo.codegen.tables.Station.STATION;

    /**
     * The table <code>public.task</code>.
     */
    public final Task TASK = com.demo.codegen.tables.Task.TASK;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Account.ACCOUNT,
            FtpServer.FTP_SERVER,
            FtpServerAccount.FTP_SERVER_ACCOUNT,
            Station.STATION,
            Task.TASK);
    }
}