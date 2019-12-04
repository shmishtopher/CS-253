package debruijn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class DeBruijnGraph<T extends Splittable<T>> {
  private Map<Node, List<Node>> graph;
  private Map<T, Node> nodes;
  private int length;


  public DeBruijnGraph(int length) {
    this.graph = new HashMap<Node, List<Node>>();
    this.nodes = new HashMap<T, Node>();
    this.length = length;
  }


  public List<String> computeEulerianPath() {
    Node head = null;
    Node last = null;

    int semiBalancedCount = 0;
    int neitherCount = 0;

    for (Node node : nodes.values()) {
      if (node.isSemiBalanced()) {
        if (node.inbound == node.outbound + 1) last = node;
        if (node.inbound == node.outbound - 1) head = node;
        semiBalancedCount += 1;
      }
      else if (!node.isBalanced()) {
        neitherCount += 1;
      }
    }
    
    if (neitherCount == 0 && (semiBalancedCount == 0 || semiBalancedCount == 2)) {
      if (semiBalancedCount == 2) {
        graph.putIfAbsent(last, new ArrayList<Node>());
        graph.get(last).add(head);
      }

      Node start = graph.keySet().iterator().next();
      List<Node> walk = traverse(start, new ArrayList<Node>());

      Collections.reverse(walk);
      walk = walk.subList(0, walk.size() - 1);

      if (semiBalancedCount == 2) {
        int headIndex = walk.indexOf(head);
        List<Node> tailList = walk.subList(headIndex, walk.size());
        List<Node> headList = walk.subList(0, headIndex);
        
        tailList.addAll(headList);
        walk = tailList;
      }

      return walk.stream()
        .map(x -> x.toString())
        .collect(Collectors.toList());
    }
    else {
      throw new Error("This graph is not Eulerian");
    }
  }


  private List<Node> traverse(Node start, List<Node> list) {
    List<Node> edges = graph.get(start);

    while (edges.size() > 0) {
      Node next = edges.remove(0);
      traverse(next, list);
    }

    list.add(start);
    return list;
  }


  public void insertSequence(T sequence) {
    if (sequence.size() == length) {
      T prefix = sequence.getPrefix();
      T suffix = sequence.getSuffix();

      Node nodeLeft = null;
      Node nodeRight = null;

      if (nodes.containsKey(prefix)) { 
        nodeLeft = nodes.get(prefix);
      }
      else {
        nodes.put(prefix, new Node(prefix));
        nodeLeft = nodes.get(prefix);
      }

      if (nodes.containsKey(suffix)) {
        nodeRight = nodes.get(suffix);
      }
      else {
        nodes.put(suffix, new Node(suffix));
        nodeRight = nodes.get(suffix);
      }

      nodeLeft.outbound += 1;
      nodeRight.inbound += 1;

      graph.putIfAbsent(nodeLeft, new ArrayList<Node>());
      graph.get(nodeLeft).add(nodeRight);
    }
    else {
      throw new Error("Size mismatch error");
    }
  }


  private class Node {
    private T internal;
    public int inbound;
    public int outbound;

    public Node(T internal) {
      this.internal = internal;
      this.inbound = 0;
      this.outbound = 0;
    }

    public boolean isSemiBalanced() {
      return Math.abs(inbound - outbound) == 1;
    }

    public boolean isBalanced() {
      return inbound == outbound;
    }

    public int hashCode() {
      return internal.hashCode();
    }

    public String toString() {
      return internal.toString();
    }
  }
}