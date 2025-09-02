package br.com.oliveira25lucas.greenShelf.math;

import br.com.oliveira25lucas.greenShelf.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;

public class SimpleMath {

    public Double sum(double numberOne, double numberTwo)  {

        return numberOne + numberTwo;
    }

    public Double sub(double numberOne, double numberTwo)  {

        return numberOne - numberTwo;
    }

    public Double div(double numberOne, double numberTwo)  {

        return numberOne / numberTwo;
    }

    public Double multiply(double numberOne, double numberTwo)  {

        return numberOne * numberTwo;
    }

    public Double mean(double numberOne, double numberTwo)  {

        return (numberOne * numberTwo) / 2;
    }

    public Double squareRoot(double numberOne)  {

        return Math.sqrt(numberOne);
    }
}
