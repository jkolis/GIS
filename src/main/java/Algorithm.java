import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.Iterator;
import java.util.LinkedList;

public class Algorithm {

    public long startTime;
    public long endTime;
    public int path;
    public int operations;

    public void BFS(Graph graph, int first, int second) {

        int n = graph.getNodeCount();

        int kroki = 0;

        int[] previous = new int[graph.getNodeCount()];

//        first = 0;
//        second = 6;

        int beginning = first;
        int end = second;

        System.out.println("First node: " + first + " second node: " + second);

        boolean visited[] = new boolean[graph.getNodeCount()];

        LinkedList<Integer> queue = new LinkedList<>();

        visited[first] = true;
        queue.add(first);

        operations = 0;

        startTime = System.nanoTime();

        while (queue.size() != 0) {
            first = queue.poll();
            operations++;
//            System.out.println("first " + first);
//            if (first == second) {
//                System.out.println("Connection found");
//                return;
//            }
//            kroki++;

            Iterator<Node> neigbours = graph.getNode(first).getNeighborNodeIterator();
            while (neigbours.hasNext()) {
                Node neigbourNode = neigbours.next();
                int neigbourIdx = neigbourNode.getIndex();
                operations++;
//                System.out.println("neighbour idx " + neigbourIdx);
                if (neigbourIdx == second) {
                    endTime = System.nanoTime();
//                    System.out.print(second + " " + first + " ");
                    kroki++;
                    while (previous[first] != beginning) {
//                        System.out.print(previous[first] + " ");
                        first = previous[first];
                        kroki++;
                    }
//                    System.out.println(beginning);
                    kroki++;
                    path = kroki;
                    System.out.println("Path length: " + path);
                    System.out.println("BFS operations: " + operations + " time: " + getTime());
//                    System.out.println("BFS: Connection found in " + kroki + " steps");
                    return;
                }
                if (!visited[neigbourIdx]) {
                    operations++;
                    visited[neigbourIdx] = true;
                    previous[neigbourIdx] = first;
                    queue.add(neigbourIdx);
                }
            }
        }


    }

    public void bidirectionalBFS(Graph graph, int beginning, int end) {
        int n = graph.getNodeCount();

        int kroki = 0;

        int[] previous = new int[graph.getNodeCount()];

        int first = 1;
        int second = 2;

//        beginning = 0;
//        end = 6;

//        System.out.println(beginning + " " + end);

        int visited[] = new int[n];

        LinkedList<Integer> firstQueue = new LinkedList<>();
        LinkedList<Integer> secondQueue = new LinkedList<>();
        LinkedList<Integer> activeQueue;

        int degrees[] = new int[n];

        firstQueue.add(beginning);
        secondQueue.add(end);

//        visited[first] = true;
//        queue.add(first);

        int firstEdges = graph.getNode(beginning).getDegree();
        int secondEdges = graph.getNode(end).getDegree();

        int node, active;

        if (firstEdges < secondEdges) {
            node = beginning;
            active = first;
            visited[node] = first;
            activeQueue = firstQueue;
        } else {
            node = end;
            active = second;
            visited[node] = second;
            activeQueue = secondQueue;
        }

        operations = 0;

        startTime = System.nanoTime();


        while (activeQueue.size() != 0) {
            operations++;

            if (active == first) {
                firstQueue = activeQueue;
            } else {
                secondQueue = activeQueue;
            }

            if (firstEdges < secondEdges) {

                node = firstQueue.poll();
                active = first;
                activeQueue = firstQueue;
                firstEdges = 0;
            } else {
                node = secondQueue.poll();
                active = second;
                activeQueue = secondQueue;
                secondEdges = 0;
            }
//            System.out.println("first " + node);
//            if (first == second) {
//                System.out.println("Connection found");
//                return;
//            }
//            kroki++;

            Iterator<Node> neigbours = graph.getNode(node).getNeighborNodeIterator();
            while (neigbours.hasNext()) {
                operations++;
                Node neigbourNode = neigbours.next();
                int neigbourIdx = neigbourNode.getIndex();
//                System.out.println("neighbour idx " + neigbourIdx);
//                if (neigbourIdx == second) {
//                    System.out.print(second + " " + node + " ");
//                    kroki++;
//                    while(previous[first] != beginning) {
//                        System.out.print(previous[node] + " ");
//                        first = previous[node];
//                        kroki++;
//                    }
//                    System.out.println(beginning);
//                    kroki++;
//                    System.out.println("Connection found in " + kroki + " steps");
//                    endTime = System.nanoTime();
//                    return;
//                }
                //przeszlo tylko jedną stroną
                if ((neigbourIdx == beginning && visited[neigbourIdx] == 0)
                        || (neigbourIdx == end && visited[neigbourIdx] == 0)) {
                    endTime = System.nanoTime();
//                    System.out.println("BIDIRECTIONAL BFS: Connection found");
                    System.out.println("bidirectional BFS operations: " + operations + " time: " + getTime());

                    System.out.println();
//                    kroki++;
//                    do {
////
//                        node = previous[node];
//                        kroki++;
//                    }
//                    while (previous[node] != beginning && previous[node] != end);
//                    System.out.println(kroki);
                    return;
                } else if (visited[neigbourIdx] != 0 && visited[neigbourIdx] != active) {
                    endTime = System.nanoTime();
                    System.out.println("bidirectional BFS operations: " + operations + " time: " + getTime());

                    System.out.println();
//                    if (node != beginning && node != end) {
//                        while (previous[node] != beginning && previous[node] != end) {
//                            node = previous[node];
//                            kroki++;
//                        }
//                    }
//                    node = neigbourIdx;
//                    do {
//
//                        node = previous[node];
//                        kroki++;
//                    }
//                    while (previous[node] != beginning && previous[node] != end);
////
//////                    kroki += 2;
//                    System.out.println(kroki);
//
                    return;
                } else if (visited[neigbourIdx] == 0) {
                    operations++;
                    visited[neigbourIdx] = active;
                    previous[neigbourIdx] = node;
                    degrees[neigbourIdx]--;
                    if (active == 1) {
                        firstEdges += (graph.getNode(neigbourIdx).getDegree() + degrees[neigbourIdx]);
                    } else {
                        secondEdges += (graph.getNode(neigbourIdx).getDegree() + degrees[neigbourIdx]);
                    }
                    activeQueue.add(neigbourIdx);
                } else {
                    degrees[neigbourIdx]--;

                }
            }
        }
    }

    public long getTime() {
        return endTime - startTime;
    }
}
