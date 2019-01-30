package com.kodilla.checkers.src.main.logicEngine;

public interface CheckersInterface {
    Board getCheckerBoard();
    Board moveFigure(MoveCoords moveCoords);
}
