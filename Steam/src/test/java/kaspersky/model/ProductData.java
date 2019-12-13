package kaspersky.model;

import com.google.gson.annotations.Expose;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductData {
    @Expose
    private String os;
    @Expose
    private String product;
    @Expose
    private String description;
    @Expose
    private String emailSubject;
    @Expose
    private String emailLink;

}
