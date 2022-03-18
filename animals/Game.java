package animals;

import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private Node root;
    private Node current;
    private final Format format;

    public Game(Format format) {
        this.format = format;

        System.out.println(Vocabulary.getGreeting());
        System.out.println();

        root = ImportExport.Import(format);

        if (root == null) {
            System.out.println(Vocabulary.getStr("ask animal"));
            Animal animal = new Animal(scanner.nextLine());

            root = new Node(animal);
       }

        System.out.println(Vocabulary.getStr("Welcome"));
        System.out.println();
    }

    public void menu() {
        System.out.println(Vocabulary.getStr("menu"));
        String input = scanner.nextLine();

        boolean exitFlag = false;

        switch (input) {
            case "1":
                startGame();
                break;
            case "2":
                root.printAnimals();
                break;
            case "3":
                System.out.println(Vocabulary.getStr("Enter the animal:"));
                root.searchForAnimal(new Animal(scanner.nextLine()));
                break;
            case "4":
                root.printStatistics();
                break;
            case "5":
                root.printTree();
                break;
            case "0":
                exitGame();
                exitFlag = true;
                break;
        }
        if (!exitFlag) {
            menu();
        }
    }

    private void startGame() {
        System.out.println(Vocabulary.getStr("game start"));
        scanner.nextLine();
        current = root;
        next();
    }

    private void exitGame() {
        System.out.println(Vocabulary.getFarewell());
        ImportExport.Export(format, root);
    }

    public void next() {
        if (current.isAnimal()) {
            giveAnswer();
        } else {
            askQuestion();
        }
    }

    private void askQuestion() {
        System.out.println(current.getFact().getQuestion());
        current = Vocabulary.isYes(scanner) ? current.getRight() : current.getLeft();
        next();
    }

    private void giveAnswer() {
        System.out.printf("%s %s?%n", Vocabulary.getStr("Is it"), current.getAnimal().getUndefinedName());

        if (Vocabulary.isYes(scanner)) {
            System.out.println(Vocabulary.getStr("win"));
            proposeAnotherGame();
        } else {
            processFailure();
        }
    }

    private void processFailure() {
        System.out.println(Vocabulary.getStr("loss"));
        Animal animal = new Animal(scanner.nextLine());

        Fact fact;
        while (true) {
            System.out.printf(Vocabulary.getStr("specifyDistinction"),
                    current.getAnimal().getUndefinedName(),
                    animal.getUndefinedName());
            try {
                fact = new Fact(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Node factNode = new Node(fact);
        Node node = new Node(animal);
        System.out.printf(Vocabulary.getStr("isStatementCorrect"), animal.getUndefinedName());
        if (Vocabulary.isYes(scanner)) {
            current.replaceAnimalWithFact(factNode, node, current);
        } else {
            current.replaceAnimalWithFact(factNode, current, node);
        }
        if (factNode.getParent() == null) {
            root = factNode;
        }
        printFact(factNode);
        proposeAnotherGame();
    }

    private void printFact(Node node) {
        System.out.println(Vocabulary.getStr("iLearned"));
        System.out.printf(" - %s %s%n",
                node.getRight().getAnimal().getDefinedName(),
                node.getFact().getFact(true));
        System.out.printf(" - %s %s%n",
                node.getLeft().getAnimal().getDefinedName(),
                node.getFact().getFact(false));
        System.out.println(Vocabulary.getStr("iCanDistinguish"));
        System.out.printf(" - %s%n", node.getFact().getQuestion());
        System.out.println(Vocabulary.getStr("todayWeLearned"));
        System.out.println();
    }

    private void proposeAnotherGame() {
        System.out.println(Vocabulary.getStr("playAgainQuestion"));

        if (Vocabulary.isYes(scanner)) {
            startGame();
        }
    }

}
