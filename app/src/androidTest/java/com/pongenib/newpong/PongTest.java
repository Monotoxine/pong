package com.pongenib.newpong;

import org.junit.Test;

/**
 * Created by user on 04/11/2017.
 */
public class PongTest {

    @Test
    public void testExecute() throws Exception {
        Pong pong = new Pong(500, 500);
        Mur mur = new Mur(20, 30, 50, 60);
        pong.add(mur);
        Triangle triangle = new Triangle(10, 80, 60, 60, 0.45);
        triangle.setPong(pong);
        pong.add(triangle);
        pong.start();
        System.in.read();
    }
}