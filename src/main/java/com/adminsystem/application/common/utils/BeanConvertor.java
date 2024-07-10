package com.adminsystem.application.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanConvertor {
    public static<T,S> T to(S source,Class<T> t) {
        T target = null;
        if (source != null) {
            try {
                target = t.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(source, target);
        }

        return target;
    }
    public static<T,S> List<T> to(List<S> source, Class<T> t) {
        if (source == null)
            return null;

        List<T> targets = getTargetList(source.size());

        for (S s : source) {
            targets.add(to(s,t));
        }

        return targets;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static<T>  List<T> getTargetList(int size){
        return new ArrayList(size);
    }
}