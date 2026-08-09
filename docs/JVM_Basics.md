# JVM Basics

## JDK, JRE, and JVM

- **JVM (Java Virtual Machine)** — the program that actually runs Java bytecode. It's what turns `.class` files into a running process: loading classes, managing memory (the heap, garbage collection), and executing instructions. Every OS/platform has its own JVM implementation, but they all understand the same bytecode format.
- **JRE (Java Runtime Environment)** — the JVM plus the standard library classes (`java.lang`, `java.util`, `java.io`, etc.) needed to actually run a compiled Java program. If you only need to *run* Java applications, the JRE is enough.
- **JDK (Java Development Kit)** — the JRE plus the developer tooling needed to *build* Java programs: `javac` (the compiler), `jar`, `javadoc`, debugging tools, and so on. This project needs the JDK, not just the JRE, because we're compiling `.java` source files.

In short: **JDK ⊃ JRE ⊃ JVM** — the JDK contains the JRE, and the JRE contains the JVM.

## What Is Bytecode?

When you compile a `.java` file with `javac`, you don't get machine code specific to your CPU — you get **bytecode**, a compact, platform-independent instruction set stored in a `.class` file. Bytecode is a middle layer: higher-level than raw machine instructions, but lower-level than Java source. The JVM reads this bytecode and either interprets it directly or uses a Just-In-Time (JIT) compiler to translate hot code paths into native machine instructions at runtime for speed.

You can see this yourself in this project — compiling any file under `com.airtribe.learntrack/` produces a `.class` file (see the `out/` directory), and that `.class` file, not the original `.java` source, is what the JVM actually executes.

## "Write Once, Run Anywhere"

This phrase describes the core promise of the JDK/JVM split: Java source is compiled once into bytecode, and that same bytecode can run unmodified on any machine that has a compatible JVM installed — Windows, macOS, Linux, or otherwise — without recompiling for each platform. The portability lives in the JVM, not in the bytecode: each OS/CPU combination gets its own native JVM build, but that JVM guarantees the same bytecode behaves the same way everywhere.

This is different from a language like C, where the compiler produces machine code tied to a specific OS/architecture, and you'd need to recompile from source for each target platform. With Java, the `.class` files produced by `javac` on this machine would run identically on a different OS, as long as a JVM is present there.
