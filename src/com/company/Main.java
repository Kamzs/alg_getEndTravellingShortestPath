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

        int spotDlaObjectuZTabelaXY=1;

        int szerok = input.nextInt();
        int wysok = input.nextInt();

        int[][] XY_table = new int[wysok][szerok];
        int aboveGasStation = 1;
        int NoOfGasStations =0;
        for (int k = 0; k < wysok; k++) {
            for (int l = 0; l < szerok; l++) {
                XY_table[k][l] = input.nextInt();
                if (XY_table[k][l] > aboveGasStation )
                    if (XY_table[k][l] > NoOfGasStations) NoOfGasStations = XY_table[k][l];
            }
        }
        NoOfGasStations-=1;

        int no_of_cases = input.nextInt();
        Case[] tabeleOfCases = new Case[no_of_cases];

        for (int n = 0; n < no_of_cases; n++) {
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

            tabeleOfCases[n] = givenCase;
        }

        System.out.println(Arrays.toString(tabeleOfCases));



/*        int init_step = 0;
        int index_of_plane = 2;
        int current_result = 0;
        boolean detonated = false;*/


        //Graph graph = new Graph(X);
        //graph.addEdge(1,2);


        long b = System.currentTimeMillis();
        System.out.println("runtime was : " + (b - a) + " ms");

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
        }

        void addEdge(int from, int to){
            edges[from].add_back(to);
            edges[to].add_back(from);
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

        @Override
        public String toString() {
            return "ListFiFO{" +
                    "SIZE=" + SIZE +
                    ", list=" + Arrays.toString(list) +
                    ", index_of_first_el=" + index_of_first_el +
                    ", current_index_for_new=" + current_index_for_new +
                    '}';
        }
    }

