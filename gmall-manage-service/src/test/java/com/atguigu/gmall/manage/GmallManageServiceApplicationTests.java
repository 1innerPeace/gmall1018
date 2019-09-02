package com.atguigu.gmall.manage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageServiceApplicationTests {



	@Test
	public void contextLoads() {
		System.out.println("++++++");
		Test1 test1 = new Test1();
		Thread thread = new Thread(test1);
		Thread thread2 = new Thread(test1);
		thread.start();
		thread2.start();

	}

}

class Test1 implements Runnable{

	int nu=50;

	@Override
	public void run() {
		if (nu>0){
			for (int i=0;i<50;i++ ){
				nu-=1;
				System.out.println(nu);
			}

		}
	}
	/*public int test1(){
		if (nu<10){
			for (int i=nu;i<10;i++ ){
                nu+=1;
				System.out.println(nu);
			}

		}
		return nu;
	}*/

}
class ThreadSafeCache{
	private int result;
	public int getResult(){
		return result;
	}
	public synchronized void setResult(int result){
		this.result=result;
	}

	public static void main(String[] args) {
		ThreadSafeCache threadSafeCache = new ThreadSafeCache();
		for (int i = 0; i <8 ; i++) {
			new Thread(() -> {
				//long id = Thread.currentThread().getId();
				String name = Thread.currentThread().getName();
				while (threadSafeCache.getResult() <100){
					System.out.println("我是线程"+name+"我陷入循环了");
				}
				System.out.println("我执行了");
			}).start();
			System.out.println("我想睡");
		}try{
			System.out.println("我准备睡了");
			String name2= Thread.currentThread().getName();
			System.out.println(name2);
			Thread.sleep(1000);
			System.out.println("我睡完了");
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		threadSafeCache.setResult(200);
	}

}
