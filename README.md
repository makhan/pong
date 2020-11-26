# pong
A minimal pong implementation in Java

The goal was to implement Pong in one day in simple Java, without using a game engine or game libraries. Took more than a day, but it is done.

Requires javax.vecmath (see [installation options](https://stackoverflow.com/a/45070532/125382)).

Command line usage:
```
$ javac -cp /usr/share/java/vecmath.jar *.java -d ./
$ java -cp /usr/share/java/vecmath.jar:. pong.Game
```
