package animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Node {
    private Animal animal;
    private Fact fact;
    private Node left;
    private Node right;
    @JsonIgnore
    private Node parent;

    public Node() {

    }

    public Node(Animal animal) {
        this.animal = animal;
    }

    public Node(Fact fact) {
        this.fact = fact;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Fact getFact() {
        return fact;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeft(Node left) {
        this.left = left;
        left.setParent(this);
    }

    public void setRight(Node right) {
        this.right = right;
        right.setParent(this);
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    @Override
    public String toString() {
        return isAnimal() ? getAnimal().getUndefinedName() : getFact().getQuestion();
    }

    public boolean isAnimal() {
        return animal != null;
    }

    public void replaceAnimalWithFact(Node factNode, Node right, Node wrong) {
        Node parent = this.getParent();
        if (parent != null) {
            if (parent.getLeft() == this) {
                parent.setLeft(factNode);
            } else if (parent.getRight() == this) {
                parent.setRight(factNode);
            }
        }

        factNode.setParent(parent);
        factNode.setLeft(wrong);
        factNode.setRight(right);
    }

    public void printAnimals() {
        List<String> animals = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.offer(this);

        while (stack.size() > 0) {
            Node node = stack.poll();
            if (node.isAnimal()) {
                animals.add(node.getAnimal().getName());
            }
            if (node.getLeft() != null) {
                stack.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                stack.offer(node.getRight());
            }
        }

        animals.sort(String::compareTo);
        System.out.println(Vocabulary.res.getString("Here are the animals I know:"));
        animals.forEach(name -> System.out.println(" - " + name));
    }

    public void searchForAnimal(Animal animal) {
        Node node = findByAnimalName(this, animal.getName());

        if (node == null) {
            System.out.printf(Vocabulary.getStr("No facts about %s.%n"), animal.getDefinedName());
            return;
        }

        Deque<String> facts = new ArrayDeque<>();

        Node parent = node.getParent();
        Node child = node;
        while (parent != null) {
            if (!parent.isAnimal()) {
                boolean isTrue = parent.getRight() == child;
                facts.offerFirst(" - " + Vocabulary.getStr("It") + parent.getFact().getFact(isTrue));
            }
            child = parent;
            parent = parent.getParent();
        }
        System.out.printf(Vocabulary.getStr("Facts about %s:%n"), animal.getDefinedName());
        facts.forEach(System.out::println);
    }

    public void printStatistics() {
        String rootString = isAnimal() ? getAnimal().getDefinedName() : Vocabulary.getStr("It")
                + " " + getFact().getFact(true);
        NodeCounts nodeCounts = treeStats();

        System.out.println(Vocabulary.getStr("The Knowledge Tree stats"));
        System.out.println();
        System.out.printf("- %-28s %s%n", Vocabulary.getStr("root node"), rootString);
        System.out.printf("- %-28s %d%n", Vocabulary.getStr("total number of nodes"), nodeCounts.count);
        System.out.printf("- %-28s %d%n", Vocabulary.getStr("total number of animals"), nodeCounts.animalsCount);
        System.out.printf("- %-28s %d%n", Vocabulary.getStr("total number of statements"), nodeCounts.count - nodeCounts.animalsCount);
        System.out.printf("- %-28s %d%n", Vocabulary.getStr("height of the tree"), nodeCounts.depth);
        System.out.printf("- %-28s %d%n", Vocabulary.getStr("minimum animal's depth"), nodeCounts.minAnimalDepth);
        System.out.printf("- %-28s %.0f%n", Vocabulary.getStr("average animal's depth"), nodeCounts.avgAnimalDepth);
    }

    private Node findByAnimalName(Node node, String name)  {
        if (node == null) {
            return null;
        }
        if (node.isAnimal() && name.equalsIgnoreCase(node.getAnimal().getName())) {
            return node;
        }
        Node result = findByAnimalName(node.getLeft(), name);
        if (result == null) {
            result = findByAnimalName(node.getRight(), name);
        }
        return result;
    }

    private NodeCounts treeStats() {
        NodeCounts counts = new NodeCounts();
        Deque<Node> stack = new ArrayDeque<>();
        stack.offer(this);

        int animalDepths = 0;

        while (stack.size() > 0) {
            Node node = stack.poll();
            counts.count++;
            int depth = nodeDepth(node);
            if (depth > counts.depth) {
                counts.depth = depth;
            }
            if (node.isAnimal()) {
                counts.animalsCount++;
                animalDepths += depth;
                if (depth < counts.minAnimalDepth) {
                    counts.minAnimalDepth = depth;
                }
            }
            if (node.getLeft() != null) {
                stack.offer(node.getLeft());
            }
            if (node.getRight() != null) {
                stack.offer(node.getRight());
            }
        }

        if (counts.animalsCount > 0) {
            counts.avgAnimalDepth = (double) animalDepths / counts.animalsCount;
        }

        return counts;
    }

    private int nodeDepth(Node node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.getParent();
        }
        return depth - 1;
    }

    public void printTree() {
        System.out.println("└ " + this);
        printNodes(this.getRight(), " ");
        printNodes(this.getLeft(), " ");
    }

    private void printNodes(Node node, String prefix) {
        if (node == null) {
            return;
        }

        boolean itsLeft = node.getParent().getLeft() == node;
        String line = prefix + (itsLeft ? "└" : "├") + " " + node;
        System.out.println(line);
        prefix = prefix + (itsLeft ? " " : "|");

        printNodes(node.getRight(), prefix);
        printNodes(node.getLeft(), prefix);
    }

}

class NodeCounts {
    int count = 0;
    int animalsCount = 0;
    int depth = 0;
    int minAnimalDepth = Integer.MAX_VALUE;
    double avgAnimalDepth = 0;
}