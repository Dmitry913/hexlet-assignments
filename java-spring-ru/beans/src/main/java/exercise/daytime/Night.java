package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void logMassageInit() {
        System.out.println("Бин был создан");
    }

    @PreDestroy
    public void logMassageDelete() {
        System.out.println("Бин был удалён");
    }
    // END
}
