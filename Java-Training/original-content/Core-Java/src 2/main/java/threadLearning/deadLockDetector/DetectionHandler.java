package threadLearning.deadLockDetector;

import java.lang.management.ThreadInfo;

public interface DetectionHandler {
    void handleDeadlock(final ThreadInfo[] deadlockedThreads);
}
