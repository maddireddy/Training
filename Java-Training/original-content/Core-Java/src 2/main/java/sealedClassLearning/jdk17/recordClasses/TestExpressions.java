package sealedClassLearning.jdk17.recordClasses;

sealed interface Expr
        permits ConstantExpr, PlusExpr, TimesExpr, NegExpr {
    public int eval();
}

public class TestExpressions {
    public static void main(String[] args) {
        // (6 + 7) * -8
        System.out.println(
                new TimesExpr(
                        new PlusExpr(new ConstantExpr(6), new ConstantExpr(7)),
                        new NegExpr(new ConstantExpr(8))
                ).eval());
    }
}

record ConstantExpr(int i) implements Expr {
    public int eval() {
        return i();
    }
}

record PlusExpr(Expr a, Expr b) implements Expr {
    public int eval() {
        return a.eval() + b.eval();
    }
}

record TimesExpr(Expr a, Expr b) implements Expr {
    public int eval() {
        return a.eval() * b.eval();
    }
}

record NegExpr(Expr e) implements Expr {
    public int eval() {
        return -e.eval();
    }
}
