# FliteTrakr

## Challenge

Consider you became the new personal assistant of our CIO. As you can image he is traveling a lot and usually doing so by plane. Since we need to optimize out travel expenses you have to look for a way how to get the best out of our money and search for an optimized travel plan. Our main offices are located in Herzogenaurach (NUE), Canton (CAK), Boston MA (BOS), Portland OR (PDX), Amsterdam (AMS) and Hong Kong (HKG). Unfortunately not all airports are reachable from any other airport directly or stop-over flights are cheaper so that you also have to consider Frankfurt (FRA), London-Heathrow (LHR) and Dubai (DXB) cause in all of those he'll find a comfortable Lounge.

You should develop an application that is reading in the current connection table including prices as the first line and being able to answer questions with every following line.

The format of each connection will be defined by `<code-of-departure-airport>-<code-of-arrival-airport>-<price-in-euro>` so e.g. `AMS-PDX-617`. Multiple values will be separated by a comma and an optional whitespace. The line containing the price list will have the prefix `Connections:`.

```
Connections: AMS-PDX-617,NUE-AMS-123, AMS-LHR-43,LHR-HKG-1235, NUR-FRA-61, FRA-HKG-1087
```

With this information your application should be able to answer questions like:

* *What is the price of the connection **NUE-LHR-BOS**?*
* *What is the price of the connection **NUE-FRA-DXB-HKG-PDX-LHR**?*
    * If the route exists add all prices and print out the number without any format, e.g. `2356`
    * If the route does not exists print out `No such connection found!`
* *What is the cheapest connection from **AMS** to **DXB**?*
* *What is the cheapest connection from **FRA** to **FRA**?*
    * Find the cheapest connection regardless of the stops in between and print out the route as well as final price, e.g. `AMS-LHR-FRA-DXB-1347`
    * If such a route does not exists print out `No such connection found!`
    * If the starting airport is equal to the destination airport assume that our CIO wants to travel and not stay in the same city. So the answer should be `FRA-LHR-FRA-312` instead of `FRA-FRA-0`.
 * *How many different connections with maximum 3 stops exist between **PDX** and **BOS**?*
 * *How many different connections with minimum 1 stop exist between **FRA** and **LHR**?*
 * *How many different connections with exactly 2 stops exist between **DXB** and **DXB**?*
    * Resolve the various trips and count the amount. Just print out the number, e.g. `7`. Be aware that `0` can also be totally valid.
 * *Find all connections from **AMS** to **LHR** below 2500 Euros!*
    * You'll have to get all connections and print them out comma separated and sorted cheapest first. Be aware that the connection might also include doing a stop-over at one place multiple times, e.g. `AMS-LHR-19, AMS-FRA-LHR-73, AMS-FRA-AMS-LHR-99, AMS-FRA-AMS-FRA-LHR-127, …`
   
So given the following input:

```
Connections: NUE-FRA-43, NUE-AMS-67, FRA-AMS-17, FRA-LHR-27, LHR-NUE-23
#1: What is the price of the connection NUE-FRA-LHR?
#2: What is the price of the connection NUE-AMS-LHR?
#3: What is the price of the connection NUE-FRA-LHR-NUE?
#4: What is the cheapest connection from NUE to AMS?
#5: What is the cheapest connection from AMS to FRA?
#6: What is the cheapest connection form LHR to LHR?
#7: How many different connections with maximum 3 stops exists between NUE and FRA?
#8: How many different connections with exactly 1 stop exists between LHR and AMS?
#9: Find all connections from NUE to LHR below 170Euros!
```

Should produce the following output:

```
#1: 70
#2: No such connection found!
#3: 93
#4: NUE-FRA-AMS-60
#5: No such connection found!
#6: LHR-NUE-FRA-LHR-93  
#7: 2
#8: 1
#9: NUE-FRA-LHR-70, NUE-FRA-LHR-NUE-FRA-LHR-163
```


## Rules

1. Develop this application as a Java command line application. It doesn't matter if the application reads the files on its own or gets it passed from STDIN. It is perfectly acceptable if it is started using `java -jar app.jar input.txt` or `cat input.txt | java -jar app.jar`
2. Package it up into a self running, all dependency containing jar file.
3. Create a `README.md` explaining how to build/run/use the app.
4. You shouldn't use any framework that solves the task for you but helping ones like Testing or Command Line Wrappers are allowed. In any case name every framework you use in your `README.md` and state what it does any why you used it.
5. Every person having Java and some standard tools (Ant, Maven, Gradle) should be able to check out the code, build and run the app.
6. Think about good design. What do you do to ensure reusability as well as maintainability.
7. Think about how you are ensuring quality assurance in your process.
8. If you're done check in your solution into any public git repo hoster (github, bitbucket, etc.) and send us the link to GlobalSoftwareDevelopment@adidas-group.com