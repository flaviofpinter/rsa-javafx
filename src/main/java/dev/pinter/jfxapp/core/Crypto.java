package dev.pinter.jfxapp.core;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crypto {
    int[] chaves;
    int[] tabelaPrimos = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101,
            103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
            179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277,
            281,
            283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401,
            409,
            419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523,
            541,
            547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653,
            659,
            661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797,
            809,
            811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887};

    public Crypto() {
        //this.chaves = keyGen()
    }

    private static int[] div(int e) {
        List<Integer> divisores = new ArrayList<>();
        for (int i = 1; i <= e + 1; i++) {
            if (e % i == 0) {
                divisores.add(i);
            }
        }
        return divisores.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] verifyDiv(int[] divE, int[] divTN){
        List<Integer> listaDivComum = new ArrayList<>();
        for(int i = 0; i <= divE.length - 1; i++){
            for(int k = 0;k <= divTN.length - 1; k++){
                if(divE[i] == divTN[k]){
                    listaDivComum.add(divE[i]);
                }
            }
        }
        return listaDivComum.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int getRandom(int[] array) {
        int rnd = new SecureRandom().nextInt(array.length);
        return array[rnd];
    }

    public int[] keyGen(){
        int p = getRandom(tabelaPrimos);
        int q = getRandom(tabelaPrimos);
        int t_n = (p - 1) * (q - 1);
        int n = p * q;

        boolean control = true;
        int e;
        while(control){
            e = new SecureRandom().nextInt(t_n);
            int[] divE = div(e);
            int[] divTN = div(n);
            int[] c = verifyDiv(divE, divTN);
            if(c.length == 1 && c[0] == 1){
                control = false;
            }
        }

        control = true;
        while (control){
            int d = new SecureRandom().nextInt(900000);
            //int mult = d * e;
        }


    }
}
