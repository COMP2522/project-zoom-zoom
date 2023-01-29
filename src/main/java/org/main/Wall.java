package org.main;

import processing.core.PVector;

public abstract class Wall {
    public abstract boolean hasHitWall(PVector currentPosition);

    public abstract void draw();
}
