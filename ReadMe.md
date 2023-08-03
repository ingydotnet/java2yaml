java2yaml
=========

Convert a Java file to an AST and write it as YAML to stdout


## Synopsis

To build and run against a test Java file, run:
```
make run
```

This will build and run the program.

Building is just `mvn build` and running is:
```
CLASSPATH=$PWD/target/classes java org.clojars.ingy.App <some-java-file>
```

I'm currently testing with Clojure's `Cons.java` file which you can get with
`make Cons.java`.
