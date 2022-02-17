import java.util.Arrays;

public class Main implements Runnable{
    static final int size = 1000000;
    static final int h = size/2;
    static float[] arr = new float[size];

    public static void main(String[] args) {
        singleThread();
        multiThread();
        Arrays.fill(arr,1);
        long a = System.currentTimeMillis();
        Thread thread1 = new Thread(()->{

            float[] first = Arrays.copyOfRange(arr,0,arr.length/2);
            for (int i = 0; i < first.length; i++) {
                first[i] = (float)(first[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(first, 0, arr, 0, first.length);
        });
        Thread thread2 = new Thread(()->{
            float[] second = Arrays.copyOfRange(arr,0,arr.length/2);
            for (int i = 0; i < second.length; i++) {
                second[i] = (float)(second[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(second, 0, arr, h, second.length);
            System.out.println("Multithreading?");
            System.out.println(System.currentTimeMillis()-a);

        });
       thread1.start();
       thread2.start();


    }

    public static void singleThread(){
        Arrays.fill(arr,1);
        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Single:");
        System.out.println(System.currentTimeMillis()-a);
    }
    public static void multiThread(){
        Arrays.fill(arr,1);
        long a = System.currentTimeMillis();

        float[] part1 = Arrays.copyOfRange(arr,0,arr.length/2);
        for (int i = 0; i < part1.length; i++) {
            part1[i] = (float)(part1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        float[] part2 = Arrays.copyOfRange(arr, arr.length/2, arr.length);

        for (int i = 0; i < part2.length; i++) {
            part2[i] = (float)(part2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
            System.arraycopy(part1, 0, arr, 0, part1.length);
            System.arraycopy(part2, 0, arr, part1.length, part2.length);
        System.out.println("Multiprocessing?");
        System.out.println(System.currentTimeMillis()-a);
    }


    @Override
    public void run() {

    }
}

