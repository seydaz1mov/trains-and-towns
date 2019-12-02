# trains-and-towns
Trains and Towns problem solution

# How to run app?

1. Write input data to src/main/resources/input.txt file
2. Click run button on TrainsAndTownsApp class's main method 

# solution explanations

From 1th to 5th problem: Just go from start city to end city and each time add distance to total distance counter.


To solve 8th and 9th problems I have used Dijkstra algorithm.



To solve 6th, 7th and 10th problems I have used Dynamic Programming:

6th problem:
```
For each stoppage:
    For each town:
        numberOfTripsWithMaximumThreeStoppage[town][stoppage] = 
            sumOf(
                for each incoming_town:
                    numberOfTripsWithMaximumThreeStoppage[incoming_town][stoppage - 1] 
            )

Get sum of numberOfTripsWithMaximumThreeStoppage[town][stoppage from 1 to 3]
```


7th problem:
```
The same trick as in 6th problem, but just get trips with exactly 4 stoppage.

For each stoppage:
    For each town:
        numberOfTripsWithFourStoppage[town][stoppage] = 
            sumOf(
                for each incoming_town:
                    numberOfTripsWithFourStoppage[incoming_town][stoppage - 1] 
            )

Get numberOfTripsWithFourStoppage[town][4]
```


10th problem:
```
numberOfTripsForFixedDistance[name_of_town][stoppage_number][distance] = 
sumOf(
    for each incoming_town_name:
        numberOfTripsForFixedDistance[incoming_town_name][stoppage_number - 1][distance - TownsRouteDistance(from: incoming_town_name, to: name_of_town)] 
)
```

