package ru.vsu.cs.semenov_d_s;

import org.junit.Assert;
import org.junit.Test;

public class TestGraphAsBigraph {

    @Test
    public void testGraphAsBigraph1() {
        String graphInString = "a b (red) (green)\n" +
                "b c (green) (red)\n" +
                "c d (red) (green)\n" +
                "c e (red) (green)";
        Graph graph = Graph.graphFromString(graphInString);
        boolean expectedResult = true;
        boolean actualResult = graph.isBigraph();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGraphAsBigraph2() {
        String graphInString = "a b (green) (green)\n" +
                "b c (green) (red)\n" +
                "c d (red) (green)\n" +
                "c e (red) (green)";
        Graph graph = Graph.graphFromString(graphInString);
        boolean expectedResult = false;
        boolean actualResult = graph.isBigraph();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGraphAsBigraph3() {
        String graphInString = "a b (green) (green)\n" +
                "b c (green) (red)";
        Graph graph = Graph.graphFromString(graphInString);
        boolean expectedResult = false;
        boolean actualResult = graph.isBigraph();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGraphAsBigraph4() {
        String graphInString = "a b (green) (red)\n" +
                "b c (red) (green)";
        Graph graph = Graph.graphFromString(graphInString);
        boolean expectedResult = true;
        boolean actualResult = graph.isBigraph();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGraphAsBigraph5() {
        String graphInString = "e f (red) (green)\n";
        Graph graph = Graph.graphFromString(graphInString);
        boolean expectedResult = true;
        boolean actualResult = graph.isBigraph();
        Assert.assertEquals(expectedResult, actualResult);
    }

}
