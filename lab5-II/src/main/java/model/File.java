package model;

/**
 * Created by claudiu on 21.05.2017.
 */
public class File {
    private int id;
    private String name;
    private String type;
    private long size;
    private String path;

    public File(int id, String name, String type, long size, String path) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.path = path;
    }

    public File(int id, String name, String type, long size) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
