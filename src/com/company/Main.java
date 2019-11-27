package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //int result;

    public static void main(String[] args) throws FileNotFoundException {
        long a = System.currentTimeMillis();
        //Main main = new Main();

        System.setIn(new FileInputStream("src/com/company/input"));
        Scanner input = new Scanner(System.in);

        int SZEROK = input.nextInt();
        int WYSOK = input.nextInt();

        int[][] XY_MAP = new int[WYSOK][SZEROK];

        int aboveGasStation = 1;
        int NoOfGasStations =0;
        for (int k = 0; k < WYSOK; k++) {
            for (int l = 0; l < SZEROK; l++) {
                XY_MAP[k][l] = input.nextInt();
                if (XY_MAP[k][l] > aboveGasStation )
                    if (XY_MAP[k][l] > NoOfGasStations) NoOfGasStations = XY_MAP[k][l];
            }
        }
        NoOfGasStations-=1;

        int NO_OF_CASES = input.nextInt();
        Case[] TABLE_OF_CASES = new Case[NO_OF_CASES];

        for (int n = 0; n < NO_OF_CASES; n++) {
            Case givenCase = new Case();
            givenCase.setXofStart(input.nextInt());
            givenCase.setYofStart(input.nextInt());
            givenCase.setXofEnd(input.nextInt());
            givenCase.setYofEnd(input.nextInt());
            givenCase.setFuel(input.nextInt());

            int[] gasOnStations = new int[NoOfGasStations];
            for (int k = 0; k < NoOfGasStations; k++) {
                gasOnStations[k] = input.nextInt();
            }
            givenCase.setFuelOnStations(gasOnStations);

            TABLE_OF_CASES[n] = givenCase;
        }

        System.out.println(Arrays.toString(TABLE_OF_CASES));



/*        int init_step = 0;
        int index_of_plane = 2;
        int current_result = 0;
        boolean detonated = false;*/


        //Graph graph = new Graph(X);
        //graph.addEdge(1,2);

        Graph graph = createGraph(XY_MAP,WYSOK,SZEROK);

        System.out.println(graph);

        System.out.println("shortest using bfs: "+ BFSStandard(graph,0,23));

        long b = System.currentTimeMillis();
        System.out.println("runtime was : " + (b - a) + " ms");

    }

    private static Graph createGraph(int[][] map, int wysok, int szer){

        System.out.println(Arrays.deepToString(map));
        Graph graph = new Graph(szer*wysok);

        int[][] nodesNumerated = new int [wysok][szer];
        int no =0;
        for (int k = 0; k < wysok; k++) {
            for (int l = 0; l < szer; l++) {
                nodesNumerated[k][l] = no;
                no++;
            }}
        System.out.println(Arrays.deepToString(nodesNumerated));

        for (int k = 0; k < wysok; k++) {
            for (int l = 0; l < szer; l++) {

                if (k >= 1 && l >= 1 && k < wysok - 1 && l < szer - 1) {
                    if (map[k][l] > 0 && map[k][l - 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l - 1]);
                    }
                    if (map[k][l] > 0 && map[k][l + 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l + 1]);
                    }
                    if (map[k][l] > 0 && map[k - 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k - 1][l]);
                    }
                    if (map[k][l] > 0 && map[k + 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k + 1][l]);
                    }
                }
                if (k == 0 && l >= 1 && l < szer - 1) {
                    if (map[k][l] > 0 && map[k][l - 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l - 1]);
                    }
                    if (map[k][l] > 0 && map[k][l + 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l + 1]);
                    }
                    if (map[k][l] > 0 && map[k + 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k + 1][l]);
                    }
                }

                if (k == wysok - 1 && l >= 1 && l < szer - 1) {
                    if (map[k][l] > 0 && map[k][l - 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l - 1]);
                    }
                    if (map[k][l] > 0 && map[k][l + 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l + 1]);
                    }
                    if (map[k][l] > 0 && map[k - 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k - 1][l]);
                    }
                }
                if (l == 0 && k >= 1 && k < wysok - 1) {
                    if (map[k][l] > 0 && map[k][l + 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l + 1]);
                    }
                    if (map[k][l] > 0 && map[k - 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k - 1][l]);
                    }
                    if (map[k][l] > 0 && map[k + 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k + 1][l]);
                    }
                }
                if (l == szer - 1 && k >= 1 && k < wysok - 1) {
                    if (map[k][l] > 0 && map[k][l - 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l - 1]);
                    }
                    if (map[k][l] > 0 && map[k - 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k - 1][l]);
                    }
                    if (map[k][l] > 0 && map[k + 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k + 1][l]);
                    }
                }

                if (l == 0 && k == 0) {
                    if (map[k][l] > 0 && map[k][l + 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l + 1]);
                    }
                    if (map[k][l] > 0 && map[k + 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k + 1][l]);
                    }
                }

                if (l == 0 && k == wysok - 1) {
                    if (map[k][l] > 0 && map[k][l + 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l + 1]);
                    }
                    if (map[k][l] > 0 && map[k - 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k - 1][l]);
                    }
                }

                if (l == szer - 1 && k == wysok - 1) {
                    if (map[k][l] > 0 && map[k][l - 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l - 1]);
                    }
                    if (map[k][l] > 0 && map[k - 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k - 1][l]);
                    }
                }
                if (l == szer - 1 && k == 0) {
                    if (map[k][l] > 0 && map[k][l - 1] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k][l - 1]);
                    }
                    if (map[k][l] > 0 && map[k + 1][l] > 0) {
                        graph.addEdge(nodesNumerated[k][l], nodesNumerated[k + 1][l]);
                    }
                }
            }

        }
        return graph;


    }
    static int BFSStandard(Graph graph, int start, int end) {

        System.out.println("graph: " + graph);

        boolean[] visitedNodesTable = new boolean[graph.NoOfNodes];
        int[] distanceOnNode = new int[graph.NoOfNodes];

        ListFiFO listOfNodesToExamine = new ListFiFO();

        listOfNodesToExamine.add_back(start);
        visitedNodesTable[start] = true;

        distanceOnNode[start] = 0;


        while (listOfNodesToExamine.length() > 0) {
            int nodeToCheck = (int)listOfNodesToExamine.pop_first_el();
            System.out.println("pulled to check: " + nodeToCheck);
            visitedNodesTable[nodeToCheck] = true;

            for (int i = 0; i < graph.edges[nodeToCheck].length(); i++) {
                int nodeAdjacent = (int)graph.edges[nodeToCheck].get_by_index(i);
                if (visitedNodesTable[nodeAdjacent] == false) {
                    System.out.println("in next level neighbours of node will be visited: " + nodeAdjacent);
                    listOfNodesToExamine.add_back(nodeAdjacent);
                    distanceOnNode[nodeAdjacent] = distanceOnNode[nodeToCheck] + 1;
                    System.out.println("for node : "+ nodeAdjacent+ "min distance was set equal to: " + distanceOnNode[nodeAdjacent]+" and it was assumed it is impossible to get this node faster");

                    if (nodeAdjacent == end) return distanceOnNode[nodeAdjacent];
                }
            }
        }
        return -100000000;
    }

    private void BFS(Main main, int[][] allSteps_events, int init_step, int index_of_plane, int current_result, boolean detonated) {

    }


    }

    class Case {
        int XofStart;
        int YofStart;
        int XofEnd;
        int YofEnd;
        int Fuel;
        int[] fuelOnStations;

        public Case(){}

        public int getXofStart() {
            return XofStart;
        }

        public void setXofStart(int xofStart) {
            XofStart = xofStart;
        }

        public int getYofStart() {
            return YofStart;
        }

        public void setYofStart(int yofStart) {
            YofStart = yofStart;
        }

        public int getXofEnd() {
            return XofEnd;
        }

        public void setXofEnd(int xofEnd) {
            XofEnd = xofEnd;
        }

        public int getYofEnd() {
            return YofEnd;
        }

        public void setYofEnd(int yofEnd) {
            YofEnd = yofEnd;
        }

        public int getFuel() {
            return Fuel;
        }

        public void setFuel(int fuel) {
            Fuel = fuel;
        }

        public int[] getFuelOnStations() {
            return fuelOnStations;
        }

        public void setFuelOnStations(int[] fuelOnStations) {
            this.fuelOnStations = fuelOnStations;
        }

        @Override
        public String toString() {
            return "Case{" +
                    "XofStart=" + XofStart +
                    ", YofStart=" + YofStart +
                    ", XofEnd=" + XofEnd +
                    ", YofEnd=" + YofEnd +
                    ", Fuel=" + Fuel +
                    ", fuelOnStations=" + Arrays.toString(fuelOnStations) +
                    '}';
        }
    }


    class Graph{
        int NoOfNodes;
        ListFiFO [] edges;

        public Graph(int NoOfNodes){
            this.NoOfNodes = NoOfNodes;
            this.edges = new ListFiFO[NoOfNodes];
            for (int i=0; i < NoOfNodes; i++){
                edges[i] = new ListFiFO();
            }
        }

        void addEdge(int from, int to){
            edges[from].add_back(to);
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "NoOfNodes=" + NoOfNodes +
                    ", edges=" + Arrays.toString(edges) +
                    '}';
        }
    }

    class ListFiFO{
        int SIZE = 1;
        Object [] list = new Object[SIZE];
        int index_of_first_el = 0;
        int current_index_for_new = 0;

        void add_back(Object x){
            if (current_index_for_new<SIZE){
                list[current_index_for_new]=x;
                current_index_for_new++;
            }
            else {
                make_bigger();
                list[current_index_for_new]=x;
                current_index_for_new++;
            }
        }

        Object pop_first_el (){
            index_of_first_el++;
            return list[index_of_first_el-1];
        }


        Object get_by_index (int index){
            return list[index];
        }

        void make_bigger(){
            SIZE *=2;
            Object [] newTable = new Object [SIZE];
            for (int i=0; i < list.length; i++){
                newTable[i] = list[i];
            }
            list = newTable;
        }

        int length() {
            return current_index_for_new - index_of_first_el;
        }

        @Override
        public String toString() {
            return "ListFiFO{" +
                    "SIZE=" + SIZE +
                    ", list=" + Arrays.toString(list) +
                    ", index_of_first_el=" + index_of_first_el +
                    ", current_index_for_new=" + current_index_for_new +
                    '}' + "\n";
        }
    }

