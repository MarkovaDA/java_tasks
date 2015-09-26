package sample.model;

/**
 * Created by 803581 on 22.09.2015.
 */
public class Model {
    private int what_row;
    public  Model()
    {
        //пустой конструктор
    }
    public void Analize()
    {
        //возвратит индексы элементов, которые нужно закрасить
    }
    //проверяем, есть ли в ряду одинокавые символы
    public int sameIntoRow(int[][] matrix)
    {
        for(int i=0; i< 3; i++)
        {   int count = 0;
            for(int j=0; j<2; j++)
            {
                if (matrix[i][j]> 0 && matrix[i][j] == matrix[i][j+1])
                    ++count;
            }
            if (count == 2)//в каком-либо ряду есть одинаковые символы
            {
               return i;
            }
            count = 0;
        }
        return -1;
    }
    //не работает
    public int sameIntoColumn(int[][] matrix)
    {
        for(int i=0; i < 3; i++) //бежим по столбцам
        {   int count = 0;
            for(int j=0; j<2; j++) //бежим по строком
            {
                if (matrix[j][i]>0 && matrix[j][i] == matrix[j+1][i])
                    ++count;
            }
            if (count == 2)//в каком-либо ряду есть одинаковые символы
            {
                return i;
            }
            count = 0;
        }
        return -1;
    }
    public int sameIntoDiag(int[][]matrix)
    {   int count = 0;
        //мы можем получить 1 или -1
        int i;
        for(i=0; i<2; i++)
        {
            if (matrix[i][i]>0 && matrix[i][i] == matrix[i+1][i+1])
                ++count;
        }
        if (count == 2)
            return 0;

        if (matrix[2][0] > 0 && matrix[2][0]== matrix[1][1] && matrix[1][1] == matrix[0][2])
            return 2;
        return -1;
    }

}
