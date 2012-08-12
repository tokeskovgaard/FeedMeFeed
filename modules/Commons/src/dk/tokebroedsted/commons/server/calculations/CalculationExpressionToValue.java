/*
package dk.tokebroedsted.commons.server.calculations;

import dk.tokebroedsted.hibernate.tables.Calculation;
import dk.tokebroedsted.hibernate.tables.FeedItem;

*/
/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 20-07-12
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 *//*

public class CalculationExpressionToValue {
    public static String subscribers = "<%subscribers%>";
    public static String replies = "<%replies%>";

    public static String divide = "/";
    public static String multiply = "*";
    public static String subtract = "-";
    public static String add = "+";

    public CalculationExpressionToValue(Calculation calculation, FeedItem feedItem) {
        String calculationExpression = calculation.getCalculation();

        */
/*  Session session = HibernateHelper.getCurrentSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM ");

            List<FeedItem> allFeedItems = query.list();
            transaction.commit();
            return allFeedItems;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }*//*

//        calculationExpression = calculationExpression.replaceAll(total, )
    }

    private Node diveAndConquer(String expression) {
        int index;

        if ((index = expression.indexOf(divide)) != -1) {
            String leftSide = expression.substring(0, index);
            String rightSide = expression.substring(index + 1, expression.length());
            return new DivideNode(leftSide, rightSide);
        } else if ((index = expression.indexOf(multiply)) != -1) {
            String leftSide = expression.substring(0, index);
            String rightSide = expression.substring(index + 1, expression.length());
            return new MultiplyNode(leftSide, rightSide);
        }
        return null;
    }

}
*/
