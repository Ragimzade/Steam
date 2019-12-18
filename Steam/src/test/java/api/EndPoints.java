package api;

import utils.DateUtil;

public final class EndPoints {
    public static final String USD_CURRENCY = "/Currencies/145";
    public static final String ALL_CURRENCIES = "/Currencies";
    public static final String CURRENCIES_FOR_PERIOD = "Rates/Dynamics/145?startDate=2019-1-1&endDate=2019-12-1";
    public static final String USD_CURRENCY_FOR_TODAY = String.format("Rates/143?onDate=%s", DateUtil.getCurrentDate());


}
