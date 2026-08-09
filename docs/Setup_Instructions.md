# Setup Instructions

## JDK Version Used

This project was built and tested with:

```
java version "17.0.4.1" 2022-08-18 LTS
Java(TM) SE Runtime Environment (build 17.0.4.1+1-LTS-2)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.4.1+1-LTS-2, mixed mode, sharing)
```

Any JDK 17 (or newer) distribution works — this was run with an Oracle/OpenJDK 17 LTS build. The IntelliJ project is also configured for language level `JDK_17` (see `.idea/misc.xml`).

To check your own installed version:

```bash
java -version
javac -version
```

## Installing the JDK (if needed)

1. Download a JDK 17+ build (e.g. from [Adoptium](https://adoptium.net/) or Oracle) for your OS.
2. Run the installer, or on macOS with Homebrew: `brew install openjdk@17`.
3. Confirm `java`/`javac` are on your `PATH` by re-running `java -version`.

## "Hello World" Verification

Before running LearnTrack itself, a minimal sanity check that the JDK is installed and working correctly:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compiled and run from the terminal:

```bash
javac HelloWorld.java
java HelloWorld
```

Expected output:

```
Hello, World!
```

`javac` compiles the `.java` source into a `.class` file containing JVM bytecode, and `java` launches the JVM to execute that bytecode. See [`JVM_Basics.md`](JVM_Basics.md) for what's happening under the hood at each step.

## Running LearnTrack

See the root [`README.md`](../README.md) for the actual project compile/run commands.
