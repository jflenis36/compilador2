package com.remilang;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Uso: java Main <archivo.remi>");
            return;
        }

        String input = new String(Files.readAllBytes(Paths.get(args[0])));
        RemiLangLexer lexer = new RemiLangLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        RemiLangParser parser = new RemiLangParser(tokens);

        ParseTree tree = parser.programa();
        System.out.println("Árbol sintáctico: " + tree.toStringTree(parser));
    }
}
