package nitz.sbjr.project.naas.pojo;

/**
 * Created by sbjr on 11/16/16.
 */

public class UserType {

    private String userid;
    private String type;
    private String name;

    public UserType(String userid, String type, String name) {
        this.userid = userid;
        this.type = type;
        this.name = name;
    }

    public UserType() {

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
