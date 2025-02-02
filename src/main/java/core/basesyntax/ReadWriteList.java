package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        Lock addLock = lock.writeLock();
        addLock.lock();
        try {
            list.add(element);
        } finally {
            addLock.unlock();
        }
    }

    public E get(int index) {
        Lock getLock = lock.readLock();
        try {
            getLock.lock();
            return list.get(index);
        } finally {
            getLock.unlock();
        }
    }

    public int size() {
        Lock getSizeLock = lock.readLock();
        try {
            getSizeLock.lock();
            return list.size();
        } finally {
            getSizeLock.unlock();
        }
    }
}
