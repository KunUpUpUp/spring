package com.seasugar.springmvc.myannotation;


import java.lang.reflect.Field;

public class MyAnnotationSolver {
    @MyAnnotation
    private String myspring;

//    @MyAnnotation
//    private int age;

    public void getAnnotation() throws IllegalAccessException {
        Field[] declaredFields = MyAnnotationSolver.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotationSolver myAnnotationSolver = new MyAnnotationSolver();
                declaredField.setAccessible(true);
                // 反射就是反着来，正常来说是 myAnnotationSolver.myspring = xxx
                // 反射就是逆向， declaredField.set(对象，xxx)
                declaredField.set(myAnnotationSolver, "zkp");
                System.out.println(myAnnotationSolver.myspring);
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        MyAnnotationSolver myAnnotationSolver = new MyAnnotationSolver();
        myAnnotationSolver.getAnnotation();
    }
}
