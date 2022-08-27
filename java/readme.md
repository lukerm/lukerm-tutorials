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
