package cn.yy.demo.generic;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : cy.wang
 * date   : 2020/10/16
 * desc   :
 */
class Generic {

    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer

        list.getClass().getMethod("add", Object.class).invoke(list, "asd");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public void testGeneric() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("aaaa");
        arrayList.add("100");

        for(int i = 0; i< arrayList.size();i++){
            String item = (String)arrayList.get(i);
            Log.d("泛型测试","item = " + item);
        }

        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            Log.d("泛型测试","类型相同");
        }

        One<String> o = new One<>();
        o.setOne("string");
        One<Object> n = new One<>();
        n.setOne(123);
        Log.d("泛型测试", o.getOne());
        Log.d("泛型测试", n.getOne().toString());

        Number[] numList = new Integer[3];
        Log.d("泛型测试", numList.getClass().getName());

        print(1);
        print(1.2);
        outString(o);
        outString1(n);

        List<Integer> listInt = new ArrayList<>();
        listInt.add(1);
        listInt.add(2);
        listInt.add(3);

        One<Number> listNumber = new One<>();
        listNumber.add(listInt);

        List<Object> listObject = new ArrayList<>();
        listNumber.copy(listObject);
        for (Object number : listObject) {
            Log.d("泛型测试", number.toString());
        }
    }

    public void outString(One<? extends String> str) {
        Log.d("泛型测试",str.toString());
    }

    public void outString1(One<? super CharSequence> str) {
        str.setOne(new StringBuilder("123"));
        Log.d("泛型测试", str.getOne().getClass().getName());
    }

    public static <T extends Number> void print(T t) {
        Log.d("泛型测试",t.toString());
    }
}
