/*
 * The MIT License
 *
 * Copyright 2018 J. Lucas Boatwright.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fastqutils;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author J. Lucas Boatwright
 */
public class FastqUtils {

    private static final Logger LOGGER = Logger.getLogger(FastqUtils.class.getName());

    /**
     * @param args FASTQ file name
     */
    public static void main(String[] args) {
        LOGGER.log(Level.INFO,"Started");
        try {
            int total = 0;
            double lengths = 0;
            String seq_id;

            // Read gzipped FASTQ file
            File file = new File(args[0]);
            FileInputStream fis = new FileInputStream(file);
            GZIPInputStream gis = new GZIPInputStream(fis);
            InputStreamReader isr = new InputStreamReader(gis);
            BufferedReader br = new BufferedReader(isr);

            // iterate entry
            while ((seq_id = br.readLine()) != null) {
                String seq = br.readLine();
                String plus_seq_id = br.readLine();
                String qual = br.readLine();
                total += 1;
                lengths += seq.length();

                /*
                Do something with the entry
                 */
            }// end while
            LOGGER.log(Level.INFO, String.format(
                    "%,d sequences with an average of %d bp reads",
                    total, Math.round(lengths / total)));
        } catch (IOException ex) {
            if (!args[0].endsWith("gz")) {
                LOGGER.log(Level.FINE,"File not gzipped.");
            } else if (!Files.exists(Paths.get(args[0]))) {
                LOGGER.log(Level.FINE,"File not found.");
            }
        }// end try-catch
        LOGGER.log(Level.INFO,"Ended");
    }// end psvm
}// end FastqUtils class
