package com.liangcha.common.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Collection 工具类
 *
 * @author 凉茶
 */
public class CollectionUtils {

    /**
     * 转为List集合
     *
     * @param from 集合
     * @param func 函数
     * @param <T>  处理前的类型
     * @param <U>  处理后的类型
     * @return 处理过后的集合
     */
    public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 转为List集合
     *
     * @param from   集合
     * @param func   函数
     * @param filter 过滤函数
     * @param <T>    处理前的类型
     * @param <U>    处理后的类型
     * @return 处理过后的集合
     */
    public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func, Predicate<T> filter) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().filter(filter).map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 转为Set集合
     *
     * @param from 集合
     * @param func 函数
     * @param <T>  处理前的类型
     * @param <U>  处理后的类型
     * @return 处理过后的集合
     */
    public static <T, U> Set<U> convertSet(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    /**
     * 转为Set集合
     *
     * @param from   集合
     * @param func   函数
     * @param filter 过滤函数
     * @param <T>    处理前的类型
     * @param <U>    处理后的类型
     * @return 处理过后的集合
     */
    public static <T, U> Set<U> convertSet(Collection<T> from, Function<T, U> func, Predicate<T> filter) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().filter(filter).map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    /**
     * 将集合转为字符串
     *
     * @param from      集合
     * @param func      函数
     * @param delimiter 指定的连接符
     * @param <T>       处理前的类型
     * @return 处理过后的集合
     */
    public static <T> String join(Collection<T> from, Function<T, String> func, String delimiter) {
        if (CollUtil.isEmpty(from)) {
            return "";
        }
        return join(from, func, element -> true, delimiter);
    }

    /**
     * 将集合转为字符串
     *
     * @param from      集合
     * @param func      函数
     * @param filter    过滤函数
     * @param delimiter 指定的连接符
     * @param <T>       处理前的类型
     * @return 处理过后的集合
     */
    public static <T> String join(Collection<T> from, Function<T, String> func, Predicate<T> filter, String delimiter) {
        if (CollUtil.isEmpty(from)) {
            return "";
        }
        return from.stream().filter(filter).map(func).filter(Objects::nonNull).collect(Collectors.joining(delimiter));
    }

    public static <K, T> Map<K, T> convertMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return convertMap(from, keyFunc, Function.identity());
    }

    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        // 有值冲突时,忽略第二个值,取第一个值
        return convertMap(from, keyFunc, valueFunc, (v1, v2) -> v1);
    }

    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc, BinaryOperator<V> mergeFunction) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return convertMap(from, keyFunc, valueFunc, mergeFunction, HashMap::new);
    }

    /**
     * 转为mp集合
     *
     * @param from          集合
     * @param keyFunc       键函数
     * @param valueFunc     值函数
     * @param mergeFunction 合并策略函数(假如map集合有重复的键,即用此规则进行合并)
     * @param supplier      指定类型 比如HashMap::new
     * @return 处理过后的集合
     * <p>
     * mergeFunction例子:
     * Map<String, Integer> userPointsMap = convertMap(userList,
     * User::getUsername, // 提取用户名作为键
     * User::getPoints,   // 提取积分作为值
     * Integer::sum,      // 指定合并策略为对积分进行求和
     * HashMap::new);     // 创建一个HashMap来存储结果
     */
    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc, BinaryOperator<V> mergeFunction, Supplier<? extends Map<K, V>> supplier) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.toMap(keyFunc, valueFunc, mergeFunction, supplier));
    }

}
