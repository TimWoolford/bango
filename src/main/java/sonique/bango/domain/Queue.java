package sonique.bango.domain;

public class Queue {

    private Integer id;
    private String name;

    public Queue(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }
}