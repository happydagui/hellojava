package hello.hotspot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * VM Args：-Xmx20M -Xms20M
 * Created by min on 17-2-20.
 */
public class AFullClass {
    String szfield = "hello world";
    long lfield;
    short sfield;
    Object obj = new Object();

    static {
        System.out.println("static intializing block");
    }

    {
        System.out.println("instance intializing block");
    }

    public AFullClass() {
        System.out.println("constructor");
    }

    public AFullClass(String szfield, long lfield, short sfield, Object obj) {
        this.szfield = szfield;
        this.lfield = lfield;
        this.sfield = sfield;
        this.obj = obj;
    }

    public static void main(String[] args) {


//        Scanner sc = new Scanner(System.in);
//        System.out.println("Press any key to start");
//        String s = sc.nextLine();

        new Thread(new Runnable() {
            public void run() {
                AFullClass inst = new AFullClass();
                Map<String, AFullClass> map = new HashMap<String, AFullClass>();
                map.put("foo", inst);
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).run();

    }
}
