package api.Pojo;

public class UserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    public String getName(){
        return name;
    }

    public String getJob(){
        return job;
    }

    public String getID(){
        return id;
    }

    public String getCreatedAt(){
        return createdAt;
    }
}
