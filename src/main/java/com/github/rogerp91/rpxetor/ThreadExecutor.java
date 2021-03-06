package com.github.rogerp91.rpxetor;/*
 * Copyright 2015 Roger Patiño
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * com.github.rogerp91.rpxetor.Executor implementation based on ThreadPoolExecutor.
 * ThreadPoolExecutorConfig:
 *
 * Core pool size: 3. Max pool size: 5. Keep alive time: 120. Time unit:
 * seconds. Work queue: LinkedBlockingQueue.
 */
public class ThreadExecutor implements Executor {

	private static final int CORE_POOL_SIZE = 3;
	private static final int MAX_POOL_SIZE = 5;
	private static final int KEEP_ALIVE_TIME = 120;
	private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
	private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

	private ThreadPoolExecutor threadPoolexecutor;

	public ThreadExecutor() {
		threadPoolexecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT, WORK_QUEUE);
	}

	public ThreadExecutor(final int corePoolSize, final int maxPoolSize, final int keepAliveTime, final TimeUnit timeUnit) {
		threadPoolexecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, WORK_QUEUE);
	}

	@Override
	public void run(final Interactor interactor) {
		if (interactor == null) {
			throw new IllegalArgumentException("com.github.rogerp91.rpxetor.Interactor to execute can't be null");
		}
		threadPoolexecutor.submit(interactor);
	}

}
