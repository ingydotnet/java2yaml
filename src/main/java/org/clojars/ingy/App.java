/*
 * A program to read a java file, parse it to an AST object and then serialize
 * the AST to YAML and print the YAML to stdout.
 */

package org.clojars.ingy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.Map;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class App {
    public static void main(String[] args) throws IOException {
        // Parse the Java file into an AST
        String javaSource = readFile(args[0]);
        ASTParser parser = ASTParser.newParser(AST.JLS14);
        parser.setSource(javaSource.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        Map<String, String> options = Map.of(
            "org.eclipse.jdt.core.compiler.source", "14",
            "org.eclipse.jdt.core.compiler.codegen.targetPlatform", "14",
            "org.eclipse.jdt.core.compiler.compliance", "14"
        );
        parser.setCompilerOptions(options);
        ASTNode compilationUnit = (ASTNode) parser.createAST(null);

        // Serialize the AST as YAML
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(dumperOptions);
        String yamlString = yaml.dump(compilationUnit);

        // Write the YAML to stdout
        System.out.println(yamlString);
    }

    private static String readFile(String filePath) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded, Charset.defaultCharset());
    }
}
