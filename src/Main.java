import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = readNumbersFromFile("numbers.txt");
        ArvoreAVL.AVLTree avlTree = new ArvoreAVL.AVLTree();
        ArvoreRubroNegra rubroNegraTree = new ArvoreRubroNegra();

        long avlInsertStartTime = System.currentTimeMillis();
        for (int num : numbers) {
            avlTree.insert(num);
        }
        long avlInsertEndTime = System.currentTimeMillis();

        long rubroNegraInsertStartTime = System.currentTimeMillis();
        for (int num : numbers) {
            rubroNegraTree.inserir(num);
        }
        long rubroNegraInsertEndTime = System.currentTimeMillis();

        Random random = new Random();
        StringBuilder output = new StringBuilder();

        long avlOperationsStartTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            int randomNumber = random.nextInt(19999) - 9999; 

            if (randomNumber % 3 == 0) {
                avlTree.insert(randomNumber);
            } else if (randomNumber % 5 == 0) {
                avlTree.remove(randomNumber);
            } else {
                avlTree.contar(randomNumber);
            }
        }
        long avlOperationsEndTime = System.currentTimeMillis();

        long rubroNegraOperationsStartTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            int randomNumber = random.nextInt(19999) - 9999; 

            if (randomNumber % 3 == 0) {
                rubroNegraTree.inserir(randomNumber);
            } else if (randomNumber % 5 == 0) {
                rubroNegraTree.remover(randomNumber);
            } else {
                rubroNegraTree.contar(randomNumber);
            }
        }
        long rubroNegraOperationsEndTime = System.currentTimeMillis();

        output.append("\nTempo de inserção na árvore AVL: ").append(avlInsertEndTime - avlInsertStartTime).append(" ms\n");
        output.append("Tempo de inserção na árvore Rubro Negra: ").append(rubroNegraInsertEndTime - rubroNegraInsertStartTime).append(" ms\n");
        output.append("Tempo total de operações na árvore AVL: ").append(avlOperationsEndTime - avlOperationsStartTime).append(" ms\n");
        output.append("Tempo total de operações na árvore Rubro Negra: ").append(rubroNegraOperationsEndTime - rubroNegraOperationsStartTime).append(" ms\n");

        writeOutputToFile("output.txt", output.toString());
    }

    private static void writeOutputToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> readNumbersFromFile(String fileName) {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Arrays.stream(line.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .forEach(numbers::add);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return numbers;
    }
}
