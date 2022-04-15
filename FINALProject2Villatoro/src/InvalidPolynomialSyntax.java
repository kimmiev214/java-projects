/**
 * @description
 * InvalidPolynomialSyntax.java defines an unchecked exception that contains a constructor that allows a message to be supplied.
 * It is thrown by the constructor of the Polynomial class should the supplied string contain coefficients or exponents
 * of an improper type or should the exponents fail to be listed in strictly descending order.
 *
 * @author kimberlyvillatoro
 * @version 02/18/2021
 *
 */
// Referenced Create a Custom Exception by baeldung
    public class InvalidPolynomialSyntax extends RuntimeException {
        public InvalidPolynomialSyntax(String errorMsg) {
            super(errorMsg);
        }
    }

