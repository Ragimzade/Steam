package kaspersky.model;

import com.google.gson.annotations.Expose;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
