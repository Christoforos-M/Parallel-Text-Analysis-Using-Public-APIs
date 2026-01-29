package com.mycompany.question3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

public class Question3 {

    static final int threads = 4;  // n νηματα
    static final int kliseis = 20;   // k κλησεις

    
    static long sinoloLekseon = 0;
    static long sinoloGramaton = 0;
    static long[] sixnotita = new long[26];

    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(threads);

        List<Future<String>> apotelesma = new ArrayList<>();

        // Κάνουμε k κλήσεις
        for (int i = 0; i < kliseis; i++) {
            apotelesma.add(pool.submit(() -> epistrofiKeimenou("http://metaphorpsum.com/paragraphs/10")));
        }

        // Περιμένουμε όλα να τελειώσουν
        for (Future<String> f : apotelesma) {
            epeksergasia(f.get());
        }

        pool.shutdown();

        // Μεσο μηκος λεξεων
        double avgMikos = (double) sinoloGramaton / sinoloLekseon;

        // Συχνοτητα γραμματων
        long grammata = 0;
        for (long l : sixnotita) grammata += l;

        double[] pososto = new double[26];
        for (int i = 0; i < 26; i++) {
            if (grammata > 0)
                pososto[i] = (sixnotita[i] * 100.0) / grammata;
        }

        // Εκτυπωση αποτελεσματων
        System.out.println("Μέσο μήκος λέξεων: " + avgMikos);

        System.out.println("Ποσοστό γραμμάτων a–z:");
        for (int i = 0; i < 26; i++) {
            System.out.printf("%c: %.2f%%\n", (char)('a' + i), pososto[i]);
        }
    }

    // Συναρτηση που καλει το URL και επιστρεφει το κειμενο
    static String epistrofiKeimenou(String urlStr) {
        try {
            URL u = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                sb.append(line).append(" ");
            }

            in.close();
            con.disconnect();
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // Συναρτηση που μαζευει τα στατιστικα
    static synchronized void epeksergasia(String text) {

        // Υπολογισμος λεξεων
        String[] lekseis = text.trim().split("\\s+");
        sinoloLekseon += lekseis.length;

        // Συνολο χαρακτηρων λεξεων
        for (String w : lekseis) {
            sinoloGramaton += w.length();
        }

        // Υπολογισμος γραμματων a–z
        text = text.toLowerCase();
        for (char c : text.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                sixnotita[c - 'a']++;
            }
        }
    }
}


