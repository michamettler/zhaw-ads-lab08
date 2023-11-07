package ch.zhaw.ads;

import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.Scanner;

public class LabyrinthServer implements CommandExecutor {
    ServerGraphics g = new ServerGraphics();

    public Graph<DijkstraNode, Edge> createGraph(String s) {
        Graph<DijkstraNode, Edge> graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
        Scanner scanner = new Scanner(s);
        while (scanner.hasNextLine()) {
            String[] coordinates = scanner.nextLine().split(" ");
            try {
                graph.addNode(coordinates[0]);
                graph.addNode(coordinates[1]);
                graph.findNode(coordinates[1]).setPrev(graph.findNode(coordinates[0]));
                graph.addEdge(coordinates[0], coordinates[1], 0.0);
                graph.addEdge(coordinates[1], coordinates[0], 0.0);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return graph;
    }

    public void drawLabyrinth(Graph<DijkstraNode, Edge> graph) {
        g.setColor(Color.GRAY);
        g.fillRect(0,0,10,7);
        g.setColor(Color.WHITE);

        for (DijkstraNode curr : graph.getNodes()) {
            for (Edge edge : curr.getEdges()) {
                g.drawPath(curr.getName(), edge.getDest().getName(), false);
            }
        }
    }

    private boolean search(DijkstraNode current, DijkstraNode ziel) {
        return false;// TODO implement 8.4
    }

    // search and draw result
    public void drawRoute(Graph<DijkstraNode, Edge> graph, String startNode, String zielNode) {
        // TODO implement 8.4
    }

    public String execute(String s) {
        Graph<DijkstraNode, Edge> graph;
        graph = createGraph(s);
        drawLabyrinth(graph);
        drawRoute(graph, "0-6", "3-0");
        return g.getTrace();
    }
}
