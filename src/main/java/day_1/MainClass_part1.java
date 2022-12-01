package day_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MainClass_part1 {
    public static void main(String[] args) throws IOException {
        List<String> allLines = Files.readAllLines(Paths.get("src/main/java/day_1/input.txt"));

        HashMap<String, List<Integer>> bags = new HashMap<>();

        int currentElfNumber = 1;
        for (String line : allLines) {
            if (line.length() > 0) {
                if(bags.get("elf_" + currentElfNumber)!=null) {
                    List<Integer> currentElfBag = bags.get("elf_" + currentElfNumber);
                    currentElfBag.add(Integer.parseInt(line));
                    bags.put("elf_" + currentElfNumber, currentElfBag);
                } else {
                    bags.put("elf_" + currentElfNumber, new ArrayList<Integer>() { {add(Integer.parseInt(line));} });
                }
            } else {
                currentElfNumber++;
            }
        }
        int max = bags.get("elf_1").stream()
                .mapToInt(Integer::intValue)
                .sum();
        for (Map.Entry<String, List<Integer>> elf : bags.entrySet()) {
            List<Integer> elfBag = elf.getValue();
            int currentElfBagCalories = elfBag.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            if (currentElfBagCalories > max)
                max = currentElfBagCalories;
        }
        System.out.println(max);
    }
}