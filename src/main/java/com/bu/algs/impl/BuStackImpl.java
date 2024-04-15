package com.bu.algs.impl;

import com.bu.algs.BuStack;


public class BuStackImpl<T> implements BuStack<T> {

    private Node<T> top;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T elem) {
        Node<T> current = top;
        while (current != null) {
            if (current.elem.equals(elem)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public T push(T item) {
        top = new Node<>(item, top);
        size++;
        return item;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T result = top.elem;
        top = top.next;
        size--;
        return result;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return top.elem;
    }

    @Override
    public int search(T elem) {
        int index = 0;
        Node<T> current = top;
        while (current != null) {
            if (current.elem.equals(elem)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    private static class Node<T> {
        private final T elem;
        private final Node<T> next;

        public Node(T elem, Node<T> next) {
            this.elem = elem;
            this.next = next;
        }
    }
}
