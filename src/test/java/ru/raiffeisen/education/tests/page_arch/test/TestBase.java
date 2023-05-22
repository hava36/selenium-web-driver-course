package ru.raiffeisen.education.tests.page_arch.test;

import org.junit.jupiter.api.BeforeEach;
import ru.raiffeisen.education.tests.page_arch.app.Application;

public class TestBase {

    public static ThreadLocal<Application> threadLocalApplications = new ThreadLocal<>();
    public Application app;

    @BeforeEach
    public void start() {

        if (threadLocalApplications.get() != null) {
            this.app = threadLocalApplications.get();
            return;
        }

        app = new Application();
        threadLocalApplications.set(app);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            app.quit();
            app = null;
        }));

    }
}
