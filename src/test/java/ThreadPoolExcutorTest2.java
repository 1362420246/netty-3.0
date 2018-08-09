import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExcutorTest2 implements Runnable {  
  
    public Integer count;  
      
    public ThreadPoolExcutorTest2(Integer count) {  
        this.count = count;  
    }  
      
    public void run() {  
        System.out.println("任务" + count);  
        try {  
            Thread.sleep(2000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void main(String[] args) throws InterruptedException {  
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();  
          
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS, workQueue);  
        for (int i = 1; i <= 20; i++) {  
            pool.execute(new ThreadPoolExcutorTest2(i));  
        }  
        Thread.sleep(1000);  
        System.out.println("线程池中队列中的线程数量：" + workQueue.size());  
          
          
        pool.shutdown();  
    }  
}  