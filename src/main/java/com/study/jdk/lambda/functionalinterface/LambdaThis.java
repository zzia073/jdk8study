package com.study.jdk.lambda.functionalinterface;

import java.util.function.Supplier;

/**
 * @author ：fei
 * @date ：Created in 2019/11/4 0004 09:40
 */
public class LambdaThis {
    String abc = "abc";
    public static void main(String[] args) {
        LambdaThis lambdaThis = new LambdaThis();

        lambdaThis.testThis(lambdaThis.test());

        lambdaThis.testThis(new Supplier() {
            String abc = "bcd";
            @Override
            public Object get() {
                System.out.println(this.abc);
                return null;
            }
        });
        //=========================================
        SubLambdaThis lambdaThis1 = new SubLambdaThis();
        lambdaThis1.testThis(lambdaThis1.test());
//        下例中的this指代main方法上下文中，说明lambda表达式中的this
//        指代的是lambda所处上下文的当前对象，静态方法中不能使用this因此下例报错
//        lambdaThis1.testThis(() -> {
//            System.out.println(this.abc);
//            return false;
//        });

    }
    Supplier test(){
        return  () -> {
            System.out.println(this.abc);
            return true;
        };
    }
    void testThis(Supplier supplier){
        supplier.get();
    }
}
