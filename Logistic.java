/*
This Logistic Regression code was made for and used in "SpeEmo" which is an android based app.

This is a very Simple code for the Logistic Regression Classifier. This technique uses the Batch Gradient Descent.
It uses the following 3 expressions for its operation:
1) Hypotheses( Used to find the difference between the expected output and the measured output)
2) Cost Function( The cost function with the help of the hypothesis is used to find the 'accuracy' of the designed classifier).
3) Gradient Descent Optimizer( Used to increase the accuracy of the classifier by predicting values of theta that minimizes the cost)
*/


    double[][] featurematrix = {/* This is the feature matrix of the training data that trains the classifier 
    Random data has been taken for this example.
    The format being: {bias element=1,feature1,feature2}
    */

                {1, 4, 474},
                {1, 4, 529},
                {1, 5, 314},
                {1, 5, 482},
                {1, 5, 451},
                {1, 5, 468},
                {1, 5, 643},
                {1, 4, 545},
                {1, 5, 335},
                {1, 5, 525},

                {1, 5, 485},
                {1, 5, 287},
                {1, 4, 283},
                {1, 4, 187},
                {1, 4, 181},
                {1, 5, 255},
                {1, 5, 354},
                {1, 5, 268},
                {1, 5, 188},
                {1, 4, 141}

        };

        double[] featurematrix1 = {1,5,461};/*This is the test data on which the learnt hypothesis is tested.
        Random data is taken. format being same as training data {bias element=1,feature1,feature2}
        */
        float zi = 0;
        double gz1=0,hz1=0;

        double[] theta = {zi, zi, zi};//This matrix consists of the optimized theta values.
        double[] theta1 = {zi, zi, zi};
        double[] gz = new double[featurematrix.length + 1];
        double[] hz = new double[featurematrix.length + 1];
        double cost = 0;
        double alpha = 0.03;//alpha is the learning rate of the classifier(rate at which the classifier learns the training data to reach the global minima)
        int[] y = new int[featurematrix.length];//this is the matrix containing outputs for the training data.
        double z = 0;
        // All the calculation in this section are done on the Training Data
        
        for (int ji = 0; ji < 200; ji++) {//This for loop indicates the number of iterations that are to be done in order to reach the minima. In this code the number was decided based on the cost for tried and tested iterations(This was done due to availability of data being less). The change in cost after 200 was non significant yet minimum enough and hence this value. Also, this could have been done with a set of validation data and a plot that could possibly indicate the value after which the classifiers accuracy for validation data decreases.
            for (int b = 0; b < featurematrix[0].length; b++) {
                for (int ij = 0; ij < featurematrix.length; ij++) {
                    //hypothesis calculation on training data begins
                    gz[ij] = Math.exp(-((theta[0] * featurematrix[ij][0]) + (theta[1] * featurematrix[ij][1]) + (theta[2] * featurematrix[ij][2])));
                    gz[ij] += 1;
                    hz[ij] = 1 / gz[ij];
                    //end of hypothesis calculation
                    /*the outputs of training data need to be specified in order
                    for the classifier to learn
                    Training Data output specification starts
                    */
                    if (ij < featurematrix.length / 2) {
                        y[ij] = 1;
                    } else {
                        y[ij] = 0;
                    }
                    //End of Training Data output specification.
                    //Cost calculation begins on Training Data 
                    cost += (-y[ij] * Math.log10(hz[ij])) - ((1 - y[ij]) * Math.log10(1 - hz[ij]));/*This is the cost function for the logistic regression classifier.*/
                    //End of Cost Calculation
                    //Gradient Descent begins
                    double j = 0;
                    j += (hz[ij] - y[ij]) * featurematrix[ij][b];
                    theta1[b] -= alpha * j;
                    //End of Graident Descent optimization
                }
                cost = cost / featurematrix.length;

            }
            theta = theta1;
        }


        
        /*End of Calculations on Training Data
        Typically, at the end of this phase we receive a set of theta values that are optimized to the training data provided to predict outputs on the test data with as much accuracy as possible.
        */
        /*Calculation of the hypothesis of the test data
        Since Logistic or Sigmoid function gives a probablistic output hypothesis values below 0.5 suggest the user to be sad while values above 0.5 suggest the user is happy.
        */
        gz1=Math.exp(-((theta[0] * featurematrix1[0]) + (theta[1] * featurematrix1[1]) + (theta[2] * featurematrix1[2])));
        gz1 += 1;
        hz1 = 1 / gz1;
        if (hz1<0.5) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView text27 = (TextView) findViewById(R.id.hz);
                    text27.setText("YOU ARE SAD");
                }
            });
        }
        else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView text27 = (TextView) findViewById(R.id.hz);
                    text27.setText("YOU ARE HAPPY");
                }
            });
        }