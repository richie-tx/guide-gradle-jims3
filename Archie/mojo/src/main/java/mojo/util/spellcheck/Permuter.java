package mojo.util.spellcheck;

import java.io.PrintStream;

class Permuter
{

    public Permuter(int i)
    {
        p1 = new int[1][];
        p2 = new int[2][];
        int j = factorial(i);
        permutations = new int[j][];
        if(i == 1)
            permutations[0] = (new int[] {
                1
            });
        else
        if(i == 2)
        {
            permutations[0] = (new int[] {
                1, 2
            });
            permutations[1] = (new int[] {
                2, 1
            });
        } else
        if(i == 3)
        {
            permutations[0] = (new int[] {
                1, 2, 3
            });
            permutations[1] = (new int[] {
                1, 3, 2
            });
            permutations[2] = (new int[] {
                3, 1, 2
            });
            permutations[3] = (new int[] {
                3, 2, 1
            });
            permutations[4] = (new int[] {
                2, 3, 1
            });
            permutations[5] = (new int[] {
                2, 1, 3
            });
        } else
        if(i == 4)
        {
            permutations[0] = (new int[] {
                1, 2, 3, 4
            });
            permutations[1] = (new int[] {
                1, 2, 4, 3
            });
            permutations[2] = (new int[] {
                1, 4, 2, 3
            });
            permutations[3] = (new int[] {
                4, 1, 2, 3
            });
            permutations[4] = (new int[] {
                4, 1, 3, 2
            });
            permutations[5] = (new int[] {
                1, 4, 3, 2
            });
            permutations[6] = (new int[] {
                1, 3, 4, 2
            });
            permutations[7] = (new int[] {
                1, 3, 2, 4
            });
            permutations[8] = (new int[] {
                3, 1, 2, 4
            });
            permutations[9] = (new int[] {
                3, 1, 4, 2
            });
            permutations[10] = (new int[] {
                3, 4, 1, 2
            });
            permutations[11] = (new int[] {
                4, 3, 1, 2
            });
            permutations[12] = (new int[] {
                4, 3, 2, 1
            });
            permutations[13] = (new int[] {
                3, 4, 2, 1
            });
            permutations[14] = (new int[] {
                3, 2, 4, 1
            });
            permutations[15] = (new int[] {
                3, 2, 1, 4
            });
            permutations[16] = (new int[] {
                2, 3, 1, 4
            });
            permutations[17] = (new int[] {
                2, 3, 4, 1
            });
            permutations[18] = (new int[] {
                2, 4, 3, 1
            });
            permutations[19] = (new int[] {
                4, 2, 3, 1
            });
            permutations[20] = (new int[] {
                4, 2, 1, 3
            });
            permutations[21] = (new int[] {
                2, 4, 1, 3
            });
            permutations[22] = (new int[] {
                2, 1, 4, 3
            });
            permutations[23] = (new int[] {
                2, 1, 3, 4
            });
        } else
        if(i == 5)
        {
            permutations[0] = (new int[] {
                1, 2, 3, 4, 5
            });
            permutations[1] = (new int[] {
                1, 2, 3, 5, 4
            });
            permutations[2] = (new int[] {
                1, 2, 5, 3, 4
            });
            permutations[3] = (new int[] {
                1, 5, 2, 3, 4
            });
            permutations[4] = (new int[] {
                5, 1, 2, 3, 4
            });
            permutations[5] = (new int[] {
                5, 1, 2, 4, 3
            });
            permutations[6] = (new int[] {
                1, 5, 2, 4, 3
            });
            permutations[7] = (new int[] {
                1, 2, 5, 4, 3
            });
            permutations[8] = (new int[] {
                1, 2, 4, 5, 3
            });
            permutations[9] = (new int[] {
                1, 2, 4, 3, 5
            });
            permutations[10] = (new int[] {
                1, 4, 2, 3, 5
            });
            permutations[11] = (new int[] {
                1, 4, 2, 5, 3
            });
            permutations[12] = (new int[] {
                1, 4, 5, 2, 3
            });
            permutations[13] = (new int[] {
                1, 5, 4, 2, 3
            });
            permutations[14] = (new int[] {
                5, 1, 4, 2, 3
            });
            permutations[15] = (new int[] {
                5, 4, 1, 2, 3
            });
            permutations[16] = (new int[] {
                4, 5, 1, 2, 3
            });
            permutations[17] = (new int[] {
                4, 1, 5, 2, 3
            });
            permutations[18] = (new int[] {
                4, 1, 2, 5, 3
            });
            permutations[19] = (new int[] {
                4, 1, 2, 3, 5
            });
            permutations[20] = (new int[] {
                4, 1, 3, 2, 5
            });
            permutations[21] = (new int[] {
                4, 1, 3, 5, 2
            });
            permutations[22] = (new int[] {
                4, 1, 5, 3, 2
            });
            permutations[23] = (new int[] {
                4, 5, 1, 3, 2
            });
            permutations[24] = (new int[] {
                5, 4, 1, 3, 2
            });
            permutations[25] = (new int[] {
                5, 1, 4, 3, 2
            });
            permutations[26] = (new int[] {
                1, 5, 4, 3, 2
            });
            permutations[27] = (new int[] {
                1, 4, 5, 3, 2
            });
            permutations[28] = (new int[] {
                1, 4, 3, 5, 2
            });
            permutations[29] = (new int[] {
                1, 4, 3, 2, 5
            });
            permutations[30] = (new int[] {
                1, 3, 4, 2, 5
            });
            permutations[31] = (new int[] {
                1, 3, 4, 5, 2
            });
            permutations[32] = (new int[] {
                1, 3, 5, 4, 2
            });
            permutations[33] = (new int[] {
                1, 5, 3, 4, 2
            });
            permutations[34] = (new int[] {
                5, 1, 3, 4, 2
            });
            permutations[35] = (new int[] {
                5, 1, 3, 2, 4
            });
            permutations[36] = (new int[] {
                1, 5, 3, 2, 4
            });
            permutations[37] = (new int[] {
                1, 3, 5, 2, 4
            });
            permutations[38] = (new int[] {
                1, 3, 2, 5, 4
            });
            permutations[39] = (new int[] {
                1, 3, 2, 4, 5
            });
            permutations[40] = (new int[] {
                3, 1, 2, 4, 5
            });
            permutations[41] = (new int[] {
                3, 1, 2, 5, 4
            });
            permutations[42] = (new int[] {
                3, 1, 5, 2, 4
            });
            permutations[43] = (new int[] {
                3, 5, 1, 2, 4
            });
            permutations[44] = (new int[] {
                5, 3, 1, 2, 4
            });
            permutations[45] = (new int[] {
                5, 3, 1, 4, 2
            });
            permutations[46] = (new int[] {
                3, 5, 1, 4, 2
            });
            permutations[47] = (new int[] {
                3, 1, 5, 4, 2
            });
            permutations[48] = (new int[] {
                3, 1, 4, 5, 2
            });
            permutations[49] = (new int[] {
                3, 1, 4, 2, 5
            });
            permutations[50] = (new int[] {
                3, 4, 1, 2, 5
            });
            permutations[51] = (new int[] {
                3, 4, 1, 5, 2
            });
            permutations[52] = (new int[] {
                3, 4, 5, 1, 2
            });
            permutations[53] = (new int[] {
                3, 5, 4, 1, 2
            });
            permutations[54] = (new int[] {
                5, 3, 4, 1, 2
            });
            permutations[55] = (new int[] {
                5, 4, 3, 1, 2
            });
            permutations[56] = (new int[] {
                4, 5, 3, 1, 2
            });
            permutations[57] = (new int[] {
                4, 3, 5, 1, 2
            });
            permutations[58] = (new int[] {
                4, 3, 1, 5, 2
            });
            permutations[59] = (new int[] {
                4, 3, 1, 2, 5
            });
            permutations[60] = (new int[] {
                4, 3, 2, 1, 5
            });
            permutations[61] = (new int[] {
                4, 3, 2, 5, 1
            });
            permutations[62] = (new int[] {
                4, 3, 5, 2, 1
            });
            permutations[63] = (new int[] {
                4, 5, 3, 2, 1
            });
            permutations[64] = (new int[] {
                5, 4, 3, 2, 1
            });
            permutations[65] = (new int[] {
                5, 3, 4, 2, 1
            });
            permutations[66] = (new int[] {
                3, 5, 4, 2, 1
            });
            permutations[67] = (new int[] {
                3, 4, 5, 2, 1
            });
            permutations[68] = (new int[] {
                3, 4, 2, 5, 1
            });
            permutations[69] = (new int[] {
                3, 4, 2, 1, 5
            });
            permutations[70] = (new int[] {
                3, 2, 4, 1, 5
            });
            permutations[71] = (new int[] {
                3, 2, 4, 5, 1
            });
            permutations[72] = (new int[] {
                3, 2, 5, 4, 1
            });
            permutations[73] = (new int[] {
                3, 5, 2, 4, 1
            });
            permutations[74] = (new int[] {
                5, 3, 2, 4, 1
            });
            permutations[75] = (new int[] {
                5, 3, 2, 1, 4
            });
            permutations[76] = (new int[] {
                3, 5, 2, 1, 4
            });
            permutations[77] = (new int[] {
                3, 2, 5, 1, 4
            });
            permutations[78] = (new int[] {
                3, 2, 1, 5, 4
            });
            permutations[79] = (new int[] {
                3, 2, 1, 4, 5
            });
            permutations[80] = (new int[] {
                2, 3, 1, 4, 5
            });
            permutations[81] = (new int[] {
                2, 3, 1, 5, 4
            });
            permutations[82] = (new int[] {
                2, 3, 5, 1, 4
            });
            permutations[83] = (new int[] {
                2, 5, 3, 1, 4
            });
            permutations[84] = (new int[] {
                5, 2, 3, 1, 4
            });
            permutations[85] = (new int[] {
                5, 2, 3, 4, 1
            });
            permutations[86] = (new int[] {
                2, 5, 3, 4, 1
            });
            permutations[87] = (new int[] {
                2, 3, 5, 4, 1
            });
            permutations[88] = (new int[] {
                2, 3, 4, 5, 1
            });
            permutations[89] = (new int[] {
                2, 3, 4, 1, 5
            });
            permutations[90] = (new int[] {
                2, 4, 3, 1, 5
            });
            permutations[91] = (new int[] {
                2, 4, 3, 5, 1
            });
            permutations[92] = (new int[] {
                2, 4, 5, 3, 1
            });
            permutations[93] = (new int[] {
                2, 5, 4, 3, 1
            });
            permutations[94] = (new int[] {
                5, 2, 4, 3, 1
            });
            permutations[95] = (new int[] {
                5, 4, 2, 3, 1
            });
            permutations[96] = (new int[] {
                4, 5, 2, 3, 1
            });
            permutations[97] = (new int[] {
                4, 2, 5, 3, 1
            });
            permutations[98] = (new int[] {
                4, 2, 3, 5, 1
            });
            permutations[99] = (new int[] {
                4, 2, 3, 1, 5
            });
            permutations[100] = (new int[] {
                4, 2, 1, 3, 5
            });
            permutations[101] = (new int[] {
                4, 2, 1, 5, 3
            });
            permutations[102] = (new int[] {
                4, 2, 5, 1, 3
            });
            permutations[103] = (new int[] {
                4, 5, 2, 1, 3
            });
            permutations[104] = (new int[] {
                5, 4, 2, 1, 3
            });
            permutations[105] = (new int[] {
                5, 2, 4, 1, 3
            });
            permutations[106] = (new int[] {
                2, 5, 4, 1, 3
            });
            permutations[107] = (new int[] {
                2, 4, 5, 1, 3
            });
            permutations[108] = (new int[] {
                2, 4, 1, 5, 3
            });
            permutations[109] = (new int[] {
                2, 4, 1, 3, 5
            });
            permutations[110] = (new int[] {
                2, 1, 4, 3, 5
            });
            permutations[111] = (new int[] {
                2, 1, 4, 5, 3
            });
            permutations[112] = (new int[] {
                2, 1, 5, 4, 3
            });
            permutations[113] = (new int[] {
                2, 5, 1, 4, 3
            });
            permutations[114] = (new int[] {
                5, 2, 1, 4, 3
            });
            permutations[115] = (new int[] {
                5, 2, 1, 3, 4
            });
            permutations[116] = (new int[] {
                2, 5, 1, 3, 4
            });
            permutations[117] = (new int[] {
                2, 1, 5, 3, 4
            });
            permutations[118] = (new int[] {
                2, 1, 3, 5, 4
            });
            permutations[119] = (new int[] {
                2, 1, 3, 4, 5
            });
        } else
        {
            ptr = 0;
            p = new int[i + 1];
            pi = new int[i + 1];
            dir = new int[i + 1];
            NN = i;
            for(int k = 1; k <= i; k++)
            {
                dir[k] = -1;
                p[k] = k;
                pi[k] = k;
            }

            Perm(1);
        }
    }

