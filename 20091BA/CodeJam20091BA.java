import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.text.DecimalFormat;

public class CodeJam20091BA {
    static DecimalFormat fmt = new DecimalFormat("0.000000");
    public enum TYPE {
        START_TREE, END_TREE, FEATURE, WEIGHT
    }

    static void println(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        for (int i=0; i < N; i++) {
            System.out.println ("Case #" + (i + 1) +":");
            // Tokenize the code
            List<Token> tokens = new ArrayList<Token>();
            int L = Integer.parseInt(sc.nextLine());
            for (int l=0; l < L; l++){
                String line = sc.nextLine();
                //println("L:"+line);
                tokens.addAll(Tokenizer.parseLine(line));
            }
            
            // Parse tokens into a DT
            DecisionTree dt = Parser.parse(tokens);
           
            // Build and evaluate animals 
            int A = Integer.parseInt(sc.nextLine());
            for (int a=0; a <A; a++) {
                String line = sc.nextLine();
                //println("A:"+line);
                Animal animal = new Animal(line);
                dt.evaluate(animal);
                System.out.println(fmt.format(animal.cuteness));
            }
            
        }
    }

    private static class Parser{
        public static DecisionTree parse(List<Token> tokens) {
            Iterator<Token> iter = tokens.iterator();
            DecisionTree currentTree = null;
            while (iter.hasNext()) {
                Token token = iter.next();
                switch(token.type) {
                    case START_TREE:
                        DecisionTree parent = currentTree;
                        token = iter.next();
                        if (token.type != TYPE.WEIGHT) {
                            throw new IllegalArgumentException("Unexpected token " + token.toString());
                        }
                        currentTree = new DecisionTree();
                        currentTree.parent = parent;
                        currentTree.weight = token.weight;
                        if (parent != null) {
                            if (parent.left == null) {
                                parent.left = currentTree;
                            }else{
                                parent.right = currentTree;
                            }
                        }
                        break;
                    case FEATURE:
                        currentTree.feature = token.feature;
                        break;
                    case END_TREE:
                        if (currentTree.parent != null) {
                            currentTree = currentTree.parent;
                        }
                }
            }
            return currentTree;
        }
    }

    private static class Tokenizer{
        public static List<Token> parseLine(String line) {
            String normalizedLine = line.replaceAll("\\(","( ").replaceAll("\\)"," )");
            String[] parts = normalizedLine.split(" ");
            List<Token> tokens = new ArrayList<Token>(parts.length);
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("(")) {
                    tokens.add(new Token(TYPE.START_TREE,null,null));
                } else if (parts[i].equals(")")) {
                    tokens.add(new Token(TYPE.END_TREE, null,null));
                } else if (parts[i].matches("\\d\\.\\d+")) {
                    tokens.add(new Token(TYPE.WEIGHT, null, Double.parseDouble(parts[i])));
                } else if (parts[i].matches("[a-z]{1,10}")){
                    tokens.add(new Token(TYPE.FEATURE, parts[i], null));
                } else if (parts[i].equals(" ") || parts[i].equals("")) {
                    // NOOP
                } else {
                    throw new IllegalArgumentException("Unknown token:"+ parts[i]);
                }
            }
            return tokens;
        }
    }

    private static class Token {
        public final TYPE type;
        public final String feature;
        public final Double weight;

        public Token(TYPE type, String feature, Double weight) {
            this.type = type;
            this.feature = feature;
            this.weight = weight;
        }

        public String toString() {
            return ""+type+","+feature+","+weight;
        }
    }

    private static class Animal {
        public String name;
        public Set<String> features = new HashSet<String>();
        public Double cuteness = 1.0d;

        public Animal(String desc) {
            Scanner sc = new Scanner(desc);
            this.name = sc.next();
            sc.nextInt();
            while(sc.hasNext()){
                this.features.add(sc.next());
            }
        }

        public Boolean hasFeature(String feature) {
            return this.features.contains(feature);
        }
    }

    private static class DecisionTree {
        public DecisionTree parent;
        public DecisionTree left;
        public DecisionTree right;
        public String feature;
        public Double weight;

        public void evaluate(Animal animal) {
            animal.cuteness = animal.cuteness * this.weight;
            if (this.feature == null) {
                return;
            } 
            if (animal.hasFeature(this.feature)){
                this.left.evaluate(animal);
            }else{
                this.right.evaluate(animal);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("(" + weight + " ");
            if (feature != null) {
                sb.append(feature + " ");
                sb.append(left);
                sb.append(right);
            }
            sb.append(")");
            return sb.toString();
        }
    }
}
