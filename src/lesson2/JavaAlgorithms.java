package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     *
     * сложность O(mn), ресурсоемкость R(mn)
     */
    static public String longestCommonSubstring(String firs, String second) {
        if (firs.length() == 0 || second.length() == 0)
            return "";
        if (firs.equals(second))
            return firs;
        int[][] matrix = new int[firs.length()][];
        int maxLength = 0;
        int maxI = 0;
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[second.length()];
            for (int j = 0; j < matrix[i].length; j++) {
                if (firs.charAt(i) == second.charAt(j)) {
                    if (i != 0 && j != 0)
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    else
                        matrix[i][j] = 1;
                    if (matrix[i][j] > maxLength) {
                        maxLength = matrix[i][j];
                        maxI = i;
                    }
                }
            }
        }
        return firs.substring(maxI - maxLength + 1, maxI + 1);
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     *
     *Сложность O(n^2), ресурсы R(mn)
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        file = new ArrayList<>();
        try(Scanner scanner = new Scanner(new FileReader(inputName))){
            while (scanner.hasNextLine())
                file.add(scanner.nextLine().trim().split(" "));
        } catch (FileNotFoundException e) {
            System.err.println("Reading error");
            System.err.println(e.toString());
        }
        Set<String> result = new TreeSet<>();
        for (String w:words) {
            boolean br = false;
            for (int y = 0; y < file.size(); y++) {
                for (int x =0; x < file.get(y).length; x++){
                    word = w;
                    br = searcher(x,y,0);
                    if (br) break;
                }
                if (br) break;
            }
            if (br) result.add(w);
        }
        return result;
    }
    static private ArrayList<String[]> file;
    static private String word;
    static private boolean searcher(int x, int y, int pos){
        if (pos == word.length())
            return true;
        if (file.get(y)[x].charAt(0) != word.charAt(pos))
            return false;
        for (int d = -1; d < 2; d+=2) {
            int dx = x + d;
            int dy = y + d;
            if ((dy > -1 && dy < file.size() && searcher(x,dy,pos+1)) ||
                    (dx > -1 && dx < file.get(y).length && searcher(dx,y,pos+1)))
                return true;
        }
        return false;
    }
}
