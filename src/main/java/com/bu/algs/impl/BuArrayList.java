package com.bu.algs.impl;

import com.bu.algs.BuList;

// TODO: implement as dynamic array. Do not use Java collections of any utils.

public class BuArrayList<T> implements BuList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public BuArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

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
        for (int i = 0; i < size; i++) {
            if (array[i].equals(elem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(T elem) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = elem;
        return true;
    }

    @Override
    public boolean add(T elem, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }

        if (size == array.length) {
            resizeArray();
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = elem;
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
        return (T) array[index];
    }

    @Override
    public boolean remove(T elem) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(elem)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }

        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
    }

    private void resizeArray() {
        int newCapacity = array.length * 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}

