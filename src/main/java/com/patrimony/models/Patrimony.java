import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Id;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
@Entity(value="patrimonies", noClassnameStored=true)
public class Patrimony {

    @Id
    @XmlElement(name = "id")
    public ObjectId id;

    @Property("description")
    @XmlElement(name = "description")
    public String description;

    public User() {
    }
}
