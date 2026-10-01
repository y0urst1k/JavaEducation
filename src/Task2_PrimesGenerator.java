import java.util.*;

class Task2_PrimesGenerator implements Iterable<Integer> {
    private final List<Integer> primes = new ArrayList<>();

    public Task2_PrimesGenerator(int n) {
        int candidate = 2;
        while (primes.size() < n) {
            if (isPrime(candidate)) primes.add(candidate);
            candidate++;
        }
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; (long) i * i <= x; i++)
            if (x % i == 0) return false;
        return true;
    }

    @Override
    public Iterator<Integer> iterator() {
        return primes.iterator();
    }

    public Iterator<Integer> reverseIterator() {
        return new Iterator<>() {
            private int idx = primes.size() - 1;
            @Override public boolean hasNext() { return idx >= 0; }
            @Override public Integer next()     { return primes.get(idx--); }
        };
    }
}

