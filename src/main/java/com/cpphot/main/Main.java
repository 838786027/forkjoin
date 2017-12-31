package com.cpphot.main;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 任务池
		ForkJoinPool forkJoinPool=new ForkJoinPool();
		
		List<String> strs=new LinkedList<String>();
		strs.add("0");
		strs.add("1");
		strs.add("2");
		strs.add("3");
		strs.add("4");
		strs.add("5");
		strs.add("6");
		strs.add("7");
		strs.add("8");
		strs.add("9");
		MapTask task=new MapTask(0, strs.size()-1, strs);
		
		// 将任务提交到任务池
		ForkJoinTask<List<Integer>> result=forkJoinPool.submit(task);
		
		System.out.println(result.get());
	}

}
