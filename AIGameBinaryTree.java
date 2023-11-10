package main;

import java.io.*;
import java.util.*;

class Node {
    String data;
    Node yesLink, noLink;

    public Node(String data) {
        this.data = data;
        this.yesLink = null;
        this.noLink = null;
    }
}

public class AIGameBinaryTree {
    private Node root;
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AIGameBinaryTree game = new AIGameBinaryTree();
        game.loadDefaultData("C:\\Users\\Dr. Swati Borse\\Desktop\\AIGameBinaryTree\\src\\main\\game1.txt");
        game.run();
    }

    private void loadDefaultData(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            root = loadTreeFromFile(fileScanner);

            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Node loadTreeFromFile(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            return null; // End of the file
        }

        String question = scanner.nextLine().trim();
        Node node = new Node(question);

        String answer = scanner.nextLine().trim().toLowerCase();
        if (answer.equals("y") || answer.equals("yes")) {
            node.yesLink = loadTreeFromFile(scanner);
        } else {
            node.noLink = loadTreeFromFile(scanner);
        }

        return node;
    }

    private void run() {
        while (true) {
            System.out.println("\nMovie Guessing Game");
            System.out.println("P Play the game");
            System.out.println("D Display the binary tree");
            System.out.println("X Exit the program");
            System.out.print("...your choice: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "p":
                    if (root != null) {
                        playGame(root);
                    } else {
                        System.out.println("Please load a game file first.");
                    }
                    break;
                case "d":
                    displayBinaryTree(root);
                    break;
                case "x":
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    private void playGame(Node current) {
        while (current != null) {
            System.out.println(current.data);
            System.out.print("Your answer (Y/N): ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("y") || answer.equals("yes")) {
                current = current.yesLink;
            } else {
                current = current.noLink;
            }

            if (current == null) {
                System.out.println("Sorry, the game data is incomplete.");
                break;
            }
        }
    }

    private void displayBinaryTree(Node node) {
        if (node != null) {
            System.out.println(node.data);
            displayBinaryTree(node.yesLink);
            displayBinaryTree(node.noLink);
        }
    }
}
