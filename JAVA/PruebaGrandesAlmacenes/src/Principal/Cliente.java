package Principal;

import java.io.Serializable;

public class Cliente implements Serializable {
    private int id;

    public Cliente(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
