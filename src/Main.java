import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Main {
    public static void main(String[] args) {
        OST ost = new OST();
        ost.insert(10);
        ost.insert(3);
        ost.insert(33);
        ost.insert(53);
        ost.insert(9);
        ost.delete(10);
        System.out.println("print original tree");
        ost.inOrder(ost.getRoot());

    }
}
