# Setup Instructions

## JDK Version Used

I built and ran this project with:

```
java version "17.0.4.1" 2022-08-18 LTS
Java(TM) SE Runtime Environment (build 17.0.4.1+1-LTS-2)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.4.1+1-LTS-2, mixed mode, sharing)
```

Any JDK 17 or newer should work. This one is an Oracle/OpenJDK 17 LTS build. The IntelliJ project is also set to language level `JDK_17` (see `.idea/misc.xml`).

To check your own version, run:

```bash
java -version
javac -version
```

## Installing the JDK

1. Download a JDK 17 or newer build (for example from [Adoptium](https://adoptium.net/) or Oracle) for your OS.
2. Run the installer. On macOS with Homebrew you can also run `brew install openjdk@17`.
3. Run `java -version` again to check that `java` and `javac` are on your PATH.

## Hello World Check

Before running LearnTrack, here is a small check to make sure Java is installed correctly.

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run it from the terminal:

```bash
javac HelloWorld.java
java HelloWorld
```

You should see:

```
Hello, World!
```

`javac` turns the `.java` file into a `.class` file with bytecode. `java` then runs that bytecode. See [`JVM_Basics.md`](JVM_Basics.md) for more on this.

## Running LearnTrack

See the `README.md` in the project root for the actual compile and run commands.
