# Codokar

![cicd](https://github.com/sylhare/codokar/workflows/Codokar%20CI%20CD/badge.svg)

A tool to get OKR measurement out of your code.

## Setup 

To run:

```bash
gradle build
```

Then take the fat jar and run:

```bash
java -jar codokar-fat-1.0.jar '/path/to/java/or/kotlin/project'
> 80%
```

The percentage is the amount of packet private classes you have in your project.

## OKR

OKR: Objective Key Results.

### Encapsulation

Encapsulation is measure by looking the ratio of package private classes versus total amount of classes in your code.
The higher the percentage, the more encapsulated your code is (the aim is not to be 100%).

The advantages of encapsulation in Object Oriented Programming is that:

- One class one job (Keep the logic inside the class)
- New features are easily added through common interfaces
- Changes can be done independently 
- No need to change the tests, when you change the implementation

