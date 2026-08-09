# JVM Basics

## JDK, JRE, and JVM

JVM (Java Virtual Machine) is the program that runs Java bytecode. It turns a `.class` file into a running program. It loads classes, manages memory, and runs the instructions. Every OS has its own JVM, but they all understand the same bytecode.

JRE (Java Runtime Environment) is the JVM plus the standard library classes, like `java.lang`, `java.util`, and `java.io`. If you only want to run a Java program, the JRE is enough.

JDK (Java Development Kit) is the JRE plus the tools you need to build Java programs, like `javac` (the compiler), `jar`, and `javadoc`. This project needs the JDK because we compile `.java` files.

So: JDK contains JRE, and JRE contains JVM.

## What Is Bytecode?

When you compile a `.java` file using `javac`, Java does not convert it directly into code that your computer's CPU understands. Instead, it creates a `.class` file containing bytecode.

You can think of bytecode as a common language that Java uses so the same program can run on different operating systems and CPUs.

When you run the program, the JVM (Java Virtual Machine) reads the bytecode and converts it into instructions that your computer can execute. The JVM can also optimize frequently used parts of the program while it is running to make them faster.

You can see this in this project. When you compile any file under `com.airtribe.learntrack/`, a corresponding `.class` file is created in the `out/` directory. The JVM runs this `.class` file, not the original `.java` file.

## Write Once, Run Anywhere

This means you compile your Java code once into bytecode, and that same bytecode can run on any computer that has a JVM, whether it is Windows, macOS, or Linux. You don't need to recompile it for each system. The JVM is what makes this possible. Each OS has its own JVM build, but every JVM runs the same bytecode the same way.

This is different from a language like C. In C, the compiler makes machine code for one specific OS and CPU. If you want to run it on a different system, you need to recompile it from the source code. With Java, the same `.class` file made by `javac` on this machine would also run on a different OS, as long as that machine has a JVM.
