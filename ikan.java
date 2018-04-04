/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutualinformation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dama
 */
public class ikan {

    private String namaIkan;
    private int sisik, kumis;

    public ikan() {

    }

    public ikan(String namaIkan, int sisik, int kumis) {
        this.namaIkan = namaIkan;
        this.sisik = sisik;
        this.kumis = kumis;
    }

    public String getNamaIkan() {
        return namaIkan;
    }

    public void setNamaIkan(String namaIkan) {
        this.namaIkan = namaIkan;
    }

    public int getSisik() {
        return sisik;
    }

    public void setSisik(int sisik) {
        this.sisik = sisik;
    }

    public int getKumis() {
        return kumis;
    }

    public void setKumis(int kumis) {
        this.kumis = kumis;
    }

    public ArrayList bacaDataIkan() {
        ArrayList<ikan> fish = new ArrayList();
        FileInputStream inputData = null;

        int dataIkan;
        StringBuffer x = new StringBuffer();
        try {
            inputData = new FileInputStream
        ("d://kuliah/semester 6/pengenalan pola/3/dataIkanMI.txt");
            while ((dataIkan = inputData.read()) != -1) {
                x.append((char) dataIkan);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                inputData.close();
            } catch (IOException ex) {
                Logger.getLogger(MutualInformation.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

        String str = "";
        String[] str2 = null;
        int baris = 0;
        for (int i = 0; i < x.length(); i++) {
            if (("" + (x.charAt(i))).equals("\n")) {
                if (baris == 0) {
                    str = "";
                } else {
                    str2 = str.split(",");
                    str = "";
                    fish.add(new ikan(str2[0].toLowerCase(),
                            Integer.parseInt(str2[1]),
                            Integer.parseInt("" + str2[2].charAt(0))));
                }
                baris++;
            } else {
                str = str + "" + x.charAt(i);
            }
        }
        return fish;
    }

    public double mutualInformation(String ciri, String namaIkan) {
        double N11 = 0, N10 = 0, N01 = 0, N00 = 0;
        ArrayList<ikan> iwak = bacaDataIkan();
        for (int i = 0; i < iwak.size(); i++) {
            if (ciri.equalsIgnoreCase("sisik")) {
                if (iwak.get(i).getSisik() == 1 && iwak.get(i).
                        getNamaIkan().contains(namaIkan)) {
                    N11++;
                } if (iwak.get(i).getSisik() == 1 && !iwak.get(i).
                        getNamaIkan().contains(namaIkan)) {
                    N10++;
                } if (iwak.get(i).getSisik() == 0 && iwak.get(i).
                        getNamaIkan().contains(namaIkan)) {
                    N01++;
                } if (iwak.get(i).getSisik() == 0 && !iwak.get(i).
                        getNamaIkan().contains(namaIkan)) {
                    N00++;
                }
            } else {
                if (iwak.get(i).getKumis() == 1 && iwak.get(i).
                        getNamaIkan().contains(namaIkan)) {
                    N11++;
                }
                if (iwak.get(i).getKumis() == 1 && !iwak.get(i).
                        getNamaIkan().contains(namaIkan)) {
                    N10++;
                }
                if (iwak.get(i).getKumis() == 0 && iwak.get(i).
                        getNamaIkan().contains(namaIkan)) {
                    N01++;
                }
                if (iwak.get(i).getKumis() == 0 && !iwak.get(i).
                        getNamaIkan().contains(namaIkan)) {
                    N00++;
                }
            }
        }
//        double N = N11 + N10 + N01 + N00;
        double N = iwak.size();
        double N1Titik = N10 + N11, NTitik1 = N11 + N01, N0Titik = N01 + N00, 
                NTitik0 = N10 + N00;

        double nilaiMI =
                (N11 / N) * Math.log((N * N11) / (N1Titik * NTitik1)) / Math.log(2)
                + (N10 / N) * Math.log((N * N10) / (N1Titik * NTitik0)) / Math.log(2)
                + (N01 / N) * Math.log((N * N01) / (N0Titik * NTitik1)) / Math.log(2)
                + (N00 / N) * Math.log((N * N00) / (N0Titik * NTitik0)) / Math.log(2);

        System.out.println("N11\t= " + N11);
        System.out.println("N10\t= " + N10);
        System.out.println("N01\t= " + N01);
        System.out.println("N00\t= " + N00);
        System.out.println("N\t= " + N);
        System.out.println("N1.\t= " + N1Titik);
        System.out.println("N.1\t= " + NTitik1);
        System.out.println("N0.\t= " + N0Titik);
        System.out.println("N.0\t= " + NTitik0);
        return nilaiMI;
    }
}
