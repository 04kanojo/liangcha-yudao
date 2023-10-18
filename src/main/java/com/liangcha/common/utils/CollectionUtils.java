package com.liangcha.common.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
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
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.joining(delimiter));
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
}
