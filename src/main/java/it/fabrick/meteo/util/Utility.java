package it.fabrick.meteo.util;

public final class Utility {
    private Utility() {

    }

    public static String converteString(String str) {
          return str.toUpperCase();
    }

    private String stringNew(String str) {
        String[] list = str.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        if (list.length > 1) {
            for (String st : list) {

                if (st.length() > 2) {
                    int pos = 0;
                    if (st.split("-").length >= 2) {


                        for (String st1 : st.split("-")) {

                            stringBuilder.append(st1.substring(0, 1).toUpperCase())
                                    .append(st1.substring(1).toLowerCase());
                            if (pos == 0) {
                                stringBuilder.append("-");
                                pos++;
                            }
                        }

                    } else {
                        if (pos > 0)
                            stringBuilder.append(" ")
                                    .append(st.substring(0, 1).toUpperCase())
                                    .append(st.substring(1).toLowerCase());
                    }

                } else {
                    stringBuilder.append("")
                            .append("");

                }
            }
        } else {
            stringBuilder.append(str.substring(0, 1).toUpperCase())
                    .append(str.substring(1).toLowerCase());
        }
        return null;
    }
}
