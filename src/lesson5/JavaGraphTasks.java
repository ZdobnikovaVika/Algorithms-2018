package lesson5;

import kotlin.NotImplementedError;
import lesson5.impl.GraphBuilder;


import java.util.*;


@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     *
     * O(v * e)
     */
    public static Graph minimumSpanningTree(Graph graph) {
        GraphBuilder graphBuilder = new GraphBuilder();
        int verticesNum = graph.getVertices().size();
        Graph.Vertex first = graph.getVertices().iterator().next();
        Deque<Graph.Vertex> deque = new ArrayDeque<>();
        Set<Graph.Vertex> used = new HashSet<>();
        deque.add(first);
        used.add(first);
        while (!deque.isEmpty()) {
            Graph.Vertex cur = deque.removeLast();
            graph.getNeighbors(cur).forEach(n -> {
                if (!used.contains(n)) {
                    used.add(n);
                    deque.add(n);
                    graphBuilder.addVertex(cur.getName());
                    graphBuilder.addConnection(cur, n, 1);
                }
            });
        }
        return graphBuilder.build();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     * сложость 2^N
     */
    public static Path longestSimplePath(Graph graph) {
        Deque<Path> stack = new ArrayDeque<>();
        graph.getVertices().forEach(v -> stack.push(new Path(v)));
        Path best = new Path(graph.getVertices().iterator().next());
        while (!stack.isEmpty()) {
            Path p = stack.removeFirst();
            if (p.getLength() > best.getLength()) {
                best = p;
                if (p.getVertices().size() == graph.getVertices().size()) {
                    break;
                }
            }
            graph.getNeighbors(p.getVertices().get(p.getVertices().size() - 1))
                    .forEach(v -> {
                        if (!p.contains(v)) {
                            stack.push(new Path(p, graph, v));
                        }
                    });
        }
        return best;

    }

}
