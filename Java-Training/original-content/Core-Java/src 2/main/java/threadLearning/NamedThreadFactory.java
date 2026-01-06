package threadLearning;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

    private static int count = 0;

    @Override
    public Thread newThread(@NotNull Runnable r) {
        Thread t = new Thread(r);
        String threadName = "learning-thread-";
        t.setName(threadName + count++);
        return t;
    }
}