    void Move(int i, int j)
    {
        int k = p[pi[i] + j];
        p[pi[i]] = k;
        p[pi[i] + j] = i;
        pi[k] = pi[i];
        pi[i] = pi[i] + j;
    }

    void Perm(int i)
    {
        if(i > NN)
        {
            RecordPerm();
        } else
        {
            Perm(i + 1);
            for(int j = 1; j <= i - 1; j++)
            {
                Move(i, dir[i]);
                Perm(i + 1);
            }

            dir[i] = -dir[i];
        }
    }

    void PrintPerm()
    {
        for(int i = 1; i <= NN; i++)
            System.out.println(p[i]);

        System.out.println();
    }

    void RecordPerm()
    {
        permutations[ptr] = new int[NN];
        for(int i = 0; i < NN; i++)
            permutations[ptr][i] = p[i + 1];

        ptr++;
    }

    int factorial(int i)
    {
        int j = 1;
        for(int k = i; k > 1; k--)
            j *= k;

        return j;
    }

    public int getNumberOfPermutations()
    {
        return permutations.length;
    }

    public int[] getPermutation(int i)
    {
        if(i < permutations.length)
            return permutations[i];
        else
            return permutations[permutations.length - 1];
    }

    public static void main(String args[])
    {
        System.out.println("n=4 ");
        byte byte0 = 4;
        System.out.println("\n");
        Permuter permuter = new Permuter(byte0);
        for(int i = 0; i < permuter.getNumberOfPermutations(); i++)
        {
            for(int j = 0; j < byte0; j++)
                System.out.println(permuter.getPermutation(i)[j]);

            System.out.println("\n");
        }

    }

    int NN;
    int p[];
    int pi[];
    int dir[];
    int permutations[][];
    int ptr;
    int p1[][];
    int p2[][];
}
