package hello;

public class class1 {
    private int a;

    public class1 (int b) {
        a = b;
    }

    public class1 (){
        a = 10;
    }

    public int get_a(){
        return a;
    }

    public static void main(String[] args) {
        class1 a = new class1();
        System.out.printf("%d", a.get_a());
    }
}

