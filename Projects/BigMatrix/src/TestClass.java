package Projects.BigMatrix.src;
public class TestClass
{
	public static void main(String[] args) {
        BigMatrix bm0 = new BigMatrix();
        BigMatrix bm1 = new BigMatrix();
        BigMatrix bm2 = new BigMatrix();
        /*
        bm0.setValue(1, 1, 1);
        bm0.setValue(1, 2, 2);
        bm0.setValue(2, 1, 1);
        bm0.setValue(2, 2, 2);

        bm1.setValue(1, 1, 1);
        bm1.setValue(1, 2, 2);
        bm1.setValue(2, 1, 1);
        bm1.setValue(2, 2, 2);

        System.out.println(bm0.addMatrix(bm1));*/
        bm0.setValue(0, 0, 1);
        bm0.setValue(1000, 10, 2);
        bm0.setValue(10, 1000, 3);
        bm0.setValue(0, 1000, 4);
        bm0.setValue(1000, 0, 5);
        bm0.setValue(0, 10, 6);
        bm0.setValue(10, 0, 7);
        bm0.setValue(10, 1000, 0);
        bm0.setValue(10, 0, 0);
        bm0.setValue(1000, 10, 0);
        bm0.setValue(0, 10, 0);

        bm1.setValue(0, 0, 1);
        bm1.setValue(1000, 10, 2);
        bm1.setValue(10, 1000, 3);
        bm1.setValue(0, 1000, 4);
        bm1.setValue(1000, 0, 5);
        bm1.setValue(0, 10, 6);
        bm1.setValue(10, 0, 7);

       bm2.setValue(0, 0, 1);
       bm2.setValue(10000, 100, 2);
       bm2.setValue(100, 10000, 3);
       bm2.setValue(0, 10000, 4);
       bm2.setValue(10000, 0, 5);
       bm2.setValue(0, 100, 6);
       bm2.setValue(100, 0, 7);

        System.out.println(bm0.addMatrix(bm1).addMatrix(bm2));
	}
}