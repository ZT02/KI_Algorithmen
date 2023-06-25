package uebung08.src.de.hsco.ki1.gradientdescent;

public class main {
    public static void main(String[] args){
   //a)
    /*    MysteryFunction mF = new MysteryFunction();
        double rand;
        for(int i = 0; i<10.0; i++) {
            rand = (int)(Math.random() * 11) - 5.0;
            System.out.println("Input: "+ rand);
            System.out.println("Output: " + mF.value(rand));
        }

    } */
        //b)


        double out = 1000;
        Vector input;

        MultiDimFunction mF = new MultiDimFunction();
        for(int i = 0; i<900000000; i++) {
            input = GradientDescent.getRandomVector(20);
            double out2 =  mF.value(input);
            if (out2 < out)
                out = out2;


        }
        System.out.println("Minimum: "+ out);
        //c)
      //  GradientDescent gradient = new GradientDescentDouble(new MysteryFunction(),1);
        //GUI<GradientDescent> test = new GUI<GradientDescent>(gradient);

    }
}
