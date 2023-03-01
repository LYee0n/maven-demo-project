package com.demo;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.yaml.snakeyaml.*;

public class SchemaGenerator {

    // Method to retrieve schema information from a PostgreSQL database
    public Map<String, Map<String, Object>> generateSchema(String url, String user, String password) throws SQLException {
        Map<String, Map<String, Object>> schema = new LinkedHashMap<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData metaData = conn.getMetaData();

            // Retrieve table information
            try (ResultSet tables = metaData.getTables(null, null, null, new String[] {"TABLE"})) {
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    Map<String, Object> tableDef = new LinkedHashMap<>();
                    List<String> columnNames = new ArrayList<>();
                    try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
                        while (columns.next()) {
                            String columnName = columns.getString("COLUMN_NAME");
                            String dataType = columns.getString("TYPE_NAME");
                            Map<String, Object> columnDef = new LinkedHashMap<>();
                            columnDef.put("name", columnName);
                            columnDef.put("type", dataType);
                            if (columns.getBoolean("IS_AUTOINCREMENT")) {
                                columnDef.put("autoIncrement", true);
                            }
                            columnNames.add(columnName);
                            tableDef.put("columns", columnNames);
                            tableDef.put(columnName, columnDef);
                        }
                    }

                    // Retrieve foreign key information
                    try (ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName)) {
                        List<String> foreignKeyList = new ArrayList<>();
                        while (foreignKeys.next()) {
                            String foreignKeyName = foreignKeys.getString("FK_NAME");
                            String parentTable = foreignKeys.getString("PKTABLE_NAME");
                            String parentColumn = foreignKeys.getString("PKCOLUMN_NAME");
                            String foreignKey = parentTable + "." + parentColumn;
                            foreignKeyList.add(foreignKey);
                        }
                        if (!foreignKeyList.isEmpty()) {
                            tableDef.put("foreignKeys", foreignKeyList);
                        }
                    }

                    schema.put(tableName, tableDef);
                }
            }
        }

        return schema;
    }

    // Method to output schema information to a YAML file
    public void outputYaml(Map<String, Map<String, Object>> schema, File outputFile) throws IOException {
        Yaml yaml = new Yaml();
        try (Writer writer = new FileWriter(outputFile)) {
            yaml.dump(schema, writer);
        }
    }
}
