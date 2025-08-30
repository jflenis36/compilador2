REMiLang — Starter Kit (Java + ANTLR 4 + Maven)
===============================================

Descripción
-----------
Plantilla mínima para construir un compilador educativo en Java usando ANTLR 4.
Funciona en IntelliJ IDEA o desde la Terminal con Maven.

Requisitos
----------
- Java JDK 17 o superior
- Maven 3.9 o superior
- (Opcional) IntelliJ IDEA / VS Code

Comprueba versiones:
  java -version
  mvn -version


QUICK START
-----------
1) Compilar:
  mvn -q clean compile

2) Ejecutar el compilador (ejemplo con examples/ejemplo.remi):
  mvn -q exec:java -Dexec.mainClass=com.remilang.Main -Dexec.args="examples/ejemplo.remi ejemplo"
   - El segundo argumento "ejemplo" es opcional; se usa como baseName de salida.
   - El compilador generará: out/ejemploOut.java

3) Compilar y ejecutar el código generado:
  javac -encoding UTF-8 -d out out/ejemploOut.java
  java -cp out ejemploOut

Nota: si no pasas baseName, se deduce del nombre del archivo:
  examples/miPrograma.remi  →  out/miProgramaOut.java  →  clase miProgramaOut


Estructura del proyecto
-----------------------
```
.
├─ examples/                     (casos de prueba .remi)
│   └─ ejemplo.remi
├─ src/
│   └─ main/
│       ├─ antlr4/
│       │   └─ RemiLang.g4      (gramática ANTLR)
│       └─ java/
│           └─ com/remilang/
│               ├─ Main.java    (orquestación: léxico → parser → semántica → código)
│               ├─ Semantico.java
│               └─ GeneradorCodigo.java
├─ out/                          (archivos Java generados, p. ej. ejemploOut.java)
├─ pom.xml
└─ README.md (este archivo)
```

Qué debes implementar
---------------------
- Gramática (RemiLang.g4): tokens, reglas del parser, precedencias y regla inicial.
- Análisis semántico (Semantico.java): tabla de símbolos, tipos, validaciones.
- Generación de código (GeneradorCodigo.java): producir un .java ejecutable.

Consejo: define una regla inicial clara (por ejemplo, "programa") y mantenla sincronizada con Main.java (parser.programa() o la que corresponda).


Casos de prueba sugeridos (colócalos en examples/)
--------------------------------------------------
OK básicos:
  - declaraciones, asignaciones, impresión
  - precedencia aritmética y paréntesis
  - si / sino / fin
  - mientras / fin
  - comparaciones de enteros (y, opcionalmente, == / != en cadenas)

Errores/avisos:
  - variable no declarada
  - tipo incompatible (ej. asignar cadena a entero)
  - doble declaración
  - uso antes de inicializar (warning o error, según tu política)
  - división por cero literal

Ejemplos:

(01_decl_print.remi)
  entero x;
  x = 5;
  imprimir(x);

(08_tipo_error.remi)
  entero x;
  x = "hola";   # debería reportar error de tipos


Ciclo de desarrollo recomendado
-------------------------------
1. Edita la gramática RemiLang.g4.
2. mvn -q clean compile   (genera y compila lexer/parser)
3. Ejecuta Main con un .remi simple.
4. Implementa/ajusta Semantico y GeneradorCodigo.
5. Añade más casos a examples/ y vuelve a probar.


Troubleshooting
---------------
- "class not found: com.remilang.Main"
  Asegúrate de que las clases tengan:  package com.remilang;
  Ejecuta con: -Dexec.mainClass=com.remilang.Main

- El parser no encuentra la regla inicial
  Cambia parser.programa() en Main.java por el nombre real de tu regla raíz.

- No se generan clases de ANTLR
  Ejecuta: mvn -q clean compile
  Maven coloca los .java generados en target/generated-sources/antlr4
  y compila todo a target/classes.

- Conflictos de paquetes
  Mantén tus .java en src/main/java/com/remilang/ y declara
  package com.remilang; al inicio de cada archivo.


.gitignore sugerido
-------------------
# Maven / Build
target/

# ANTLR artefactos sueltos
*.tokens
*.interp

# Salidas locales
out/
*.class

# IDE / SO
.idea/
*.iml
.vscode/
.DS_Store
Thumbs.db


Créditos
--------
- ANTLR 4 — https://www.antlr.org/ (generación de analizadores léxicos/sintácticos)
- Este Starter Kit es para fines educativos.
