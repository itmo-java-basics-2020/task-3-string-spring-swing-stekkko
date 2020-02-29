package ru.itmo.java;

import java.util.*;

public class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return new int[0];
        }

        int p = inputArray[inputArray.length - 1];
        for (int i = inputArray.length - 1; i >= 1; i--) {
            inputArray[i] = inputArray[i - 1];
        }
        inputArray[0] = p;
        return inputArray;
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     *
     * Если входной массив пуст или равен null - вернуть 0
     *
     * Пример: 2 4 6 -> 24
     */
    int getMaxProduct(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return 0;
        }
        if (inputArray.length == 1) {
            return inputArray[0];
        }

        int min = (inputArray[0] < inputArray[1]) ? inputArray[0] : inputArray[1];
        int nextMin = (inputArray[0] < inputArray[1]) ? inputArray[1] : inputArray[0];
        int max = (inputArray[0] < inputArray[1]) ? inputArray[1] : inputArray[0];
        int nextMax = (inputArray[0] < inputArray[1]) ? inputArray[0] : inputArray[1];


        for (int i = 2; i < inputArray.length; i++) {
            int current = inputArray[i];
            if (current < min) {
                nextMin = min;
                min = current;
            } else if (current < nextMin) {
                nextMin = current;
            }
            if (current > max) {
                nextMax = max;
                max = current;
            } else if (current > nextMax) {
                nextMax = current;
            }
        }
        return Math.max(min * nextMin, max * nextMax);
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     *
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        int countAB = 0;
        Set<Character> pattern = Set.of('A', 'B', 'a', 'b');
        for (int i = 0; i < input.length(); i++) {
            if (pattern.contains(input.charAt(i))) {
                countAB++;
            }
        }
        return 100 * countAB / input.length();
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }
        if (input.length() == 0) {
            return true;
        }

        for (int i = 0; i < (input.length() + 1) / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuilder st = new StringBuilder();

        char pred = input.charAt(0);
        int cnt = 1;
        for (int i = 1; i < input.length(); i++) {
            char cur = input.charAt(i);
            if (cur == pred) {
                cnt++;
            } else {
                st.append(pred).append(cnt);
                cnt = 1;
                pred = cur;
            }
        }
        st.append(pred).append(cnt);

        return st.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String fst, String snd) {
        if (fst == null || snd == null) {
            return false;
        }
        if (fst.length() == 0 || snd.length() == 0) {
            return false;
        }

        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < fst.length(); i++) {
            char c = fst.charAt(i);
            if (dict.containsKey(c)) {
                dict.put(c, dict.get(c) + 1);
            } else {
                dict.put(c, 1);
            }
        }

        for (int i = 0; i < snd.length(); i++) {
            char c = snd.charAt(i);
            if (!dict.containsKey(c)) {
                return false;
            } else {
                dict.put(c, dict.get(c) - 1);
                if (dict.get(c) < 0) return false;
            }
        }

        return true;
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {
        if (s == null || s.length() == 0) { 
            return false;
        }
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }
        Arrays.sort(chars);

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     *
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {
        if (m == null || m.length != m[0].length) {
            return new int[2][0];
        }

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < i; j++) {
                int temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }

        return m;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     *
     * Запрещается пользоваться функций join
     *
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {
        if (inputStrings == null || inputStrings.length == 0) {
            return "";
        }
        if (separator == null) {
            separator = ' ';
        }

        StringBuilder st = new StringBuilder();

        for (int i = 0; i < inputStrings.length - 1; i++) {
            st.append(inputStrings[i]).append(separator);
        }
        st.append(inputStrings[inputStrings.length - 1]);

        return st.toString();
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {
        if (inputStrings == null || inputStrings.length == 0) {
            return 0;
        }
        if (prefix == null) {
            return 0;
        }

        int count = 0;
        for (String str : inputStrings) {
            boolean havePref = true;
            for (int j = 0; j < prefix.length(); j++) {
                if (str.charAt(j) != prefix.charAt(j)) {
                    havePref = false;
                    break;
                }
            }
            if (havePref) {
                count++;
            }
        }

        return count;
    }
}
