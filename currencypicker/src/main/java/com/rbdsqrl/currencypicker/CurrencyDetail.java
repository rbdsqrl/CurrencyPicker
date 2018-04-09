package com.rbdsqrl.currencypicker;

import android.content.Context;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CurrencyDetail {
    public static final CurrencyDetail[] CURRENCIES = {
            new CurrencyDetail("EUR", "Euro", "€", R.drawable.flag_eur),
            new CurrencyDetail("USD", "United States Dollar", "$", R.drawable.flag_usd),
            new CurrencyDetail("GBP", "British Pound", "£", R.drawable.flag_gbp),
            new CurrencyDetail("CZK", "Czech Koruna", "Kč", R.drawable.flag_czk),
            new CurrencyDetail("TRY", "Turkish Lira", "₺", R.drawable.flag_try),
            new CurrencyDetail("AED", "Emirati Dirham", "د.إ", R.drawable.flag_aed),
            new CurrencyDetail("AFN", "Afghanistan Afghani", "؋", R.drawable.flag_afn),
            new CurrencyDetail("ARS", "Argentine Peso", "$", R.drawable.flag_ars),
            new CurrencyDetail("AUD", "Australian Dollar", "$", R.drawable.flag_aud),
            new CurrencyDetail("BBD", "Barbados Dollar", "$", R.drawable.flag_bbd),
            new CurrencyDetail("BDT", "Bangladeshi Taka", " Tk", R.drawable.flag_bdt),
            new CurrencyDetail("BGN", "Bulgarian Lev", "лв", R.drawable.flag_bgn),
            new CurrencyDetail("BHD", "Bahraini Dinar", "BD", R.drawable.flag_bhd),
            new CurrencyDetail("BMD", "Bermuda Dollar","$", R.drawable.flag_bmd),
            new CurrencyDetail("BND", "Brunei Darussalam Dollar","$", R.drawable.flag_bnd),
            new CurrencyDetail("BOB", "Bolivia Bolíviano","$b", R.drawable.flag_bob),
            new CurrencyDetail("BRL", "Brazil Real","R$", R.drawable.flag_brl),
            new CurrencyDetail("BTN", "Bhutanese Ngultrum","Nu.", R.drawable.flag_btn),
            new CurrencyDetail("BZD", "Belize Dollar","BZ$", R.drawable.flag_bzd),
            new CurrencyDetail("CAD", "Canada Dollar","$", R.drawable.flag_cad),
            new CurrencyDetail("CHF", "Switzerland Franc","CHF", R.drawable.flag_chf),
            new CurrencyDetail("CLP", "Chile Peso","$", R.drawable.flag_clp),
            new CurrencyDetail("CNY", "China Yuan Renminbi","¥", R.drawable.flag_cny),
            new CurrencyDetail("COP", "Colombia Peso","$", R.drawable.flag_cop),
            new CurrencyDetail("CRC", "Costa Rica Colon","₡", R.drawable.flag_crc),
            new CurrencyDetail("DKK", "Denmark Krone","kr", R.drawable.flag_dkk),
            new CurrencyDetail("DOP", "Dominican Republic Peso","RD$", R.drawable.flag_dop),
            new CurrencyDetail("EGP", "Egypt Pound","£", R.drawable.flag_egp),
            new CurrencyDetail("ETB", "Ethiopian Birr","Br", R.drawable.flag_etb),
            new CurrencyDetail("GEL", "Georgian Lari","₾", R.drawable.flag_gel),
            new CurrencyDetail("GHS", "Ghana Cedi","¢", R.drawable.flag_ghs),
            new CurrencyDetail("GMD", "Gambian dalasi","D", R.drawable.flag_gmd),
            new CurrencyDetail("GYD", "Guyana Dollar","$", R.drawable.flag_gyd),
            new CurrencyDetail("HKD", "Hong Kong Dollar","$", R.drawable.flag_hkd),
            new CurrencyDetail("HRK", "Croatia Kuna","kn", R.drawable.flag_hrk),
            new CurrencyDetail("HUF", "Hungary Forint","Ft", R.drawable.flag_huf),
            new CurrencyDetail("IDR", "Indonesia Rupiah","Rp", R.drawable.flag_idr),
            new CurrencyDetail("ILS", "Israel Shekel","₪", R.drawable.flag_ils),
            new CurrencyDetail("INR", "Indian Rupee","₹", R.drawable.flag_inr),
            new CurrencyDetail("ISK", "Iceland Krona","kr", R.drawable.flag_isk),
            new CurrencyDetail("JMD", "Jamaica Dollar","J$", R.drawable.flag_jmd),
            new CurrencyDetail("JPY", "Japanese Yen","¥", R.drawable.flag_jpy),
            new CurrencyDetail("KES", "Kenyan Shilling","KSh", R.drawable.flag_kes),
            new CurrencyDetail("KRW", "Korea (South) Won","₩", R.drawable.flag_krw),
            new CurrencyDetail("KWD", "Kuwaiti Dinar","د.ك", R.drawable.flag_kwd),
            new CurrencyDetail("KYD", "Cayman Islands Dollar","$", R.drawable.flag_kyd),
            new CurrencyDetail("KZT", "Kazakhstan Tenge","лв", R.drawable.flag_kzt),
            new CurrencyDetail("LAK", "Laos Kip","₭", R.drawable.flag_lak),
            new CurrencyDetail("LKR", "Sri Lanka Rupee","₨", R.drawable.flag_lkr),
            new CurrencyDetail("LRD", "Liberia Dollar","$", R.drawable.flag_lrd),
            new CurrencyDetail("LTL", "Lithuanian Litas","Lt", R.drawable.flag_ltl),
            new CurrencyDetail("MAD", "Moroccan Dirham","MAD", R.drawable.flag_mad),
            new CurrencyDetail("MDL", "Moldovan Leu","MDL", R.drawable.flag_mdl),
            new CurrencyDetail("MKD", "Macedonia Denar","ден", R.drawable.flag_mkd),
            new CurrencyDetail("MNT", "Mongolia Tughrik","₮", R.drawable.flag_mnt),
            new CurrencyDetail("MUR", "Mauritius Rupee","₨", R.drawable.flag_mur),
            new CurrencyDetail("MWK", "Malawian Kwacha","MK", R.drawable.flag_mwk),
            new CurrencyDetail("MXN", "Mexico Peso","$", R.drawable.flag_mxn),
            new CurrencyDetail("MYR", "Malaysia Ringgit","RM", R.drawable.flag_myr),
            new CurrencyDetail("MZN", "Mozambique Metical","MT", R.drawable.flag_mzn),
            new CurrencyDetail("NAD", "Namibia Dollar","$", R.drawable.flag_nad),
            new CurrencyDetail("NGN", "Nigeria Naira","₦", R.drawable.flag_ngn),
            new CurrencyDetail("NIO", "Nicaragua Cordoba","C$", R.drawable.flag_nio),
            new CurrencyDetail("NOK", "Norway Krone","kr", R.drawable.flag_nok),
            new CurrencyDetail("NPR", "Nepal Rupee","₨", R.drawable.flag_npr),
            new CurrencyDetail("NZD", "New Zealand Dollar","$", R.drawable.flag_nzd),
            new CurrencyDetail("OMR", "Oman Rial","﷼", R.drawable.flag_omr),
            new CurrencyDetail("PEN", "Peru Sol","S/.", R.drawable.flag_pen),
            new CurrencyDetail("PGK", "Papua New Guinean Kina","K", R.drawable.flag_pgk),
            new CurrencyDetail("PHP", "Philippines Peso","₱", R.drawable.flag_php),
            new CurrencyDetail("PKR", "Pakistan Rupee","₨", R.drawable.flag_pkr),
            new CurrencyDetail("PLN", "Poland Zloty","zł", R.drawable.flag_pln),
            new CurrencyDetail("PYG", "Paraguay Guarani","Gs", R.drawable.flag_pyg),
            new CurrencyDetail("QAR", "Qatar Riyal","﷼", R.drawable.flag_qar),
            new CurrencyDetail("RON", "Romania Leu","lei", R.drawable.flag_ron),
            new CurrencyDetail("RSD", "Serbia Dinar","Дин.", R.drawable.flag_rsd),
            new CurrencyDetail("RUB", "Russia Ruble","₽", R.drawable.flag_rub),
            new CurrencyDetail("SAR", "Saudi Arabia Riyal","﷼", R.drawable.flag_sar),
            new CurrencyDetail("SEK", "Sweden Krona","kr", R.drawable.flag_sek),
            new CurrencyDetail("SGD", "Singapore Dollar","$", R.drawable.flag_sgd),
            new CurrencyDetail("SOS", "Somalia Shilling","S", R.drawable.flag_sos),
            new CurrencyDetail("SRD", "Suriname Dollar","$", R.drawable.flag_srd),
            new CurrencyDetail("THB", "Thailand Baht","฿", R.drawable.flag_thb),
            new CurrencyDetail("TTD", "Trinidad and Tobago Dollar","TT$", R.drawable.flag_ttd),
            new CurrencyDetail("TWD", "Taiwan New Dollar","NT$", R.drawable.flag_twd),
            new CurrencyDetail("TZS", "Tanzanian Shilling","TSh", R.drawable.flag_tzs),
            new CurrencyDetail("UAH", "Ukraine Hryvnia","₴", R.drawable.flag_uah),
            new CurrencyDetail("UGX", "Ugandan Shilling","USh", R.drawable.flag_ugx),
            new CurrencyDetail("UYU", "Uruguay Peso","$U", R.drawable.flag_uyu),
            new CurrencyDetail("VEF", "Venezuela Bolívar","Bs", R.drawable.flag_vef),
            new CurrencyDetail("VND", "Viet Nam Dong","₫", R.drawable.flag_vnd),
            new CurrencyDetail("YER", "Yemen Rial","﷼", R.drawable.flag_yer),
            new CurrencyDetail("ZAR", "South Africa Rand","R", R.drawable.flag_zar)
    };
    private String code;
    private String name;
    private String symbol;
    private int flag = -1;

    public CurrencyDetail(String code, String name, String symbol, int flag) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
        this.flag = flag;
    }

    public CurrencyDetail() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void loadFlagByCode(Context context) {
        if (this.flag != -1)
            return;

        try {
            this.flag = context.getResources()
                    .getIdentifier("flag_" + this.code.toLowerCase(), "drawable",
                            context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            this.flag = -1;
        }
    }

    /*
     *      GENERIC STATIC FUNCTIONS
     */

    private static List<CurrencyDetail> allCurrenciesList;

    public static List<CurrencyDetail> getAllCurrencies() {
        if (allCurrenciesList == null) {
            allCurrenciesList = Arrays.asList(CURRENCIES);
        }
        return allCurrenciesList;
    }

    public static CurrencyDetail getCurrencyByISO(String currencyIsoCode) {
        // Because the data we have is sorted by ISO codes and not by names, we must check all
        // currencies one by one

        for (CurrencyDetail c : CURRENCIES) {
            if (currencyIsoCode.equals(c.getCode())) {
                return c;
            }
        }
        return null;
    }

    public static CurrencyDetail getCurrencyByName(String currencyName) {
        // Because the data we have is sorted by ISO codes and not by names, we must check all
        // currencies one by one

        for (CurrencyDetail c : CURRENCIES) {
            if (currencyName.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    /*
     * COMPARATORS
     */

    public static class ISOCodeComparator implements Comparator<CurrencyDetail> {
        @Override
        public int compare(CurrencyDetail currency, CurrencyDetail t1) {
            return currency.code.compareTo(t1.code);
        }
    }


    public static class NameComparator implements Comparator<CurrencyDetail> {
        @Override
        public int compare(CurrencyDetail currency, CurrencyDetail t1) {
            return currency.name.compareTo(t1.name);
        }
    }
}
