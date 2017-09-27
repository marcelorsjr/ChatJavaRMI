import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 886846
 */
public class Message implements Serializable{
    private long id;
    private Client sender;
    private Date dateTime;
    private String text;

    public Message(long id, Client sender, Date dateTime, String text) {
        this.id = id;
        this.sender = sender;
        this.dateTime = dateTime;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getSender() {
        return sender;
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
