package com.bu.algs.impl;

import com.bu.algs.BuList;
import com.bu.algs.Reversible;
import com.bu.algs.Sortable;
import com.bu.algs.util.Objects;


public class BuLinkedList<T> implements BuList<T>, Sortable, Reversible {

    private Node<T> head;

    @Override
    public int size() {
        if (head == null) {
            return 0;
        }
        int size = 1;
        Node<T> current = head;

        while (current.next != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(T elem) {
        if (isEmpty()) {
            return false;
        }

        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(elem, current.elem)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean add(T elem) {
        if (isEmpty()) {
            head = new Node<>(elem, null);
            return true;
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node<>(elem, null);
        return true;
    }

    @Override
    public boolean add(T elem, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }

        if (index == 0) {
            head = new Node<>(elem, head);
            return true;
        }

        Node<T> prev = head;
        Node<T> current = head.next;
        int currentIndex = 1;

        while (currentIndex < index) {
            prev = current;
            current = current.next;
            currentIndex++;
        }

        prev.next = new Node<>(elem, current);
        return true;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }

        Node<T> current = head;
        int currentIndex = 0;

        while (currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        return current.elem;
    }

    @Override
    public boolean remove(T elem) {
        if (isEmpty()) {
            return false;
        }

        if (Objects.equals(head.elem, elem)) {
            head = head.next;
            return true;
        }

        Node<T> prev = head;
        Node<T> current = head.next;

        while (current != null) {
            if (Objects.equals(elem, current.elem)) {
                prev.next = current.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public T remove(int index) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }

        if (index == 0) {
            T deletedElement = head.elem;
            head = head.next;
            return deletedElement;
        }

        int currentIndex = 0;
        Node<T> prev = head;
        Node<T> current = head.next;

        while (currentIndex < index) {
            prev = current;
            current = current.next;
            currentIndex++;
        }

        prev.next = current.next;
        return current.elem;
    }

    @Override
    public void sort() {
        head = mergeSort(head);
    }

    private Node<T> mergeSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }
    
        Node<T> middle = getMiddle(head);
        Node<T> nextToMiddle = middle.next;
        middle.next = null;
    
        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(nextToMiddle);
    
        return merge(left, right);
    }
    
    private Node<T> merge(Node<T> left, Node<T> right) {
        Node<T> result = null;
    
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
    
        if (((Comparable<T>) left.elem).compareTo(right.elem) <= 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
    
        return result;
    }
    
    private Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return head;
        }
    
        Node<T> slow = head;
        Node<T> fast = head;
    
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
    
        return slow;
    }
    

    @Override
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }
    
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;
    
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev; 
    }

    private static class Node<T> {

        private final T elem;

        private Node<T> next;

        public Node(T elem, Node<T> next) {
            this.elem = elem;
            this.next = next;
        }
    }
}
