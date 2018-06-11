import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {

    private static int N = 10;

    public static void main(String[] args) throws FileNotFoundException {
//
        N = Integer.valueOf(args[6]);

        Graph graph = new SingleGraph("Barabasi-Albert");
        Generator gen = new BarabasiAlbertGenerator(Integer.valueOf(args[4]));

        gen.addSink(graph);
        System.out.println("Starting to generate graph");
        gen.begin();
        int n = Integer.valueOf(args[5]);
        for (int i = 0; i < n; i++) {
            gen.nextEvents();
//            System.out.println(i);
        }
        gen.end();
        System.out.println("Graph generated");
        System.out.println();
//        graph.display();

//        int n =  7;
//
//        Graph graph = new SingleGraph("test");
//        for (int i = 0; i < n; i++) {
//            graph.addNode(String.valueOf(i));
//        }
//
//        graph.addEdge("01", 0, 1);
//        graph.addEdge("03", 0, 3);
//        graph.addEdge("31", 3, 1);
////        graph.addEdge("14", 1, 4);
//        graph.addEdge("12", 1 ,2);
//        graph.addEdge("25", 2, 5);
//        graph.addEdge("56", 5, 6);

//        graph.display();


//        for (int i = 0; i < graph.getNodeCount(); i++) {
//            System.out.println(i + " " + graph.getNode(i).getDegree());
//        }

        PrintWriter writer1 = new PrintWriter(args[0]);
        PrintWriter writer2 = new PrintWriter(args[1]);
        PrintWriter writer3 = new PrintWriter(args[2]);
        PrintWriter writer4 = new PrintWriter(args[3]);

        Algorithm algorithm = new Algorithm();
        for (int i = 0; i < N; i++) {
            System.out.println("petla nr " + (i+1));
            Random random = new Random();
            int first = random.nextInt(n);
            int second = random.nextInt(n);
            long time1, time2;
//        first = 0;
//        second = 6;
            algorithm.BFS(graph, first, second);
            time1 = algorithm.getTime();
//        System.out.println(algorithm.getTime());
//        System.out.println(TimeUnit.MILLISECONDS.convert(algorithm.getTime(), TimeUnit.NANOSECONDS));
            writer3.println(algorithm.operations);
            algorithm.bidirectionalBFS(graph, first, second);
            time2 = algorithm.getTime();
            writer4.println(algorithm.operations);
//        System.out.println(algorithm.getTime());
//        System.out.println(TimeUnit.MILLISECONDS.convert(algorithm.getTime(), TimeUnit.NANOSECONDS));
            writer1.println(time1);
            writer2.println(time2);
        }
        writer1.close();
        writer2.close();
        writer3.close();
        writer4.close();
    }


}
