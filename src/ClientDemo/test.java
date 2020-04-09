package ClientDemo;

public class test {

    public test t;
    private int i;


    public test(int i) {
        this.i = i;
    }

    public static void main(String[] args) {
       test t= new test(1);

       t.t=t;

       System.out.println(t.t.i);
    }
}
