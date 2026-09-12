/**
 * Задание 4: работа со строками и char[].
 * Палиндром, реверс слов, подсчёт символов, шифр Цезаря, поиск слова.
 */
package ru.university.lab2.strings;

public class Task04TextProcessing {
    public void run() {
        checkPalindrome("A man, a plan, a canal: Panama");
        reverseWords("кот съел мышь");
        countCharacters("Hello, 123 мир!");
        testCaesarCipher("Hello", 3);
        findLongestWord("The quick brown fox jumps over the lazy dog");
    }

    /**
     * Проверка палиндрома: удаляем всё, кроме букв и цифр, приводим к нижнему регистру.
     */
    private void checkPalindrome(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        char[] chars = cleaned.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                System.out.printf("'%s' — НЕ палиндром%n", s);
                return;
            }
        }
        System.out.printf("'%s' — палиндром%n", s);
    }

    /**
     * Разворот слов: разбиваем по пробелам, выводим в обратном порядке.
     */
    private void reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }
        System.out.printf("'%s' → '%s'%n", s, reversed.toString().trim());
    }

    /**
     * Подсчёт гласных, согласных, цифр и пробелов за один проход.
     * Гласные: aeiou (англ) и аеёиоуыэюя (рус).
     * Остальные буквы — согласные.
     */
    private void countCharacters(String s) {
        int vowels = 0, consonants = 0, digits = 0, spaces = 0;
        String vowelChars = "aeiouаеёиоуыэюя";

        for (char c : s.toLowerCase().toCharArray()) {
            if (vowelChars.indexOf(c) >= 0) {
                vowels++;
            } else if (c >= 'a' && c <= 'z') {
                consonants++;
            } else if (c >= 'а' && c <= 'я') {
                consonants++;
            } else if (Character.isDigit(c)) {
                digits++;
            } else if (c == ' ') {
                spaces++;
            }
        }

        System.out.printf("Строка: '%s'%n", s);
        System.out.printf("Гласные: %d, Согласные: %d, Цифры: %d, Пробелы: %d%n", vowels, consonants, digits, spaces);
    }

    /**
     * Шифр Цезаря: сдвиг только для латинских букв.
     * Регистр сохраняется.
     */
    private String caesarEncrypt(String s, int k) {
        char[] result = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                result[i] = (char) ((c - 'a' + k + 26) % 26 + 'a');
            } else if (c >= 'A' && c <= 'Z') {
                result[i] = (char) ((c - 'A' + k + 26) % 26 + 'A');
            } else {
                result[i] = c; // Не-латинские символы не меняются
            }
        }
        return new String(result);
    }

    private String caesarDecrypt(String s, int k) {
        return caesarEncrypt(s, -k);
    }

    private void testCaesarCipher(String s, int k) {
        String encrypted = caesarEncrypt(s, k);
        String decrypted = caesarDecrypt(encrypted, k);
        System.out.printf("Цезарь: '%s' → '%s' → '%s'%n", s, encrypted, decrypted);
    }

    /**
     * Поиск самого длинного слова.
     * Разделители — любые не-буквенные символы.
     */
    private void findLongestWord(String s) {
        String[] words = s.split("[^a-zA-Z]+");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        System.out.printf("Самое длинное слово в '%s': '%s'%n", s, longest);
    }
}
