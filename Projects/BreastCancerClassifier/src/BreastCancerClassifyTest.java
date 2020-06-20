package Projects.BreastCancerClassifier.src;

import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.Arrays;

public class BreastCancerClassifyTest {

    @Test
    public void testCalculateDistance() {
        double tolerance = 0.0001;
        int[] first1D = {1247, 5, 2};
        int[] second1D = {673, 11, 4};
        double dist1D = 6.0;
        double studentAnswer1D = BreastCancerClassify.calculateDistance(first1D, second1D);
        assertEquals("Fails for distance between 2 points in 1-D space. Did you disregard the " +
                "Patient ID and Classification Label?", dist1D, studentAnswer1D, tolerance);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int[] first2D = {1234567, 0, 0, 4};
        int[] second2D = {7654321, 3, 4, 4};
        double dist2D = Math.sqrt(25);
        double studentAnswer2D = BreastCancerClassify.calculateDistance(first2D, second2D);
        assertEquals("Fails for distance between 2 points in 2-D plane. Did you disregard the " +
                "Patient ID and Classification Label?", dist2D, studentAnswer2D, tolerance);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int[] first3D = {0, 0, 0, 0, 0};
        int[] second3D = {7, 7, 7, 7, 8};
        double dist3D = 0.0;
        for (int i = 1; i < first3D.length - 1; i++){
            dist3D += Math.pow(second3D[i], 2);
        }
        dist3D = Math.sqrt(dist3D);
        double studentAnswer3D = BreastCancerClassify.calculateDistance(first3D, second3D);
        assertEquals("Fails for distance between 2 points in a 3-D plane.", dist3D, studentAnswer3D, tolerance);
    }

    @Test
    public void testGetAllDistances() {
        int[][] trainData = {{00, 1, 2}, {01, 2, 2}, {02, 3, 4}, {03, 5, 2}, {04, 7, 4}, {05, 10, 4}};
        int[] testInstance = {06, 25, 4};
        double[] expected_answer = {24.0, 23.0, 22.0, 20.0, 18.0, 15.0};
        double[] student_answer = BreastCancerClassify.getAllDistances(trainData, testInstance);
        assertEquals("Output array does not match length of the training data input array.",
                trainData.length, student_answer.length);
        assertArrayEquals("Does not contain the correct values in the output array for the 1D case.",
                expected_answer, student_answer, 0.00001);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int[][] trainData2D = {{000, 0, 0, 2}, {001, 1, 1, 2}, {002, 5, 5, 2}, {003, 10, 10, 2}};
        int[] testInstance2D = {004, 13, 15, 4};
        double[] expected_answer2D = {Math.sqrt(169+225), Math.sqrt(144+196), Math.sqrt(64+100), Math.sqrt(9+25)};
        double[] student_answer2D = BreastCancerClassify.getAllDistances(trainData2D, testInstance2D);
        assertEquals("Output array does not match length of the training data input array.",
                trainData2D.length, student_answer2D.length);
        assertArrayEquals("Does not contain the correct values in the output array for the 2D case.",
                expected_answer2D, student_answer2D, 0.00001);
    }

