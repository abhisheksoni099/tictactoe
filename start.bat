@echo off

mvn clean compile assembly:single
java -cp target/tictactoe-1.0-jar-with-dependencies.jar com.abhisheksoni.tictactoe.Runner
