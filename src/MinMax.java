import java.util.Random;

public class MinMax {
    int depth;
    int best = 0;

    public MinMax(int depth){
        this.depth = depth;
    }


    public int min(int[] a){
        int val = 99999;
        int j = 0;
        int count = 0;
        for(int i = 1; i < a.length; i++){
            System.out.print(a[i] + " ");
            if(a[i] < val){
                val = a[i];
                j = i;
                count = 1;
            }else if(a[i] == val){
                count++;
            }
        }
        System.out.println();
        int[] mins = new int[count];
        Random r = new Random();
        int k = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] == val) {
                mins[k] = i;
                k++;
            }
        }
        return mins[r.nextInt(count)];
    }

    public int gerar(Game atual, int ordem){
            boolean[] poss = atual.movimentosPossiveis();
            int[] vals = new int[poss.length];
            for (int i = 1; i < poss.length; i++) {
                if (poss[i]) {
                    vals[i] = MIN_VALUE(atual.sucessor('O', i), depth - 1);
                } else {
                    vals[i] = 99999999;
                }
            }
            return min(vals);

    }

    public int MAX_VALUE(Game atual, int depth){
        if(depth == 0){
            return atual.utilidade();
        }else{
            int v = -99999;
            int k;
            for(int i = 1; i < 8; i++){
                boolean[] poss = atual.movimentosPossiveis();
                if(poss[i]) {
                    k = MIN_VALUE(atual.sucessor('O', i), depth-1) ;
                    if(k > v){
                        v = k;
                        best = i;
                    }
                }
            }
            return v;
        }
    }
    public int MIN_VALUE(Game atual, int depth){
        if(depth == 0){
            return atual.utilidade();
        }else{
            int v = 999999;
            int k;
            for(int i = 1; i < 8; i++){
                boolean[] poss = atual.movimentosPossiveis();
                if(poss[i]) {
                    k = MAX_VALUE(atual.sucessor('X', i), depth-1) ;
                    if(k <= v){
                        v = k;
                    }
                }
            }
            return v;
        }
    }
}
