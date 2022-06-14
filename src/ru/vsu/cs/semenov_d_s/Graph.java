package ru.vsu.cs.semenov_d_s;

import java.util.*;

public class Graph {
    private final Map<String, Vertex> graph;

    public static class Edge {
        public final String v1, v2;

        public Edge(String v1, String v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public static class Vertex {
        public final String name;
        public ColorForVertex color = ColorForVertex.NULL;
        public Map<String, Vertex> neighbours = new HashMap<>();

        public Vertex(String name) {
            this.name = name;
        }

        public Vertex(String name, ColorForVertex color) {
            this.name = name;
            this.color = color;
        }

        public boolean neighborOfSameColor() {
            for (String vName : this.neighbours.keySet()) {
                if (this.neighbours.get(vName).color.equals(this.color)) {
                    return true;
                }
            }
            return false;
        }
    }

    public Graph() {
        graph = new HashMap<>(0);
    }

    public void addEdge(String v1, String v2, ColorForVertex color1, ColorForVertex color2) {
        Edge edge = new Edge(v1, v2);
        if (!graph.containsKey(edge.v1)) {
            graph.put(edge.v1, new Vertex(edge.v1, color1));
        }
        if (!graph.containsKey(edge.v2)) {
            graph.put(edge.v2, new Vertex(edge.v2, color2));
        }

        graph.get(v1).neighbours.put(v2, graph.get(v2));
        graph.get(v2).neighbours.put(v1, graph.get(v1));
    }

    public void addEdge(String v1, String v2) {
        if (graph.containsKey(v1) && graph.containsKey(v2)) {
            graph.get(v1).neighbours.put(v2, graph.get(v2));
            graph.get(v2).neighbours.put(v1, graph.get(v1));
        }
    }

    public void removeEdge(String v1, String v2) {
        if (graph.containsKey(v1) && graph.containsKey(v2)) {
            if (graph.get(v1).neighbours.containsKey(v2) && graph.get(v2).neighbours.containsKey(v1)) {
                graph.get(v1).neighbours.remove(v2);
                graph.get(v2).neighbours.remove(v1);
            }
        }
    }

    public void addVertex(String name, ColorForVertex color) {
        if (!graph.containsKey(name)) {
            Vertex v = new Vertex(name, color);
            graph.put(name, v);
        }
    }

    public void removeVertex(String name) {
        if (graph.containsKey(name)) {
            Vertex vertexForRemoving = graph.get(name);
            for (String currName : graph.keySet()) {
                Vertex currVertex = graph.get(currName);
                currVertex.neighbours.remove(vertexForRemoving.name);
            }
            graph.remove(name);
        }
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder();
        String nl = System.getProperty("line.separator");
        sb.append("graph").append(" {").append(nl).append(nl);
        List<Vertex> vertexes = new ArrayList<>();

        for (Map.Entry<String, Vertex> entry : graph.entrySet()) {
            Vertex v = entry.getValue();
            String vColor = "red";

            if (v.color == ColorForVertex.GREEN) {
                vColor = "green";
            }

            if (v.neighbours.size() == 0) {
                sb.append("{ ").append(v.name).append(" [color=\"").append(vColor).append("\"] } ");
            } else {
                for (String v1 : v.neighbours.keySet()) {
                    if (!vertexes.contains(graph.get(v1))) {
                        String v1Color = "red";
                        if (graph.get(v1).color == ColorForVertex.GREEN) {
                            v1Color = "green";
                        }

                        sb.append("{ ").append(v.name).append(" [color=\"").append(vColor).append("\"] } ");
                        sb.append(" -- ").append("{ ").append(v1).append(" [color=\"").append(v1Color).append("\"] } ");
                        sb.append(nl);
                    }
                }
                vertexes.add(v);
            }
        }
        sb.append("}").append(nl);

        return sb.toString();
    }

    public String takeFirstKeyInSet() {
        return graph.keySet().iterator().next();
    }

    public static Graph graphFromString(String graphStr) {
        Graph graph = new Graph();
        String[] lines = graphStr.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] values = lines[i].split(" ");
            graph.addEdge(values[0], values[1], colorFromString(values[2]), colorFromString(values[3]));
        }
        return graph;
    }

    public static ColorForVertex colorFromString(String colorStr) {
        if (colorStr.equals("(red)")) {
            return ColorForVertex.RED;
        } else if (colorStr.equals("(green)")) {
            return ColorForVertex.GREEN;
        } else {
            return ColorForVertex.NULL;
        }
    }


    public boolean isBigraph() {
        String vertexName = takeFirstKeyInSet();
        return isBigraph(graph.get(vertexName), new HashSet<>());
    }

    private boolean isBigraph(Vertex v, Set<Vertex> set) {
        if (!set.contains(v)) {
            set.add(v);
            if (v.neighborOfSameColor()) {
                return false;
            }
            for (String neighborName : v.neighbours.keySet()) {
                Vertex neighbour = v.neighbours.get(neighborName);
                if (!set.contains(neighbour)) {
                    if (!isBigraph(neighbour, set)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
