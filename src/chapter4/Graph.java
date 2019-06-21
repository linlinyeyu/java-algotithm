package chapter4;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author ybliu
 * @version 1.0
 * @date 2019/6/14 16:01
 */
public class Graph {
    class Vertex {
        boolean isVisit = false;
        String value;
        List<Edge> edges;
        Vertex(String value){
            this.value = value;
        }

        Vertex edges(List<Edge> edges) {
            this.edges = edges;
            return this;
        }

        Vertex edge(Vertex vertex) {
            if (edges == null) {
                edges = new ArrayList<>();
            }
            edges.add(new Edge(vertex));
            return this;
        }

        Vertex edge(Vertex vertex,double weight) {
            if (edges == null) {
                edges = new ArrayList<>();
            }
            edges.add(new Edge(vertex).weight(weight));
            return this;
        }
    }

    class Edge {
        double weight;
        Vertex toVertex;
        Edge(Vertex toVertex) {
            this.toVertex = toVertex;
        }

        Edge weight(double weight) {
            this.weight = weight;
            return this;
        }
    }

    public void display(Vertex vertex) {
        System.out.print(vertex.value);
    }

    /**
     * 深度优先遍历
     * @param vertex
     */
    public void depthTraversal(Vertex vertex) {
        display(vertex);
        vertex.isVisit = true;
        if (!Objects.isNull(vertex.edges)) {
            vertex.edges.forEach(e -> {
                if (!e.toVertex.isVisit) {
                    depthTraversal(e.toVertex);
                }
            });
        }
    }

    /**
     * 广度优先遍历
     * @param vertex
     * @throws InterruptedException
     */
    public void breathTraversal(Vertex vertex) throws InterruptedException {
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        queue.put(vertex);
        vertex.isVisit = true;
        putQueue(queue,vertex.edges);
        queue.forEach(q -> display((Vertex) q));
    }

    public void putQueue(LinkedBlockingQueue queue,List<Edge> edges) {
        if (edges != null) {
            edges.forEach(e -> {
                try {
                    if (!e.toVertex.isVisit) {
                        e.toVertex.isVisit = true;
                        queue.put(e.toVertex);
                    }
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            });
            List<Edge> edgeList = edges.stream().filter(e-> e.toVertex.edges != null).flatMap(e -> e.toVertex.edges.stream()).collect(Collectors.toList());
            if (edgeList != null && !edgeList.isEmpty()) {
                putQueue(queue,edgeList);
            }
        }
    }

    /**
     * 带权有向图最短路径
     * @param start
     * @param end
     */
    public void minWeightPath(Vertex start,Vertex end) {
        Map<String,Double> weights = new HashMap<>();
        Map<String,Object> parents = new HashMap<>();
        weights.put(end.value,Double.POSITIVE_INFINITY);
        weights.put(start.value,0d);
        traversalPath(start,weights,parents);
        System.out.println(weights.get(end.value));
        System.out.print(end.value);
        displayParents(parents,end.value);
    }

    private void displayParents(Map<String,Object> parents,String vertex) {
        if (parents.get(vertex) == null) {
            return;
        }
        System.out.print(parents.get(vertex));
        displayParents(parents,parents.get(vertex).toString());
    }

    /**
     * 如果存在循环环向，可以优化为只记录父节点，当判断节点已经被处理过后，如果找到更短的路径，只需要更新此节点父节点就可以，不需要计算下游路径权值
     * @param vertex
     * @param weights
     * @param parents
     */
    private void traversalPath(Vertex vertex,Map<String,Double> weights,Map<String,Object> parents) {
        if (vertex.edges != null) {
            vertex.edges.forEach(e -> {
                if (weights.get(e.toVertex.value) != null) {
                    //如果新路径比旧路径小，更新父节点和权值
                    if (weights.get(e.toVertex.value) > (weights.get(vertex.value) + e.weight)) {
                        weights.put(e.toVertex.value,weights.get(vertex.value)+e.weight);
                        parents.put(e.toVertex.value,vertex.value);
                    } else {
                        return;
                    }
                    //如果没有存入过节点，将新节点权值存入表
                } else {
                    weights.put(e.toVertex.value,weights.get(vertex.value) + e.weight);
                    parents.put(e.toVertex.value,vertex.value);
                }
                traversalPath(e.toVertex,weights,parents);
                e.toVertex.isVisit = true;
            });
        }
    }

    /**
     * 贝尔曼-福特算法，计算负权边
     * @param start
     */
    public void negativeLoopMinPath(Vertex start) {
        Map<String,Object> parents = new HashMap<>();
        Map<String,Double> weights = new HashMap<>();
        parents.put(start.value,start.value);
        weights.put(start.value,0d);
        int k = 0;
        for (int i = 0;i<=15;i++) {
            boolean isChange;
            k++;
            isChange = negativeSearchPath(start,parents,weights,false);
            if (!isChange) {
                break;
            }
        }
        System.out.print(k);
    }

    private boolean negativeSearchPath(Vertex vertex,Map<String,Object> parents,Map<String,Double> weights,boolean isChange) {
        boolean tempChange = false;
        if (vertex.edges != null) {
            for (Edge e : vertex.edges) {
                if (weights.get(e.toVertex.value) == null) {
                    weights.put(e.toVertex.value, weights.get(vertex.value)+e.weight);
                    parents.put(e.toVertex.value, vertex.value);
                    tempChange = true;
                } else {
                    if (weights.get(e.toVertex.value) > (weights.get(vertex.value) + e.weight)) {
                        weights.put(e.toVertex.value, weights.get(vertex.value) + e.weight);
                        parents.put(e.toVertex.value, vertex.value);
                        tempChange = true;
                    }
                }
                tempChange = negativeSearchPath(e.toVertex,parents,weights,tempChange);
            }
        }
        if (isChange) {
            return true;
        } else {
            return tempChange;
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Graph graph = new Graph();
        Vertex top = graph.new Vertex("A");
        Vertex B = graph.new Vertex("B");
        Vertex C = graph.new Vertex("C");
        Vertex D = graph.new Vertex("D");
        Vertex E = graph.new Vertex("E");
        Vertex F = graph.new Vertex("F");
        Vertex G = graph.new Vertex("G");
        Vertex H = graph.new Vertex("H");
        Vertex I = graph.new Vertex("I");
        Vertex J = graph.new Vertex("J");
        Vertex K = graph.new Vertex("K");
        Vertex L = graph.new Vertex("L");
        Vertex M = graph.new Vertex("M");
        Vertex N = graph.new Vertex("N");
        Vertex O = graph.new Vertex("O");
        Vertex P = graph.new Vertex("P");
        top.edge(B,3).edge(C,2);
        B.edge(D,1).edge(E,4).edge(F,1).edge(C,-3);
        C.edge(B,2).edge(J,1).edge(K,4);
        D.edge(G,4).edge(H,2);
        E.edge(H,1).edge(I,5);
        F.edge(I,4).edge(J,3).edge(L,1);
        K.edge(L,2).edge(M,1).edge(N,3);
        L.edge(O,5).edge(P,2);
        M.edge(P,-1);
        N.edge(P,2);
        J.edge(K,1);
        graph.negativeLoopMinPath(top);
    }
}
