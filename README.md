# Splitter
Java program that calculates and outputs the transfers needed for an equal split of expenses in a group of people. This project was part of an assignment. The plan for this is to extend on the UX part and make it more interaction friendly, or at least change the mechanism for the input. See below for a current working version.
## Get started
* Have Java 17 installed
* Clone this repository
* Run `./gradlew run` (`./gradlew.bat run` on Windows) using the `--args` flag. <br></br> For example `./gradlew run --args="Willy 320 Tim 40 Gaby 48 Tim 100 Karl"` has each named person followed by their expense. Since Karl has no value after his name, the program just assumes 0 for his part.
