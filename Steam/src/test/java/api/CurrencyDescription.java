package api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CurrencyDescription {
    @JsonProperty("Cur_ID")
    private String curID;
    @JsonProperty("Cur_ParentID")
    private String curParentID;
    @JsonProperty("Cur_Code")
    private String curCode;
    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;
    @JsonProperty("Cur_Name")
    private String curName;
    @JsonProperty("Cur_Name_Bel")
    private String curNameBel;
    @JsonProperty("Cur_Name_Eng")
    private String curNameEng;
    @JsonProperty("Cur_QuotName")
    private String curQuotName;
    @JsonProperty("Cur_QuotName_Bel")
    private String curQuotNameBel;
    @JsonProperty("Cur_QuotName_Eng")
    private String curQuotNameEng;
    @JsonProperty("Cur_NameMulti")
    private String curNameMulti;
    @JsonProperty("Cur_Name_BelMulti")
    private String curNameBelMulti;
    @JsonProperty("Cur_Name_EngMulti")
    private String curNameEngMulti;
    @JsonProperty("Cur_Scale")
    private String curScale;
    @JsonProperty("Cur_Periodicity")
    private String curPeriodicity;
    @JsonProperty("Cur_DateStart")
    private String curDateStart;
    @JsonProperty("Cur_DateEnd")
    private String curDateEnd;


}
