## General 

To run these tutorials, you will need a [java compiler](https://www.java.com/en/download/help/linux_install.html). For Ubuntu, this can be as simple as:

```
sudo apt update
sudo apt install default-jre
sudo apt install default-jdk
javac -version
```


## Times Tables

Create an interactive tool for playing a Times Tables quiz. The complexity increases as the tutorials progress.


```
# compile all classes at once
javac timestables/*.java
# run
java timestables.TimesTable1
java timestables.TimesTable2
java timestables.TimesTable3
...
```


## Recursion

These tutorials aren't as fleshed out as Times Tables, but do give a flavour of how the concept of recursion can be implemented in Java.

To compile these programs:

```
javac recursion/*.java
```

### Tooth

```
0	
0	1	
0	1	2	
0	1	2	3	
0	1	2	3	4	
0	1	2	3	4	5	
0	1	2	3	4	
0	1	2	3	
0	1	2	
0	1	
0	
```

Recursive tooth building, with a couple of exercises. It can be run with:

```
java recursion.Tooth 5
# Perhaps try to write the exercises first before looking at the source?
java recursion.ToothEx1 5 10
java recursion.ToothEx2 5
```


### Clock

```
  _______
 /  12   \
|    |    |
|9   |   3|
|     \   |
|         |
 \___6___/

```


This one is a pretty weak tutorial (I wrote it over 10 years ago!). You can run it with:

```
java recursion.ClockTest
```

As an exercise, try to extend this to accept command-line arguments to specify the number of ticks for each clock hand (no solution given!).
