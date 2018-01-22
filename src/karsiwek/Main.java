package karsiwek;

import java.util.*;

public class Main {
    private static Map<Pair<Integer, List<Integer>>, Set<List<Integer>>> cache = new HashMap();

    public static void main(String[] args) {
        List<Integer> coins = Arrays.asList(1, 2, 5, 10);
        long time1 = System.currentTimeMillis();
        Set<List<Integer>> combinations = getCombinations(200, coins);
        System.out.println(combinations.size());
        System.out.println(System.currentTimeMillis() - time1);
    }

    private static Set<List<Integer>> getCombinations(int amount, List<Integer> coins) {
        coins.sort(Comparator.reverseOrder());
        Set<List<Integer>> result = getCombinationsFor(amount, coins);

        return result;
    }

    private static Set<List<Integer>> getCombinationsFor(int amount, List<Integer> coins) {
        Pair<Integer, List<Integer>> pair = new Pair(amount, coins);
        if (!cache.containsKey(pair)) {
            Set<List<Integer>> results = new HashSet<>();
            for (int i = 0; i < coins.size(); i++) {
                int coin = coins.get(i);
                if (amount == coin) {
                    results.add(Arrays.asList(coin));
                } else if (amount > coin) {
                    Set<List<Integer>> partialResults = getCombinationsFor(amount - coin, coins.subList(i, coins.size()));
                    for (List<Integer> partialResult : partialResults) {
                        LinkedList joinedResult = new LinkedList(partialResult);
                        joinedResult.add(coin);
                        results.add(joinedResult);
                    }
                }
            }
            cache.put(pair, results);
        }
        return cache.get(pair);

    }

    public static class Pair<A, B> {
        private A first;
        private B second;

        public Pair(A first, B second) {
            super();
            this.first = first;
            this.second = second;
        }

        public int hashCode() {
            int hashFirst = first != null ? first.hashCode() : 0;
            int hashSecond = second != null ? second.hashCode() : 0;

            return (hashFirst + hashSecond) * hashSecond + hashFirst;
        }

        public boolean equals(Object other) {
            if (other instanceof Pair) {
                Pair otherPair = (Pair) other;
                return
                        ((this.first == otherPair.first ||
                                (this.first != null && otherPair.first != null &&
                                        this.first.equals(otherPair.first))) &&
                                (this.second == otherPair.second ||
                                        (this.second != null && otherPair.second != null &&
                                                this.second.equals(otherPair.second))));
            }

            return false;
        }

        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }
}
