package bg.tilchev.entity.base;

import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
public class BaseDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        BaseDomainObject other = (BaseDomainObject) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        }
        return this.id.equals(other.id);
    }
}
