
import java.io.Serializable;


/**
 *
 * @author 886846
 */
public class Client implements Serializable {
    
    private String name;
    private long id;

    public Client(String name, long id) {
        this.name = name;
        this.id = id;
    }
    
    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
}
