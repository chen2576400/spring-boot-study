接口限流 使用 (不适用于分布式集群)





RateLimiter ：常用方法
create(int permits)	创建每秒发放permits个令牌的桶
acquire()	不带参数表示获取一个令牌.如果没有令牌则一直等待,返回等待的时间(单位为秒),没有被限流则直接返回0.0
acquire(int permits )	获取permits 个令牌,.如果没有获取完令牌则一直等待,返回等待的时间(单位为秒),没有被限流则直接返回0.0
tryAcquire()	尝试获取一个令牌,立即返回(非阻塞)
tryAcquire(int permits)	尝试获取permits 个令牌,立即返回(非阻塞)
tryAcquire(long timeout, TimeUnit unit)	尝试获取1个令牌,带超时时间
tryAcquire(int permits, long timeout, TimeUnit unit)	尝试获取permits个令牌,带超时时间





Semaphore：常用方法
acquire()	从许可集中请求获取一个许可，此时当前线程开始阻塞，直到获得一个可用许可，或者当前线程被中断。
acquire(int permits)	从许可集中请求获取指定个数(permits)的许可，此时当前线程开始阻塞，直到获得指定数据(permits)可用许可，或者当前线程被中断。
release()	释放一个许可，将其返回给许可集。
release(int permits)	释放指定个数(permits)许可，将其返回给许可集。
tryAcquire()	尝试获取一个可用许可，如果此时有一个可用的许可，则立即返回true，同时许可集中许可个数减一；如果此时许可集中无可用许可，则立即返回false。
tryAcquire(int permits)	尝试获取指定个数(permits)可用许可，如果此时有指定个数(permits)可用的许可，则立即返回true，同时许可集中许可个数减指定个数(permits)；如果此时许可集中许可个数不足指定个数(permits)，则立即返回false。
tryAcquire(long timeout, TimeUnit unit)	在给定的等待时间内，尝试获取一个可用许可，如果此时有一个可用的许可，则立即返回true，同时许可集中许可个数减一；如果此时许可集中无可用许可，当前线程阻塞，直至其它某些线程调用此Semaphore的release()方法并且当前线程是下一个被分配许可的线程，或者其它某些线程中断当前线程或者已超出指定的等待时间
tryAcquire(int permits, long timeout, TimeUnit unit)	在给定的等待时间内，尝试获取指定个数(permits)可用许可，如果此时有指定个数(permits)可用的许可，则立即返回true，同时许可集中许可个数减指定个数(permits)；如果此时许可集中许可个数不足指定个数(permits)，当前线程阻塞，直至其它某些线程调用此Semaphore的release()方法并且当前线程是下一个被分配许可的线程并且许可个数满足指定个数，或者其它某些线程中断当前线程，或者已超出指定的等待时间。
