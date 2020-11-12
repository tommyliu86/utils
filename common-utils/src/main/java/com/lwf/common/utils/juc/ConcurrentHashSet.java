package com.lwf.common.utils.juc;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: liuwenfei14
 * @date: 2020-11-11 10:29
 */
public class ConcurrentHashSet<T> extends AbstractSet<T> implements Set<T>, Serializable {
    private static final Object V=new Object();
    private final ConcurrentHashMap<T,Object> map;
    public ConcurrentHashSet(){
        this.map=new ConcurrentHashMap<>();
    }
    public ConcurrentHashSet(int initialCapacity){
        this.map=new ConcurrentHashMap<>(initialCapacity);
    }
    public ConcurrentHashSet(Collection<? extends T> collection){
        this();
        this.addAll(collection);
    }
    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<T> iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.map.keySet().contains(o);
    }

    @Override
    public boolean add(T t) {
        return this.map.put(t,V)==null;
    }

    @Override
    public boolean remove(Object o) {
        return this.map.remove(o)==V;
    }

    @Override
    public void clear() {
        this.map.clear();
    }
}