    @Test
    public void testFindKClosestEntries() {
        // TO DO: TEST FOR IMPORTS OF ARRAYS AND ARRAYLIST CLASS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        double[] distances01 = {0.0, 0.1, 0.2, 0.3, 0.4};
        int[] expectedKClosestIndexes = {0, 1, 2, 3, 4};
        int[] studentKClosestIndexes = BreastCancerClassify.findKClosestEntries(distances01);
        // sort for comparision
        Arrays.sort(studentKClosestIndexes);
        assertEquals("Your returned array does not contain K elements.",
                expectedKClosestIndexes.length, studentKClosestIndexes.length);
        assertArrayEquals("Your returned array is not equal to the expected answer. The returned array should"
                        + " be the indices of the K-closest distances.",
                expectedKClosestIndexes, studentKClosestIndexes);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        double[] distances02 = {100.0, Double.MAX_VALUE, Math.PI, 0.00001, 0.0001, 0.001, 0.01, 0.1};
        int[] expectedKClosestIndexes2 = {3, 4, 5, 6, 7};
        int[] studentKClosestIndexes2 = BreastCancerClassify.findKClosestEntries(distances02);
        // sort for comparision
        Arrays.sort(studentKClosestIndexes2);
        assertEquals("Your returned array does not contain K elements.",
                expectedKClosestIndexes2.length, studentKClosestIndexes2.length);
        assertArrayEquals("Your returned array is not equal to the expected answer. The returned array should"
                        + " be the indices of the K-closest distances.",
                expectedKClosestIndexes2, studentKClosestIndexes2);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
         *This test case tests that the student did not hard-code in a value for K, but rather uses the class variable.
         */
        try {
            Class c = BreastCancerClassify.class;
            Field k_constant = c.getDeclaredField("K");
            k_constant.setAccessible(true);
            k_constant.setInt(k_constant, 3);
            double[] distances03 = {5.7, 0.2, 5.8, 0.25, 5.9, 0.1, 6.4, 0.9, 7.8, 0.11};
            int[] expectedKClosestIndexes3 = {1, 5, 9};
            int[] studentKClosestIndexes3 = BreastCancerClassify.findKClosestEntries(distances03);
            // sort for comparision
            Arrays.sort(studentKClosestIndexes3);
            assertEquals("Your returned array does not contain K elements when K was changed.",
                    expectedKClosestIndexes3.length, studentKClosestIndexes3.length);
            assertArrayEquals("Your returned array is not equal to the expected answer." +
                            "The returned array should be the indices of the closest distances.",
                    expectedKClosestIndexes3, studentKClosestIndexes3);
        }
        catch (Exception e){
            e.printStackTrace();
            fail("Missing class field K. Did you delete or modify it?");
        }
    }

