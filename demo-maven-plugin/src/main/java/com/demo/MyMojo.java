package com.demo;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Goal which touches a timestamp file.
 */
@Mojo( name = "touch", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class MyMojo
    extends AbstractMojo
{
    /**
     * Location of the file.
     */
    @Parameter( defaultValue = "${project.build.directory}", property = "outputDir", required = true )
    private File outputDirectory;

    
    @Parameter( property = "sayhi.greeting", defaultValue = "Hello World!" )
    private String greeting;
    
    @Parameter
    private boolean myBoolean;
    
    @Parameter
    private Integer myInteger;
    
    @Parameter
    private Double myDouble;
    
    @Parameter
    private Date myDate;
    
    @Parameter
    private File myFile;
    
    @Parameter
    private URL myURL;
    
    public enum Color {
      GREEN,
      RED,
      BLUE
    }
 
    @Parameter(defaultValue = "GREEN")
    private Color myColor;

    @Parameter
    private String[] myArray;

    
    @Parameter
    private List myList;

    
    @Parameter
    private Map myMap;

    
    @Parameter
    private Properties myProperties;

    public void execute()
        throws MojoExecutionException
    {
        getLog().info("Executing...");
        getLog().info(outputDirectory.toString());
        getLog().info(greeting);
        getLog().info("myBoolean: " + myBoolean);
        getLog().info("myInteger: " + myInteger);
        getLog().info("myDouble: " + myDouble);
        getLog().info("myDate: " + myDate);
        getLog().info("myFile: " + myFile);
        getLog().info("myURL: " + myURL);
        getLog().info("myColor: " + myColor);
        getLog().info("myArray: " + myArray);
        getLog().info("myMap: " + myMap);
        getLog().info("myProperties: " + myProperties);
        File f = outputDirectory;

        if ( !f.exists() )
        {
            f.mkdirs();
        }

        File touch = new File( f, "touch.txt" );

        FileWriter w = null;
        try
        {
            w = new FileWriter( touch );

            w.write( "touch.txt" );
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error creating file " + touch, e );
        }
        finally
        {
            if ( w != null )
            {
                try
                {
                    w.close();
                }
                catch ( IOException e )
                {
                    // ignore
                }
            }
        }
    }
}
