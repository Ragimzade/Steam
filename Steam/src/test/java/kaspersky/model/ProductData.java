package kaspersky.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ProductData {
    @Expose
    private String osName;
    @Expose
    private String product;
    @Expose
    private String description;
    @Expose
    private String emailSubject;
    @Expose
    private String emailLink;

}
