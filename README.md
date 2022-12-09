# maven-demo-project


```
mvn archetype:generate \
  -DgroupId=com.demo \
  -DartifactId=demo-maven-plugin \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DarchetypeArtifactId=maven-archetype-plugin
```

```
docker compose up -d
```

```
mvn jooq-codegen:generate
```

```
mvn demo:touch
mvn demo:touch -Dsayhi.greeting=?
```