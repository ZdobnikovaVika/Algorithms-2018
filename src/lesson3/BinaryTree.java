package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;
    private boolean rightChild;


    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */

    
    @Override
    public boolean remove(Object o) {

        rightChild = true;
        Node<T> removeNode = root;
        Node<T> subroot = root;

        int compare = 0;

        T elected = (T) o;

        while ((compare = removeNode.value.compareTo(elected)) != 0) {
            subroot = removeNode;

            if (compare < 0) {
                rightChild = true;
                removeNode = removeNode.right;
            } else {
                rightChild = false;
                removeNode = removeNode.left;
            }

            if (removeNode == null)
                return false;
        }

        remRemove(removeNode, subroot);
        size--;

        return true;
    }

    private void remRemove(Node<T> removeNode, Node<T> subroot) {
        if (removeNode.right == null && removeNode.left == null) {
            if (removeNode == root) {
                root = null;
            } else if (rightChild) {
                subroot.right = null;
            } else
                subroot.left = null;

        } else if (removeNode.right == null) {
            if (removeNode == root) {
                root = removeNode.left;
            } else if (rightChild) {
                subroot.right = removeNode.left;
            } else
                subroot.left = removeNode.left;

        } else if (removeNode.left == null) {
            if (removeNode == root) {
                root = removeNode.right;
            } else if (rightChild) {
                subroot.right = removeNode.right;
            } else
                subroot.left = removeNode.right;

        } else {
            Node changeSubroot = changeNode(removeNode);

            if (removeNode == root) {
                root = change;
            } else if (rightChild) {
                subroot.right = change;
            } else
                subroot.left = change;

            change.right = removeNode.right;
        }
    }

    private Node<T> changeNode(Node<T> changeNod) {
        Node<T> changeSubroot = changeNod;
        Node<T> change = changeNod;
        Node<T> search = changeNod.right;

        while (Search != null) {
            changeSubroot = change;
            change = Search;
            Search = Search.left;
        }

        if (change != changeNod.right) {
            changeSubroot.left = replacement.right;
            change.right = changeNod.right;
        }

        return change;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {
        private Node<T> current;
        Stack<Node<T>> st;

        private BinaryTreeIterator() {
            st = new Stack<>();
            Node<T> node = root;
            while (node != null) {
                st.push(node);
                node = node.left;
            }
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */

        private Node<T> findNext() {
            Node<T> node = st.pop();
            current = node;
            if (node.left != null) {
                node = node.left;
                while (node != null) {
                    st.push(node);
                    node = node.right;
                }
            }
            return current;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();

        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
