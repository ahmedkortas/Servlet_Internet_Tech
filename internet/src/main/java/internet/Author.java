package internet;

public class Author {
    private int id;
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Author(String name) {
        this.name = name;
    }

    public int setId() {
        return this.id;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}