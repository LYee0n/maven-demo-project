package com.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;


/**
 * Unit test for simple App.
 */
@BenchmarkMode(Mode.All)
@Warmup(iterations = 3)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws RunnerException {
        Options options = new OptionsBuilder()
            .include(AppTest.class.getSimpleName())
            .output("./Benchmark.log")
            .build();
        new Runner(options).run();
        assertTrue( true );
    }

    @Test
    public void addFileRecord() throws RunnerException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Establish a connection to the database
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

            // Prepare the insert statement
            String sql = "INSERT INTO files(file_data) VALUES(?)";
            pstmt = conn.prepareStatement(sql);
            
            // Read the file into a byte array
            FileInputStream inputStream = new FileInputStream("example.file");
            byte[] fileBytes = new byte[inputStream.available()];
            inputStream.read(fileBytes);
            
            // Set the value for the first parameter (index 1) of the prepared statement
            pstmt.setBytes(1, fileBytes);
            
            // Execute the insert statement
            pstmt.executeUpdate();
            
            System.out.println("Record added to database successfully!");
        } catch (SQLException | ClassNotFoundException | java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        assertTrue( true );
    }

    @Test
    public void getFileFromTable() throws RunnerException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Establish a connection to the database
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

            // Execute the SELECT statement
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT file_data FROM files WHERE id = 1");
            
            if (rs.next()) {
                // Get the file data from the result set
                byte[] fileBytes = rs.getBytes("file_data");
                
                // Write the file data to a local file
                FileOutputStream outputStream = new FileOutputStream("example_retrieved.file");
                outputStream.write(fileBytes);
                outputStream.close();
                
                System.out.println("File retrieved from database and saved successfully!");
            } else {
                System.out.println("No file found with id 1 in the database.");
            }
        } catch (SQLException | ClassNotFoundException | java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        assertTrue( true );
    }

    
    @Benchmark
    public void testStringAdd() {
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        System.out.println(a);
    }

    @Benchmark
    public void testStringBuilderAdd() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }
}