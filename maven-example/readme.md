



```sh
mvn archetype:generate -DgroupId=com.example -DartifactId=hello-world -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

cd hello-world 

# jar > pom in pom.xml <packaging>

mvn archetype:generate -DgroupId=com.example.child1 -DartifactId=hello-world-child1 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.example.child2 -DartifactId=hello-world-child2 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

mvn package

# [ERROR] Source option 5 is no longer supported. Use 7 or later.
# [ERROR] Target option 5 is no longer supported. Use 7 or later.

# append below to pom.xml file
# <build>
#   <plugins>
#     <plugin>
#       <groupId>org.apache.maven.plugins</groupId>
#       <artifactId>maven-compiler-plugin</artifactId>
#       <version>3.8.0</version>
#       <configuration>
#         <source>7</source>
#         <target>7</target>
#       </configuration>
#     </plugin>
#   </plugins>
# </build>

mvn package

# [INFO] hello-world ........................................ SUCCESS [  0.008 s]
# [INFO] hello-world-child1 ................................. SUCCESS [  4.811 s]
# [INFO] hello-world-child2 ................................. SUCCESS [  0.327 s]
# [INFO] ------------------------------------------------------------------------
# [INFO] BUILD SUCCESS
# [INFO] ------------------------------------------------------------------------

java -jar hello-world-child1/target/hello-world-child1-1.0-SNAPSHOT.jar 
# no main manifest attribute, in hello-world-child1/target/hello-world-child1-1.0-SNAPSHOT.jar



# append below to child pom.xml file
#       <plugin>
#         <groupId>org.apache.maven.plugins</groupId>
#         <artifactId>maven-jar-plugin</artifactId>
#         <version>3.2.0</version>
#         <configuration>
#           <archive>
#             <manifest>
#               <mainClass>com.example.child1.App</mainClass>
#             </manifest>
#           </archive>
#         </configuration>
#       </plugin>

mvn package

java -jar hello-world-child1/target/hello-world-child1-1.0-SNAPSHOT.jar 
# Hello World!

```