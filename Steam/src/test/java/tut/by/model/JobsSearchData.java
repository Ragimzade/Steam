package tut.by.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class JobsSearchData {
    @Expose
    private String search;
    @Expose
    private List<String> keyWords;
}
