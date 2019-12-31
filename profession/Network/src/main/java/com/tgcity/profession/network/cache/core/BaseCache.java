package com.tgcity.profession.network.cache.core;


import com.tgcity.profession.network.utils.CallBackUtils;

import java.lang.reflect.Type;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author TGCity
 * 缓存的基类
 * 1.所有缓存处理都继承该基类
 * 2.增加了锁机制，防止频繁读取缓存造成的异常。
 * 3.子类直接考虑具体的实现细节就可以了
 */
public abstract class BaseCache {
    private final ReadWriteLock mLock = new ReentrantReadWriteLock();

    /**
     * 读取缓存
     *
     * @param key       缓存key
     * @param existTime 缓存时间
     */
    final <T> T load(Type type, String key, long existTime) {
        //1.先检查key
        CallBackUtils.checkNotNull(key, "key == null");

        //2.判断key是否存在,key不存在去读缓存没意义
        if (!containsKey(key)) {
            return null;
        }

        //3.判断是否过期，过期自动清理
        if (isExpiry(key, existTime)) {
            remove(key);
            return null;
        }

        //4.开始真正的读取缓存
        mLock.readLock().lock();
        try {
            // 读取缓存
            return doLoad(type, key);
        } finally {
            mLock.readLock().unlock();
        }
    }

    /**
     * 保存缓存
     *
     * @param key   缓存key
     * @param value 缓存内容
     * @return T
     */
    final <T> boolean save(String key, T value) {
        //1.先检查key
        CallBackUtils.checkNotNull(key, "key == null");

        //2.如果要保存的值为空,则删除
        if (value == null) {
            return remove(key);
        }

        //3.写入缓存
        boolean status = false;
        mLock.writeLock().lock();
        try {
            status = doSave(key, value);
        } finally {
            mLock.writeLock().unlock();
        }
        return status;
    }

    /**
     * 删除缓存
     */
    final boolean remove(String key) {
        mLock.writeLock().lock();
        try {
            return doRemove(key);
        } finally {
            mLock.writeLock().unlock();
        }
    }


    /**
     * 获取缓存大小
     * @return long
     */
    long size() {
        return getSize();
    }

    /**
     * 清空缓存
     */
    final boolean clear() {
        mLock.writeLock().lock();
        try {
            return doClear();
        } finally {
            mLock.writeLock().unlock();
        }
    }

    /**
     * 是否包含 加final 是让子类不能被重写，只能使用doContainsKey<br>
     * 这里加了锁处理，操作安全。<br>
     *
     * @param key 缓存key
     * @return 是否有缓存
     */
    public final boolean containsKey(String key) {
        mLock.readLock().lock();
        try {
            return doContainsKey(key);
        } finally {
            mLock.readLock().unlock();
        }
    }

    /**
     * 是否包含  采用protected修饰符  被子类修改
     * @param key String
     * @return boolean
     */
    protected abstract boolean doContainsKey(String key);

    /**
     * 是否过期
     * @param key       String
     * @param existTime long
     * @return boolean
     */
    protected abstract boolean isExpiry(String key, long existTime);

    /**
     * 读取缓存
     * @param type  Type
     * @param key   String
     * @return T
     */
    protected abstract <T> T doLoad(Type type, String key);

    /**
     * 保存
     * @param key   String
     * @param value  T
     * @return T
     */
    protected abstract <T> boolean doSave(String key, T value);

    /**
     * 删除缓存
     * @param key   String
     * @return boolean
     */
    protected abstract boolean doRemove(String key);

    /**
     * 清空缓存
     * @return boolean
     */
    protected abstract boolean doClear();

    /**
     * 获取缓存大小
     *
     * @return long
     */
    protected abstract long getSize();
}
