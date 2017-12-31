package com.cpphot.main;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin任务 用于将一个String list映射Integer list
 * 
 * @author caixiaopeng
 *
 */
public class MapTask extends RecursiveTask<List<Integer>> {

	private int start;
	private int end;
	private List<String> strs;

	public MapTask(int start, int end, List<String> strs) {
		super();
		this.start = start;
		this.end = end;
		this.strs = strs;
	}

	@Override
	protected List<Integer> compute() {
		if (end - start + 1 > 2) {
			System.out.println(start+"~"+end+"：拆分任务");
			// 切割任务
			int middle = (end + start) / 2;
			MapTask leftTask = new MapTask(start, middle, strs);
			MapTask rightTask = new MapTask(middle + 1, end, strs);

			// 执行子任务
			leftTask.fork();
			rightTask.fork();

			// 等待子任务完成
			List<Integer> leftResult = leftTask.join();
			List<Integer> rightResult = rightTask.join();

			// 合并子任务
			leftResult.addAll(rightResult);
			return leftResult;
		} else {
			System.out.println("计算任务");
			List<Integer> result = new LinkedList<Integer>();
			for (int i = start; i <= end; i++) {
				System.out.print(strs.get(i)+" ");
				result.add(Integer.valueOf(strs.get(i))+10);
			}
			System.out.println();
			return result;
		}
	}

}
