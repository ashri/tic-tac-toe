# Tic Tac Toe Example

An example of a basic web application which can return a move in a Tic Tac Toe game.

This example is built by TravisCI then deployed to Heroku.

## Build and run locally

You will require a Java 8 install.

`mvn clean tictactoe:run`

That will start the embedded server running on port 8080. To run on another port you can:

`mvn clean tictactoe:run --port $PORT`
