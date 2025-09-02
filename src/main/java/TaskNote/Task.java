package TaskNote;

public class Task {
    private String title;
    private String description;
    private boolean done;

    public Task(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isDone() { return done; }
}
