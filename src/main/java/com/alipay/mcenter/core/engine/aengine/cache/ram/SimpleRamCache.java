/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.alipay.mcenter.core.engine.aengine.cache.ram;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通用内存缓存,供单利模式使用
 * @author chingsung.zihong
 * @version $Id: 2017-05-11 $
 */
public final class SimpleRamCache<K, V> {

    private final Lock      lock = new ReentrantLock();

    private final int       maxCapacity;

    private final Map<K, V> eden;

    private final Map<K, V> perm;

    public SimpleRamCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.eden = new ConcurrentHashMap<K, V>(maxCapacity);
        this.perm = new WeakHashMap<K, V>(maxCapacity);
    }

    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            lock.lock();
            try {
                v = this.perm.get(k);
            } finally {
                lock.unlock();
            }
            if (v != null) {
                this.eden.put(k, v);
            }
        }
        return v;
    }

    public boolean hasKey(K k) {
        return get(k) != null;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= maxCapacity) {
            lock.lock();
            try {
                this.perm.putAll(this.eden);
            } finally {
                lock.unlock();
            }
            this.eden.clear();
        }
        this.eden.put(k, v);
    }

    public void del(K k){
        if(this.eden.containsKey(k)){
            this.eden.remove(k);
        }
        if(this.perm.containsKey(k)){
            this.perm.remove(k);
        }
    }
}
