import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class ThreadPoolExcutorTest implements Runnable {  
    public String name;  
    public ThreadPoolExcutorTest(String name) {  
        this.name = name;  
    }  
    public void run() {  
        System.out.println(name);  
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
          
    }  
    public static void main(String[] args) {  
    	//有界队列：队列长度3
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3); 
        //核心线程 2 ，最大线程 3
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(  
                            2, //corePoolSize  
                            3,  //maximumPoolSize  
                            1L,  
                            TimeUnit.SECONDS,  
                            workQueue  
                            ); 
        //添加任务
        //当小于等于2个任务时候，直接执行
        //当大于2个任务，小于等于5个任务时候。   每次执行2个任务（核心线程数） 。队列存储剩下的任务。
        //当大于5个任务时候。 小于等于6个任务时候。  每次执行3个个任务（最大线程数） 。队列存储剩下的任务。
        //当大于6个任务时候 。 （超出队列长度  和 最大线程数）执行默认饱和策略（拒绝策略）。最后一个任务抛出异常。但前6个正常执行（每次执行3个）
        threadPool.execute(new ThreadPoolExcutorTest("任务1"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务2"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务3"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务4"));  
        threadPool.execute(new ThreadPoolExcutorTest("任务5"));  
//        threadPool.execute(new ThreadPoolExcutorTest("任务6"));  
//        threadPool.execute(new ThreadPoolExcutorTest("任务7")); 
//        threadPool.execute(new ThreadPoolExcutorTest("任务8")); 
        threadPool.shutdown();  
    }  
}  