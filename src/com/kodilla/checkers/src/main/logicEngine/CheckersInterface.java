package com.kodilla.checkers.src.main.logicEngine;

public interface CheckersInterface {
    Board getCheckerBoard();
    Board moveFigure(Board board, MoveCoords moveCoords);
}
