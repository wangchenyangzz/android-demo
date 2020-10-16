package cn.yy.demo.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * author : cy.wang
 * date   : 2020/10/16
 * desc   :
 */
class One<T> {
    private T one;
    private final List<T> list = new ArrayList<>();

    public void setOne(T one) {
        this.one = one;
    }

    public T getOne() {
        return one;
    }

    public void add(List<? extends T> l) {
        list.addAll(l);
    }

    public void addd(List<T> l) {
        list.addAll(l);
    }

    public void copy(List<? super T> listObject) {
        listObject.addAll(list);
    }

    public List<T> getList() {
        return list;
    }
}
