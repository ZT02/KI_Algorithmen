package uebung08.src.de.hsco.ki1;

import de.hsco.ki1.gradientdescent.Function;
import uebung08.src.de.hsco.ki1.gradientdescent.MultiDimFunction;
import uebung08.src.de.hsco.ki1.gradientdescent.MysteryFunction;
import uebung08.src.de.hsco.ki1.gradientdescent.Vector;


import java.util.Random;


public class GradientDescentSimple<T> {


  public static final Random rnd = new Random();
  public static final int MAX_RANGE = 10;
  public static final double epsilon = 0.000001;

  protected Function<T> func;

  /**
   * @param args
   */
  public static void main(String[] args) {

    // Example:
    GradientDescentSimple<Double> gdMystery = new GradientDescentSimple<Double>(new MysteryFunction());

    double x0 = getRandomDouble();
    double alpha = 0.000000000001;
    double result = gdMystery.findMinimum(x0, alpha);

    System.out.println("x=" + result + " ; f(x)=" + gdMystery.func.value(result));


    GradientDescentSimple<Vector> gdMultiDim = new GradientDescentSimple<>(new MultiDimFunction());
    Vector v0 = getRandomVector(gdMultiDim.func.numDimensions());
    Vector resultV = gdMultiDim.findMinimum(v0, alpha);

    System.out.println("v=");
    resultV.transpose().print(8,7);
    System.out.println("f(v)=" + gdMultiDim.func.value(resultV));
  }



  public GradientDescentSimple(Function<T> func) {
    this.func = func;
  }


  public T findMinimum(T x0, double alpha) {
    T result = null;


    if( x0 instanceof Double ) {
      result = (T)findMinimumDouble((Double)x0, alpha);
    } else if( x0 instanceof Vector ) {
      result = (T)findMinimumVector((Vector)x0, alpha);
    }

    return result;
  }

  public Double findMinimumDouble(double x0, double alpha) {
    // Initialisieren und Casten (erspart uns Schreibarbeit später)
    double result = Double.NaN;
    double x = x0;
    Function<Double> funcD = (Function<Double>)func;


    while( true ) {
      double gradient = funcD.gradient(x);

      // Breche ab, wenn der Gradient klein genug ist
      if( Math.abs(gradient * gradient) < epsilon ) {
        break;
      }

      // Starte mit Schrittweite = alpha
      double a = alpha;

      // Verdopple a solange bis f(x-a*g) >= f(x) ist
      while( funcD.value(x - a * gradient) < funcD.value(x) ) {
        a = a * 2;
      }
      // Mache letzte Verdopplung rückgängig, damit f(x-a*g) < f(x) ist
      a = a / 2;

      // Führe Schritt aus
      x = x - a * gradient;

      System.out.println(x + " - Stepsize: " + a);
    }

    return x;
  }

  public Vector findMinimumVector(Vector x0, double alpha) {
    // Initialisieren und Casten (erspart uns Schreibarbeit später)
    double result = Double.NaN;
    Vector x = x0;
    Function<Vector> funcV = (Function<Vector>)func;

    while( true ) {
      Vector gradient = funcV.gradient(x);

      // Breche ab, wenn der Gradient klein genug ist
      if( Math.pow(gradient.normF(), 2) < epsilon ) {
        break;
      }

      // Starte mit Schrittweite = alpha
      double a = alpha;

      // Verdopple a solange bis f(x-a*g) >= f(x) ist
      while( funcV.value(x.minus(gradient.times(a))) < funcV.value(x) ) {
        a = a * 2;
      }
      // Mache letzte Verdopplung rückgängig, damit f(x-a*g) < f(x) ist
      a = a / 2;

      // Führe Schritt aus
      x = x.minus(gradient.times(a));

      x.transpose().print(8,7);
      System.out.println("|Gradient|^2: " + gradient.normF() + " - Stepsize: " + a);
    }

    return x;
  }
  
  
  /**
   * @return a random Double value from -MAX_RANGE/2 to MAX_RANGE/2
   */
  public static double getRandomDouble() {
    return (rnd.nextDouble() * MAX_RANGE) - (MAX_RANGE / 2);
  }
  
  /**
   * @param d dimension
   * @return a Vector with random Double values from -MAX_RANGE/2 to MAX_RANGE/2
   */
  public static Vector getRandomVector(int d) {
    Vector v = new Vector(d);
    for( int i = 0; i < d; i++ ) {
      v.set(i, 0, getRandomDouble());
    }
    return v;
  }  

}
