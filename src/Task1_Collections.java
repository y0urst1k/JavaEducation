import java.util.*;

public class Task1_Collections {
    public static void run() {
        int N = 20;
        Random rnd = new Random();

        // 1. Массив случайных чисел 0..100
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = rnd.nextInt(101);
        }
        System.out.println("Массив:          " + Arrays.toString(arr));

        // 2. Список из массива
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        System.out.println("Список:          " + list);

        // 3. Сортировка по возрастанию (пузырьком)
        bubbleSortAsc(list);
        System.out.println("По возрастанию:  " + list);

        // 4. Сортировка в обратном порядке (по убыванию)
        bubbleSortDesc(list);
        System.out.println("По убыванию:     " + list);

        // 5. Перемешивание (алгоритм Фишера–Йейтса)
        shuffleList(list, rnd);
        System.out.println("Перемешан:       " + list);

        // 6. Циклический сдвиг на 1 вправо
        rotateRight(list, 1);
        System.out.println("Сдвиг на 1:      " + list);

        // 7. Только уникальные элементы
        ArrayList<Integer> uniqueList = getUniqueElements(list);
        System.out.println("Уникальные:      " + uniqueList);

        // 8. Только дублирующиеся элементы
        ArrayList<Integer> dupList = getDuplicateElements(list);
        System.out.println("Дубликаты:       " + dupList);

        // 9. Из списка — массив
        Integer[] arr2 = listToArray(list);
        System.out.println("Массив из списка:" + Arrays.toString(arr2));

        // 10. Подсчёт количества вхождений каждого числа
        ArrayList<Integer> freqKeys = new ArrayList<>();
        ArrayList<Integer> freqValues = new ArrayList<>();

        for (Integer v : list) {
            int idx = indexOf(freqKeys, v);
            if (idx == -1) {
                freqKeys.add(v);
                freqValues.add(1);
            } else {
                int oldCount = freqValues.get(idx);
                freqValues.set(idx, oldCount + 1);
            }
        }

        System.out.println("Частоты:");
        for (int i = 0; i < freqKeys.size(); i++) {
            System.out.println("  " + freqKeys.get(i) + " -> " + freqValues.get(i));
        }
    }

    // --- Вспомогательные методы ---

    private static void bubbleSortAsc(ArrayList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private static void bubbleSortDesc(ArrayList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) < list.get(j + 1)) {
                    int tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private static void shuffleList(ArrayList<Integer> list, Random rnd) {
        // Алгоритм Фишера–Йейтса: идём с конца, меняем с случайным индексом [0..i]
        for (int i = list.size() - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1); // [0..i] включительно
            int tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
    }

    private static void rotateRight(ArrayList<Integer> list, int k) {
        if (list.isEmpty()) return;
        int n = list.size();
        k = ((k % n) + n) % n; // нормализация k (на случай отрицательных)
        if (k == 0) return;

        // Три разворота: [0..n-k-1], [n-k..n-1], затем весь список
        reverseRange(list, 0, n - k - 1);
        reverseRange(list, n - k, n - 1);
        reverseRange(list, 0, n - 1);
    }

    private static void reverseRange(ArrayList<Integer> list, int start, int end) {
        while (start < end) {
            int tmp = list.get(start);
            list.set(start, list.get(end));
            list.set(end, tmp);
            start++;
            end--;
        }
    }

    private static ArrayList<Integer> getUniqueElements(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer v : list) {
            if (!contains(result, v)) {
                result.add(v);
            }
        }
        return result;
    }

    private static boolean contains(ArrayList<Integer> list, Integer value) {
        for (Integer v : list) {
            if (v.equals(value)) return true;
        }
        return false;
    }

    private static ArrayList<Integer> getDuplicateElements(ArrayList<Integer> list) {
        ArrayList<Integer> seen = new ArrayList<>();
        ArrayList<Integer> duplicates = new ArrayList<>();

        for (Integer v : list) {
            if (contains(seen, v)) {
                // Если ещё не добавляли этот дубликат в результат — добавляем
                if (!contains(duplicates, v)) {
                    duplicates.add(v);
                }
            } else {
                seen.add(v);
            }
        }
        return duplicates;
    }

    private static Integer[] listToArray(ArrayList<Integer> list) {
        Integer[] res = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private static int indexOf(ArrayList<Integer> list, Integer value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