    @Test
    public void testClassify() {
        // Test case for a majority of benign
        int[][] trainDataBenign = {{00, 1, 2}, {01, 2, 2}, {02, 3, 4}, {03, 5, 2}, {04, 7, 4}, {05, 10, 2}, {06, 18, 4}};
        int[] kClosestIndicesBenign = {4, 3, 1, 2, 0};
        assertEquals("Did not correctly classify a test case when the KNN are bengign.",
                2, BreastCancerClassify.classify(trainDataBenign, kClosestIndicesBenign));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Test case for a majority of malignant
        int[][] trainDataMalignant = {{00, 1, 2}, {01, 2, 2}, {02, 3, 4}, {03, 5, 2}, {04, 7, 4}, {05, 10, 2},
                {06, 18, 4}, {07, 15, 4}};
        int[] kClosestIndicesMalignant = {0, 7, 6, 2, 4};
        assertEquals("Did not correctly classify a test case when the KNN are malignant.",
                4, BreastCancerClassify.classify(trainDataMalignant, kClosestIndicesMalignant));

        // Test case when there is a tie, which should yield benign
        try {
            Class c = BreastCancerClassify.class;
            Field k_constant = c.getDeclaredField("K");
            k_constant.setAccessible(true);
            k_constant.setInt(k_constant, 4);
            int[][] trainDataTie = {{00, 1, 2}, {01, 2, 2}, {02, 3, 4}, {03, 5, 2}, {04, 7, 4}, {05, 10, 2},
                {06, 18, 4}, {07, 15, 4}};
            int[] kClosestIndicesTie = {7, 6, 0, 1};
            assertEquals("Did not correctly classify a test case when the labels for the KNN are tied.",
                2, BreastCancerClassify.classify(trainDataTie, kClosestIndicesTie));
        }
        catch (Exception e){
            e.printStackTrace();
            fail("Missing class field K. Did you delete or modify it?");
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Test cases ensuring student used the class variable, and not hard-coded values of 2 and 4
        try {
            int NEW_BENIGN_LABEL = 3;
            int NEW_MALIGNANT_LABEL = 5;
            Class c = BreastCancerClassify.class;
            Field benign = c.getDeclaredField("BENIGN");
            Field malig = c.getDeclaredField("MALIGNANT");
            benign.setAccessible(true); malig.setAccessible(true);
            benign.setInt(benign,NEW_BENIGN_LABEL); malig.setInt(malig, NEW_MALIGNANT_LABEL);
            Field k_constant = c.getDeclaredField("K");
            k_constant.setAccessible(true);
            k_constant.setInt(k_constant, 3);

            // Test that benign label was not hard-coded
            int[][] trainDataNewBenign = {{00, 1, 345, NEW_BENIGN_LABEL}, {01, 2, 347, NEW_MALIGNANT_LABEL},
                    {02, 3, 568, NEW_BENIGN_LABEL}};
            int[] kClosestIndicesNewBenign = {2, 1, 0};
            assertEquals("Failed when the label for benign changed. Did you hard-code this value?",
                    NEW_BENIGN_LABEL, BreastCancerClassify.classify(trainDataNewBenign, kClosestIndicesNewBenign));


            // Test that malignant label was not hard-coded
            int[][] trainDataNewMalig = {{00, 1, 345, 678, NEW_MALIGNANT_LABEL}, {01, 2, 347, 894, NEW_BENIGN_LABEL},
                    {02, 3, 568, 999, NEW_MALIGNANT_LABEL}};
            int[] kClosestIndicesNewMalig = {1, 2, 0};
            assertEquals("Failed when the label for benign changed. Did you hard-code this value?",
                    NEW_MALIGNANT_LABEL, BreastCancerClassify.classify(trainDataNewMalig, kClosestIndicesNewMalig));
        }
        catch (Exception e){
            fail("Missing class field K. Did you delete or modify it?");
        }
    }

    @Test
    public void testKNearestNeighbors() {
        int[][] bcc_training_data = InputHandler.populateData("./datasets/train_data.csv");
        int[][] bcc_testing_data = InputHandler.populateData("./datasets/test_data.csv");
        int[] student_bcc_predictions = BreastCancerClassify.kNearestNeighbors(bcc_training_data, bcc_testing_data);
        /*
        int[][] parkinsons_training_data = InputHandler.populateData("./datasets/parkinsons_train_data.csv");
        int[][] parkinsons_testing_data = InputHandler.populateData("./datasets/parkinsons_test_data.csv");
        int[] student_parkinsons_predictions = BreastCancerClassify.kNearestNeighbors(parkinsons_training_data, parkinsons_testing_data);
        */
        // tests that the returned array is the same size as testing data
        assertEquals("Your predictions did not match the number of instances in the testing data.",
                bcc_testing_data.length, student_bcc_predictions.length);
        /*
        assertEquals("Your predictions did not match the number of instances in a different data set.",
                parkinsons_testing_data.length, student_parkinsons_predictions.length);
        */
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // test case 1 for accuracy should be 94.37%

        // test case 2 for accuracy should be ?

    }

    @Test
    public void testGetAccuracy() {
        int[][] perfect_results = {{1}, {2}, {3}, {4}, {5}, {6}};
        int[] perect_student = {1, 2, 3, 4, 5, 6};
        String student_perfect_score = BreastCancerClassify.getAccuracy(perect_student, perfect_results);
        String expected_perfect_score = "100.00%";
        assertEquals("Incorrect accuracy String for a perfect set of results.", expected_perfect_score, student_perfect_score);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int[][] zero_results = {{1}, {2}, {3}};
        int[] zero_student = {999, 999, 999};
        String student_zero_score = BreastCancerClassify.getAccuracy(zero_student, zero_results);
        String expected_zero_score = "0.00%";
        assertEquals("Incorrect accuracy String for all incorrect results.", expected_zero_score, student_zero_score);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int[][] one_third_results = {{1}, {1}, {2}, {2}, {3}, {3}};
        int[] one_third_student = {0, 0, 2, 2, 0, 0};
        String one_third_score = BreastCancerClassify.getAccuracy(one_third_student, one_third_results);
        String expected_one_third_score = "33.33%";
        assertEquals("Incorrect accuracy String for one-third correct results.", expected_one_third_score, one_third_score);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int[][] two_third_results = {{1}, {1}, {2}, {2}, {3}, {3}};
        int[] two_third_student = {0, 0, 2, 2, 3, 3};
        String two_third_score = BreastCancerClassify.getAccuracy(two_third_student, two_third_results);
        String expected_two_third_score = "66.67%";
        assertEquals("Incorrect accuracy String for two-thirds correct results.", expected_two_third_score, two_third_score);
    }
}