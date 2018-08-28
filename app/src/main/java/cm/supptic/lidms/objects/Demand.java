package cm.supptic.lidms.objects;

/**
 * Created by codename-tkc on 23/07/2018.
 */

public class Demand {
    public String name;
    public String percentage;
    public String title;
    public int  day;
    public String due;
    public int status;
    public String comment;

    public Demand(String name, String percentage, String title, int day, String due, int status,String comment) {
        this.name = name;
        this.percentage = percentage;
        this.title = title;
        this.day = day;
        this.due = due;
        this.status = status;
        this.comment =  comment;
    }
}
